namespace TheSeed
{
    partial class SeriesList
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
            this.SeriesLists = new System.Windows.Forms.DataGridView();
            this.Column1 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Column2 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Column3 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Column4 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Column5 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Column6 = new System.Windows.Forms.DataGridViewCheckBoxColumn();
            this.NamTBX = new System.Windows.Forms.TextBox();
            this.button1 = new System.Windows.Forms.Button();
            this.TypeCBX = new System.Windows.Forms.ComboBox();
            this.comboBox1 = new System.Windows.Forms.ComboBox();
            ((System.ComponentModel.ISupportInitialize)(this.SeriesLists)).BeginInit();
            this.SuspendLayout();
            // 
            // SeriesLists
            // 
            this.SeriesLists.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.SeriesLists.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.Column1,
            this.Column2,
            this.Column3,
            this.Column4,
            this.Column5,
            this.Column6});
            this.SeriesLists.Location = new System.Drawing.Point(13, 41);
            this.SeriesLists.Name = "SeriesLists";
            this.SeriesLists.RowTemplate.Height = 23;
            this.SeriesLists.Size = new System.Drawing.Size(641, 208);
            this.SeriesLists.TabIndex = 6;
            // 
            // Column1
            // 
            this.Column1.HeaderText = "Column1";
            this.Column1.Name = "Column1";
            // 
            // Column2
            // 
            this.Column2.HeaderText = "Column2";
            this.Column2.Name = "Column2";
            // 
            // Column3
            // 
            this.Column3.HeaderText = "Column3";
            this.Column3.Name = "Column3";
            // 
            // Column4
            // 
            this.Column4.HeaderText = "Column4";
            this.Column4.Name = "Column4";
            // 
            // Column5
            // 
            this.Column5.HeaderText = "Column5";
            this.Column5.Name = "Column5";
            // 
            // Column6
            // 
            this.Column6.HeaderText = "Column6";
            this.Column6.Name = "Column6";
            // 
            // NamTBX
            // 
            this.NamTBX.Location = new System.Drawing.Point(12, 12);
            this.NamTBX.Name = "NamTBX";
            this.NamTBX.Size = new System.Drawing.Size(293, 21);
            this.NamTBX.TabIndex = 5;
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(579, 12);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 4;
            this.button1.Text = "检索";
            this.button1.UseVisualStyleBackColor = true;
            // 
            // TypeCBX
            // 
            this.TypeCBX.FormattingEnabled = true;
            this.TypeCBX.Location = new System.Drawing.Point(312, 12);
            this.TypeCBX.Name = "TypeCBX";
            this.TypeCBX.Size = new System.Drawing.Size(93, 20);
            this.TypeCBX.TabIndex = 7;
            // 
            // comboBox1
            // 
            this.comboBox1.FormattingEnabled = true;
            this.comboBox1.Location = new System.Drawing.Point(411, 12);
            this.comboBox1.Name = "comboBox1";
            this.comboBox1.Size = new System.Drawing.Size(145, 20);
            this.comboBox1.TabIndex = 8;
            // 
            // SeriesList
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(668, 281);
            this.Controls.Add(this.comboBox1);
            this.Controls.Add(this.SeriesLists);
            this.Controls.Add(this.NamTBX);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.TypeCBX);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "SeriesList";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "主题一览";
            this.Load += new System.EventHandler(this.SeriesList_Load);
            ((System.ComponentModel.ISupportInitialize)(this.SeriesLists)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView SeriesLists;
        private System.Windows.Forms.DataGridViewTextBoxColumn Column1;
        private System.Windows.Forms.DataGridViewTextBoxColumn Column2;
        private System.Windows.Forms.DataGridViewTextBoxColumn Column3;
        private System.Windows.Forms.DataGridViewTextBoxColumn Column4;
        private System.Windows.Forms.DataGridViewTextBoxColumn Column5;
        private System.Windows.Forms.DataGridViewCheckBoxColumn Column6;
        private System.Windows.Forms.TextBox NamTBX;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.ComboBox TypeCBX;
        private System.Windows.Forms.ComboBox comboBox1;
    }
}