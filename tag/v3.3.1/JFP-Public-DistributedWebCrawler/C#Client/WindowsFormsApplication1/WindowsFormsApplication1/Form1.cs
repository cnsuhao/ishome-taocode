using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.IO;
using System.Runtime.InteropServices;

namespace WindowsFormsApplication1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            //s File.CreateText("d:/001745/2.text");
            html = File.ReadAllLines("E:/t.txt");
        }
        String[] html;
        List<String> citys = new List<String>();

        private void webBrowser1_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            try
            {
                var html = webBrowser1.Document.GetElementsByTagName("a");
                {
                    foreach (HtmlElement item in html)
                    {
                        if (item.GetAttribute("href").IndexOf("http:") > -1)
                            citys.Add(item.GetAttribute("href"));
                    }
                }
            }
            catch (Exception)
            {
            }
        }
        int waitStep = 0;
        int cityIndex = 0;
        int page = 1;
        private void timer1_Tick(object sender, EventArgs e)
        {
            if (waitStep == 5)
            {
                cityIndex = cityIndex + 1;
                textBox2.Text = "" + cityIndex;
                textBox1.Text = citys[cityIndex];
                webBrowser2.Navigate(citys[cityIndex]);
                waitStep = 21;
            }
            else if (waitStep == 20)
            {
                if (page >= 100)
                {
                    waitStep = 1;
                    return;
                }
                page = page + 1;
                String url = web3URL + "?page=" + page;
                textBox1.Text = url;
                textBox3.Text = "" + page;
                webBrowser3.Navigate(url);
            }

            waitStep = waitStep + 1;

        }
        String web3URL;
        private void webBrowser2_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            try
            {
                foreach (HtmlElement div in webBrowser2.Document.GetElementsByTagName("div"))
                {
                    if (div.OuterHtml.IndexOf("立信单位") > -1)
                    {
                        foreach (HtmlElement a in div.GetElementsByTagName("a"))
                        {
                            if (a.OuterHtml.IndexOf("立信单位") > -1)
                            {
                                web3URL = a.GetAttribute("href");
                                textBox1.Text = web3URL;
                                webBrowser3.Navigate(web3URL);
                                break;
                            }
                        }
                        waitStep = 31;
                        break;
                    }
                }

            }
            catch (Exception)
            {
            }
        }

        private void webBrowser3_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            //<a class="ran_name" href="http://29018489.11315.com" target="_blank"><b class="ran_ran">2</b>&nbsp;阿勒泰地区福海县迪娜尔服饰店</a>
            try
            {
                String text = "";
                String href = "";
                foreach (HtmlElement div in webBrowser3.Document.GetElementsByTagName("div"))
                {
                    if (div.OuterHtml.IndexOf("rank_list_r rank_list_ra fl") > -1)
                    {

                        foreach (HtmlElement a in div.GetElementsByTagName("a"))
                        {
                            if (a.OuterHtml.IndexOf("ran_ran") > -1)
                            {
                                text = a.InnerText;
                                href = a.GetAttribute("href");
                                File.AppendAllText("E:/tmp.txt", text + " " + href + "\r\n");

                            }
                        }
                        waitStep = 11;
                        break;
                    }
                }
                webBrowser4.Navigate(href);
            }
            catch (Exception)
            {
            }
        }

        [DllImport("shell32.dll")]
        static extern IntPtr ShellExecute(IntPtr hwnd, string lpOperation, string lpFile, string lpParameters, string lpDirectory, ShowCommands nShowCmd);

        int waitNum = 0;
        public enum ShowCommands : int
        {

            SW_HIDE = 0,

            SW_SHOWNORMAL = 1,

            SW_NORMAL = 1,

            SW_SHOWMINIMIZED = 2,

            SW_SHOWMAXIMIZED = 3,

            SW_MAXIMIZE = 3,

            SW_SHOWNOACTIVATE = 4,

            SW_SHOW = 5,

            SW_MINIMIZE = 6,

            SW_SHOWMINNOACTIVE = 7,

            SW_SHOWNA = 8,

            SW_RESTORE = 9,

            SW_SHOWDEFAULT = 10,

            SW_FORCEMINIMIZE = 11,

            SW_MAX = 11

        }

        private void webBrowser4_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            ShellExecute(IntPtr.Zero, "open", "rundll32.exe", " InetCpl.cpl,ClearMyTracksByProcess 255", "", ShowCommands.SW_HIDE);
        }
    }
}
