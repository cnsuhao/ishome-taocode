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

namespace FPM.Framework.MT
{
    /// <summary>
    /// MTS1.xaml 的交互逻辑
    /// </summary>
    public partial class MTS1 : UserControl
    {
        public MTS1()
        {
            InitializeComponent();
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowBussnessTitle("数据表XXX（）");
        }

        private void CreatBTN_Click(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowBusinessFrom(new MTS2());
        }

        private void SearchBTN_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}
