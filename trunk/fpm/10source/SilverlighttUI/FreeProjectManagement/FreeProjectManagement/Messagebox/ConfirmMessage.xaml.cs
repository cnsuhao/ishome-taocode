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

namespace JSS.Common.Message
{
    public partial class ConfirmMessage : ChildWindow
    {
        public ConfirmMessage()
        {
            InitializeComponent();
        }
        public void SetTitle(String MessageTitle)
        {
            if (!String.IsNullOrEmpty(MessageTitle))
            {
                this.Title = MessageTitle;
            }
        }
        public void SetMessage(String MessageValue)
        {
            if (!String.IsNullOrEmpty(MessageValue))
            {
                MessageText.Text = MessageValue;
            }
        }
        public int QueryResult = 0;
        private void OKButton_Click(object sender, RoutedEventArgs e)
        {
            QueryResult = 2;
            this.DialogResult = true;
        }

        private void CancelButton_Click(object sender, RoutedEventArgs e)
        {
            QueryResult = 0;
            this.DialogResult = false;
        }

        private void NOButton_Click(object sender, RoutedEventArgs e)
        {
            QueryResult = 1;
            this.DialogResult = false;
        }
    }
}

