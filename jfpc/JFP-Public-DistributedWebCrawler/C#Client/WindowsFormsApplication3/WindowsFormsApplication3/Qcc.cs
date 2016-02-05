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

namespace WindowsFormsApplication3
{
    public partial class Qcc : Form
    {
        public Qcc()
        {
            InitializeComponent();
        }
        Hashtable configs = new Hashtable();
        private void Form1_Load(object sender, EventArgs e)
        {
            //http://spookfcy.3vfree.net/

            //ConfigURL.Text = "http://127.0.0.1:8080";

            TargetKeyTBX.Text = "QCC";

            KeyWordConfigWB.Navigate(ConfigURL.Text + "/K/" + TargetKeyTBX.Text);
            CompanyConfigWB.Navigate(ConfigURL.Text + "/N/" + TargetKeyTBX.Text);
            CompNameUploadWB.Navigate(Application.StartupPath + "/CompNameUpload.html");
            CompanyUploadWB.Navigate(Application.StartupPath + "/CompanyUpload.html");
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void showToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.ShowInTaskbar = true;
            this.Visible = true;
            this.Activate();
        }
        private void Qcc_FormClosing(object sender, FormClosingEventArgs e)
        {
            //保存数据
            loadCompList(KeyWordSearchWB);
            //保存数据
            loadCompany(CompanyWB);
        }

        private void Qcc_SizeChanged(object sender, EventArgs e)
        {
            //判断是否选择的是最小化按钮 
            if (WindowState == FormWindowState.Minimized)
            {
                //托盘显示图标等于托盘图标对象 
                //注意notifyIcon1是控件的名字而不是对象的名字 
                //隐藏任务栏区图标 
                this.ShowInTaskbar = false;
                //图标显示在托盘区 
                this.notifyIcon1.Visible = true;
            }
        }

        int KeyWordSearchTimer_Interval = 1000;
        int CompanyTimer_Interval = 1000;

        int KeyWordSearchTimer_Num = 1;
        int CompanyTimer_Num = 1;

        private void ConfigTimer_Tick(object sender, EventArgs e)
        {
            //查询数据
            if (KeyWordSearchTimer_Num > KeyWordSearchTimer_Interval && KeyWordSearchRunning == true)
            {
                KeyWordConfigWB.Navigate(ConfigURL.Text + "/K/" + TargetKeyTBX.Text);
                KeyWordSearchRunning = false;
            }

            KeyWordSearchTimer_Num = KeyWordSearchTimer_Num + 1;

            //提交结果
            if (CompNameUploadRunning == true && CompNameListDatas.Count > 0)
            {
                CompNameUploadWB.Navigate(Application.StartupPath + "/CompNameUpload.html");
                CompNameUploadRunning = false;
            }


            //查询数据
            if (CompanyTimer_Num > CompanyTimer_Interval && CompanyRunning == true)
            {
                CompanyConfigWB.Navigate(ConfigURL.Text + "/N/" + TargetKeyTBX.Text);
                CompanyRunning = false;
            }

            CompanyTimer_Num = CompanyTimer_Num + 1;

            //提交结果
            if (CompanyUploadWBRunning == true && CompanyListDatas.Count > 0)
            {
                CompanyUploadWB.Navigate(Application.StartupPath + "/CompanyUpload.html");
                CompanyUploadWBRunning = false;
            }

            TimerTBX.Text = KeyWordSearchTimer_Num + ":" + CompanyTimer_Num;

            //保存检索数据
            if (loadCompanyRunning == true)
            {
                loadCompanyRunning = false;
                loadCompany(CompanyWB);

                MessageTBX.Text = "准备新的企业数据抓取>>>>>>";
                //抓取数据
                if (String.IsNullOrEmpty((String)configs["COMP_URL"]) == false)
                {
                    String[] html = ((String)configs["COMP_URL"]).Split(' ');
                    //CompanyListDatas.Clear();
                    CompanyListDatas.Add(html[0] + "_" + html[1]);
                    CompanyWB.Navigate("http://qichacha.com/company_base?unique=" + html[0] + "&companyname=" + EncodeTools.UrlEncode(html[1]));

                    CompanyRunning = false;
                    CompanyTimer_Num = 1;
                    configs.Remove("COMP_URL");
                }
            }
            //保存企业数据
            if (loadCompListRunning == true)
            {
                loadCompListRunning = false;
                loadCompList(KeyWordSearchWB);

                MessageTBX.Text = "准备新的检索数据抓取>>>>>>";
                //抓取数据
                if (String.IsNullOrEmpty((String)configs["COMP_KEY"]) == false)
                {
                    KeyWordSearchWB.Navigate("http://qichacha.com/search?key=" + EncodeTools.UrlEncode((String)configs["COMP_KEY"]) + "&index=0");

                    KeyWordSearchRunning = false;
                    KeyWordSearchTimer_Num = 1;
                    configs.Remove("COMP_KEY");
                }
            }
        }

