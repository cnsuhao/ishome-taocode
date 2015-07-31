namespace CQGame
{
    partial class MainForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.CloseLastBTN = new System.Windows.Forms.Button();
            this.StartBrowseBTN = new System.Windows.Forms.Button();
            this.GamePath = new System.Windows.Forms.TextBox();
            this.GameTC = new System.Windows.Forms.TabControl();
            this.notifyIcon1 = new System.Windows.Forms.NotifyIcon(this.components);
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.ExitToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.tabPage1.SuspendLayout();
            this.GameTC.SuspendLayout();
            this.contextMenuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabPage1
            // 
            this.tabPage1.Controls.Add(this.CloseLastBTN);
            this.tabPage1.Controls.Add(this.StartBrowseBTN);
            this.tabPage1.Controls.Add(this.GamePath);
            this.tabPage1.Location = new System.Drawing.Point(4, 22);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(814, 275);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "主页";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // CloseLastBTN
            // 
            this.CloseLastBTN.Location = new System.Drawing.Point(636, 103);
            this.CloseLastBTN.Name = "CloseLastBTN";
            this.CloseLastBTN.Size = new System.Drawing.Size(143, 35);
            this.CloseLastBTN.TabIndex = 3;
            this.CloseLastBTN.Text = "关闭最后一个";
            this.CloseLastBTN.UseVisualStyleBackColor = true;
            this.CloseLastBTN.Click += new System.EventHandler(this.CloseLastBTN_Click);
            // 
            // StartBrowseBTN
            // 
            this.StartBrowseBTN.Location = new System.Drawing.Point(57, 103);
            this.StartBrowseBTN.Name = "StartBrowseBTN";
            this.StartBrowseBTN.Size = new System.Drawing.Size(143, 35);
            this.StartBrowseBTN.TabIndex = 2;
            this.StartBrowseBTN.Text = "开始";
            this.StartBrowseBTN.UseVisualStyleBackColor = true;
            this.StartBrowseBTN.Click += new System.EventHandler(this.StartBrowseBTN_Click);
            // 
            // GamePath
            // 
            this.GamePath.Location = new System.Drawing.Point(57, 36);
            this.GamePath.Name = "GamePath";
            this.GamePath.Size = new System.Drawing.Size(722, 21);
            this.GamePath.TabIndex = 1;
            this.GamePath.Text = "http://my.qzone.qq.com/app/1104216374.html?via=PORTALSTORE.XX.SIMPLE-HOME-USERINF" +
                "O-APPS-USED.SEQ1";
            // 
            // GameTC
            // 
            this.GameTC.Controls.Add(this.tabPage1);
            this.GameTC.Dock = System.Windows.Forms.DockStyle.Fill;
            this.GameTC.Location = new System.Drawing.Point(0, 0);
            this.GameTC.Name = "GameTC";
            this.GameTC.SelectedIndex = 0;
            this.GameTC.Size = new System.Drawing.Size(822, 301);
            this.GameTC.TabIndex = 0;
            // 
            // notifyIcon1
            // 
            this.notifyIcon1.ContextMenuStrip = this.contextMenuStrip1;
            this.notifyIcon1.Icon = ((System.Drawing.Icon)(resources.GetObject("notifyIcon1.Icon")));
            this.notifyIcon1.MouseDoubleClick += new System.Windows.Forms.MouseEventHandler(this.notifyIcon1_MouseDoubleClick);
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ExitToolStripMenuItem});
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(101, 26);
            // 
            // ExitToolStripMenuItem
            // 
            this.ExitToolStripMenuItem.Name = "ExitToolStripMenuItem";
            this.ExitToolStripMenuItem.Size = new System.Drawing.Size(100, 22);
            this.ExitToolStripMenuItem.Text = "退出";
            this.ExitToolStripMenuItem.Click += new System.EventHandler(this.ExitToolStripMenuItem_Click);
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(822, 301);
            this.Controls.Add(this.GameTC);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "MainForm";
            this.Text = "多开浏览器";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.MainForm_FormClosing);
            this.Load += new System.EventHandler(this.MainForm_Load);
            this.tabPage1.ResumeLayout(false);
            this.tabPage1.PerformLayout();
            this.GameTC.ResumeLayout(false);
            this.contextMenuStrip1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.Button CloseLastBTN;
        private System.Windows.Forms.Button StartBrowseBTN;
        private System.Windows.Forms.TextBox GamePath;
        private System.Windows.Forms.TabControl GameTC;
        private System.Windows.Forms.NotifyIcon notifyIcon1;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.ToolStripMenuItem ExitToolStripMenuItem;


    }
}

