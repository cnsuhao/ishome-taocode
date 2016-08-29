namespace WindowsFormsApplication3
{
    partial class Qcc
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Qcc));
            this.MessageTBX = new System.Windows.Forms.TextBox();
            this.KeyWordSearchWB = new System.Windows.Forms.WebBrowser();
            this.notifyIcon1 = new System.Windows.Forms.NotifyIcon(this.components);
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.exitToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.showToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.TimerTBX = new System.Windows.Forms.TextBox();
            this.TargetKeyTBX = new System.Windows.Forms.TextBox();
            this.panel3 = new System.Windows.Forms.Panel();
            this.CompanyConfigWB = new System.Windows.Forms.WebBrowser();
            this.panel2 = new System.Windows.Forms.Panel();
            this.KeyWordConfigWB = new System.Windows.Forms.WebBrowser();
            this.panel1 = new System.Windows.Forms.Panel();
            this.label1 = new System.Windows.Forms.Label();
            this.ConfigURL = new System.Windows.Forms.TextBox();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.tabPage4 = new System.Windows.Forms.TabPage();
            this.CompanyWB = new System.Windows.Forms.WebBrowser();
            this.tabPage3 = new System.Windows.Forms.TabPage();
            this.CompanyUploadWB = new System.Windows.Forms.WebBrowser();
            this.tabPage5 = new System.Windows.Forms.TabPage();
            this.CompNameUploadWB = new System.Windows.Forms.WebBrowser();
            this.ConfigTimer = new System.Windows.Forms.Timer(this.components);
            this.RunNumerCBX = new System.Windows.Forms.ComboBox();
            this.contextMenuStrip1.SuspendLayout();
            this.tabControl1.SuspendLayout();
            this.tabPage1.SuspendLayout();
            this.panel3.SuspendLayout();
            this.panel2.SuspendLayout();
            this.panel1.SuspendLayout();
            this.tabPage2.SuspendLayout();
            this.tabPage4.SuspendLayout();
            this.tabPage3.SuspendLayout();
            this.tabPage5.SuspendLayout();
            this.SuspendLayout();
            // 
            // MessageTBX
            // 
            this.MessageTBX.Location = new System.Drawing.Point(10, 358);
            this.MessageTBX.Name = "MessageTBX";
            this.MessageTBX.Size = new System.Drawing.Size(545, 21);
            this.MessageTBX.TabIndex = 1;
            // 
            // KeyWordSearchWB
            // 
            this.KeyWordSearchWB.Dock = System.Windows.Forms.DockStyle.Fill;
            this.KeyWordSearchWB.Location = new System.Drawing.Point(3, 3);
            this.KeyWordSearchWB.MinimumSize = new System.Drawing.Size(20, 20);
            this.KeyWordSearchWB.Name = "KeyWordSearchWB";
            this.KeyWordSearchWB.ScriptErrorsSuppressed = true;
            this.KeyWordSearchWB.Size = new System.Drawing.Size(555, 381);
            this.KeyWordSearchWB.TabIndex = 0;
            this.KeyWordSearchWB.Url = new System.Uri("http://qichacha.com", System.UriKind.Absolute);
            this.KeyWordSearchWB.DocumentCompleted += new System.Windows.Forms.WebBrowserDocumentCompletedEventHandler(this.KeyWordSearchWB_DocumentCompleted);
            // 
            // notifyIcon1
            // 
            this.notifyIcon1.ContextMenuStrip = this.contextMenuStrip1;
            this.notifyIcon1.Icon = ((System.Drawing.Icon)(resources.GetObject("notifyIcon1.Icon")));
            this.notifyIcon1.Text = "来自家园的分享";
            this.notifyIcon1.Visible = true;
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.exitToolStripMenuItem,
            this.showToolStripMenuItem});
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(108, 48);
            // 
            // exitToolStripMenuItem
            // 
            this.exitToolStripMenuItem.Name = "exitToolStripMenuItem";
            this.exitToolStripMenuItem.Size = new System.Drawing.Size(107, 22);
            this.exitToolStripMenuItem.Text = "Exit";
            this.exitToolStripMenuItem.Click += new System.EventHandler(this.exitToolStripMenuItem_Click);
            // 
            // showToolStripMenuItem
            // 
            this.showToolStripMenuItem.Name = "showToolStripMenuItem";
            this.showToolStripMenuItem.Size = new System.Drawing.Size(107, 22);
            this.showToolStripMenuItem.Text = "Show";
            this.showToolStripMenuItem.Click += new System.EventHandler(this.showToolStripMenuItem_Click);
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.tabPage1);
            this.tabControl1.Controls.Add(this.tabPage2);
            this.tabControl1.Controls.Add(this.tabPage4);
            this.tabControl1.Controls.Add(this.tabPage3);
            this.tabControl1.Controls.Add(this.tabPage5);
            this.tabControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabControl1.Location = new System.Drawing.Point(0, 0);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(569, 413);
            this.tabControl1.TabIndex = 5;
            // 
            // tabPage1
            // 
            this.tabPage1.Controls.Add(this.RunNumerCBX);
            this.tabPage1.Controls.Add(this.TimerTBX);
            this.tabPage1.Controls.Add(this.TargetKeyTBX);
            this.tabPage1.Controls.Add(this.panel3);
            this.tabPage1.Controls.Add(this.panel2);
            this.tabPage1.Controls.Add(this.panel1);
            this.tabPage1.Controls.Add(this.ConfigURL);
            this.tabPage1.Controls.Add(this.MessageTBX);
            this.tabPage1.Location = new System.Drawing.Point(4, 22);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(561, 387);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "基本信息";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // TimerTBX
            // 
            this.TimerTBX.Location = new System.Drawing.Point(389, 5);
            this.TimerTBX.Name = "TimerTBX";
            this.TimerTBX.ReadOnly = true;
            this.TimerTBX.Size = new System.Drawing.Size(91, 21);
            this.TimerTBX.TabIndex = 8;
            // 
            // TargetKeyTBX
            // 
            this.TargetKeyTBX.Location = new System.Drawing.Point(9, 5);
            this.TargetKeyTBX.Name = "TargetKeyTBX";
            this.TargetKeyTBX.ReadOnly = true;
            this.TargetKeyTBX.Size = new System.Drawing.Size(107, 21);
            this.TargetKeyTBX.TabIndex = 7;
            this.TargetKeyTBX.Text = "QCC";
            // 
            // panel3
            // 
            this.panel3.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel3.Controls.Add(this.CompanyConfigWB);
            this.panel3.Location = new System.Drawing.Point(10, 107);
            this.panel3.Name = "panel3";
            this.panel3.Size = new System.Drawing.Size(545, 55);
            this.panel3.TabIndex = 6;
            // 
            // CompanyConfigWB
            // 
            this.CompanyConfigWB.Dock = System.Windows.Forms.DockStyle.Fill;
            this.CompanyConfigWB.Location = new System.Drawing.Point(0, 0);
            this.CompanyConfigWB.MinimumSize = new System.Drawing.Size(20, 20);
            this.CompanyConfigWB.Name = "CompanyConfigWB";
            this.CompanyConfigWB.ScriptErrorsSuppressed = true;
            this.CompanyConfigWB.Size = new System.Drawing.Size(543, 53);
            this.CompanyConfigWB.TabIndex = 5;
            this.CompanyConfigWB.DocumentCompleted += new System.Windows.Forms.WebBrowserDocumentCompletedEventHandler(this.CompanyConfigWB_DocumentCompleted);
            // 
            // panel2
            // 
            this.panel2.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel2.Controls.Add(this.KeyWordConfigWB);
            this.panel2.Location = new System.Drawing.Point(10, 46);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(545, 55);
            this.panel2.TabIndex = 6;
            // 
            // KeyWordConfigWB
            // 
            this.KeyWordConfigWB.Dock = System.Windows.Forms.DockStyle.Fill;
            this.KeyWordConfigWB.Location = new System.Drawing.Point(0, 0);
            this.KeyWordConfigWB.MinimumSize = new System.Drawing.Size(20, 20);
            this.KeyWordConfigWB.Name = "KeyWordConfigWB";
            this.KeyWordConfigWB.ScriptErrorsSuppressed = true;
            this.KeyWordConfigWB.Size = new System.Drawing.Size(543, 53);
            this.KeyWordConfigWB.TabIndex = 5;
            this.KeyWordConfigWB.DocumentCompleted += new System.Windows.Forms.WebBrowserDocumentCompletedEventHandler(this.KeyWordConfigWB_DocumentCompleted);
            // 
            // panel1
            // 
            this.panel1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel1.Controls.Add(this.label1);
            this.panel1.Location = new System.Drawing.Point(9, 168);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(545, 162);
            this.panel1.TabIndex = 4;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(164, 77);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(197, 12);
            this.label1.TabIndex = 0;
            this.label1.Text = "七域互联技术服务  (www.7Yui.com)";
            // 
            // ConfigURL
            // 
            this.ConfigURL.Location = new System.Drawing.Point(122, 5);
            this.ConfigURL.Name = "ConfigURL";
            this.ConfigURL.ReadOnly = true;
            this.ConfigURL.Size = new System.Drawing.Size(261, 21);
            this.ConfigURL.TabIndex = 1;
            this.ConfigURL.Text = "http://qcctest.wzyrz.cn";
            // 
            // tabPage2
            // 
            this.tabPage2.Controls.Add(this.KeyWordSearchWB);
            this.tabPage2.Location = new System.Drawing.Point(4, 22);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(561, 387);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "词汇检索";
            this.tabPage2.UseVisualStyleBackColor = true;
            // 
            // tabPage4
            // 
            this.tabPage4.Controls.Add(this.CompanyWB);
            this.tabPage4.Location = new System.Drawing.Point(4, 22);
            this.tabPage4.Name = "tabPage4";
            this.tabPage4.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage4.Size = new System.Drawing.Size(561, 387);
            this.tabPage4.TabIndex = 3;
            this.tabPage4.Text = "企业信息";
            this.tabPage4.UseVisualStyleBackColor = true;
            // 
            // CompanyWB
            // 
            this.CompanyWB.Dock = System.Windows.Forms.DockStyle.Fill;
            this.CompanyWB.Location = new System.Drawing.Point(3, 3);
            this.CompanyWB.MinimumSize = new System.Drawing.Size(20, 20);
            this.CompanyWB.Name = "CompanyWB";
            this.CompanyWB.ScriptErrorsSuppressed = true;
            this.CompanyWB.Size = new System.Drawing.Size(555, 381);
            this.CompanyWB.TabIndex = 1;
            this.CompanyWB.DocumentCompleted += new System.Windows.Forms.WebBrowserDocumentCompletedEventHandler(this.CompanyWB_DocumentCompleted);
            // 
            // tabPage3
            // 
            this.tabPage3.Controls.Add(this.CompanyUploadWB);
            this.tabPage3.Location = new System.Drawing.Point(4, 22);
            this.tabPage3.Name = "tabPage3";
            this.tabPage3.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage3.Size = new System.Drawing.Size(561, 387);
            this.tabPage3.TabIndex = 4;
            this.tabPage3.Text = "企业资料数据提交";
            this.tabPage3.UseVisualStyleBackColor = true;
            // 
            // CompanyUploadWB
            // 
            this.CompanyUploadWB.Dock = System.Windows.Forms.DockStyle.Fill;
            this.CompanyUploadWB.Location = new System.Drawing.Point(3, 3);
            this.CompanyUploadWB.MinimumSize = new System.Drawing.Size(20, 20);
            this.CompanyUploadWB.Name = "CompanyUploadWB";
            this.CompanyUploadWB.ScriptErrorsSuppressed = true;
            this.CompanyUploadWB.Size = new System.Drawing.Size(555, 381);
            this.CompanyUploadWB.TabIndex = 0;
            this.CompanyUploadWB.Url = new System.Uri("", System.UriKind.Relative);
            this.CompanyUploadWB.DocumentCompleted += new System.Windows.Forms.WebBrowserDocumentCompletedEventHandler(this.CompanyUploadWB_DocumentCompleted);
            // 
            // tabPage5
            // 
            this.tabPage5.Controls.Add(this.CompNameUploadWB);
            this.tabPage5.Location = new System.Drawing.Point(4, 22);
            this.tabPage5.Name = "tabPage5";
            this.tabPage5.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage5.Size = new System.Drawing.Size(561, 387);
            this.tabPage5.TabIndex = 5;
            this.tabPage5.Text = "企业列表数据提交";
            this.tabPage5.UseVisualStyleBackColor = true;
            // 
            // CompNameUploadWB
            // 
            this.CompNameUploadWB.Dock = System.Windows.Forms.DockStyle.Fill;
            this.CompNameUploadWB.Location = new System.Drawing.Point(3, 3);
            this.CompNameUploadWB.MinimumSize = new System.Drawing.Size(20, 20);
            this.CompNameUploadWB.Name = "CompNameUploadWB";
            this.CompNameUploadWB.ScriptErrorsSuppressed = true;
            this.CompNameUploadWB.Size = new System.Drawing.Size(555, 381);
            this.CompNameUploadWB.TabIndex = 0;
            this.CompNameUploadWB.DocumentCompleted += new System.Windows.Forms.WebBrowserDocumentCompletedEventHandler(this.CompNameUploadWB_DocumentCompleted);
            // 
            // ConfigTimer
            // 
            this.ConfigTimer.Enabled = true;
            this.ConfigTimer.Interval = 1000;
            this.ConfigTimer.Tick += new System.EventHandler(this.ConfigTimer_Tick);
            // 
            // RunNumerCBX
            // 
            this.RunNumerCBX.FormattingEnabled = true;
            this.RunNumerCBX.Items.AddRange(new object[] {
            "30",
            "60",
            "90"});
            this.RunNumerCBX.Location = new System.Drawing.Point(487, 5);
            this.RunNumerCBX.Name = "RunNumerCBX";
            this.RunNumerCBX.Size = new System.Drawing.Size(67, 20);
            this.RunNumerCBX.TabIndex = 9;
            this.RunNumerCBX.Text = "60";
            // 
            // Qcc
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(569, 413);
            this.Controls.Add(this.tabControl1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Qcc";
            this.Text = "网页数据抓取 ver 0.2.1";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Qcc_FormClosing);
            this.Load += new System.EventHandler(this.Form1_Load);
            this.SizeChanged += new System.EventHandler(this.Qcc_SizeChanged);
            this.contextMenuStrip1.ResumeLayout(false);
            this.tabControl1.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.tabPage1.PerformLayout();
            this.panel3.ResumeLayout(false);
            this.panel2.ResumeLayout(false);
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.tabPage2.ResumeLayout(false);
            this.tabPage4.ResumeLayout(false);
            this.tabPage3.ResumeLayout(false);
            this.tabPage5.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TextBox MessageTBX;
        private System.Windows.Forms.WebBrowser KeyWordSearchWB;
        private System.Windows.Forms.NotifyIcon notifyIcon1;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.ToolStripMenuItem exitToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem showToolStripMenuItem;
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
        private System.Windows.Forms.Panel panel2;
        private System.Windows.Forms.WebBrowser KeyWordConfigWB;
        private System.Windows.Forms.Timer ConfigTimer;
        private System.Windows.Forms.TabPage tabPage4;
        private System.Windows.Forms.WebBrowser CompanyWB;
        private System.Windows.Forms.TabPage tabPage3;
        private System.Windows.Forms.WebBrowser CompanyUploadWB;
        private System.Windows.Forms.TabPage tabPage5;
        private System.Windows.Forms.WebBrowser CompNameUploadWB;
        private System.Windows.Forms.Panel panel3;
        private System.Windows.Forms.WebBrowser CompanyConfigWB;
        private System.Windows.Forms.TextBox ConfigURL;
        private System.Windows.Forms.TextBox TargetKeyTBX;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox TimerTBX;
        private System.Windows.Forms.ComboBox RunNumerCBX;
    }
}

