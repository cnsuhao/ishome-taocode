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
        private static int LoadingProgress = 0;
        public static void LoadingProcess()
        {
            try
            {
                Boolean Result = false;
                if (LoadingProgress==0)
                {
                    Result = LoadingMaster();
                    LoadingProgress = 10;
                }
                else if(LoadingProgress==10)
                    LoadingProgress = 10;
                else if(LoadingProgress==20)
                    LoadingProgress = 10;
                else if (LoadingProgress == 30)
                    LoadingProgress = 10;
                else if (LoadingProgress == 40)
                    LoadingProgress = 10;
                else
                    LoadingProgress = 100;

                Utils.FormUtil.BusinessProgress.Value = Utils.FormUtil.BusinessProgress.Value + 50;
                Utils.FormUtil.ShowOnTimeMessge(DateTime.Now.ToString("yyyy/MM/dd HH:mm:ss") + " " + FormUtil.BusinessProgress.Value);

               
            }
            catch (Exception)
            {

                throw;
            }
        }

        private static Boolean LoadingMaster()
        {
            return true;
        }


    }
}
