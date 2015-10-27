using System;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TheSeed
{
    class DataUtils
    {
        /// <summary>
        /// 数据整理
        /// </summary>
        /// <param name="ServerList"></param>
        /// <param name="Servers"></param>
        /// <returns></returns>
        public static Boolean Arrange(DataTable LocalDataTable,List<String> CloundDatas) {
            DataTable TempTable;
            foreach (String item in CloundDatas)
            {
                try
                {
                    //保存到临时文件
                    File.WriteAllText(FilePathUtils.LOCAL_TEMP + FilePathUtils.LOCAL_FILE_TYPE, item, Encoding.UTF8);
                    TempTable = new DataTable();
                    TempTable.ReadXml(FilePathUtils.LOCAL_TEMP + FilePathUtils.LOCAL_FILE_TYPE);
                    //保存到本地
                    LocalDataTable.Merge(TempTable);
                }
                catch (Exception)
                {
                }
            }
            return true;
        }
    }
}
