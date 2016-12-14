using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.IO;

namespace WindowsFormsApplication11
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void ChooseBTN_Click(object sender, EventArgs e)
        {
            folderBrowserDialog1.ShowDialog();
            if (String.IsNullOrEmpty(folderBrowserDialog1.SelectedPath) == false && Directory.Exists(folderBrowserDialog1.SelectedPath) == true)
            {
                comboBox1.Items.Clear();
                foreach (var item in Directory.GetFiles(folderBrowserDialog1.SelectedPath))
                {
                    if(((String)item).IndexOf("CompanyList")>0)
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

        private void RunBTN_Click(object sender, EventArgs e)
        {
            //加载文件文件
            String[] vs = File.ReadAllLines(comboBox1.SelectedItem.ToString());
            CompNameListDatas.AddRange(vs);
            vs = null;
            textBox2.Text = "准备新的企业数据抓取>>>>>>";
            CompNameUploadRunning = false;
            CompNameUploadTM.Start();
            //刷新页面
            LoadCompanyName();
        }

        List<String> CompNameListDatas = new List<String>();
       

        Boolean CompNameUploadRunning = false;
        String CompanyFileName = "";

        public void LoadCompanyName()
        {
            try
            {
                String[] html = CompNameListDatas[0].Split(' ');
                //CompanyListDatas.Clear();
                CompanyFileName = html[0] + "_" + html[1];
                if (String.IsNullOrEmpty(html[1]) == false)
                    CompanyWB.Navigate("http://qichacha.com/company_base?unique=" + html[0] + "&companyname=" + EncodeTools.UrlEncode(html[1]));
            }
            catch (Exception)
            {
                textBox2.Text = "企业数据抓取异常！！！！！！";
            }
        }
        
        private void CompNameUploadTM_Tick(object sender, EventArgs e)
        {
            if (CompNameUploadRunning == true) 
            {
                LoadCompanyName();
                CompNameUploadRunning = false;
            }
        }

        private void StopBTN_Click(object sender, EventArgs e)
        {
            CompNameUploadRunning = false;
            CompNameUploadTM.Stop();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            this.Text = this.Text + "  ver 0.3.6";
            foreach (var item in Directory.GetFiles(Application.StartupPath))
            {
                if (((String)item).IndexOf("CompanyList") > 0)
                    comboBox1.Items.Add(item);
            }

        }

        private void CompanyWB_DocumentCompleted_1(object sender, WebBrowserDocumentCompletedEventArgs e)
        {
            try
            {
                HtmlElement body = CompanyWB.Document.Body;

                {
                    String path = Application.StartupPath + "/" + DateTime.Now.ToString("yyyyMMdd") + DateTime.Now.Hour;
                    if (Directory.Exists(path) == false)
                        Directory.CreateDirectory(path);
                    File.WriteAllText(path + "/" + CompanyFileName, body.InnerHtml);
                }
            }
            catch (Exception)
            {
            }
            CompNameListDatas.RemoveAt(0);
            CompNameUploadRunning = true;
            textBox2.Text = "成功结束企业数据抓取<<<<<<" + CompanyFileName;
        }

        private void NextBTN_Click(object sender, EventArgs e)
        {
            CompNameListDatas.RemoveAt(0);
            CompNameUploadRunning = true;
        }
    }
}
