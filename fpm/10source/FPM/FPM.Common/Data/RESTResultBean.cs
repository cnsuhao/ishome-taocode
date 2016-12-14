using System;

namespace FPM.Common.Data
{
    public class RESTResultBean 
    {
        /// <summary>
        /// 授权ID	
        /// </summary>
        public String token { get; set; }
        /// <summary>
        /// 操作结果
        /// </summary>
        public String resultcode { get; set; }
        /// <summary>
        /// 操作提示信息
        /// </summary>
        public String resultmessage { get; set; }
        /// <summary>
        /// 返回结果数据
        /// </summary>
        public Object resultobject { get; set; }

    }
}
