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

namespace FPM.Framework
{
    /// <summary>
    /// Login.xaml 的交互逻辑
    /// </summary>
    public partial class Login : UserControl
    {
        public Login()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowMainFrom(new SF.SF0101());
            Utils.FormUtil.Login();
        }

        private void button2_Click(object sender, RoutedEventArgs e)
        {
            FPM.Framework.Window1 win = new FPM.Framework.Window1();
            win.ShowDialog();
        }
    }
}
