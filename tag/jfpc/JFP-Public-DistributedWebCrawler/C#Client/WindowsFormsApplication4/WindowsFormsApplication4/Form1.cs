using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Web;
using System.Windows.Forms;
using System.Runtime.InteropServices;
using System.Threading;
using System.IO;

namespace WindowsFormsApplication4
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            //ShellExecute(IntPtr.Zero, "open", "rundll32.exe", " InetCpl.cpl,ClearMyTracksByProcess 255", "", ShowCommands.SW_HIDE);

            phones = File.ReadAllLines("c.txt", Encoding.Default);
            indexUser = File.ReadAllText("i.txt", Encoding.Default);
            num = Convert.ToInt32(indexUser);
            webBrowser1.Navigate("http://bbss.lasp.com.cn/plugin.php?id=tom_mengbao&mod=info&vid=11&tid=2201");
            File.WriteAllText("i.txt", ""+(num+1));
        }

        String[] phones;
        String indexUser;

        [DllImport("shell32.dll")]
        static extern IntPtr ShellExecute(IntPtr hwnd, string lpOperation, string lpFile, string lpParameters, string lpDirectory, ShowCommands nShowCmd);
        [DllImport("wininet.dll", SetLastError = true)]
        private static extern bool InternetSetOption(IntPtr hInternet, int dwOption, IntPtr lpBuffer, int lpdwBufferLength);
        int num = 1;
        private void RefreshIESettings(string strProxy)
        {
            const int INTERNET_OPTION_PROXY = 38;
            const int INTERNET_OPEN_TYPE_PROXY = 3;

            Struct_INTERNET_PROXY_INFO struct_IPI;

            // Filling in structure 
            struct_IPI.dwAccessType = INTERNET_OPEN_TYPE_PROXY;
            struct_IPI.proxy = Marshal.StringToHGlobalAnsi(strProxy);
            struct_IPI.proxyBypass = Marshal.StringToHGlobalAnsi("local");

            // Allocating memory 
            IntPtr intptrStruct = Marshal.AllocCoTaskMem(Marshal.SizeOf(struct_IPI));

            // Converting structure to IntPtr 
            Marshal.StructureToPtr(struct_IPI, intptrStruct, true);

            bool iReturn = InternetSetOption(IntPtr.Zero, INTERNET_OPTION_PROXY, intptrStruct, Marshal.SizeOf(struct_IPI));
        }

        private void webBrowser1_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            tp();
        }

        public void tp()
        {
            try
            {
                String tpxm = phones[num].Split('@')[0];
                String tptel = phones[num].Split('@')[1];
                //姓名
                webBrowser1.Document.GetElementById("tpxm").SetAttribute("value", tpxm);
                //手机号
                webBrowser1.Document.GetElementById("tptel").SetAttribute("value", tptel);
                //按钮点击
                foreach (HtmlElement item in webBrowser1.Document.GetElementsByTagName("div"))
                {
                    if (item.InnerHtml != null && item.InnerHtml.IndexOf("win-alert-button") > -1)
                    {
                        foreach (HtmlElement button in webBrowser1.Document.GetElementsByTagName("button"))
                        {
                            if (button.InnerHtml.IndexOf("投票") > -1)
                            {
                                button.InvokeMember("Click");
                                return;
                            }
                        }
                    }
                }
            }
            catch (Exception)
            {


            }
        }
        int total = 0;

        private void timer1_Tick(object sender, EventArgs e)
        {
            if (total == 2)
            {
                tp();
            }
            if (total > 3)
            {
                ShellExecute(IntPtr.Zero, "open", "rundll32.exe", " InetCpl.cpl,ClearMyTracksByProcess 255", "", ShowCommands.SW_HIDE);
                Application.Exit();
            }
            total = total + 1;
        }



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

        public struct Struct_INTERNET_PROXY_INFO
        {
            public int dwAccessType;
            public IntPtr proxy;
            public IntPtr proxyBypass;
        }
    }
}
