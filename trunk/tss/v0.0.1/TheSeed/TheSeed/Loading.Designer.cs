namespace TheSeed
{
    partial class Loading
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
            this.LoadPB = new System.Windows.Forms.ProgressBar();
            this.MessageLBL = new System.Windows.Forms.Label();
            this.timer = new System.Windows.Forms.Timer(this.components);
            this.button1 = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // LoadPB
            // 
            this.LoadPB.Location = new System.Drawing.Point(13, 13);
            this.LoadPB.Name = "LoadPB";
            this.LoadPB.Size = new System.Drawing.Size(485, 21);
            this.LoadPB.TabIndex = 0;
            // 
            // MessageLBL
            // 
            this.MessageLBL.Location = new System.Drawing.Point(13, 48);
            this.MessageLBL.Name = "MessageLBL";
            this.MessageLBL.Size = new System.Drawing.Size(414, 21);
            this.MessageLBL.TabIndex = 1;
            this.MessageLBL.Text = "系统初始化进行中...";
            // 
            // timer
            // 
            this.timer.Interval = 1000;
            this.timer.Tick += new System.EventHandler(this.timer_Tick);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(445, 48);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 2;
            this.button1.Text = "button1";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // Loading
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(513, 72);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.MessageLBL);
            this.Controls.Add(this.LoadPB);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "Loading";
            this.Text = "初始化";
            this.Load += new System.EventHandler(this.Loading_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ProgressBar LoadPB;
        private System.Windows.Forms.Label MessageLBL;
        private System.Windows.Forms.Timer timer;
        private System.Windows.Forms.Button button1;
    }
}