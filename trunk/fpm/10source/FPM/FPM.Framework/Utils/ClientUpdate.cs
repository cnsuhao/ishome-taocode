using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace FPM.Framework.Utils
{
    /// <summary>
    /// 反复执行，直到结束或者异常
    /// </summary>
    public partial class FormUtil
    {
        private static int UpdateProgress = 0;
        public static void ClientUpdate()
        {
            try
            {
                Utils.FormUtil.BusinessProgress.Value = Utils.FormUtil.BusinessProgress.Value + 50;
                //if (UpdateProgressBar.Value > 99)
                {
                }
                Utils.FormUtil.ShowOnTimeMessge(DateTime.Now.ToString("yyyy/MM/dd HH:mm:ss") + " " + FormUtil.BusinessProgress.Value);

               
            }
            catch (Exception)
            {
                throw;
            }
        }
    }
}
