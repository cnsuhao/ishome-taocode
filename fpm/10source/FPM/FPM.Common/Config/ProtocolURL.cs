using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace FPM.Common.Config
{
    /// <summary>
    /// 协议地址列表
    /// </summary>
    public class ProtocolURL
    {

        public static String Master_Server_URL = "aaa";



        private static ProtocolURL _ProtocolURL = new ProtocolURL();
        public static ProtocolURL GetInstance()
        {
            return _ProtocolURL;
        }
        private ProtocolURL ()
        {
           
        }

        /// <summary>
        /// 获得一个动作的协议地址
        /// </summary>
        /// <param name="Todo"></param>
        /// <returns></returns>
        public String GetProtocol(String ProtocolName)
        {
            return "";
        }
    }
}
