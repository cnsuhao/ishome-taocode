namespace WindowsFormsApplication5
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
            this.ThreadTM = new System.Windows.Forms.Timer(this.components);
            this.MessageTBX = new System.Windows.Forms.TextBox();
            this.TimerCBX = new System.Windows.Forms.ComboBox();
            this.PathTBX = new System.Windows.Forms.TextBox();
            this.RunTBX = new System.Windows.Forms.TextBox();
            this.StopBTN = new System.Windows.Forms.Button();
            this.RunBTN = new System.Windows.Forms.Button();
            this.FilesTBX = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // ThreadTM
            // 
            this.ThreadTM.Enabled = true;
            this.ThreadTM.Interval = 6000;
            this.ThreadTM.Tick += new System.EventHandler(this.timer1_Tick);
            // 
            // MessageTBX
            // 
            this.MessageTBX.Location = new System.Drawing.Point(12, 35);
            this.MessageTBX.Name = "MessageTBX";
            this.MessageTBX.ReadOnly = true;
            this.MessageTBX.Size = new System.Drawing.Size(291, 21);
            this.MessageTBX.TabIndex = 1;
            this.MessageTBX.Text = "请启动程序......";
            // 
            // TimerCBX
            // 
            this.TimerCBX.FormattingEnabled = true;
            this.TimerCBX.Items.AddRange(new object[] {
            "150",
            "300",
            "450",
            "600"});
            this.TimerCBX.Location = new System.Drawing.Point(12, 8);
            this.TimerCBX.Name = "TimerCBX";
            this.TimerCBX.Size = new System.Drawing.Size(57, 20);
            this.TimerCBX.TabIndex = 2;
            this.TimerCBX.Text = "300";
            // 
            // PathTBX
            // 
            this.PathTBX.Location = new System.Drawing.Point(75, 8);
            this.PathTBX.Name = "PathTBX";
            this.PathTBX.Size = new System.Drawing.Size(228, 21);
            this.PathTBX.TabIndex = 3;
            this.PathTBX.Text = "QCC-0.3.0-Local";
            // 
            // RunTBX
            // 
            this.RunTBX.Location = new System.Drawing.Point(309, 8);
            this.RunTBX.Name = "RunTBX";
            this.RunTBX.Size = new System.Drawing.Size(157, 21);
            this.RunTBX.TabIndex = 4;
            this.RunTBX.Text = "QCC-0.3.0-Local.exe";
            // 
            // StopBTN
            // 
            this.StopBTN.Location = new System.Drawing.Point(309, 35);
            this.StopBTN.Name = "StopBTN";
            this.StopBTN.Size = new System.Drawing.Size(68, 23);
            this.StopBTN.TabIndex = 5;
            this.StopBTN.Text = "Stop";
            this.StopBTN.UseVisualStyleBackColor = true;
            this.StopBTN.Click += new System.EventHandler(this.StopBTN_Click);
            // 
            // RunBTN
            // 
            this.RunBTN.Location = new System.Drawing.Point(398, 35);
            this.RunBTN.Name = "RunBTN";
            this.RunBTN.Size = new System.Drawing.Size(68, 23);
            this.RunBTN.TabIndex = 6;
            this.RunBTN.Text = "Run";
            this.RunBTN.UseVisualStyleBackColor = true;
            this.RunBTN.Click += new System.EventHandler(this.RunBTN_Click);
            // 
            // FilesTBX
            // 
            this.FilesTBX.Location = new System.Drawing.Point(13, 64);
            this.FilesTBX.Name = "FilesTBX";
            this.FilesTBX.ReadOnly = true;
            this.FilesTBX.Size = new System.Drawing.Size(453, 21);
            this.FilesTBX.TabIndex = 7;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(478, 91);
            this.Controls.Add(this.FilesTBX);
            this.Controls.Add(this.RunBTN);
            this.Controls.Add(this.StopBTN);
            this.Controls.Add(this.RunTBX);
            this.Controls.Add(this.PathTBX);
            this.Controls.Add(this.TimerCBX);
            this.Controls.Add(this.MessageTBX);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "Form1";
            this.Text = "调度器 ver 0.3.0 For Exe";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.Form1_FormClosed);
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Timer ThreadTM;
        private System.Windows.Forms.TextBox MessageTBX;
        private System.Windows.Forms.ComboBox TimerCBX;
        private System.Windows.Forms.TextBox PathTBX;
        private System.Windows.Forms.TextBox RunTBX;
        private System.Windows.Forms.Button StopBTN;
        private System.Windows.Forms.Button RunBTN;
        private System.Windows.Forms.TextBox FilesTBX;

    }
}

