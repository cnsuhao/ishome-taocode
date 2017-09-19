using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Diagnostics;
using System.Threading;
using System.IO;
using System.Collections;

namespace WindowsFormsApplication5
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            RunningFlag = false;
            InitializeComponent();
        }

        int[] nums = new int[6];

        int num = 0;
        private void timer1_Tick(object sender, EventArgs e)
        {
            MessageTBX.Text = "监控时间：" + num;
            if ((num ==0 && RunningFlag == true) || ( num > Convert.ToInt32(TimerCBX.Text)))
            {
                //关闭所有运行程序
                KillALL();

                //启动新的程序
                StartAll();

                //启动计数器
                num = 1;
            }
            else if (RunningFlag == true)
            {
                num = num + 1;

                try
                {
                    //文件数目监控
                    for (int i = 1; i < 6; i++)
                    {
                        String path = Application.StartupPath + @"\" + PathTBX.Text + i + @"\" + DateTime.Today.ToString("yyyyMMdd");
                        if (nums[i] != Directory.GetFiles(path).Length)
                        {
                            nums[i] = Directory.GetFiles(path).Length;
                        }
                        else
                        {
                            KillALL();
                            StartAll();
                            //启动计数器
                            num = 1;
                        }
                    }
                    FilesTBX.Text = nums[1] + ":" + nums[2] + ":" + nums[3] + ":" + nums[4] + ":" + nums[5];
                }
                catch (Exception)
                {
                }
            }
        }

        private void StartAll()
        {
            for (int i = 1; i < 6; i++)
            {
                Process myProcess = new Process();
                try
                {
                    myProcess.StartInfo.UseShellExecute = false;
                    myProcess.StartInfo.FileName = Application.StartupPath + @"\" + PathTBX.Text + i + @"\" + RunTBX.Text;
                    myProcess.StartInfo.CreateNoWindow = true;
                    myProcess.Start();
                    nums[i] = 1;
                }
                catch (Exception)
                {
                }
            }

        }

        public void KillALL() 
        {
            Process[] ps = Process.GetProcesses();
            foreach (Process item in ps)
            {
                try
                {
                    if (RunTBX.Text.IndexOf(item.ProcessName) >= 0)
                    {
                        item.Kill();
                    }
                }
                catch (Exception)
                {
                }
            }
        }

        private void RunBTN_Click(object sender, EventArgs e)
        {
            num = 0;
            RunningFlag = true;
        }
        Boolean RunningFlag = false;
        private void StopBTN_Click(object sender, EventArgs e)
        {
            num = 1;
            RunningFlag = false;
            KillALL();
        }

        Hashtable configs = new Hashtable();
        private void Form1_Load(object sender, EventArgs e)
        {
            try
            {
                String[] config = File.ReadAllLines(Application.StartupPath + @"\Config.ini");

                foreach (String item in config)
                {
                    String[] cs = item.Split('=');
                    configs[cs[0].Trim()] = cs[1].Trim();
                }

                TimerCBX.Text = (String)configs["TimerCBX"];
                PathTBX.Text = (String)configs["PathTBX"];
                RunTBX.Text = (String)configs["RunTBX"];
            }
            catch (Exception)
            {
            }
        }

        private void Form1_FormClosed(object sender, FormClosedEventArgs e)
        {
            try
            {
                if (File.Exists(Application.StartupPath + @"\Config.ini"))
                    File.Delete(Application.StartupPath + @"\Config.ini");
                File.AppendAllText(Application.StartupPath + @"\Config.ini", "TimerCBX=" + TimerCBX.Text);
                File.AppendAllText(Application.StartupPath + @"\Config.ini", "\r\nPathTBX=" + PathTBX.Text);
                File.AppendAllText(Application.StartupPath + @"\Config.ini", "\r\nRunTBX=" + RunTBX.Text);
            }
            catch (Exception)
            {
            }
        }
    }
}
