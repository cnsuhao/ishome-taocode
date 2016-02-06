namespace TheSeed
{
    partial class Login
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
            this.UserID = new System.Windows.Forms.TextBox();
            this.Password = new System.Windows.Forms.TextBox();
            this.LoginBTN = new System.Windows.Forms.Button();
            this.ConfigBTN = new System.Windows.Forms.Button();
            this.SystemDesineBTN = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // UserID
            // 
            this.UserID.Location = new System.Drawing.Point(41, 26);
            this.UserID.Name = "UserID";
            this.UserID.Size = new System.Drawing.Size(194, 21);
            this.UserID.TabIndex = 0;
            this.UserID.Text = "请输入密码";
            // 
            // Password
            // 
            this.Password.Location = new System.Drawing.Point(41, 73);
            this.Password.Name = "Password";
            this.Password.Size = new System.Drawing.Size(194, 21);
            this.Password.TabIndex = 1;
            this.Password.Text = "请再次输入密码";
            // 
            // LoginBTN
            // 
            this.LoginBTN.Location = new System.Drawing.Point(41, 133);
            this.LoginBTN.Name = "LoginBTN";
            this.LoginBTN.Size = new System.Drawing.Size(194, 38);
            this.LoginBTN.TabIndex = 2;
            this.LoginBTN.Text = "登录";
            this.LoginBTN.UseVisualStyleBackColor = true;
            this.LoginBTN.Click += new System.EventHandler(this.LoginBTN_Click);
            // 
            // ConfigBTN
            // 
            this.ConfigBTN.Location = new System.Drawing.Point(160, 216);
            this.ConfigBTN.Name = "ConfigBTN";
            this.ConfigBTN.Size = new System.Drawing.Size(75, 23);
            this.ConfigBTN.TabIndex = 3;
            this.ConfigBTN.Text = "系统配置";
            this.ConfigBTN.UseVisualStyleBackColor = true;
            this.ConfigBTN.Click += new System.EventHandler(this.ConfigBTN_Click);
            // 
            // SystemDesineBTN
            // 
            this.SystemDesineBTN.Location = new System.Drawing.Point(41, 216);
            this.SystemDesineBTN.Name = "SystemDesineBTN";
            this.SystemDesineBTN.Size = new System.Drawing.Size(75, 23);
            this.SystemDesineBTN.TabIndex = 4;
            this.SystemDesineBTN.Text = "系统设计";
            this.SystemDesineBTN.UseVisualStyleBackColor = true;
            this.SystemDesineBTN.Click += new System.EventHandler(this.SystemDesineBTN_Click);
            // 
            // Login
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(275, 261);
            this.Controls.Add(this.SystemDesineBTN);
            this.Controls.Add(this.ConfigBTN);
            this.Controls.Add(this.LoginBTN);
            this.Controls.Add(this.Password);
            this.Controls.Add(this.UserID);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "Login";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "用户登录";
            this.Load += new System.EventHandler(this.Login_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox UserID;
        private System.Windows.Forms.TextBox Password;
        private System.Windows.Forms.Button LoginBTN;
        private System.Windows.Forms.Button ConfigBTN;
        private System.Windows.Forms.Button SystemDesineBTN;
    }
}