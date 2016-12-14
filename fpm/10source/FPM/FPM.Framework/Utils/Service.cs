using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using FPM.Common.Data;

namespace FPM.Framework.Utils
{
    public partial class FormUtil
    {
        /// <summary>
        /// 单条数据返回
        /// </summary>
        /// <param name="URL"></param>
        /// <param name="Data"></param>
        /// <returns></returns>
        public static RESTResultBean Interview(String URL, FromBean Data)
        {
            return new RESTResultBean();
        }

        /// <summary>
        /// 数据位List返回
        /// </summary>
        /// <param name="URL"></param>
        /// <param name="Data"></param>
        /// <returns></returns>
        public static RESTResultBean InterviewList(String URL, FromBean Data)
        {
            List<FromBean> lfb = new List<FromBean>();

            return new RESTResultBean();
        }

        /// <summary>
        /// 服务器交互原生方法
        /// </summary>
        /// <param name="URL"></param>
        /// <param name="Method">REST四种方法0:GET1:POST2:DELETE</param>
        /// <param name="Data"></param>
        /// <returns></returns>
        public static RESTResultBean InterviewServer(String URL, String Method, FPM.Common.Data.FromBean fb)
        {
            ShowOnTimeMessge("加载===>>>" + URL);
            //String Data = "param=" + FPM.Common.Net.RESTFullServerInterview.JsonSerializer.Serialize(fb).ToString();
           // RESTResultBean rb = FPM.Common.Net.RESTFullServerInterview.Interview(URL, Method, Data);
            //ShowOnTimeMessge(rb);
           // return rb;
            return null;
        }
       
    }
}
