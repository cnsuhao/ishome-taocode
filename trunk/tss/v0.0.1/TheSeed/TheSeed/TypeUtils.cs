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
    /// <summary>
    /// 分类管理工具
    /// </summary>
    class TypeUtils
    {
        public static DataSet.TypeDataTable Types { get; set; }
        public static String TypeFile = ConfigUtils.DataFileSavePath + @"\" + FilePathUtils.LOCAL_CFG + FilePathUtils.LOCAL_CFG_SYS + FilePathUtils.LOCAL_TYPE + FilePathUtils.LOCAL_FILE_TYPE;

        public static Boolean SaveAllTypes()
        {
            Types.WriteXml(TypeFile);
            return true;
        }
        /// <summary>
        /// 加载全部属性信息
        /// </summary>
        /// <returns></returns>
        public static Boolean LoadAllTypes()
        {
            //云端获取所有分类信息
            List<String> items = ConfigUtils.ServerProtocol.ListType("DateTime");
            File.WriteAllLines(FilePathUtils.LOCAL_TEMP + FilePathUtils.LOCAL_FILE_TYPE, items, Encoding.UTF8);
            Types = new DataSet.TypeDataTable();
            //加载全部分类
            Types.ReadXml(FilePathUtils.LOCAL_TEMP + FilePathUtils.LOCAL_FILE_TYPE);
            return true;
        }

        /// <summary>
        /// 创建新的分类到云端
        /// </summary>
        /// <param name="tr"></param>
        /// <returns></returns>
        public static Boolean CreatNewType(DataSet.TypeRow tr)
        {
            Types.AddTypeRow(tr);//本地保存
            //Types.WriteXml();//
            //云端保存
            return true;
        }
       
        public static Boolean SaveNewTypes()
        {
            return true;
        }
        
        public static Boolean LoadNewTypes()
        {          

            return true;
        }


        /// <summary>
        /// 加载我订阅的分类资源
        /// </summary>
        /// <returns></returns>
        public static Boolean LoadMyOrderTypes()
        { 
            //获取网络通信
            List<String> items = ConfigUtils.ServerProtocol.ListResourceTop10();
            
            return true;
        }

        /// <summary>
        /// 保存我订阅的分类资源
        /// </summary>
        /// <returns></returns>
        public static Boolean SaveMyOrderTypes()
        {
            //加载本地保存文件
            //更新资源清单
            //保存本地文件
            return true;
        }
    }
}
