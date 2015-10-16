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
        public static String ResourceTop10File = ConfigUtils.DataFileSavePath + @"\" + FilePathUtils.LOCAL_CFG + FilePathUtils.LOCAL_RES + FilePathUtils.LOCAL_RES_TOP + FilePathUtils.LOCAL_FILE_TYPE;

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
         /// 获得最新资源
         /// </summary>
         /// <returns></returns>
        public static Boolean ReadResourcesTop10()
        {
            ResourceTop10 = new DataSet.ResourceDataTable();
            ResourceTop10.ReadXml(ResourceTop10File);

            return true;
        }

        public static Boolean LoadResourcesTop10()
        {
            //获取网络通信
            List<String> items = ConfigUtils.ServerProtocol.ListResourceTop10();
            File.WriteAllLines(FilePathUtils.LOCAL_TEMP + FilePathUtils.LOCAL_FILE_TYPE, items, Encoding.UTF8);
            ResourceTop10 = new DataSet.ResourceDataTable();
            ResourceTop10.ReadXml(FilePathUtils.LOCAL_TEMP + FilePathUtils.LOCAL_FILE_TYPE);
            
            return true;
        }
    }
}
