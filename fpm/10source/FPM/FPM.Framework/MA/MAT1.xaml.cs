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

namespace FPM.Framework.MA
{
    /// <summary>
    /// MAT1.xaml 的交互逻辑
    /// </summary>
    public partial class MAT1 : UserControl
    {
        public MAT1()
        {
            InitializeComponent();
        }

        private void SearchBTN_Click(object sender, RoutedEventArgs e)
        {
            //利用循环添加所有表头
            DataGridTextColumn dgtm = new DataGridTextColumn();
            dgtm.Header = "aaaa";
            dataGrid1.Columns.Add(dgtm);
            //处理所有数据
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowBussnessTitle("数据表XXX（一览）");
        }

        private void CreatBTN_Click(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowBusinessFrom(new MAT2());
        }

        private void SearchBTN_Click_1(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowBusinessFrom(new MAT0());
        }

        private void button3_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("数据成功删除", "正常", MessageBoxButton.OK, MessageBoxImage.Information);
        }
    }
}
