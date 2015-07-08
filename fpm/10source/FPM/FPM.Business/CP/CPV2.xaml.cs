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

namespace FPM.Business.CP
{
    /// <summary>
    /// CPV2.xaml 的交互逻辑
    /// </summary>
    public partial class CPV2 : UserControl
    {
        public CPV2()
        {
            InitializeComponent();
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            FormUtil.ShowBussnessTitle("版本管理");
        }
    }
}
