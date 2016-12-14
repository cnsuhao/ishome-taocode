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
    /// 所有画面的基底容器
    /// </summary>
    public partial class Background : UserControl
    {
        public Background()
        {
            InitializeComponent();
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.MainUIControl = this.RootLayoutRoot;
            Utils.FormUtil.Indicator = this.InitContral;
        }

        private void Login_Loaded(object sender, RoutedEventArgs e)
        {

        }
    }
}
