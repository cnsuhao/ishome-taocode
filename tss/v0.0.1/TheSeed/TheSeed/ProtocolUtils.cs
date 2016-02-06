using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

/// <summary>
/// TSS分享分析
/// 作者：同位素
/// 时间：2015/10/1
/// </summary>
namespace TheSeed
{
    interface TSSProtocol
    {
        /// <summary>
        /// 连接主服务器
        /// </summary>
        /// <returns></returns>
        Boolean ConnectServer();        
        /// <summary>
        /// 获取分享服务器清单(FTP、网盘)
        /// </summary>
        /// <returns></returns>
        List<String> ListServer();

        #region 分类
        /// <summary>
        /// 分类一览
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        List<String> ListType(String DateTime);
        /// <summary>
        /// 创建分类
        /// </summary>
        /// <param name="TypeStruct"></param>
        /// <param name="TypeFilePath"></param>
        /// <returns></returns>
        Boolean CreatType(String TypeStruct, String TypeFilePath);
        /// <summary>
        /// 创建分类
        /// </summary>
        /// <param name="TypeID"></param>
        /// <param name="TypeFilePath"></param>
        /// <returns></returns>
        Boolean DeleteType(String TypeID, String TypeFilePath);
        /// <summary>
        /// 获取分类
        /// </summary>
        /// <param name="TypeID"></param>
        /// <param name="TypeFilePath"></param>
        /// <returns></returns>
        String ReadType(String TypeID, String TypeFilePath);
        #endregion

        #region 分享
        /// <summary>
        /// 分享一览
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        List<String> ListResourceTop10();
        /// <summary>
        /// 分享目录一览<ListResource>
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        List<String> ListResourceFilePath(String DateTime);
        /// <summary>
        /// 分享目录一览
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        Dictionary<String, String> ListResource(String DateTime);
        /// <summary>
        /// 发布分享
        /// </summary>
        /// <param name="ResourceStruct"></param>
        /// <param name="ResourceFilePath">文件名称(分享名称/内容标题)</param>
        /// <returns></returns>
        Boolean CreatResource(String ResourceStruct, String ResourceFilePath);
        /// <summary>
        /// 删除分享
        /// </summary>
        /// <param name="ResourceID"></param>
        /// <param name="ResourceFilePath">文件名称(分享名称/内容标题</param>
        /// <returns></returns>
        Boolean DeleteResource(String ResourceID, String ResourceFilePath);
        /// <summary>
        /// 读取分享
        /// </summary>
        /// <param name="ResourceID"></param>
        /// <param name="ResourceFilePath">文件名称(分享名称/内容标题</param>
        /// <returns></returns>
        String ReadResource(String ResourceID,String ResourceFilePath);
        #endregion

        #region 主题
        /// <summary>
        /// 主题目录一览
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        List<String> ListSeriesFilePath(String DateTime);
        /// <summary>
        /// 主题一览
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        Dictionary<String,String> ListSeries(String DateTime);
        /// <summary>
        /// 发布主题
        /// </summary>
        /// <param name="SeriesStruct"></param>
        /// <param name="SeriesFilePath">文件名称(分享名称/内容标题</param>
        /// <returns></returns>
        Boolean CreatSeries(String SeriesStruct, String SeriesFilePath);
        /// <summary>
        /// 删除主题
        /// </summary>
        /// <param name="SeriesID"></param>
        /// <param name="SeriesFilePath">文件名称(分享名称/内容标题</param>
        /// <returns></returns>
        Boolean DeleteSeries(String SeriesID, String SeriesFilePath);
        /// <summary>
        /// 读取主题
        /// </summary>
        /// <param name="SeriesID"></param>
        /// <param name="SeriesFilePath">文件名称(分享名称/内容标题</param>
        /// <returns></returns>
        String ReadSeries(String SeriesID, String SeriesFilePath);
        /// <summary>
        /// 主题分享一览
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        Dictionary<String, String> ListSeriesResource(String SeriesResourceID, String DateTime);
        /// <summary>
        /// 发布主题分享
        /// </summary>
        /// <param name="SeriesResourceStruct"></param>
        /// <param name="SeriesResourceFilePath">文件名称(分享名称/内容标题</param>
        /// <returns></returns>
        Boolean CreatSeriesResource(String SeriesResourceStruct, String SeriesResourceFilePath);
        /// <summary>
        /// 删除主题分享
        /// </summary>
        /// <param name="SeriesResourceID"></param>
        /// <param name="SeriesResourceFilePath">文件名称(分享名称/内容标题</param>
        /// <returns></returns>
        Boolean DeleteSeriesResource(String SeriesResourceID, String SeriesResourceFilePath);
        /// <summary>
        /// 读取主题分享
        /// </summary>
        /// <param name="SeriesResourceID"></param>
        /// <param name="SeriesResourceFilePath">文件名称(分享名称/内容标题</param>
        /// <returns></returns>
        String ReadSeriesResource(String SeriesResourceID, String SeriesResourceFilePath);

