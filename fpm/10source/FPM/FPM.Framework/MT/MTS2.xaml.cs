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
    /// MTS2.xaml 的交互逻辑
    /// </summary>
    public partial class MTS2 : UserControl
    {
        public MTS2()
        {
            InitializeComponent();
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowBussnessTitle("数据表明细");
        }

        private void BackBTN_Click(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowLastBusinessFrom();
        }
    }
}
