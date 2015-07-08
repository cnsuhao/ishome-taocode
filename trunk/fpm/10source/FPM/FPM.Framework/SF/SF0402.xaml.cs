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
using FPM.Common.Data;

namespace FPM.Framework.SF
{
    /// <summary>
    /// 主键关联表TD01一览应用
    /// </summary>
    public partial class SF0402 : UserControl
    {
        public SF0402()
        {
            InitializeComponent();
        }

        public new Object Content
        {
            get
            {
                
                    return "";
               
            }
            set 
            {
                if (value != null)
                {
                    FromBean fb = new FromBean();
                    fb.k03 = value.ToString();
                }
            }
        }
        public String DateType { get; set; }

        private void SaveBTN_Click(object sender, RoutedEventArgs e)
        {
            this.Visibility = System.Windows.Visibility.Hidden;
        }

        private void BackBTN_Click(object sender, RoutedEventArgs e)
        {
            this.Visibility = System.Windows.Visibility.Hidden;
        }
    }
}
