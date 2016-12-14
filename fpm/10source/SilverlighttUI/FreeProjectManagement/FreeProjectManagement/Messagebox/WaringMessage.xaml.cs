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
    public partial class WaringMessage : ChildWindow
    {
        public WaringMessage()
        {
            InitializeComponent();
            SolidColorBrush brush = new SolidColorBrush();
            brush.Opacity = 0;
            this.OverlayBrush = brush;
        }
        public void SetTitle(String MessageTitle)
        {
            if (!String.IsNullOrEmpty(MessageTitle))
            {
                this.Title = MessageTitle;
            }
        }
        public void SetMessageID(String MessageID)
        {
            if (!String.IsNullOrEmpty(MessageID))            
                MSG_ID = MessageID;            
            if (!String.IsNullOrEmpty(MSG_ID) && !String.IsNullOrEmpty(MSG_Val))            
                MessageText.Text = MSG_ID + ":" + MSG_Val;            
            else if (String.IsNullOrEmpty(MSG_ID) && !String.IsNullOrEmpty(MSG_Val))
                MessageText.Text = MSG_Val;
        }  
        public string MSG_ID { get; set; }
        public string MSG_Val { get; set; }
        public void SetMessage(String MessageValue)
        {
            if (!String.IsNullOrEmpty(MessageValue))
                MSG_Val = MessageValue;
            if (!String.IsNullOrEmpty(MSG_ID) && !String.IsNullOrEmpty(MSG_Val))
                MessageText.Text = MSG_ID + ":" + MSG_Val;
            else if (String.IsNullOrEmpty(MSG_ID) && !String.IsNullOrEmpty(MSG_Val))
                MessageText.Text = MSG_Val;
        }        
        private void OKButton_Click(object sender, RoutedEventArgs e)
        {
            this.DialogResult = true;
        }

        private void CancelButton_Click(object sender, RoutedEventArgs e)
        {
            this.DialogResult = false;
        }
    }
}

