using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Json;
using System.Net;

namespace FreeProjectManagement.Utils
{
    class Service
    {

        /// <summary>
        /// 调用服务器
        /// </summary>
        /// <param name="URL"></param>
        /// <param name="Method"></param>
        /// <param name="Data"></param>
        /// <returns></returns>
        public static String UploadString(String URL, String Method, String Data)
        {
            WebClient client = new WebClient();
            client.UploadStringAsync
            client.Encoding = Encoding.UTF8;
            String val = client.UploadString(URL, Method, "param=" + Data);
            RestltBean rb = JsonSerializer.Deserialize<RestltBean>(val);
            if (rb.RT.Equals("1"))
            {
                throw new Exception("MCpP is dead !!!");
            }
            return val;
        }
        ////合作用户登录
        //public static AuthorityBean MCpPLogin(String URL, String Method, String uid, String pwd)
        //{
        //    pwd = MD5.StringToMD5Hash(pwd);
        //    LoginBean lb = new LoginBean();
        //    lb.UID = uid;
        //    lb.PSW = pwd;
        //    return JsonSerializer.Deserialize<AuthorityBean>(
        //        MCpPUploadString(URL, Method,
        //        JsonSerializer.Serialize(lb)));
        //}
        //操作者登录
        //序列化反序列化工具
        // JavaScriptSerializer jsonSerializer = new JavaScriptSerializer();
        public static AuthorityBean MCpPLogin(String URL, String uid, String pwd)
        {
            try
            {
                LoginBean lb = new LoginBean();
                lb.UID = uid;
                lb.PSW = pwd;
                return JsonSerializer.Deserialize<AuthorityBean>(
                    MCpPUploadString(URL, "POST",
                    JsonSerializer.Serialize(lb)));
            }
            catch (Exception)
            {
                AuthorityBean rb = new AuthorityBean();
                rb.RT = "1";
                return rb;
            }
        }
    }
}
