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
    public partial class SeriesCreat : Form
    {
        public SeriesCreat()
        {
            InitializeComponent();
        }

        private void SeriesCreat_Load(object sender, EventArgs e)
        {
            this.Icon = Properties.Resources.TSS;

            LB.Items.Add("1"); LB.Items.Add("2"); LB.Items.Add("3"); LB.Items.Add("4");

        }

        private void CreatResourceBTN_Click(object sender, EventArgs e)
        {
            ResourceCreat rc = new ResourceCreat();
            rc.SeriesType = true;
            rc.ShowDialog(this);
        }
    }
}
