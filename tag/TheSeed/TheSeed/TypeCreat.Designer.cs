namespace TheSeed
{
    partial class TypeCreat
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
            this.BM = new System.Windows.Forms.TextBox();
            this.NR = new System.Windows.Forms.TextBox();
            this.SaveBTN = new System.Windows.Forms.Button();
            this.FindBTN = new System.Windows.Forms.Button();
            this.ViewBTN = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // BM
            // 
            this.BM.Location = new System.Drawing.Point(75, 37);
            this.BM.Name = "BM";
            this.BM.ReadOnly = true;
            this.BM.Size = new System.Drawing.Size(239, 21);
            this.BM.TabIndex = 0;
            this.BM.Text = "类别代码";
            // 
            // NR
            // 
            this.NR.Location = new System.Drawing.Point(75, 80);
            this.NR.Name = "NR";
            this.NR.Size = new System.Drawing.Size(137, 21);
            this.NR.TabIndex = 0;
            this.NR.Text = "类别名称";
            // 
            // SaveBTN
            // 
            this.SaveBTN.Location = new System.Drawing.Point(153, 158);
            this.SaveBTN.Name = "SaveBTN";
            this.SaveBTN.Size = new System.Drawing.Size(75, 23);
            this.SaveBTN.TabIndex = 1;
            this.SaveBTN.Text = "创建";
            this.SaveBTN.UseVisualStyleBackColor = true;
            this.SaveBTN.Click += new System.EventHandler(this.Save_Click);
            // 
            // FindBTN
            // 
            this.FindBTN.Location = new System.Drawing.Point(269, 80);
            this.FindBTN.Name = "FindBTN";
            this.FindBTN.Size = new System.Drawing.Size(45, 23);
            this.FindBTN.TabIndex = 2;
            this.FindBTN.Text = "检索";
            this.FindBTN.UseVisualStyleBackColor = true;
            this.FindBTN.Click += new System.EventHandler(this.Find_Click);
            // 
            // ViewBTN
            // 
            this.ViewBTN.Location = new System.Drawing.Point(218, 80);
            this.ViewBTN.Name = "ViewBTN";
            this.ViewBTN.Size = new System.Drawing.Size(45, 23);
            this.ViewBTN.TabIndex = 3;
            this.ViewBTN.Text = "查看";
            this.ViewBTN.UseVisualStyleBackColor = true;
            // 
            // TypeCreat
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(395, 223);
            this.Controls.Add(this.ViewBTN);
            this.Controls.Add(this.FindBTN);
            this.Controls.Add(this.SaveBTN);
            this.Controls.Add(this.NR);
            this.Controls.Add(this.BM);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "TypeCreat";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "类别创建";
            this.Load += new System.EventHandler(this.TypeCreat_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox BM;
        private System.Windows.Forms.TextBox NR;
        private System.Windows.Forms.Button SaveBTN;
        private System.Windows.Forms.Button FindBTN;
        private System.Windows.Forms.Button ViewBTN;
    }
}