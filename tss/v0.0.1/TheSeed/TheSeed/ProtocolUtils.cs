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
    interface TSSProtocol
    {
        /// <summary>
        /// 连接云服务器
        /// </summary>
        /// <returns></returns>
        Boolean ConnectSetver();

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
        /// <returns></returns>
        Boolean CreatType(String TypeStruct);
        /// <summary>
        /// 创建分类
        /// </summary>
        /// <param name="Name"></param>
        /// <returns></returns>
        Boolean DeleteType(String Name);
        #endregion

        #region 资源
        /// <summary>
        /// 资源一览
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        List<String> ListResourceTop10();
        /// <summary>
        /// 资源目录一览
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        List<String> ListResourcePath(String DateTime);
        /// <summary>
        /// 资源目录一览
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        Dictionary<String, String> ListResource(String DateTime);
        /// <summary>
        /// 发布资源
        /// </summary>
        /// <param name="ResourceStruct"></param>
        /// <param name="ResourcePath"></param>
        /// <returns></returns>
        Boolean CreatResource(String ResourceStruct, String ResourcePath);
        /// <summary>
        /// 删除资源
        /// </summary>
        /// <param name="ResourceID"></param>
        /// <param name="ResourcePath"></param>
        /// <returns></returns>
        Boolean DeleteResource(String ResourceID, String ResourcePath);
        /// <summary>
        /// 读取资源
        /// </summary>
        /// <param name="ResourceID"></param>
        /// <param name="ResourcePath"></param>
        /// <returns></returns>
        String ReadResource(String ResourceID,String ResourcePath);
        #endregion

        #region 剧集
        /// <summary>
        /// 剧集目录一览
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        List<String> ListSeriesPath(String DateTime);
        /// <summary>
        /// 剧集一览
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        Dictionary<String,String> ListSeries(String DateTime);
        /// <summary>
        /// 发布剧集
        /// </summary>
        /// <param name="SeriesStruct"></param>
        /// <param name="SeriesPath"></param>
        /// <returns></returns>
        Boolean CreatSeries(String SeriesStruct, String SeriesPath);
        /// <summary>
        /// 删除剧集
        /// </summary>
        /// <param name="SeriesID"></param>
        /// <param name="SeriesPath"></param>
        /// <returns></returns>
        Boolean DeleteSeries(String SeriesID, String SeriesPath);
        /// <summary>
        /// 读取剧集
        /// </summary>
        /// <param name="SeriesID"></param>
        /// <param name="SeriesPath"></param>
        /// <returns></returns>
        String ReadSeries(String SeriesID, String SeriesPath);
        /// <summary>
        /// 剧集资源一览
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        Dictionary<String, String> ListSeriesResource(String SeriesResourceID, String DateTime);
        /// <summary>
        /// 发布剧集资源
        /// </summary>
        /// <param name="SeriesResourceStruct"></param>
        /// <param name="SeriesResourcePath"></param>
        /// <returns></returns>
        Boolean CreatSeriesResource(String SeriesResourceStruct, String SeriesResourcePath);
        /// <summary>
        /// 删除剧集资源
        /// </summary>
        /// <param name="SeriesResourceID"></param>
        /// <param name="SeriesResourcePath"></param>
        /// <returns></returns>
        Boolean DeleteSeriesResource(String SeriesResourceID, String SeriesResourcePath);
        /// <summary>
        /// 读取剧集资源
        /// </summary>
        /// <param name="SeriesResourceID"></param>
        /// <param name="SeriesResourcePath"></param>
        /// <returns></returns>
        String ReadSeriesResource(String SeriesResourceID, String SeriesResourcePath);

        /// <summary>
        /// 保存用户关注配置文件（2.0版本实现）
        /// </summary>
        /// <returns></returns>
        //public static Boolean SaveUsrConfig(DataSet.ConfigDataTable UserOrders)
        #endregion
    }

    /// <summary>
    /// 本地协议实现
    /// </summary>
    static class LoaclProtocolUtils
    {
        public static Boolean ConnectSetver()
        {
            //检查本地资源路径合法性
            if (Directory.Exists(ConfigUtils.DataFileSavePath) == false)
                return false;
            return true;
        }
        
    }

    /// <summary>
    /// FTP服务器协议实现
    /// </summary>
    class FTPCloundProtocolUtils : TSSProtocol
    {
        public bool ConnectSetver()
        {
            return false;
        }

        public bool CreatResource(string ResourceStruct, string ResourcePath)
        {
            throw new NotImplementedException();
        }

        public bool CreatSeries(string SeriesStruct, string SeriesPath)
        {
            throw new NotImplementedException();
        }

        public bool CreatSeriesResource(string SeriesResourceStruct, string SeriesResourcePath)
        {
            throw new NotImplementedException();
        }

        public bool CreatType(string TypeStruct)
        {
            throw new NotImplementedException();
        }

        public bool DeleteResource(string ResourceID, string ResourcePath)
        {
            throw new NotImplementedException();
        }

        public bool DeleteSeries(string SeriesID, string SeriesPath)
        {
            throw new NotImplementedException();
        }

        public bool DeleteSeriesResource(string SeriesResourceID, string SeriesResourcePath)
        {
            throw new NotImplementedException();
        }

        public bool DeleteType(string Name)
        {
            throw new NotImplementedException();
        }

        public Dictionary<string, string> ListResource(string DateTime)
        {
            throw new NotImplementedException();
        }

        public List<string> ListResourcePath(string DateTime)
        {
            return new
                 List<string>();
        }

        public List<string> ListResourceTop10()
        {
            return new List<string>();
        }

        public Dictionary<string, string> ListSeries(string DateTime)
        {
            throw new NotImplementedException();
        }

        public List<string> ListSeriesPath(string DateTime)
        {
            throw new NotImplementedException();
        }

        public Dictionary<string, string> ListSeriesResource(string SeriesResourceID, string DateTime)
        {
            throw new NotImplementedException();
        }

        public List<string> ListType(string DateTime)
        {
            return new List<string>();
                 
        }

        public string ReadResource(string ResourceID, string ResourcePath)
        {
            throw new NotImplementedException();
        }

        public string ReadSeries(string SeriesID, string SeriesPath)
        {
            throw new NotImplementedException();
        }

        public string ReadSeriesResource(string SeriesResourceID, string SeriesResourcePath)
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
