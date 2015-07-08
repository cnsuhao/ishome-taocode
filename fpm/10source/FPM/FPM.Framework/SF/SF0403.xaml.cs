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
    public partial class SF0403 : UserControl
    {
        public SF0403()
        {
            InitializeComponent();
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.PopupFormControl = this.PopupFormControl;
        }

        private void image1_MouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            this.Visibility = Visibility.Collapsed;
        }
    }
}
