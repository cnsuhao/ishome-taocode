using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.Web.Script.Serialization;
using FPM.Common.Data;
using FPM.Common.Config;

namespace FPM.Common.Net
{
    public class RESTFullServerInterview
    {
        public static JavaScriptSerializer JsonSerializer = new JavaScriptSerializer();

        /// <summary>
        /// 与服务器交互
        /// </summary>
        /// <param name="URL"></param>
        /// <param name="Method"></param>
        /// <param name="Data"></param>
        /// <returns></returns>
        public static RESTResultBean Interview(String ProtocolName, String Method, String Data)
        {
            String URL = ProtocolURL.GetInstance().GetProtocol(ProtocolName);
            WebClient client = new WebClient();
            //client.UploadStringAsync
            client.Encoding = Encoding.UTF8;
            String val = client.UploadString(URL, Method, Data);
            Data.RESTResultBean rb = JsonSerializer.Deserialize<Data.RESTResultBean>(val);
            if (rb.resultcode.Equals("1"))
            {
                throw new Exception("Server is dead !!!");
            }
            return rb;
        }

        /// <summary>
        /// 登录系统
        /// </summary>
        /// <param name="URL"></param>
        /// <param name="uid"></param>
        /// <param name="pwd"></param>
        /// <returns></returns>
        public static Data.RESTResultBean Login(String uid, String pwd)
        {
            try
            {
                Data.FromBean lb = new Data.FromBean();
                lb.k01 = uid;
                lb.k02 = pwd;
                return JsonSerializer.Deserialize<Data.RESTResultBean>(
                    Interview("LOGIN", "POST",
                    JsonSerializer.Serialize(lb)).ToString());
            }
            catch (Exception)
            {
                Data.RESTResultBean rb = new Data.RESTResultBean();
                rb.resultcode = "1";
                return rb;
            }
        }

    }
}
