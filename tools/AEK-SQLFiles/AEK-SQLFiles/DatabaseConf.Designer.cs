namespace AEK_SQLFiles
{
    partial class DatabaseConf
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(DatabaseConf));
            this.FileView = new System.Windows.Forms.CheckedListBox();
            this.AllRB = new System.Windows.Forms.RadioButton();
            this.SelectRB = new System.Windows.Forms.RadioButton();
            this.FirstRB = new System.Windows.Forms.RadioButton();
            this.dbStartBTN = new System.Windows.Forms.Button();
            this.label7 = new System.Windows.Forms.Label();
            this.dbuser = new System.Windows.Forms.TextBox();
            this.dbpass = new System.Windows.Forms.TextBox();
            this.dbtns = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.OraCliPathSelectBTN = new System.Windows.Forms.Button();
            this.oraCli = new System.Windows.Forms.TextBox();
            this.dbhost = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.flowLayoutPanel1 = new System.Windows.Forms.FlowLayoutPanel();
            this.AutoCBX = new System.Windows.Forms.CheckBox();
            this.label6 = new System.Windows.Forms.Label();
            this.SQLPathSelectBTN = new System.Windows.Forms.Button();
            this.A2cwFBD = new System.Windows.Forms.FolderBrowserDialog();
            this.sqlPath = new System.Windows.Forms.TextBox();
            this.flowLayoutPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // FileView
            // 
            this.FileView.Font = new System.Drawing.Font("宋体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.FileView.FormattingEnabled = true;
            this.FileView.Location = new System.Drawing.Point(15, 106);
            this.FileView.Name = "FileView";
            this.FileView.Size = new System.Drawing.Size(239, 235);
            this.FileView.TabIndex = 45;
            // 
            // AllRB
            // 
            this.AllRB.AutoSize = true;
            this.AllRB.Location = new System.Drawing.Point(145, 3);
            this.AllRB.Name = "AllRB";
            this.AllRB.Size = new System.Drawing.Size(71, 16);
            this.AllRB.TabIndex = 2;
            this.AllRB.Text = "全部运行";
            this.AllRB.UseVisualStyleBackColor = true;
            this.AllRB.CheckedChanged += new System.EventHandler(this.AllRB_CheckedChanged);
            // 
            // SelectRB
            // 
            this.SelectRB.AutoSize = true;
            this.SelectRB.Checked = true;
            this.SelectRB.Location = new System.Drawing.Point(68, 3);
            this.SelectRB.Name = "SelectRB";
            this.SelectRB.Size = new System.Drawing.Size(71, 16);
            this.SelectRB.TabIndex = 1;
            this.SelectRB.TabStop = true;
            this.SelectRB.Text = "选中运行";
            this.SelectRB.UseVisualStyleBackColor = true;
            this.SelectRB.CheckedChanged += new System.EventHandler(this.SelectRB_CheckedChanged);
            // 
            // FirstRB
            // 
            this.FirstRB.AutoSize = true;
            this.FirstRB.Location = new System.Drawing.Point(3, 3);
            this.FirstRB.Name = "FirstRB";
            this.FirstRB.Size = new System.Drawing.Size(59, 16);
            this.FirstRB.TabIndex = 0;
            this.FirstRB.Text = "初始化";
            this.FirstRB.UseVisualStyleBackColor = true;
            this.FirstRB.CheckedChanged += new System.EventHandler(this.FirstRB_CheckedChanged);
            // 
            // dbStartBTN
            // 
            this.dbStartBTN.Font = new System.Drawing.Font("宋体", 15.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.dbStartBTN.Location = new System.Drawing.Point(297, 299);
            this.dbStartBTN.Name = "dbStartBTN";
            this.dbStartBTN.Size = new System.Drawing.Size(158, 42);
            this.dbStartBTN.TabIndex = 43;
            this.dbStartBTN.Text = "开始安装";
            this.dbStartBTN.UseVisualStyleBackColor = true;
            this.dbStartBTN.Click += new System.EventHandler(this.dbStartBTN_Click);
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Font = new System.Drawing.Font("宋体", 10.5F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label7.ForeColor = System.Drawing.Color.Blue;
            this.label7.Location = new System.Drawing.Point(12, 18);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(352, 14);
            this.label7.TabIndex = 42;
            this.label7.Text = "即将运行下列数据库脚本，请确保用户拥有最高权限";
            // 
            // dbuser
            // 
            this.dbuser.Location = new System.Drawing.Point(333, 136);
            this.dbuser.Name = "dbuser";
            this.dbuser.Size = new System.Drawing.Size(158, 21);
            this.dbuser.TabIndex = 46;
            this.dbuser.Text = "dbuser";
            // 
            // dbpass
            // 
            this.dbpass.Location = new System.Drawing.Point(333, 165);
            this.dbpass.Name = "dbpass";
            this.dbpass.Size = new System.Drawing.Size(158, 21);
            this.dbpass.TabIndex = 47;
            this.dbpass.Text = "dbpass";
            // 
            // dbtns
            // 
            this.dbtns.Location = new System.Drawing.Point(333, 194);
            this.dbtns.Name = "dbtns";
            this.dbtns.Size = new System.Drawing.Size(158, 21);
            this.dbtns.TabIndex = 48;
            this.dbtns.Text = "dbtns";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(283, 139);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(41, 12);
            this.label1.TabIndex = 49;
            this.label1.Text = "用户名";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(295, 168);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(29, 12);
            this.label2.TabIndex = 49;
            this.label2.Text = "密码";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(277, 197);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(47, 12);
            this.label3.TabIndex = 50;
            this.label3.Text = "TNS名称";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(32, 51);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(83, 12);
            this.label4.TabIndex = 53;
            this.label4.Text = "ORA客户端路径";
            // 
            // OraCliPathSelectBTN
            // 
            this.OraCliPathSelectBTN.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.OraCliPathSelectBTN.Location = new System.Drawing.Point(455, 46);
            this.OraCliPathSelectBTN.Name = "OraCliPathSelectBTN";
            this.OraCliPathSelectBTN.Size = new System.Drawing.Size(37, 23);
            this.OraCliPathSelectBTN.TabIndex = 52;
            this.OraCliPathSelectBTN.Text = "...";
            this.OraCliPathSelectBTN.UseVisualStyleBackColor = true;
            this.OraCliPathSelectBTN.Click += new System.EventHandler(this.OraCliPathSelectBTN_Click);
            // 
            // oraCli
            // 
            this.oraCli.Location = new System.Drawing.Point(121, 48);
            this.oraCli.Name = "oraCli";
            this.oraCli.Size = new System.Drawing.Size(328, 21);
            this.oraCli.TabIndex = 51;
            this.oraCli.Text = "C:\\Program Files\\Oracle\\Instant Client";
            // 
            // dbhost
            // 
            this.dbhost.Location = new System.Drawing.Point(333, 106);
            this.dbhost.Name = "dbhost";
            this.dbhost.Size = new System.Drawing.Size(158, 21);
            this.dbhost.TabIndex = 54;
            this.dbhost.Text = "dbhost";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(283, 109);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(41, 12);
            this.label5.TabIndex = 55;
            this.label5.Text = "IP地址";
            // 
            // flowLayoutPanel1
            // 
            this.flowLayoutPanel1.Controls.Add(this.FirstRB);
            this.flowLayoutPanel1.Controls.Add(this.SelectRB);
            this.flowLayoutPanel1.Controls.Add(this.AllRB);
            this.flowLayoutPanel1.Location = new System.Drawing.Point(269, 255);
            this.flowLayoutPanel1.Name = "flowLayoutPanel1";
            this.flowLayoutPanel1.Size = new System.Drawing.Size(223, 28);
            this.flowLayoutPanel1.TabIndex = 56;
            // 
            // AutoCBX
            // 
            this.AutoCBX.AutoSize = true;
            this.AutoCBX.Checked = true;
            this.AutoCBX.CheckState = System.Windows.Forms.CheckState.Checked;
            this.AutoCBX.ForeColor = System.Drawing.Color.Blue;
            this.AutoCBX.Location = new System.Drawing.Point(317, 227);
            this.AutoCBX.Name = "AutoCBX";
            this.AutoCBX.Size = new System.Drawing.Size(162, 16);
            this.AutoCBX.TabIndex = 57;
            this.AutoCBX.Text = "手动生成ORA连接配置文件";
            this.AutoCBX.UseVisualStyleBackColor = true;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("宋体", 11.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label6.ForeColor = System.Drawing.Color.Blue;
            this.label6.Location = new System.Drawing.Point(12, 79);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(130, 15);
            this.label6.TabIndex = 58;
            this.label6.Text = "SQL脚本文件地址";
            // 
            // SQLPathSelectBTN
            // 
            this.SQLPathSelectBTN.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.SQLPathSelectBTN.Location = new System.Drawing.Point(148, 77);
            this.SQLPathSelectBTN.Name = "SQLPathSelectBTN";
            this.SQLPathSelectBTN.Size = new System.Drawing.Size(37, 23);
            this.SQLPathSelectBTN.TabIndex = 59;
            this.SQLPathSelectBTN.Text = "...";
            this.SQLPathSelectBTN.UseVisualStyleBackColor = true;
            this.SQLPathSelectBTN.Click += new System.EventHandler(this.SQLPathSelectBTN_Click);
            // 
            // sqlPath
            // 
            this.sqlPath.Enabled = false;
            this.sqlPath.Location = new System.Drawing.Point(191, 79);
            this.sqlPath.Name = "sqlPath";
            this.sqlPath.Size = new System.Drawing.Size(301, 21);
            this.sqlPath.TabIndex = 60;
            this.sqlPath.Text = "C:\\SQL";
            // 
            // DatabaseConf
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(504, 357);
            this.Controls.Add(this.sqlPath);
            this.Controls.Add(this.SQLPathSelectBTN);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.AutoCBX);
            this.Controls.Add(this.flowLayoutPanel1);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.dbhost);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.OraCliPathSelectBTN);
            this.Controls.Add(this.oraCli);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.dbtns);
            this.Controls.Add(this.dbpass);
            this.Controls.Add(this.dbuser);
            this.Controls.Add(this.FileView);
            this.Controls.Add(this.dbStartBTN);
            this.Controls.Add(this.label7);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "DatabaseConf";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "ORACLE数据库初始化工具";
            this.Load += new System.EventHandler(this.DatabaseConf_Load);
            this.flowLayoutPanel1.ResumeLayout(false);
            this.flowLayoutPanel1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.CheckedListBox FileView;
        private System.Windows.Forms.RadioButton AllRB;
        private System.Windows.Forms.RadioButton SelectRB;
        private System.Windows.Forms.RadioButton FirstRB;
        private System.Windows.Forms.Button dbStartBTN;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox dbuser;
        private System.Windows.Forms.TextBox dbpass;
        private System.Windows.Forms.TextBox dbtns;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button OraCliPathSelectBTN;
        private System.Windows.Forms.TextBox oraCli;
        private System.Windows.Forms.TextBox dbhost;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.FlowLayoutPanel flowLayoutPanel1;
        private System.Windows.Forms.CheckBox AutoCBX;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Button SQLPathSelectBTN;
        private System.Windows.Forms.FolderBrowserDialog A2cwFBD;
        private System.Windows.Forms.TextBox sqlPath;


    }
}