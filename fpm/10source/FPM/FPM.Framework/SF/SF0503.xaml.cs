using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace FPM.Framework.SF
{
    public partial class SF0503 : UserControl
    {
        public SF0503()
        {
            InitializeComponent();
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.BusinessProgress = this.BusinessProgress;
            //加载基本数据         
            t.Elapsed += new System.Timers.ElapsedEventHandler(ToLoadingClient);  //到达时间的时候执行事件；    
            t.AutoReset = true;//设置是执行一次（false）还是一直执行(true)；
            t.Start();
            //t.Enabled = true;  //是否执行System.Timers.Timer.Elapsed事件；  ,调用start()方法也可以将其设置为true     

        }
        System.Timers.Timer t = new System.Timers.Timer(1000);//实例化Timer类，设置间隔时间为200毫秒；

        public void ToLoadingClient(object source, System.Timers.ElapsedEventArgs e)
        {
            try
            {
                Dispatcher.Invoke(new Action(
                     delegate
                     {
                         try
                         {
                             //加载服务缓存数据
                             Utils.FormUtil.LoadingProcess();
                             //结束加载
                             if (FPM.Framework.Utils.FormUtil.BusinessProgress.Value == 100)
                             {
                                 t.AutoReset = false;
                                 t.Stop();
                                 FPM.Framework.Utils.FormUtil.FormBusy(false);
                                 Utils.FormUtil.BusinessProgress.Value = 0;
                                 Utils.FormUtil.ShowOnTimeMessge(DateTime.Now.ToString("yyyy/MM/dd HH:mm:ss") + " 基本数据加载成功");

                             }
                         }
                         catch (Exception ex)
                         {
                             t.AutoReset = false;
                             t.Stop();
                             MessageBox.Show(ex.Message, "异常", MessageBoxButton.OK, MessageBoxImage.Error);
                         }
                     }));
            }
            catch (Exception ex)
            {
                t.AutoReset = false;
                t.Stop();
                MessageBox.Show(ex.Message, "异常", MessageBoxButton.OK, MessageBoxImage.Error);
            }
        }
    }
}
