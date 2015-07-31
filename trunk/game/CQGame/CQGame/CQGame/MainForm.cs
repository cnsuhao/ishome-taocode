using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace CQGame
{
    public partial class MainForm : Form
    {
        public MainForm()
        {
            InitializeComponent();
        }

        private void StartBrowseBTN_Click(object sender, EventArgs e)
        {
            System.Windows.Forms.TabPage tabPage = new System.Windows.Forms.TabPage();
            System.Windows.Forms.WebBrowser webBrowser = new System.Windows.Forms.WebBrowser();

            GameTC.Controls.Add(tabPage);
            // 
            // tabPage
            // 
            tabPage.Controls.Add(webBrowser);
            tabPage.Location = new System.Drawing.Point(4, 22);
            tabPage.Name = "tabPage";
            tabPage.Padding = new System.Windows.Forms.Padding(3);
            tabPage.Size = new System.Drawing.Size(814, 275);
            tabPage.TabIndex = 1;
            tabPage.Text = "" + GameTC.Controls.Count;
            tabPage.UseVisualStyleBackColor = true;
            // 
            // webBrowser
            // 
            webBrowser.Dock = System.Windows.Forms.DockStyle.Fill;
            webBrowser.Location = new System.Drawing.Point(3, 3);
            webBrowser.MinimumSize = new System.Drawing.Size(20, 20);
            webBrowser.Name = "" + GameTC.Controls.Count;
            webBrowser.Size = new System.Drawing.Size(808, 269);
            webBrowser.TabIndex = 0;
            webBrowser.Url = new System.Uri(GamePath.Text, System.UriKind.Absolute);
            webBrowser.NewWindow += new System.ComponentModel.CancelEventHandler(this.webBrowser_NewWindow);
        }

        private void CloseLastBTN_Click(object sender, EventArgs e)
        {
            if (GameTC.Controls.Count!=1)
            GameTC.Controls.RemoveAt(GameTC.Controls.Count-1);
        }

        private void webBrowser_NewWindow(object sender, CancelEventArgs e)
        {
            ((WebBrowser)sender).Url = new System.Uri(GamePath.Text, System.UriKind.Absolute);
            e.Cancel = true;
        }

        private void notifyIcon1_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            this.Visible = true;
            notifyIcon1.Visible = false;
        }

        private void MainForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (exit == false)
            {
                this.Visible = false;
                e.Cancel = true;
                notifyIcon1.Visible = true;
                notifyIcon1.ShowBalloonTip(1000, "亲", "我在这里喝点水~", ToolTipIcon.Info);
            }
        }
        Boolean exit = false;
        private void ExitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            exit = true;
            Application.Exit();
        }

        private void MainForm_Load(object sender, EventArgs e)
        {
            notifyIcon1.Visible = false;
        }
    }
}
