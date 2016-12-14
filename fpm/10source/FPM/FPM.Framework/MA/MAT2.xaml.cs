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
    /// MTS2.xaml 的交互逻辑
    /// </summary>
    public partial class MAT2 : UserControl
    {
        public MAT2()
        {
            InitializeComponent();
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            if (Utils.FormUtil.BusinessFromData != null)
                Utils.FormUtil.ShowBussnessTitle("数据表XXX（编辑）");
            else
                Utils.FormUtil.ShowBussnessTitle("数据表XXX（新建）");

            for (int i = 0; i < 100; i++)
            {
                MATR mart = new MATR();
                mart.SetValue(Grid.RowProperty, i);
                FormList.RowDefinitions.Add(mart.RowItemDefinition);
                FormList.Children.Add(mart);
            }
        }

        private void BackBTN_Click(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowLastBusinessFrom();
        }

        private void ClearBTN_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}
