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
using System.Threading;
using System.Diagnostics;

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
            this.Text = this.Text + "  ver 0.3.3 in " + Application.StartupPath;

            TargetKeyTBX.Text = "QCC";

            CompanyConfigWB.Navigate(ConfigURL.Text + "/N/" + TargetKeyTBX.Text);

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
        
        private void CompanyWB_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            //企业信息
            loadCompany(CompanyWB);
            CompanyConfigWB.Navigate(ConfigURL.Text + "/N/" + TargetKeyTBX.Text);
        }

        Boolean loadCompanyRunning = false;

        /// <summary>
        /// 获得企业基本信息
        /// </summary>
        /// <param name="webBrowser1"></param>
        private void loadCompany(WebBrowser webBrowser1)
        {
            try
            {
                HtmlElement body = CompanyWB.Document.Body;

                {
                    String path = Application.StartupPath + "/" + DateTime.Today.ToString("yyyyMMdd");
                    if (Directory.Exists(path) == false)
                        Directory.CreateDirectory(path);
                    String filename = CompanyListDatas[0];
                    File.WriteAllText(path + "/" + filename, body.InnerHtml);
                    CompanyListDatas.RemoveAt(0);
                    CompanyUploadWBRunning = true;
                    //CompanyUploadWBRunning = true;
                }
                if (DateTime.Now.Minute == 1 && DateTime.Now.Second > 10)
                {
                    Thread.Sleep(1000 * 60);
                    StartApp();
                }
            }
            catch (Exception) {
            }
            MessageTBX.Text = "成功结束企业数据抓取<<<<<<";
        }

        List<String> CompanyListDatas = new List<String>();
       
        Boolean CompanyUploadWBRunning = false;

        public void StartApp() {
            
            Process myProcess = new Process();
            try
            {
                myProcess.StartInfo.UseShellExecute = false;
                myProcess.StartInfo.FileName = Application.ExecutablePath;
                myProcess.StartInfo.CreateNoWindow = true;
                myProcess.Start();
                Application.Exit();
            }
            catch (Exception ex)
            {
                Application.Exit();
            }
        }

        private void CompanyConfigWB_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            HtmlElement body = CompanyConfigWB.Document.Body;

             if (body.InnerHtml == null)
             {
             }
             else
             {
                 try
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

                     loadCompany(CompanyWB);

                     MessageTBX.Text = "准备新的企业数据抓取>>>>>>";
                     //抓取数据
                     if (String.IsNullOrEmpty((String)configs["COMP_URL"]) == false)
                     {
                         String[] html = ((String)configs["COMP_URL"]).Split(' ');
                         //CompanyListDatas.Clear();
                         CompanyListDatas.Add(html[0] + "_" + html[1]);
                         if (String.IsNullOrEmpty(html[1]) == true)
                         {
                             CompanyConfigWB.Navigate(ConfigURL.Text + "/N/" + TargetKeyTBX.Text);
                         }
                         else
                         {
                             CompanyWB.Navigate("http://qichacha.com/company_base?unique=" + html[0] + "&companyname=" + EncodeTools.UrlEncode(html[1]));
                         }
                         configs.Remove("COMP_URL");
                     }
                 }
                 catch (Exception)
                 {
                     StartApp();
                     //CompanyConfigWB.Navigate(ConfigURL.Text + "/N/" + TargetKeyTBX.Text);
                 }
             }
        }
        List<String> CompNameListDatas = new List<String>();
        private void RunBTN_Click(object sender, EventArgs e)
        {
            //加载文件文件
            String[] vs = File.ReadAllLines(comboBox1.SelectedItem.ToString());
            CompNameListDatas.AddRange(vs);
            vs = null;
            textBox2.Text = "准备上传新的企业数据抓取>>>>>>";
            CompNameUploadRunning = false;
            CompNameUploadTM.Start();
            
        }

        private void ChooseBTN_Click(object sender, EventArgs e)
        {
            folderBrowserDialog1.ShowDialog();
            if (String.IsNullOrEmpty(folderBrowserDialog1.SelectedPath) == false && Directory.Exists(folderBrowserDialog1.SelectedPath) == true)
            {
                comboBox1.Items.Clear();
                foreach (var item in Directory.GetFiles(folderBrowserDialog1.SelectedPath))
                {
                    if (((String)item).IndexOf("CompanyList") > 0)
                        comboBox1.Items.Add(item);
                }
                if (comboBox1.Items.Count > 0)
                {
                    comboBox1.SelectedIndex = 0;
                }
                else
                {
                    MessageBox.Show("该目录下面没有可用文件！");
                }
            }
            else
            {
                MessageBox.Show("请选择路径");
            }
        }
    }
}
