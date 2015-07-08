using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace FPM.Common.Data
{
    /// <summary>
    /// 文件版本
    /// </summary>
    public class VersionBean
    {
        /// <summary>
        /// 名称
        /// </summary>
        public String clientname { get; set; }
        /// <summary>
        /// 版本
        /// </summary>
        public String version { get; set; }
        /// <summary>
        /// 版本日期
        /// </summary>
        public String datetime { get; set; }
    }
}
