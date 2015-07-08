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

namespace FPM.Framework.SM
{
    /// <summary>
    /// SM0101.xaml 的交互逻辑
    /// </summary>
    public partial class SM0102 : UserControl
    {
        public SM0102()
        {
            InitializeComponent();
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowBussnessTitle("系统菜单");
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowBusinessFrom(new MA.MAT0());
        }
    }
}
