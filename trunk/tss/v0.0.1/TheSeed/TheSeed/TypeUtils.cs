using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TheSeed
{
    /// <summary>
    /// 分类管理工具
    /// </summary>
    class TypeUtils
    {
        public static DataSet.TypeDataTable Types { get; set; }
        public static Boolean SaveAllTypes()
        {
            return true;
        }
        /// <summary>
        /// 加载全部属性信息
        /// </summary>
        /// <returns></returns>
        public static Boolean LoadAllTypes()
        {
            Types = new DataSet.TypeDataTable();
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

        public static Boolean LoadMyOrderTypes()
        {
            return true;
        }

        public static Boolean SaveMyOrderTypes()
        {
            return true;
        }
    }
}
