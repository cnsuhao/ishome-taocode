using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace TheSeed
{
    public partial class ConfigSet : Form
    {
        public ConfigSet()
        {
            InitializeComponent();
        }

        private void ConfigSet_Load(object sender, EventArgs e)
        {
            this.Icon = Properties.Resources.TSS;

            DataFileSavePath.Text = Application.StartupPath;

            if (ConfigUtils.Login == false)
            {
                NeedAdminBTN.Visible = false;
                ChangePasswordBTN.Visible = false;
            }
        }

        private void SavePathBTN_Click(object sender, EventArgs e)
        {
            folderBrowserDialog.ShowDialog(this);
            DataFileSavePath.Text = folderBrowserDialog.SelectedPath;
        }

        private void UploadConfigBTN_Click(object sender, EventArgs e)
        {

        }
    }
}
