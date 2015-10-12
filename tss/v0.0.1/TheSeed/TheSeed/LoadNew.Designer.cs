namespace TheSeed
{
    partial class LoadNew
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.MessageLBL = new System.Windows.Forms.Label();
            this.LoadPB = new System.Windows.Forms.ProgressBar();
            this.timer = new System.Windows.Forms.Timer(this.components);
            this.SuspendLayout();
            // 
            // MessageLBL
            // 
            this.MessageLBL.AutoSize = true;
            this.MessageLBL.Location = new System.Drawing.Point(12, 47);
            this.MessageLBL.Name = "MessageLBL";
            this.MessageLBL.Size = new System.Drawing.Size(119, 12);
            this.MessageLBL.TabIndex = 3;
            this.MessageLBL.Text = "系统初始化进行中...";
            // 
            // LoadPB
            // 
            this.LoadPB.Location = new System.Drawing.Point(12, 12);
            this.LoadPB.Name = "LoadPB";
            this.LoadPB.Size = new System.Drawing.Size(485, 21);
            this.LoadPB.TabIndex = 2;
            // 
            // timer
            // 
            this.timer.Interval = 1000;
            this.timer.Tick += new System.EventHandler(this.timer_Tick);
            // 
            // LoadNew
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(509, 71);
            this.Controls.Add(this.MessageLBL);
            this.Controls.Add(this.LoadPB);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "LoadNew";
            this.Text = "获取更新";
            this.Load += new System.EventHandler(this.LoadNew_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label MessageLBL;
        private System.Windows.Forms.ProgressBar LoadPB;
        private System.Windows.Forms.Timer timer;
    }
}