        /// <summary>
        /// 保存用户关注配置文件（2.0版本实现）
        /// </summary>
        /// <returns></returns>
        //public static Boolean SaveUsrConfig(DataSet.ConfigDataTable UserOrders)
        #endregion
    }

    //DateTime.Now.ToString("yyyy/MM/dd HH:mm:ss")

    /// <summary>
    /// 本地协议实现
    /// </summary>
    class LoaclProtocolUtils : TSSProtocol
    {
        public Boolean ConnectServer()
        {
            //检查本地分享路径合法性
            if (Directory.Exists(ConfigUtils.DataFileSavePath) == false)
                return false;
            return true;
        }

        private Boolean CreatResourceFilePath()
        {
            ResourcePath = ConfigUtils.DataFileSavePath + "Server" + FilePathUtils.LOCAL_RES + @"\" + DateTime.Now.ToString("yyyy");
            Directory.CreateDirectory(ResourcePath);
            return true;
        }


        private Boolean CreatSeriesFilePath(String SeriesName)
        {
            SeriesPath = ConfigUtils.DataFileSavePath + "Server" + FilePathUtils.LOCAL_RES + @"\" + DateTime.Now.ToString("yyyy") + @"\" + SeriesName;
            Directory.CreateDirectory(SeriesPath);
            return true;
        }

        public String TypePath { get; set; }
        public String ResourcePath { get; set; }
        public String SeriesPath { get; set; }

