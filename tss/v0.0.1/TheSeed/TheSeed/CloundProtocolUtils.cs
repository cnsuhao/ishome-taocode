using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TheSeed
{
    interface CloundProtocol
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
        List<String> ListResource(String DateTime);
        /// <summary>
        /// 发布资源
        /// </summary>
        /// <param name="ResourceStruct"></param>
        /// <returns></returns>
        Boolean CreatResource(String ResourceStruct);
        /// <summary>
        /// 删除资源
        /// </summary>
        /// <param name="SeriesID"></param>
        /// <returns></returns>
        Boolean DeleteResource(String ResourceID);
        #endregion

        #region 剧集
        /// <summary>
        /// 资源一览
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        List<String> ListSeries(String DateTime);
        /// <summary>
        /// 发布资源
        /// </summary>
        /// <param name="SeriesStruct"></param>
        /// <returns></returns>
        Boolean CreatSeries(String SeriesStruct);
        /// <summary>
        /// 删除剧集
        /// </summary>
        /// <param name="SeriesID"></param>
        /// <returns></returns>
        Boolean DeleteSeries(String SeriesID);
        /// <summary>
        /// 资源一览
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        List<String> ListSeriesResource(String SeriesResourceID, String DateTime);
        /// <summary>
        /// 发布剧集资源
        /// </summary>
        /// <param name="SeriesResourceStruct"></param>
        /// <returns></returns>
        Boolean CreatSeriesResource(String SeriesResourceStruct);
        /// <summary>
        /// 删除剧集资源
        /// </summary>
        /// <param name="SeriesID"></param>
        /// <returns></returns>
        Boolean DeleteSeriesResource(String SeriesResourceID);
        #endregion
    }

    /// <summary>
    /// FTP服务器协议实现
    /// </summary>
    class FTPCloundProtocolUtils : CloundProtocol
    {
        public bool ConnectSetver()
        {
            throw new NotImplementedException();
        }

        public bool CreatResource(string ResourceStruct)
        {
            throw new NotImplementedException();
        }

        public bool CreatSeries(string SeriesStruct)
        {
            throw new NotImplementedException();
        }

        public bool CreatSeriesResource(string SeriesResourceStruct)
        {
            throw new NotImplementedException();
        }

        public bool CreatType(string TypeStruct)
        {
            throw new NotImplementedException();
        }

        public bool DeleteResource(string ResourceID)
        {
            throw new NotImplementedException();
        }

        public bool DeleteSeries(string SeriesID)
        {
            throw new NotImplementedException();
        }

        public bool DeleteSeriesResource(string SeriesResourceID)
        {
            throw new NotImplementedException();
        }

        public bool DeleteType(string Name)
        {
            throw new NotImplementedException();
        }

        public List<string> ListResource(string DateTime)
        {
            throw new NotImplementedException();
        }

        public List<string> ListSeries(string DateTime)
        {
            throw new NotImplementedException();
        }

        public List<string> ListSeriesResource(string SeriesResourceID, string DateTime)
        {
            throw new NotImplementedException();
        }

        public List<string> ListType(string DateTime)
        {
            throw new NotImplementedException();
        }
    }

    /// <summary>
    /// 百度云服务器协议实现
    /// </summary>
    class BaiDuCloundProtocolUtils : CloundProtocol
    {
        public bool ConnectSetver()
        {
            throw new NotImplementedException();
        }

        public bool CreatResource(string ResourceStruct)
        {
            throw new NotImplementedException();
        }

        public bool CreatSeries(string SeriesStruct)
        {
            throw new NotImplementedException();
        }

        public bool CreatSeriesResource(string SeriesResourceStruct)
        {
            throw new NotImplementedException();
        }

        public bool CreatType(string TypeStruct)
        {
            throw new NotImplementedException();
        }

        public bool DeleteResource(string ResourceID)
        {
            throw new NotImplementedException();
        }

        public bool DeleteSeries(string SeriesID)
        {
            throw new NotImplementedException();
        }

        public bool DeleteSeriesResource(string SeriesResourceID)
        {
            throw new NotImplementedException();
        }

        public bool DeleteType(string Name)
        {
            throw new NotImplementedException();
        }

        public List<string> ListResource(string DateTime)
        {
            throw new NotImplementedException();
        }

        public List<string> ListSeries(string DateTime)
        {
            throw new NotImplementedException();
        }

        public List<string> ListSeriesResource(string SeriesResourceID, string DateTime)
        {
            throw new NotImplementedException();
        }

        public List<string> ListType(string DateTime)
        {
            throw new NotImplementedException();
        }
    }
}
