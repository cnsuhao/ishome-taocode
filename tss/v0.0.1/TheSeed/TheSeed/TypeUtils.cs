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
        private static Dictionary<String,String> DownloadOrderResouces { get; set; }
        /// <summary>
        /// 从云端加载全部属性信息
        /// </summary>
        /// <returns></returns>
        public static Boolean LoadAllTypes()
        {
            #region 获得网络数据
            //初始化
            Types = new DataSet.TypeDataTable();
            //云端获取所有分类信息
            DataUtils.Arrange(Types, ConfigUtils.ServerProtocol.ListType(""));
            #endregion

            //保存到本地
            return SaveAllTypes();
        }

        /// <summary>
        /// 保存所有分类信息到文件
        /// </summary>
        /// <returns></returns>
        public static Boolean SaveAllTypes()
        {
            Types.WriteXml(TypeFile);
            return true;
        }

        /// <summary>
        /// 从文件里面读取所有分类信息
        /// </summary>
        /// <returns></returns>
        public static Boolean ReadAllTypes()
        {
            Types = new DataSet.TypeDataTable();
            Types.ReadXml(TypeFile);
            return true;
        }                

        /// <summary>
        /// 创建新的分类到云端
        /// </summary>
        /// <param name="tr"></param>
        /// <returns></returns>
        public static Boolean CreatNewType(DataSet.TypeRow tr)
        {
            ReadAllTypes();
            Types.AddTypeRow(tr);//本地保存
            SaveAllTypes();
            //云端保存
            DataSet.TypeDataTable NewType = new DataSet.TypeDataTable();
            NewType.AddTypeRow(tr);
            NewType.WriteXml(FilePathUtils.LOCAL_TEMP + FilePathUtils.LOCAL_FILE_TYPE);
            String TypeStruct = File.ReadAllText(FilePathUtils.LOCAL_TEMP + FilePathUtils.LOCAL_FILE_TYPE);
            ConfigUtils.ServerProtocol.CreatType(TypeStruct,tr.BM);
            return true;
        }

        /// <summary>
        /// 从云端获取最新分类信息
        /// </summary>
        /// <returns></returns>
        public static Boolean LoadNewTypes()
        {
            //确定时间点
            DataSet.TypeRow[] NewType = (DataSet.TypeRow[])Types.Select("CreatDateTime = MAX(CreatDateTime)");
            //读取最新数据
            DataUtils.Arrange(Types, ConfigUtils.ServerProtocol.ListType(NewType[0].CreatDateTime));
            //本地保存            
            return SaveAllTypes();
        }

        /// <summary>
        /// 加载我订阅的分类资源
        /// </summary>
        /// <returns></returns>
        public static Boolean LoadMyOrderTypes()
        {
            //获取网络通信
            //MyOrderResource
            //获取本地资源数据最后更新日期（文件列表）
            String[] LocalPaths = Directory.GetDirectories(ConfigUtils.DataFileSavePath + FilePathUtils.LOCAL_RES);
            //获取最大的日期路径
            Int32 LastPath = Int32.MinValue;//YYYYMMDD
            foreach (String NowPath in LocalPaths)
            {
                if (LastPath <= Int32.Parse(NowPath))
                    LastPath = Int32.Parse(NowPath);
            }

            if (DownloadOrderResouces == null)
                DownloadOrderResouces = new Dictionary<string, string>();

            //获取云端资源数据更新日期（文件列表）
            List<String> CloundPaths = ConfigUtils.ServerProtocol.ListResourceFilePath(LastPath.ToString());
            foreach (String item in CloundPaths)
            {
                //item = yyyyMMdd
                //创建目录
                Directory.CreateDirectory(ConfigUtils.DataFileSavePath + FilePathUtils.LOCAL_RES + @"\" + item.Substring(0, 3));
                Directory.CreateDirectory(ConfigUtils.DataFileSavePath + FilePathUtils.LOCAL_RES + @"\" + item.Substring(0, 3) + @"\" + item.Substring(4, 2));
                String FilePathResource = ConfigUtils.DataFileSavePath + FilePathUtils.LOCAL_RES + @"\" + item.Substring(0, 3) + @"\" + item.Substring(4, 2) + @"\" + item.Substring(6, 2);
                Directory.CreateDirectory(FilePathResource);
                //获取云端资源列表判断时间）
                List<String> CloundItems = ConfigUtils.ServerProtocol.ListResourceFilePath(LastPath.ToString());

                DataSet.ConfigDataTable NowResouseItems = null;
                DataSet.ConfigRow NowResouseItem = null;

                //今日更新
                String ItemsFilePathForResouseList = FilePathResource + @"\" + item + FilePathUtils.LOCAL_FILE_TYPE;
                NowResouseItems = new DataSet.ConfigDataTable();
                if (Int32.Parse(item) == DateTime.Now.Day)
                {
                    NowResouseItems.ReadXml(ItemsFilePathForResouseList);
                }

                #region 保存用户订阅资源数据到本地（资源XML文件列表副本）
                foreach (String ResouseItem in CloundItems)
                {
                    //判断类别决定是否需要更新到本地
                    String ItemType = ResouseItem.Substring(11, 12);
                    DataSet.MyOrderResourceRow[] NewType = (DataSet.MyOrderResourceRow[])ConfigUtils.MyOrderResource.Select("ResourceTypeValue = " + ItemType + "");
                    //判断是否为用户订阅
                    if (NewType != null && NewType.Length > 0)
                    {
                        String FileResource = FilePathResource + @"\" + ResouseItem;
                        File.Create(FileResource);

                        //保存到更新清单
                        NowResouseItem = NowResouseItems.NewConfigRow();
                        NowResouseItem.Key = ResouseItem;
                        NowResouseItem.Value = FileResource;
                        NowResouseItem.Type = "T";
                        NowResouseItem.CreatDateTime = DateTime.Now.ToLocalTime().ToString();
                        NowResouseItems.AddConfigRow(NowResouseItem);

                        //加入加载队列
                        FileResource.Replace(ConfigUtils.DataFileSavePath, String.Empty);

                        //****************************用于下载的内容数据*****************************************
                        DownloadOrderResouces.Add(ResouseItem,FileResource);
                        //*********************************************************************
                    }
                }
                #endregion

                //保存订阅资源清单
                NowResouseItems.WriteXml(ItemsFilePathForResouseList);
            }

            return SaveMyOrderTypes();
        }
        // foreach (KeyValuePair<String, String> Resouses in CloundPathItems)
        /// <summary>
        /// 保存我订阅的分类资源
        /// </summary>
        /// <returns></returns>
        public static Boolean SaveMyOrderTypes()
        {
            //根据下载清单更新资源文件内容
            foreach (KeyValuePair<String, String> ResouseItem in DownloadOrderResouces)
            {
                File.WriteAllText(ResouseItem.Value, ConfigUtils.ServerProtocol.ReadResource(ResouseItem.Key, ResouseItem.Value));
            }
            return true;
        }
    }
}
