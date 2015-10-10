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
    public partial class Login : Form
    {
        public Login()
        {
            InitializeComponent();
        }

        private void LoginBTN_Click(object sender, EventArgs e)
        {
            SystemParam.Login = true;
            this.Close();
        }

        private void ConfigBTN_Click(object sender, EventArgs e)
        {
            ConfigSet c = new ConfigSet();
            c.ShowDialog(this);
        }

        private void Login_Load(object sender, EventArgs e)
        {
            this.Icon = Properties.Resources.TSS;
        }
    }
}
