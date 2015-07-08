using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;

namespace FreeProjectManagement.SL
{
    public partial class SL0101 : UserControl
    {
        public SL0101()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowMainFrom(new SF.SF0101());
        }
    }
}
