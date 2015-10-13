namespace TheSeed
{
    partial class ConfigSet
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
            this.FirstServerAdress = new System.Windows.Forms.TextBox();
            this.SecondServerAdress = new System.Windows.Forms.TextBox();
            this.NeedAdminBTN = new System.Windows.Forms.Button();
            this.ChangePasswordBTN = new System.Windows.Forms.Button();
            this.SavePath = new System.Windows.Forms.TextBox();
            this.SavePathBTN = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.folderBrowserDialog = new System.Windows.Forms.FolderBrowserDialog();
            this.UploadConfigBTN = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // FirstServerAdress
            // 
            this.FirstServerAdress.Location = new System.Drawing.Point(40, 34);
            this.FirstServerAdress.Name = "FirstServerAdress";
            this.FirstServerAdress.Size = new System.Drawing.Size(419, 21);
            this.FirstServerAdress.TabIndex = 1;
            // 
            // SecondServerAdress
            // 
            this.SecondServerAdress.Location = new System.Drawing.Point(40, 230);
            this.SecondServerAdress.Name = "SecondServerAdress";
            this.SecondServerAdress.Size = new System.Drawing.Size(419, 21);
            this.SecondServerAdress.TabIndex = 3;
            // 
            // NeedAdminBTN
            // 
            this.NeedAdminBTN.Location = new System.Drawing.Point(117, 71);
            this.NeedAdminBTN.Name = "NeedAdminBTN";
            this.NeedAdminBTN.Size = new System.Drawing.Size(303, 32);
            this.NeedAdminBTN.TabIndex = 5;
            this.NeedAdminBTN.Text = "申请管理员";
            this.NeedAdminBTN.UseVisualStyleBackColor = true;
            // 
            // ChangePasswordBTN
            // 
            this.ChangePasswordBTN.Location = new System.Drawing.Point(117, 165);
            this.ChangePasswordBTN.Name = "ChangePasswordBTN";
            this.ChangePasswordBTN.Size = new System.Drawing.Size(157, 37);
            this.ChangePasswordBTN.TabIndex = 6;
            this.ChangePasswordBTN.Text = "修改密码";
            this.ChangePasswordBTN.UseVisualStyleBackColor = true;
            // 
            // SavePath
            // 
            this.SavePath.Location = new System.Drawing.Point(40, 131);
            this.SavePath.Name = "SavePath";
            this.SavePath.Size = new System.Drawing.Size(380, 21);
            this.SavePath.TabIndex = 2;
            // 
            // SavePathBTN
            // 
            this.SavePathBTN.Location = new System.Drawing.Point(428, 129);
            this.SavePathBTN.Name = "SavePathBTN";
            this.SavePathBTN.Size = new System.Drawing.Size(31, 23);
            this.SavePathBTN.TabIndex = 4;
            this.SavePathBTN.Text = "...";
            this.SavePathBTN.UseVisualStyleBackColor = true;
            this.SavePathBTN.Click += new System.EventHandler(this.SavePathBTN_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 206);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(89, 12);
            this.label1.TabIndex = 5;
            this.label1.Text = "镜像服务器地址";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(14, 106);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(101, 12);
            this.label2.TabIndex = 6;
            this.label2.Text = "本地数据储存路径";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(13, 13);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(77, 12);
            this.label3.TabIndex = 7;
            this.label3.Text = "主服务器地址";
            // 
            // UploadConfigBTN
            // 
            this.UploadConfigBTN.Location = new System.Drawing.Point(302, 165);
            this.UploadConfigBTN.Name = "UploadConfigBTN";
            this.UploadConfigBTN.Size = new System.Drawing.Size(157, 37);
            this.UploadConfigBTN.TabIndex = 6;
            this.UploadConfigBTN.Text = "上传配置";
            this.UploadConfigBTN.UseVisualStyleBackColor = true;
            this.UploadConfigBTN.Visible = false;
            this.UploadConfigBTN.Click += new System.EventHandler(this.UploadConfigBTN_Click);
            // 
            // ConfigSet
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(484, 261);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.SavePathBTN);
            this.Controls.Add(this.SavePath);
            this.Controls.Add(this.UploadConfigBTN);
            this.Controls.Add(this.ChangePasswordBTN);
            this.Controls.Add(this.NeedAdminBTN);
            this.Controls.Add(this.SecondServerAdress);
            this.Controls.Add(this.FirstServerAdress);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "ConfigSet";
            this.ShowInTaskbar = false;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "系统配置";
            this.Load += new System.EventHandler(this.ConfigSet_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox FirstServerAdress;
        private System.Windows.Forms.TextBox SecondServerAdress;
        private System.Windows.Forms.Button NeedAdminBTN;
        private System.Windows.Forms.Button ChangePasswordBTN;
        private System.Windows.Forms.TextBox SavePath;
        private System.Windows.Forms.Button SavePathBTN;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.FolderBrowserDialog folderBrowserDialog;
        private System.Windows.Forms.Button UploadConfigBTN;
    }
}