using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace FPMWA
{
    /// <summary>
    /// UpdateClient.xaml 的交互逻辑
    /// </summary>
    public partial class UpdateClient : UserControl
    {
        public UpdateClient()
        {
            InitializeComponent();
        }
        System.Timers.Timer t = new System.Timers.Timer(1000);//实例化Timer类，设置间隔时间为200毫秒；      

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            FPM.Framework.Utils.FormUtil.OnTimeMessage = this.OnTimeMessage;
            FPM.Framework.Utils.FormUtil.BusinessProgress = this.UpdateProgressBar;
            t.Elapsed += new System.Timers.ElapsedEventHandler(ToUpdateClient);  //到达时间的时候执行事件；    
            t.AutoReset = true;//设置是执行一次（false）还是一直执行(true)；
            t.Start();
            //t.Enabled = true;  //是否执行System.Timers.Timer.Elapsed事件；  ,调用start()方法也可以将其设置为true     
        }
        public void ToUpdateClient(object source, System.Timers.ElapsedEventArgs e)
        {
            try
            {
                Dispatcher.Invoke(new Action(
                     delegate
                     {
                         try
                         {
                             //更新客户端
                             FPM.Framework.Utils.FormUtil.ClientUpdate();

                             //加载登录
                             if (FPM.Framework.Utils.FormUtil.BusinessProgress.Value == 100)
                             {
                                 t.AutoReset = false;
                                 t.Stop();
                                 FPM.Framework.Utils.FormUtil.ShowMainFrom(new FPM.Framework.Background());
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
