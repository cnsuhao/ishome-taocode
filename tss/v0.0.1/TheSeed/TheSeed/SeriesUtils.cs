using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

/// <summary>
/// TSS资源分析
/// 作者：同位素
/// 时间：2015/10/1
/// </summary>
namespace TheSeed
{
    class SeriesUtils
    {
        public static DataSet.SeriesDataTable Series { get; set; }

        /// <summary>
        /// 获得所有剧集清单
        /// </summary>
        /// <returns></returns>
        public static Boolean LoadAllSeries()
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
            DataSet.SeriesDataTable Resurces = new DataSet.SeriesDataTable();
            DataUtils.Arrange(Resurces, CloundPaths);
            Resurces.WriteXml(FilePath);
            #endregion
            return true;
        }
        /// <summary>
        /// 获得每日资源列表（全体）
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        public static Boolean LoadResources(String DateTime)
        {
            //按照年/月/日.XML格式保存（文件内保存左右资源信息列表）
            return true;
        }

        /// <summary>
        /// 发布新的剧集
        /// </summary>
        /// <returns></returns>
        public static Boolean SaveNewSeriess()
        {
            return true;
        }

        /// <summary>
        /// 加载我订阅的剧集更新
        /// </summary>
        /// <returns></returns>
        public static Boolean LoadMyOrderSeriess()
        {
            return true;
        }

        /// <summary>
        /// 保存我订阅的剧集资源
        /// </summary>
        /// <returns></returns>
        public static Boolean SaveMyOrderSeriess()
        {
            return true;
        }
    }
}
