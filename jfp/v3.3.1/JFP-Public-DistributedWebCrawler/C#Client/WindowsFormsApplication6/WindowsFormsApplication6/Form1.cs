using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace WindowsFormsApplication6
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void webBrowser1_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            HtmlElementCollection html = webBrowser3.Document.Links;

            foreach(HtmlElement h in html)
            {
                HtmlElementCollection a = h.Document.GetElementsByTagName("href");
            }
        }

        private void webBrowser2_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
           var a  = webBrowser3.Document.Body;
        }

        private void webBrowser3_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            HtmlElementCollection html = webBrowser3.Document.Links;
        }

        private void webBrowser1_NewWindow(object sender, CancelEventArgs e)
        {
            String link = ((System.Windows.Forms.WebBrowser)(sender)).StatusText;
            webBrowser2.Navigate(link);
            e.Cancel = true;
        }

        private void webBrowser2_NewWindow(object sender, CancelEventArgs e)
        {
            String link = ((System.Windows.Forms.WebBrowser)(sender)).StatusText;
            webBrowser3.Navigate(link);
            e.Cancel = true;
        }

        private void webBrowser3_NewWindow(object sender, CancelEventArgs e)
        {
            e.Cancel = true;
        }
    }
}
