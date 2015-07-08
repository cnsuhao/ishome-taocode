using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace FPM.Common.Data
{
    public class ConfigBean
    {
        /// <summary>
        /// 配置使用关键字
        /// </summary>
        public String key { get; set; }

        /// <summary>
        /// 配置对应的内容
        /// </summary>
        public String value { get; set; }
    }
}
