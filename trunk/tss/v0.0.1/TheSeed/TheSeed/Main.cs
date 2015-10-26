using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

/// <summary>
/// TSS资源分析
/// 作者：同位素
/// 时间：2015/10/1
/// </summary>
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
            if (ConfigUtils.Login == false)
            {
                Login l = new Login();
                l.ShowDialog(this);
            }
            //用户登录
            if (ConfigUtils.Login == true)
            {
                //初始化
                Loading ld = new Loading();
                ld.ShowDialog(this);
               // if (ConfigUtils.NetConnect == true)
                {
                    this.Show();
                }
              //  else
                {
              //      this.Close();
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

        private void LoadNewBTN_Click(object sender, EventArgs e)
        {
            Loading l = new Loading();
            l.ShowDialog(this);
        }

        private void ResourceListBTN_Click(object sender, EventArgs e)
        {
            ResourceList rl = new ResourceList();
            rl.ShowDialog(this);
        }

        private void ResourceCreatBTN_Click(object sender, EventArgs e)
        {
            ResourceCreat rc = new ResourceCreat();
            rc.ShowDialog(this);
        }

        private void SeriesCreatBTN_Click(object sender, EventArgs e)
        {
            SeriesCreat sc = new SeriesCreat();
            sc.ShowDialog(this);
        }

        private void TypeCreatBTN_Click(object sender, EventArgs e)
        {
            TypeCreat tc = new TypeCreat();
            tc.ShowDialog(this);
        }

        private void button6_Click(object sender, EventArgs e)
        {
            MyNeed mn = new MyNeed();
            mn.ShowDialog(this);
        }
    }
}
