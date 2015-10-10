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
    public partial class Main : Form
    {
        public Main()
        {
            InitializeComponent();
        }

        private void Main_Load(object sender, EventArgs e)
        {
            this.Icon = Properties.Resources.TSS;

            this.Hide();
            //加载初始化
            if (SystemParam.Login == false)
            {
                Login l = new Login();
                l.ShowDialog(this);
            }
            //用户登录
            if (SystemParam.Login == true)
            {
                //初始化
                Loading ld = new Loading();
                ld.ShowDialog(this);
                if (SystemParam.NetConnect == true)
                {
                    this.Show();
                }
                else
                {
                    this.Close();
                }
            }
            else
            {
                this.Close();
            }
        }

        private void ConfigBTN_Click(object sender, EventArgs e)
        {
            ConfigSet c = new ConfigSet();
            c.ShowDialog(this);
        }
    }
}
