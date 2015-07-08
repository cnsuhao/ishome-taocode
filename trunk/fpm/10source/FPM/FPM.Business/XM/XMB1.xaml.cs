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
using FPM.Framework.Utils;

namespace FPM.Business.XM
{
    /// <summary>
    /// XMB1.xaml 的交互逻辑
    /// </summary>
    public partial class XMB1 : UserControl
    {
        public XMB1()
        {
            InitializeComponent();
        }

        private void CreatBTN_Click(object sender, RoutedEventArgs e)
        {
            FormUtil.ShowBusinessFrom(new XMB2());
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            FormUtil.ShowBussnessTitle("项目一览");
        }
    }
}
