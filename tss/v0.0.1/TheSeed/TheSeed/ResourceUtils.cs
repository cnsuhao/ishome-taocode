using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Json;
using System.Text;
using System.Threading.Tasks;

/// <summary>
/// TSS资源分析
/// 作者：同位素
/// 时间：2015/10/1
/// </summary>
namespace TheSeed
{
    /// <summary>
    /// 资源管理工具
    /// </summary>
    class ResourceUtils
    {
        /// <summary>
        /// 最新资源信息清单（Top10）
        /// </summary>
        public static DataSet.ResourceDataTable ResourceTop10 { get; set; }

        /// <summary>
        /// 获得最新资源
        /// </summary>
        /// <returns></returns>
        public static Boolean SaveResourcesTop10()
        {
            ResourceTop10.WriteXml(ConfigUtils.ResourceTop10FileName);
            return true;
        }
        /// <summary>
         /// 获得最新资源（本地展示）
         /// </summary>
         /// <returns></returns>
        public static Boolean ReadResourcesTop10()
        {
            ResourceTop10 = new DataSet.ResourceDataTable();
            ResourceTop10.ReadXml(ConfigUtils.ResourceTop10FileName);
            return true;
        }

        /// <summary>
        /// 更新热播（最新Top10）
        /// </summary>
        /// <returns></returns>
        public static Boolean LoadResourcesTop10()
        {
            ResourceTop10 = new DataSet.ResourceDataTable();
            DataUtils.Arrange(ResourceTop10, ConfigUtils.ServerProtocol.ListResourceTop10());
            //保存到本地
            return SaveResourcesTop10();
        }

        /// <summary>
        /// 获得每日资源列表（全体）
        /// </summary>
        /// <returns></returns>
        public static Boolean LoadAllResources()
        {
            //按照年/日.XML格式保存（文件内保存左右资源信息列表）
            //yyyy/MM/dd HH:mm:ss
            #region 获取本地资源数据最后更新日期（文件列表）
            String FilePath = ConfigUtils.DataFileSavePath + FilePathUtils.LOCAL_RES + @"\" + DateTime.Now.ToString("yyyy");
            String[] LocalPaths = Directory.GetDirectories(FilePath);
            //获取最大的日期路径
            Int32 LastPath = Int32.MinValue;//YYYYMMDD
            foreach (String NowPath in LocalPaths)
            {
                if (LastPath <= Int32.Parse(NowPath))
                    LastPath = Int32.Parse(NowPath);
            }
            #endregion

            #region 获得网络数据
            List<String> CloundPaths = ConfigUtils.ServerProtocol.ListResourceFilePath(LastPath.ToString());
            #endregion

            #region 保存到本地
            FilePath = ConfigUtils.DataFileSavePath + FilePathUtils.LOCAL_RES + @"\" + DateTime.Now.ToString("yyyy") + @"\" + DateTime.Now.ToString("yyyyMMdd");
            DataSet.ResourceDataTable Resurces = new DataSet.ResourceDataTable();
            DataUtils.Arrange(Resurces, CloundPaths);
            Resurces.WriteXml(FilePath);
            #endregion

            return true;
        }
    }
}
