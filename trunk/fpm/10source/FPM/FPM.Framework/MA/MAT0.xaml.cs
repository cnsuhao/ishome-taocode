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
    public partial class MAT0 : UserControl
    {
        public MAT0()
        {
            InitializeComponent();
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowBussnessTitle("数据表XXX（检索）");

            for (int i = 0; i < 100; i++)
            {
            //创建新列
            RowDefinition rd = new RowDefinition();
            rd.Height = new GridLength(34);
            FormList.RowDefinitions.Add(rd);
            //显示标题
            Label lt = new Label();
            lt.Margin = new Thickness(1) ;
            lt.Content = "中文名称";
            lt.HorizontalAlignment = HorizontalAlignment.Right;
            lt.VerticalAlignment = VerticalAlignment.Center;
            lt.SetValue(Grid.RowProperty, i);
            lt.SetValue(Grid.ColumnProperty, 0);
            FormList.Children.Add(lt);

            //显示标题
            TextBox lt1 = new TextBox();
            lt1.Margin = new Thickness(2) ;
            lt1.Text = "中文名称111";
            //lt1.HorizontalAlignment = HorizontalAlignment.Right;
            //lt1.VerticalAlignment = VerticalAlignment.Center;
            lt1.VerticalContentAlignment = VerticalAlignment.Center;
            lt1.SetValue(Grid.RowProperty, i);
            lt1.SetValue(Grid.ColumnProperty, 1);
            FormList.Children.Add(lt1); 
                
            }

                
            ////显示标题
            //Label lt2 = new Label();
            //lt2.Margin = new Thickness(1) ;
            //lt2.Content = "中文名称222";
            //lt2.HorizontalAlignment = HorizontalAlignment.Right;
            //lt2.VerticalAlignment = VerticalAlignment.Center;
            //lt2.SetValue(Grid.RowProperty, 0);
            //lt2.SetValue(Grid.ColumnProperty, 2);
            //FormList.Children.Add(lt2);
        }

        private void BackBTN_Click(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowLastBusinessFrom();
        }

        private void SearchBTN_Click(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowBusinessFrom(new MAT1(),true);
        }
    }
}
