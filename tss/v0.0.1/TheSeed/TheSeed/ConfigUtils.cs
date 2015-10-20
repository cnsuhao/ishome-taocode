using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

/// <summary>
/// TSS资源分析
/// 作者：同位素
/// 时间：2015/10/1
/// </summary>
namespace TheSeed
{
    class ConfigUtils
    {
        #region 系统运行参数
        /// <summary>
        /// 用户登录状态
        /// </summary>
        public static Boolean Login { get; set; }
        /// <summary>
        /// 系统网络状态
        /// </summary>
        public static Boolean NetConnect { get; set; }

        /// <summary>
        /// 管理员权限
        /// </summary>
        public static Boolean AdminRool { get; set; }
        /// <summary>
        /// 主服务器地址
        /// </summary>
        public static String FirstServerAdress { get; set; }
        /// <summary>
        /// 备份服务器地址
        /// </summary>
        public static String SecondServerAdress { get; set; }
        /// <summary>
        /// 本地数据储存路径
        /// </summary>
        public static String DataFileSavePath { get; set; }
        /// <summary>
        /// 云端服务实现类
        /// </summary>
        public static TSSProtocol ServerProtocol { get; set; }
        #endregion

        #region 用户个人参数
        /// <summary>
        /// 我的关注<MyOrderResource><MyOrderSeries>
        /// </summary>
        public static DataSet.MyOrderResourceDataTable MyOrderResource { get; set; }
        public static DataSet.MyOrderSeriesDataTable MyOrderSeries { get; set; }
        #endregion
        
        public static String ConfigFile = Application.StartupPath + @"\" + FilePathUtils.LOCAL_CFG + FilePathUtils.LOCAL_FILE_TYPE;

        public static Boolean CheckConfigFile()
        {
            //检查本地资源路径合法性
            return Directory.Exists(ConfigFile);
        }

        /// <summary>
        /// 初始化配置路径
        /// </summary>
        /// <returns></returns>
        public static Boolean InitSavePath()
        {
            Directory.CreateDirectory(ConfigUtils.DataFileSavePath);

            Directory.CreateDirectory(ConfigUtils.DataFileSavePath + @"\" + FilePathUtils.LOCAL_CFG);
            //Directory.CreateDirectory(ConfigUtils.DataFileSavePath + @"\" + FilePathUtils.LOCAL_CFG + FilePathUtils.LOCAL_CFG_SYS);
            //Directory.CreateDirectory(ConfigUtils.DataFileSavePath + @"\" + FilePathUtils.LOCAL_CFG + FilePathUtils.LOCAL_CFG_USR);

            Directory.CreateDirectory(ConfigUtils.DataFileSavePath + @"\" + FilePathUtils.LOCAL_RES);
            Directory.CreateDirectory(ConfigUtils.DataFileSavePath + @"\" + FilePathUtils.LOCAL_SER);

            return true;
        }

        //config/conf.dat
        /// <summary>
        /// 加载系统配置文件
        /// </summary>
        /// <returns></returns>
        public static Boolean LoadConfigFile()
        {
            //获得系统启动路径
            //配置文件检查
            if (File.Exists(ConfigFile))
            {
                //本地保存路径
                DataFileSavePath = File.ReadAllText(ConfigFile, Encoding.UTF8);
            }
            //第一次启动处理
            else
            {
                DataFileSavePath = @"D:\TSS";
                //创建配置文件
                File.WriteAllText(ConfigFile, DataFileSavePath, Encoding.UTF8);
            }

            //加载网络连接
            ServerProtocol = new FTPCloundProtocolUtils();
            NetConnect = ServerProtocol.ConnectSetver();

            SysConfigFileName = DataFileSavePath + @"\" + FilePathUtils.LOCAL_CFG + FilePathUtils.LOCAL_CFG_SYS + FilePathUtils.LOCAL_FILE_TYPE;
            UsrConfigFileName = DataFileSavePath + @"\" + FilePathUtils.LOCAL_CFG + FilePathUtils.LOCAL_CFG_USR + FilePathUtils.LOCAL_FILE_TYPE;

            //检查本地资源路径合法性
            if (Directory.Exists(ConfigUtils.DataFileSavePath) == false)
            {
                return false;
            }

            return true;
        }

        /// <summary>
        /// 保存默认配置文件
        /// </summary>
        /// <returns></returns>
        public static Boolean SaveConfigFile()
        {
            //创建配置文件
            File.WriteAllText(ConfigFile, DataFileSavePath, Encoding.UTF8);
            return true;
        }

        public static String SysConfigFileName { get; set; }

        /// <summary>
        /// 加载系统配置文件
        /// </summary>
        /// <returns></returns>
        public static Boolean LoadSysConfig()
        {
            //纯粹文本格式
            if (File.Exists(SysConfigFileName))
            {
                //本地保存路径
                String[] FileValue = File.ReadAllLines(SysConfigFileName, Encoding.UTF8);
                FirstServerAdress = FileValue[0];
                SecondServerAdress = FileValue[1];

                return true;
            }

            return false;
        }

        /// <summary>
        /// 保存系统配置文件
        /// </summary>
        /// <param name="FileValue"></param>
        /// <returns></returns>
        public static Boolean SaveSysConfig()
        {
            SysConfigFileName = DataFileSavePath + @"\" + FilePathUtils.LOCAL_CFG + FilePathUtils.LOCAL_CFG_SYS + FilePathUtils.LOCAL_FILE_TYPE;
            UsrConfigFileName = DataFileSavePath + @"\" + FilePathUtils.LOCAL_CFG + FilePathUtils.LOCAL_CFG_USR + FilePathUtils.LOCAL_FILE_TYPE;

            String[] FileValue = { FirstServerAdress, SecondServerAdress };
            File.WriteAllLines(SysConfigFileName, FileValue, Encoding.UTF8);
            return true;
        }

        public static String UsrConfigFileName { get; set; }
        /// <summary>
        /// 加载用户配置文件
        /// </summary>
        /// <returns></returns>
        public static Boolean LoadUsrConfig()
        {
            DataSet.ConfigDataTable UserOrders = new DataSet.ConfigDataTable();
            if (File.Exists(UsrConfigFileName) == false)
                return false;
            UserOrders.ReadXml(UsrConfigFileName);

            MyOrderResource = new DataSet.MyOrderResourceDataTable();
            MyOrderSeries = new DataSet.MyOrderSeriesDataTable();
            foreach (DataSet.ConfigRow item in UserOrders)
            {
                if (item.Type.Equals("R"))
                {
                    var ResourceRow = MyOrderResource.NewMyOrderResourceRow();
                    ResourceRow.ResourceTypeID = item.Key;
                    ResourceRow.ResourceTypeValue = item.Value;
                    MyOrderResource.AddMyOrderResourceRow(ResourceRow);
                }
                if (item.Type.Equals("S"))
                {
                    var SeriesRow = MyOrderSeries.NewMyOrderSeriesRow();
                    SeriesRow.SeriesID = item.Key;
                    SeriesRow.SeriesValue = item.Value;
                    MyOrderSeries.AddMyOrderSeriesRow(SeriesRow);
                }
            }

            return true;
        }

        /// <summary>
        /// 保存用户配置文件
        /// </summary>
        /// <returns></returns>
        public static Boolean SaveUsrConfig(DataSet.ConfigDataTable UserOrders)
        {
            UserOrders.WriteXml(UsrConfigFileName);
            return true;
        }
    }
}
