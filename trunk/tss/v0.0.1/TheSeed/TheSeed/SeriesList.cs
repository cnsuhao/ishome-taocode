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
    public partial class SeriesList : Form
    {
        public SeriesList()
        {
            InitializeComponent();
        }

        private void SeriesList_Load(object sender, EventArgs e)
        {
            this.Icon = Properties.Resources.TSS;
        }
    }
}
