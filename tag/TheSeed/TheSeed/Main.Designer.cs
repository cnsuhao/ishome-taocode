namespace TheSeed
{
    partial class Main
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
        /// 设计器支持所需的方法 - 不要修改
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.LoadNewBTN = new System.Windows.Forms.Button();
            this.ResourceListBTN = new System.Windows.Forms.Button();
            this.ResourceCreatBTN = new System.Windows.Forms.Button();
            this.TypeCreatBTN = new System.Windows.Forms.Button();
            this.button6 = new System.Windows.Forms.Button();
            this.SeriesCreatBTN = new System.Windows.Forms.Button();
            this.ConfigBTN = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // LoadNewBTN
            // 
            this.LoadNewBTN.Location = new System.Drawing.Point(32, 25);
            this.LoadNewBTN.Name = "LoadNewBTN";
            this.LoadNewBTN.Size = new System.Drawing.Size(202, 36);
            this.LoadNewBTN.TabIndex = 1;
            this.LoadNewBTN.Text = "获取更新";
            this.LoadNewBTN.UseVisualStyleBackColor = true;
            this.LoadNewBTN.Click += new System.EventHandler(this.LoadNewBTN_Click);
            // 
            // ResourceListBTN
            // 
            this.ResourceListBTN.Location = new System.Drawing.Point(32, 81);
            this.ResourceListBTN.Name = "ResourceListBTN";
            this.ResourceListBTN.Size = new System.Drawing.Size(202, 36);
            this.ResourceListBTN.TabIndex = 1;
            this.ResourceListBTN.Text = "资源查看";
            this.ResourceListBTN.UseVisualStyleBackColor = true;
            this.ResourceListBTN.Click += new System.EventHandler(this.ResourceListBTN_Click);
            // 
            // ResourceCreatBTN
            // 
            this.ResourceCreatBTN.Location = new System.Drawing.Point(32, 137);
            this.ResourceCreatBTN.Name = "ResourceCreatBTN";
            this.ResourceCreatBTN.Size = new System.Drawing.Size(93, 36);
            this.ResourceCreatBTN.TabIndex = 2;
            this.ResourceCreatBTN.Text = "资源发布";
            this.ResourceCreatBTN.UseVisualStyleBackColor = true;
            this.ResourceCreatBTN.Click += new System.EventHandler(this.ResourceCreatBTN_Click);
            // 
            // TypeCreatBTN
            // 
            this.TypeCreatBTN.Location = new System.Drawing.Point(32, 192);
            this.TypeCreatBTN.Name = "TypeCreatBTN";
            this.TypeCreatBTN.Size = new System.Drawing.Size(202, 36);
            this.TypeCreatBTN.TabIndex = 1;
            this.TypeCreatBTN.Text = "类别发布";
            this.TypeCreatBTN.UseVisualStyleBackColor = true;
            this.TypeCreatBTN.Click += new System.EventHandler(this.TypeCreatBTN_Click);
            // 
            // button6
            // 
            this.button6.Location = new System.Drawing.Point(32, 250);
            this.button6.Name = "button6";
            this.button6.Size = new System.Drawing.Size(202, 36);
            this.button6.TabIndex = 1;
            this.button6.Text = "我的关注";
            this.button6.UseVisualStyleBackColor = true;
            this.button6.Click += new System.EventHandler(this.button6_Click);
            // 
            // SeriesCreatBTN
            // 
            this.SeriesCreatBTN.Location = new System.Drawing.Point(144, 137);
            this.SeriesCreatBTN.Name = "SeriesCreatBTN";
            this.SeriesCreatBTN.Size = new System.Drawing.Size(90, 36);
            this.SeriesCreatBTN.TabIndex = 2;
            this.SeriesCreatBTN.Text = "剧集发布";
            this.SeriesCreatBTN.UseVisualStyleBackColor = true;
            this.SeriesCreatBTN.Click += new System.EventHandler(this.SeriesCreatBTN_Click);
            // 
            // ConfigBTN
            // 
            this.ConfigBTN.Location = new System.Drawing.Point(159, 305);
            this.ConfigBTN.Name = "ConfigBTN";
            this.ConfigBTN.Size = new System.Drawing.Size(75, 23);
            this.ConfigBTN.TabIndex = 4;
            this.ConfigBTN.Text = "系统设置";
            this.ConfigBTN.UseVisualStyleBackColor = true;
            this.ConfigBTN.Click += new System.EventHandler(this.ConfigBTN_Click);
            // 
            // Main
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(265, 345);
            this.Controls.Add(this.ConfigBTN);
            this.Controls.Add(this.button6);
            this.Controls.Add(this.TypeCreatBTN);
            this.Controls.Add(this.ResourceListBTN);
            this.Controls.Add(this.LoadNewBTN);
            this.Controls.Add(this.SeriesCreatBTN);
            this.Controls.Add(this.ResourceCreatBTN);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "Main";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "The Seed Share Ver 0.01";
            this.Load += new System.EventHandler(this.Main_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button LoadNewBTN;
        private System.Windows.Forms.Button ResourceListBTN;
        private System.Windows.Forms.Button ResourceCreatBTN;
        private System.Windows.Forms.Button TypeCreatBTN;
        private System.Windows.Forms.Button button6;
        private System.Windows.Forms.Button SeriesCreatBTN;
        private System.Windows.Forms.Button ConfigBTN;
    }
}

