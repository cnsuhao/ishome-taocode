using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;
using System.Diagnostics;

namespace AEK_SQLFiles
{
    public partial class DatabaseConf : Form
    {
        public DatabaseConf()
        {
            InitializeComponent();
        }

        private void DatabaseConf_Load(object sender, EventArgs e)
        {
            this.Text = this.Text + " Ver 0.2";
        }

        private void FirstRB_CheckedChanged(object sender, EventArgs e)
        {
            for (int i = 0; i < FileView.Items.Count; i++)
            {
                if (SQLFiles[i].Name.ElementAt(1).ToString().Equals("0"))
                    FileView.SetItemChecked(i, true);
                else
                    FileView.SetItemChecked(i, false);
            }
        }

        private void SelectRB_CheckedChanged(object sender, EventArgs e)
        {
            for (int i = 0; i < FileView.Items.Count; i++)
            {
                FileView.SetItemChecked(i, false);
            }
        }

        private void AllRB_CheckedChanged(object sender, EventArgs e)
        {
            for (int i = 0; i < FileView.Items.Count; i++)
            {
                FileView.SetItemChecked(i, true);
            }
        }

        private void dbStartBTN_Click(object sender, EventArgs e)
        {
            #region TNS配置
            
            String FileName = oraCli.Text + @"\network\admin\tnsnames.ora";
            if (!Directory.Exists(oraCli.Text) && !File.Exists(FileName))
            {
                MessageBox.Show(this, "Oracle客户端不存在!", "警告", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }
            StringBuilder sb = new StringBuilder();
            if (AutoCBX.Checked == false)
            {
                try
                {
                    sb.Append(dbtns.Text + "=\r\n");
                    sb.Append("  (DESCRIPTION=\r\n");
                    sb.Append("    (ADDRESS=\r\n");
                    sb.Append("      (PROTOCOL=TCP)\r\n");
                    sb.Append("      (HOST=" + dbhost.Text + ")\r\n");
                    sb.Append("      (PORT=1521)\r\n");
                    sb.Append("    )\r\n");
                    sb.Append("    (CONNECT_DATA=\r\n");
                    sb.Append("      (SERVER=dedicated)\r\n");
                    sb.Append("      (SERVICE_NAME=" + dbtns.Text + ")\r\n");
                    sb.Append("    )\r\n");
                    sb.Append("  )");
                }
                catch (Exception)
                {
                    MessageBox.Show(this, "网站TNS连接配置失败，请手动修改文件【tnsnames.ora】", "警告", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                }
                File.WriteAllText(FileName, sb.ToString());
            }
            #endregion

            #region SQLRun.bat制作
            String fileName = sqlPath.Text + @"\SQLRun.bat";
            String sqlplus = "sqlplus.exe";
            String oracleTNS = dbuser.Text + "/" + dbpass.Text + "@" + dbtns.Text;
            sb = new StringBuilder();
            sb.Append("PATH=" + oraCli.Text + "\\bin\r\n");
            for (int i = 0; i < FileView.Items.Count; i++)
            {
                if (FileView.GetItemChecked(i))
                {
                    sb.Append(sqlplus + " " + oracleTNS + " @" + SQLFiles[i].FullName + "\r\n");
                }
            }

            sb.Append("pause\r\n");
            sb.Append("EXIT\r\n");

            File.WriteAllText(fileName, sb.ToString());
            DoDosCMD(fileName);
            Process.Start(sqlPath.Text);
            #endregion
        }

        private void OraCliPathSelectBTN_Click(object sender, EventArgs e)
        {
            A2cwFBD.ShowDialog();
            if (!String.IsNullOrEmpty(A2cwFBD.SelectedPath))
                oraCli.Text = A2cwFBD.SelectedPath;
        }

        FileInfo[] SQLFiles;
        private void SQLPathSelectBTN_Click(object sender, EventArgs e)
        {
            A2cwFBD.ShowDialog();
            if (!String.IsNullOrEmpty(A2cwFBD.SelectedPath))
            {
                sqlPath.Text = A2cwFBD.SelectedPath;
                try
                {
                    String[] files = Directory.GetFiles(A2cwFBD.SelectedPath);
                    SQLFiles = new FileInfo[files.Length];
                    for (int i = 0; i < files.Length; i++)
                    {
                        SQLFiles[i] = new FileInfo(files[i]);
                        FileView.Items.Add(SQLFiles[i].Name);
                    }
                }
                catch (Exception)
                {
                    MessageBox.Show(this, "文件路径读取异常!", "异常", MessageBoxButtons.OK, MessageBoxIcon.Error);               
                }
            }
        }

        public void LogWrite(String text)
        {
            try
            {
                //yyyy年MM月d日HH小时mm分ss秒
                String fileName = sqlPath.Text + "\\" + DateTime.Now.ToString("yyyyMMdd") + ".txt";
                StreamWriter logFile = new StreamWriter(fileName, true);
                logFile.WriteLine("[" + DateTime.Now.ToString("yyyy/MM/dd HH:mm:ss") + "]" + text);
                logFile.Close();
            }
            catch (Exception)
            {
            }
        }

        public void DoDosCMD(String DosCommandFile)
        {
            try
            {
                //StreamReader sr = DosCommandFile.OpenText();
                Process p = null;
                p = new Process();
                p.StartInfo.FileName = DosCommandFile;
                //p.StartInfo.UseShellExecute = false;
                //p.StartInfo.RedirectStandardInput = true;
                //p.StartInfo.RedirectStandardOutput = true;
                //p.StartInfo.RedirectStandardError = true;
                //p.StartInfo.CreateNoWindow = true;
                p.Start();
                //p.StandardInput.WriteLine(sr.ReadToEnd());
                //p.StandardInput.WriteLine("exit");
                //while (p.StandardOutput.EndOfStream)
                // {
                //      String strOutput = p.StandardOutput.ReadLine();
                //      Console.WriteLine(strOutput);
                // }
                p.WaitForExit();
                p.Close();
                //p.CloseMainWindow();
            }
            catch (Exception)
            {

            }
        }
        
    }
}
