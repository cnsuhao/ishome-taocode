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
        public static String ResourceTop10File = ConfigUtils.DataFileSavePath + @"\" + FilePathUtils.LOCAL_RES + FilePathUtils.LOCAL_RES_TOP + FilePathUtils.LOCAL_FILE_TYPE;

        /// <summary>
        /// 获得最新资源
        /// </summary>
        /// <returns></returns>
        public static Boolean SaveResourcesTop10()
        {
            ResourceTop10.WriteXml(ResourceTop10File);

            return true;
        }
        /// <summary>
         /// 获得最新资源（本地展示）
         /// </summary>
         /// <returns></returns>
        public static Boolean ReadResourcesTop10()
        {
            ResourceTop10 = new DataSet.ResourceDataTable();
            ResourceTop10.ReadXml(ResourceTop10File);

            return true;
        }

        /// <summary>
        /// 更新热播（最新Top10）
        /// </summary>
        /// <returns></returns>
        public static Boolean LoadResourcesTop10()
        {
            #region 获得网络数据
            List<String> items = ConfigUtils.ServerProtocol.ListResourceTop10();
            ResourceTop10 = new DataSet.ResourceDataTable();
            DataSet.ResourceDataTable TempResource = null;
            foreach (String item in items)
            {
                try
                {
                    File.WriteAllText(FilePathUtils.LOCAL_TEMP + FilePathUtils.LOCAL_FILE_TYPE, item, Encoding.UTF8);
                    TempResource = new DataSet.ResourceDataTable();
                    TempResource.ReadXml(FilePathUtils.LOCAL_TEMP + FilePathUtils.LOCAL_FILE_TYPE);
                    ResourceTop10.Merge(TempResource);
                }
                catch (Exception)
                {
                }
            }
            #endregion

            //保存到本地
            return SaveResourcesTop10();
        }

        /// <summary>
        /// 获得每日资源列表（全体）
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        public static Boolean LoadAllResources(String DateTime)
        {
            //按照年/月/日.XML格式保存（文件内保存左右资源信息列表）
            #region 获得网络数据

            #endregion

            #region 保存到本地

            #endregion


            return true;
        }
    }
}
