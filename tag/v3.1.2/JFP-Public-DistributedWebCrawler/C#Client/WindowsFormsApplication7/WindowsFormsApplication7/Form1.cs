using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace WindowsFormsApplication7
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            try
            {
                String[] html = list[0].Split(' ');
                list.RemoveAt(0);
                webBrowser2.Navigate("http://qichacha.com/company_base?unique=" + html[0] + "&companyname=" + html[1]);
            }
            catch (Exception) { }
        }

        private void webBrowser1_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            htmlCode.Text = webBrowser1.DocumentText;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            webBrowser1.Navigate(textBox1.Text);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            webBrowser1.Document.GetElementById("newInput").SetAttribute("value", "南正科技");
            webBrowser1.Document.GetElementById("new-search").InvokeMember("Click");
        }

        private void button3_Click(object sender, EventArgs e)
        {
            foreach (HtmlElement item in webBrowser1.Document.GetElementsByTagName("li")) 
            {
                if ("site-list-item company-list-item".Equals(item.GetAttribute("className")))
                {
                    foreach (HtmlElement a in item.GetElementsByTagName("a"))
                    {
                        //MessageBox.Show(a.OuterHtml);
                        //获得企业访问地址
                        if (a.GetElementsByTagName("img").Count > 0) { continue; }

                        list.Add(a.GetAttribute("href").Split('_')[2] + " " + a.OuterText.Split(' ')[0]);
                        break;
                    }
                }
            }
        }

        List<String> list = new List<string>();

        private void webBrowser2_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            htmlCode.Text = webBrowser2.DocumentText;
            //foreach (HtmlElement item in webBrowser2.Document.GetElementsByTagName("div"))
            //{
            //    if ("tab-content base_info".Equals(item.GetAttribute("className")))
            //    {
            //        htmlCode.Text = item.OuterHtml;
            //    }
            //}
        }
    }
}