        Boolean KeyWordSearchRunning = false;
        Boolean CompanyRunning = false;

        private void KeyWordSearchWB_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            //列表搜索
            KeyWordSearchRunning = true;
            loadCompListRunning = true;
        }

        private void CompanyWB_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            //企业信息
            CompanyRunning = true;
            loadCompanyRunning = true;
        }

        /// <summary>
        /// 获得企业列表信息
        /// </summary>
        /// <param name="webBrowser1"></param>
        private void loadCompList(WebBrowser webBrowser1)
        {
            try
            {
                foreach (HtmlElement item in webBrowser1.Document.GetElementsByTagName("section"))
                {
                    if ("panel panel-default".Equals(item.GetAttribute("className")))
                    {
                        String name = "";
                        foreach (HtmlElement span in item.GetElementsByTagName("span"))
                        {
                            //MessageBox.Show(a.OuterHtml);
                            //获得企业访问地址
                            if ("name".Equals(span.GetAttribute("className")))
                            {
                                name = span.OuterText;
                                break;
                            }
                        }
                        String code = "";
                        HtmlElement a = item.GetElementsByTagName("a")[0];                        
                        {
                            //获得企业访问地址
                            code = a.GetAttribute("href").Split('_')[2];
                           
                        }
                        CompNameListDatas.Add(code+ " " +name);
                    }
                }
                if(CompNameListDatas.Count>0)
                    CompNameUploadRunning = true;
            }
            catch (Exception) { }
            MessageTBX.Text = "成功结束检索数据抓取<<<<<<";
        }
        Boolean loadCompanyRunning = false;
        Boolean loadCompListRunning = false;   
        /// <summary>
        /// 获得企业基本信息
        /// </summary>
        /// <param name="webBrowser1"></param>
        private void loadCompany(WebBrowser webBrowser1)
        {
            try
            {
                HtmlElement body = CompanyWB.Document.Body;

                if (body.InnerHtml == null)
                {
                }
                else
                {
                    File.WriteAllText("TMP", body.InnerHtml);
                    CompanyUploadWBRunning = true;
                }
            }
            catch (Exception) { }
            MessageTBX.Text = "成功结束企业数据抓取<<<<<<";
        }
        List<String> CompNameListDatas = new List<String>();
        List<String> CompanyListDatas = new List<String>();
       
        Boolean CompanyUploadWBRunning = false;
        Boolean CompNameUploadRunning = false;       

        private void CompNameUploadWB_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            try
            {
                String[] html = CompNameListDatas[0].Split(' ');
                CompNameUploadWB.Document.GetElementById("code").SetAttribute("value", html[0]);
                CompNameUploadWB.Document.GetElementById("name").SetAttribute("value", html[1]);
                CompNameUploadWB.Document.GetElementById("Upload").InvokeMember("Click");
                CompNameListDatas.RemoveAt(0);
                CompNameUploadRunning = true;
            }
            catch (Exception)
            {
            }
        }

        /// <summary>
        /// 企业数据提交
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void CompanyUploadWB_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            try
            {
                String filename = CompanyListDatas[0];
                CompanyUploadWB.Document.GetElementById("fileName").SetAttribute("value", filename);
                CompanyUploadWB.Document.GetElementById("file").SetAttribute("value", File.ReadAllText("TMP"));
                CompanyUploadWB.Document.GetElementById("Upload").InvokeMember("Click");
                CompanyListDatas.RemoveAt(0);
                CompanyUploadWBRunning = true;
            }
            catch (Exception)
            {
            }
        }

        private void KeyWordConfigWB_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            //加载参数
            String config = KeyWordConfigWB.Document.Body.InnerText;
            //TASK_INVEL=5000, COMP_KEY=晖棋
            foreach (String item in config.Split(','))
            {
                String[] cs = item.Split('=');
                configs[cs[0].Trim()] = cs[1].Trim();
            }
            String[] tk = ((String)configs["TASK_INVEL"]).Split(';');

            KeyWordSearchTimer_Interval = Convert.ToInt32(tk[0]);
            CompanyTimer_Interval = Convert.ToInt32(tk[1]);

            //保存数据
            loadCompListRunning = true;
        }

        private void CompanyConfigWB_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            //加载参数
            String config = CompanyConfigWB.Document.Body.InnerText;
            //TASK_INVEL=5000, COMP_URL=123124 丁云婴幼服装公司
            foreach (String item in config.Split(','))
            {
                String[] cs = item.Split('=');
                configs[cs[0].Trim()] = cs[1].Trim();
            }
            String[] tk = ((String)configs["TASK_INVEL"]).Split(';');
            KeyWordSearchTimer_Interval = Convert.ToInt32(tk[0]);
            CompanyTimer_Interval = Convert.ToInt32(tk[1]);

            loadCompanyRunning = true;           
        }
    }
}
