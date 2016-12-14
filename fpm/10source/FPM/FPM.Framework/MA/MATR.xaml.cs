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
    /// UIElementUtil.xaml 的交互逻辑
    /// </summary>
    public partial class MATR : UserControl
    {
        public MATR()
        {
            InitializeComponent();
            SureUIElement(null);
        }
        public MATR(FPM.Common.Data.FromBean fb)
        {
            InitializeComponent();
            SureUIElement(fb);
        }
        public MATR(FPM.Common.Data.FromBean fb, int pixels)
        {
            InitializeComponent();
            ItemColumn.Width = new GridLength(pixels);
            SureUIElement(fb);
        }

        //行显示输入控件对象
        public Object RowUIElement { get; set; }
        //行显示标题
        public String RowItemLabel { get; set; }
        public RowDefinition RowItemDefinition { get; set; }
        //行显示标题
        public int RowItemNum { get; set; }
        //行显示输入控件的输入值
        public Object RowItemValue
        {
            get
            {
                if (RowUIElement == null)
                {
                    return "";
                }
                else if (RowUIElement is TextBox)
                {
                    return ((TextBox)RowUIElement).Text;
                }
                else if (RowUIElement is RadioButton[])////////////////////
                {
                    if (((RadioButton)RowUIElement).IsChecked == true)
                        return "1";
                    return "0";
                }
                else if (RowUIElement is CheckBox[])////////////////////////////
                {
                    if (((CheckBox)RowUIElement).IsChecked == true)
                        return "1";
                    return "0";
                }
                else if (RowUIElement is ComboBox)
                {
                    return ((ComboBox)RowUIElement).Text;
                }
                else { return ""; }
            }
            set { }
        }
        //行显示输入控件的数据源
        public List<FPM.Common.Data.FromBean> RowItemSource { get; set; }

        /// <summary>
        /// MTY0根据数据类型生成UIElement控件
        /// </summary>
        /// <param name="FB"></param>
        /// <returns></returns>
        public void SureUIElement(FPM.Common.Data.FromBean fb)
        {
            if (fb == null)
                fb = new FPM.Common.Data.FromBean();
            fb.f01 = "aaa";
            fb.f05 = "2";
            //标题内容
            ItemNameLBL.Content = fb.f01;
            //行数
            RowItemNum = 1;
            RowItemLabel = fb.f01;
            //1单行文本，2多行文本、3日期选择，4文件选择，5单选按钮，6多选按钮，7下拉列表，默认单行
            #region 显示控件
            if ("1".Equals(fb.f05))
            {
                //单行文本
                TextBox uie = new TextBox();
                uie.Margin = new Thickness(2);
                uie.Text = fb.f01;
                uie.VerticalContentAlignment = VerticalAlignment.Center;
                uie.SetValue(Grid.RowProperty, 0);
                uie.SetValue(Grid.ColumnProperty, 1);
                RootGrid.Children.Add(uie);
                RowUIElement = uie;
            }
            else if ("2".Equals(fb.f05))
            {
                //多行文本
                TextBox uie = new TextBox();
                uie.Margin = new Thickness(2);
                uie.Text = fb.f01;
                uie.VerticalContentAlignment = VerticalAlignment.Center;


                RowItemNum = 1;


                uie.SetValue(Grid.RowProperty, 0);
                uie.SetValue(Grid.ColumnProperty, 1);
                RootGrid.Children.Add(uie);
                RowUIElement = uie;
            }
            else if ("3".Equals(fb.f05))
            {
                //日期选择
                TextBox uie = new TextBox();
                uie.Margin = new Thickness(2);
                uie.Text = fb.f01;
                uie.VerticalContentAlignment = VerticalAlignment.Center;
                uie.SetValue(Grid.RowProperty, 0);
                uie.SetValue(Grid.ColumnProperty, 1);
                RootGrid.Children.Add(uie);
                RowUIElement = uie;
            }
            else if ("4".Equals(fb.f05))
            {
                //文件选择
                TextBox uie = new TextBox();
                uie.Margin = new Thickness(2);
                uie.Text = fb.f01;
                uie.VerticalContentAlignment = VerticalAlignment.Center;
                uie.SetValue(Grid.RowProperty, 0);
                uie.SetValue(Grid.ColumnProperty, 1);
                RootGrid.Children.Add(uie);
                RowUIElement = uie;
            }
            //else if ("5".Equals(fb.f05))
            //{
            //    //数据源加载
            //    FPM.Common.Data.RestltBean ServiceRestlt = Utils.FormUtil.InterviewList(FPM.Common.Config.ProtocolURL.Master_Server_URL, fb);
            //    if ("0".Equals(ServiceRestlt.resultcode))
            //    {
            //        RowItemSource = new List<FPM.Common.Data.FromBean>();
            //        foreach (FPM.Common.Data.FromBean item in RowItemSource)
            //        {
            //            ComboBoxItem cbi = new ComboBoxItem();
            //            cbi.Name = item.puk;
            //            cbi.Content = item.f01;
            //            uie.Items.Add(cbi);
            //        }
            //    }
            //    //单选按钮
            //    RadioButton uie = new RadioButton();
            //    uie.Margin = new Thickness(2);
            //    uie.Content = fb.f01;
            //    uie.VerticalAlignment = VerticalAlignment.Center;
            //    uie.VerticalContentAlignment = VerticalAlignment.Center;
            //    uie.SetValue(Grid.RowProperty, 0);
            //    uie.SetValue(Grid.ColumnProperty, 1);
            //    RootGrid.Children.Add(uie);
            //    return uie;
            //}
            //else if ("6".Equals(fb.f05))
            //{
            //    //数据源加载
            //    FPM.Common.Data.RestltBean ServiceRestlt = Utils.FormUtil.InterviewList(FPM.Common.Config.ProtocolURL.Master_Server_URL, fb);
            //    if ("0".Equals(ServiceRestlt.resultcode))
            //    {
            //        RowItemSource = new List<FPM.Common.Data.FromBean>();
            //        foreach (FPM.Common.Data.FromBean item in RowItemSource)
            //        {
            //            ComboBoxItem cbi = new ComboBoxItem();
            //            cbi.Name = item.puk;
            //            cbi.Content = item.f01;
            //            uie.Items.Add(cbi);
            //        }
            //    }
            //    //多选按钮
            //    CheckBox uie = new CheckBox();
            //    uie.Margin = new Thickness(2);
            //    uie.Content = fb.f01;
            //    uie.VerticalAlignment = VerticalAlignment.Center;
            //    uie.VerticalContentAlignment = VerticalAlignment.Center;
            //    uie.SetValue(Grid.RowProperty, 0);
            //    uie.SetValue(Grid.ColumnProperty, 1);
            //    RootGrid.Children.Add(uie);
            //    return uie;
            //}
            else if ("7".Equals(fb.f05))
            {
                //<ComboBox Grid.ColumnSpan="3" Height="23" HorizontalAlignment="Left" Margin="100,14,0,0" Name="comboBox1" VerticalAlignment="Top" Width="120">
                //    <ComboBoxItem Content="aaaa" />
                //    <ComboBoxItem Content="bbb" />
                //</ComboBox>
                //下拉列表
                ComboBox uie = new ComboBox();
                uie.Margin = new Thickness(2);
                //数据源加载
                FPM.Common.Data.RESTResultBean ServiceRestlt = Utils.FormUtil.InterviewList(FPM.Common.Config.ProtocolURL.Master_Server_URL, fb);
                if ("0".Equals(ServiceRestlt.resultcode))
                {
                    RowItemSource = new List<FPM.Common.Data.FromBean>();
                    foreach (FPM.Common.Data.FromBean item in RowItemSource)
                    {
                        ComboBoxItem cbi = new ComboBoxItem();
                        cbi.Name = item.puk;
                        cbi.Content = item.f01;
                        uie.Items.Add(cbi);
                    }
                }
                uie.Text = fb.f01;
                uie.VerticalContentAlignment = VerticalAlignment.Center;
                uie.SetValue(Grid.RowProperty, 0);
                uie.SetValue(Grid.ColumnProperty, 1);
                RootGrid.Children.Add(uie);
                RowUIElement = uie;
            }
            else
            {
                TextBox uie = new TextBox();
                uie.Margin = new Thickness(2);
                uie.Text = fb.f01;
                uie.VerticalContentAlignment = VerticalAlignment.Center;
                uie.SetValue(Grid.RowProperty, 0);
                uie.SetValue(Grid.ColumnProperty, 1);
                RootGrid.Children.Add(uie);
                RowUIElement = uie;
            }
            #endregion

            //需要行信息
            RowItemDefinition = new RowDefinition();
            RowItemDefinition.Height = new GridLength(32 * RowItemNum);
        }
    }
}
