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
    public partial class InputMessage : ChildWindow
    {
        public InputMessage()
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

        public String InputValue = String.Empty;

        private void OKButton_Click(object sender, RoutedEventArgs e)
        {
            InputValue = InputBox.Text;
            this.DialogResult = true;
        }
    }
}

