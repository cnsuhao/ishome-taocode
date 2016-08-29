namespace WindowsFormsApplication11
{
    partial class Form1
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
            this.RunBTN = new System.Windows.Forms.Button();
            this.textBox2 = new System.Windows.Forms.TextBox();
            this.StopBTN = new System.Windows.Forms.Button();
            this.ChooseBTN = new System.Windows.Forms.Button();
            this.comboBox1 = new System.Windows.Forms.ComboBox();
            this.folderBrowserDialog1 = new System.Windows.Forms.FolderBrowserDialog();
            this.CompNameUploadTM = new System.Windows.Forms.Timer(this.components);
            this.CompanyWB = new System.Windows.Forms.WebBrowser();
            this.tabControl1 = new System.Windows.Forms.TabControl();
            this.tabPage1 = new System.Windows.Forms.TabPage();
            this.label1 = new System.Windows.Forms.Label();
            this.tabPage2 = new System.Windows.Forms.TabPage();
            this.NextBTN = new System.Windows.Forms.Button();
            this.tabControl1.SuspendLayout();
            this.tabPage1.SuspendLayout();
            this.tabPage2.SuspendLayout();
            this.SuspendLayout();
            // 
            // RunBTN
            // 
            this.RunBTN.Location = new System.Drawing.Point(95, 135);
            this.RunBTN.Name = "RunBTN";
            this.RunBTN.Size = new System.Drawing.Size(58, 23);
            this.RunBTN.TabIndex = 2;
            this.RunBTN.Text = "运行";
            this.RunBTN.UseVisualStyleBackColor = true;
            this.RunBTN.Click += new System.EventHandler(this.RunBTN_Click);
            // 
            // textBox2
            // 
            this.textBox2.Location = new System.Drawing.Point(6, 56);
            this.textBox2.Name = "textBox2";
            this.textBox2.Size = new System.Drawing.Size(503, 21);
            this.textBox2.TabIndex = 3;
            // 
            // StopBTN
            // 
            this.StopBTN.Location = new System.Drawing.Point(227, 135);
            this.StopBTN.Name = "StopBTN";
            this.StopBTN.Size = new System.Drawing.Size(58, 23);
            this.StopBTN.TabIndex = 4;
            this.StopBTN.Text = "停止";
            this.StopBTN.UseVisualStyleBackColor = true;
            this.StopBTN.Click += new System.EventHandler(this.StopBTN_Click);
            // 
            // ChooseBTN
            // 
            this.ChooseBTN.Location = new System.Drawing.Point(451, 15);
            this.ChooseBTN.Name = "ChooseBTN";
            this.ChooseBTN.Size = new System.Drawing.Size(58, 23);
            this.ChooseBTN.TabIndex = 1;
            this.ChooseBTN.Text = "选择";
            this.ChooseBTN.UseVisualStyleBackColor = true;
            this.ChooseBTN.Click += new System.EventHandler(this.ChooseBTN_Click);
            // 
            // comboBox1
            // 
            this.comboBox1.FormattingEnabled = true;
            this.comboBox1.Location = new System.Drawing.Point(6, 17);
            this.comboBox1.Name = "comboBox1";
            this.comboBox1.Size = new System.Drawing.Size(439, 20);
            this.comboBox1.TabIndex = 5;
            // 
            // CompNameUploadTM
            // 
            this.CompNameUploadTM.Enabled = true;
            this.CompNameUploadTM.Interval = 200;
            this.CompNameUploadTM.Tick += new System.EventHandler(this.CompNameUploadTM_Tick);
            // 
            // CompanyWB
            // 
            this.CompanyWB.Dock = System.Windows.Forms.DockStyle.Fill;
            this.CompanyWB.Location = new System.Drawing.Point(3, 3);
            this.CompanyWB.MinimumSize = new System.Drawing.Size(20, 20);
            this.CompanyWB.Name = "CompanyWB";
            this.CompanyWB.ScriptErrorsSuppressed = true;
            this.CompanyWB.Size = new System.Drawing.Size(514, 179);
            this.CompanyWB.TabIndex = 6;
            this.CompanyWB.DocumentCompleted += new System.Windows.Forms.WebBrowserDocumentCompletedEventHandler(this.CompanyWB_DocumentCompleted_1);
            // 
            // tabControl1
            // 
            this.tabControl1.Controls.Add(this.tabPage1);
            this.tabControl1.Controls.Add(this.tabPage2);
            this.tabControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabControl1.Location = new System.Drawing.Point(0, 0);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedIndex = 0;
            this.tabControl1.Size = new System.Drawing.Size(528, 211);
            this.tabControl1.TabIndex = 7;
            // 
            // tabPage1
            // 
            this.tabPage1.Controls.Add(this.NextBTN);
            this.tabPage1.Controls.Add(this.label1);
            this.tabPage1.Controls.Add(this.comboBox1);
            this.tabPage1.Controls.Add(this.ChooseBTN);
            this.tabPage1.Controls.Add(this.StopBTN);
            this.tabPage1.Controls.Add(this.RunBTN);
            this.tabPage1.Controls.Add(this.textBox2);
            this.tabPage1.Location = new System.Drawing.Point(4, 22);
            this.tabPage1.Name = "tabPage1";
            this.tabPage1.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage1.Size = new System.Drawing.Size(520, 185);
            this.tabPage1.TabIndex = 0;
            this.tabPage1.Text = "运行参数";
            this.tabPage1.UseVisualStyleBackColor = true;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(159, 111);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(197, 12);
            this.label1.TabIndex = 8;
            this.label1.Text = "七域互联技术服务  (www.7Yui.com)";
            // 
            // tabPage2
            // 
            this.tabPage2.Controls.Add(this.CompanyWB);
            this.tabPage2.Location = new System.Drawing.Point(4, 22);
            this.tabPage2.Name = "tabPage2";
            this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
            this.tabPage2.Size = new System.Drawing.Size(520, 185);
            this.tabPage2.TabIndex = 1;
            this.tabPage2.Text = "企业信息";
            this.tabPage2.UseVisualStyleBackColor = true;
            // 
            // NextBTN
            // 
            this.NextBTN.Location = new System.Drawing.Point(357, 134);
            this.NextBTN.Name = "NextBTN";
            this.NextBTN.Size = new System.Drawing.Size(58, 23);
            this.NextBTN.TabIndex = 9;
            this.NextBTN.Text = "略过";
            this.NextBTN.UseVisualStyleBackColor = true;
            this.NextBTN.Click += new System.EventHandler(this.NextBTN_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(528, 211);
            this.Controls.Add(this.tabControl1);
            this.Name = "Form1";
            this.Text = "网页数据抓取";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.tabControl1.ResumeLayout(false);
            this.tabPage1.ResumeLayout(false);
            this.tabPage1.PerformLayout();
            this.tabPage2.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button StopBTN;
        private System.Windows.Forms.TextBox textBox2;
        private System.Windows.Forms.Button RunBTN;
        private System.Windows.Forms.Button ChooseBTN;
        private System.Windows.Forms.ComboBox comboBox1;
        private System.Windows.Forms.FolderBrowserDialog folderBrowserDialog1;
        private System.Windows.Forms.Timer CompNameUploadTM;
        private System.Windows.Forms.WebBrowser CompanyWB;
        private System.Windows.Forms.TabControl tabControl1;
        private System.Windows.Forms.TabPage tabPage1;
        private System.Windows.Forms.TabPage tabPage2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button NextBTN;
    }
}

