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

namespace FreeProjectManagement.SF
{
    public partial class SF0503 : UserControl
    {
        public SF0503()
        {
            InitializeComponent();
        }

        private void UserControl_Loaded(object sender, RoutedEventArgs e)
        {
            Utils.FormUtil.BusinessProgress = this.BusinessProgress;
        }
    }
}
