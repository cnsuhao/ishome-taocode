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

namespace FPM.Framework.SF
{
    public partial class SF0301 : UserControl
    {
        public SF0301()
        {
            InitializeComponent();
        }

        private void Menu_Click(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowBusinessFrom(new SM.SM0101());
        }

        private void Home_Click(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowBusinessFrom(new Home());
        }

        private void Seeting_Click(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.ShowBusinessFrom(new MT.MTS1(), true);
        }
    }
}
