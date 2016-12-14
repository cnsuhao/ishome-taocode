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
using System.Reflection;

namespace FPM.Framework.SM
{
    /// <summary>
    /// SM0101.xaml 的交互逻辑
    /// </summary>
    public partial class SM0101 : UserControl
    {
        public SM0101()
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

        private void Button_Click_1(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.PopupListForm.Visibility = System.Windows.Visibility.Visible;
        }

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowBusinessFrom("FPM.Business.XM.XMB1");
        }

        private void button2_Click(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowBusinessFrom("FPM.Business.CP.CPB1");
        }

        private void button22_Click(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowBusinessFrom("FPM.Business.CP.CPV1");
        }
    }
}