        public Boolean CreatResource(String ResourceStruct, String ResourceFilePath)
        {
            //创建路径
            CreatResourceFilePath();
            //创建文件
            File.WriteAllText(ResourcePath + @"\" + ResourceFilePath + FilePathUtils.LOCAL_FILE_TYPE, ResourceStruct, Encoding.UTF8);
            return true;
        }

        public Boolean CreatSeries(String SeriesStruct, String SeriesFilePath)
        {
            //创建路径
            CreatSeriesFilePath(SeriesFilePath);
            //创建文件
            File.WriteAllText(SeriesPath + @"\" + SeriesFilePath + FilePathUtils.LOCAL_FILE_TYPE, SeriesStruct, Encoding.UTF8);
            return true;
        }

        public Boolean CreatSeriesResource(String SeriesResourceStruct, String SeriesResourceFilePath)
        {
            //创建文件
            File.WriteAllText(SeriesPath + @"\" + SeriesResourceFilePath + FilePathUtils.LOCAL_FILE_TYPE, SeriesResourceStruct, Encoding.UTF8);
            return true;
        }

        public Boolean CreatType(String TypeStruct, String TypeFilePath)
        {
            TypePath = ConfigUtils.DataFileSavePath + @"Server\" + FilePathUtils.LOCAL_TYPE;
            Directory.CreateDirectory(TypePath);
            File.WriteAllText(TypePath + @"\" + TypeFilePath + FilePathUtils.LOCAL_FILE_TYPE, TypeStruct, Encoding.UTF8);
            return true;
        }

        public Boolean DeleteType(String TypeID, String TypeFilePath)
        {
            String FileName = ConfigUtils.DataFileSavePath + @"Server\" + FilePathUtils.LOCAL_TYPE + TypeFilePath + @"\" + TypeID + FilePathUtils.LOCAL_FILE_TYPE;
            File.Delete(FileName);
            return true;
        }
        public Boolean DeleteResource(String ResourceID, String ResourceFilePath)
        {
            throw new NotImplementedException();
        }

        public Boolean DeleteSeries(String SeriesID, String SeriesFilePath)
        {
            throw new NotImplementedException();
        }

        public Boolean DeleteSeriesResource(String SeriesResourceID, String SeriesResourceFilePath)
        {
            throw new NotImplementedException();
        }

        public Boolean DeleteType(String Name)
        {
            throw new NotImplementedException();
        }

        public Dictionary<String, String> ListResource(String DateTime)
        {
            throw new NotImplementedException();
        }

        public List<String> ListResourceFilePath(String DateTime)
        {
            throw new NotImplementedException();
        }

        public List<String> ListResourceTop10()
        {
            throw new NotImplementedException();
        }

        public Dictionary<String, String> ListSeries(String DateTime)
        {
            throw new NotImplementedException();
        }

        public List<String> ListSeriesFilePath(String DateTime)
        {
            throw new NotImplementedException();
        }

        public Dictionary<String, String> ListSeriesResource(String SeriesResourceID, String DateTime)
        {
            throw new NotImplementedException();
        }

        public List<String> ListServer()
        {
            throw new NotImplementedException();
        }

        public List<String> ListType(String DateTime)
        {
            throw new NotImplementedException();
        }

        public String ReadResource(String ResourceID, String ResourceFilePath)
        {
            throw new NotImplementedException();
        }

        public String ReadSeries(String SeriesID, String SeriesFilePath)
        {
            throw new NotImplementedException();
        }

        public String ReadSeriesResource(String SeriesResourceID, String SeriesResourceFilePath)
        {
            throw new NotImplementedException();
        }

        public String ReadType(String TypeID, String TypeFilePath)
        {
            String FileName = ConfigUtils.DataFileSavePath + @"Server\" + FilePathUtils.LOCAL_TYPE + TypeFilePath + @"\" + TypeID + FilePathUtils.LOCAL_FILE_TYPE;
            return File.ReadAllText(FileName, Encoding.UTF8);
        }

    }

    /// <summary>
    /// FTP服务器协议实现
    /// </summary>
    class FTPCloundProtocolUtils : TSSProtocol
    {
        public bool ConnectServer()
        {
            return false;
        }

        public bool CreatResource(String ResourceStruct, String ResourceFilePath)
        {
            throw new NotImplementedException();
        }

        public bool CreatSeries(String SeriesStruct, String SeriesFilePath)
        {
            throw new NotImplementedException();
        }

        public bool CreatSeriesResource(String SeriesResourceStruct, String SeriesResourceFilePath)
        {
            throw new NotImplementedException();
        }

        public bool CreatType(String TypeStruct)
        {
            throw new NotImplementedException();
        }

        public bool CreatType(String TypeStruct, String TypeFilePath)
        {
            throw new NotImplementedException();
        }

        public bool DeleteResource(String ResourceID, String ResourceFilePath)
        {
            throw new NotImplementedException();
        }

        public bool DeleteSeries(String SeriesID, String SeriesFilePath)
        {
            throw new NotImplementedException();
        }

        public bool DeleteSeriesResource(String SeriesResourceID, String SeriesResourceFilePath)
        {
            throw new NotImplementedException();
        }

        public bool DeleteType(String Name)
        {
            throw new NotImplementedException();
        }

        public bool DeleteType(String TypeID, String TypeFilePath)
        {
            throw new NotImplementedException();
        }

        public Dictionary<String, String> ListResource(String DateTime)
        {
            throw new NotImplementedException();
        }

        public List<String> ListResourceFilePath(String DateTime)
        {
            return new
                 List<String>();
        }

        public List<String> ListResourceTop10()
        {
            return new List<String>();
        }

        public Dictionary<String, String> ListSeries(String DateTime)
        {
            throw new NotImplementedException();
        }

        public List<String> ListSeriesFilePath(String DateTime)
        {
            throw new NotImplementedException();
        }

        public Dictionary<String, String> ListSeriesResource(String SeriesResourceID, String DateTime)
        {
            throw new NotImplementedException();
        }

        public List<String> ListServer()
        {
            throw new NotImplementedException();
        }

        public List<String> ListType(String DateTime)
        {
            return new List<String>();
                 
        }

        public String ReadResource(String ResourceID, String ResourceFilePath)
        {
            throw new NotImplementedException();
        }

        public String ReadSeries(String SeriesID, String SeriesFilePath)
        {
            throw new NotImplementedException();
        }

        public String ReadSeriesResource(String SeriesResourceID, String SeriesResourceFilePath)
        {
            throw new NotImplementedException();
        }

        public String ReadType(String TypeID, String TypeFilePath)
        {
            throw new NotImplementedException();
        }
    }

    /// <summary>
    /// 百度云服务器协议实现
    /// </summary>
    //class BaiDuCloundProtocolUtils : TSSProtocol
    //{
    //}
}
