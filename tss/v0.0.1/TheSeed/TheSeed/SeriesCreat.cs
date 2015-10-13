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
    public partial class SeriesCreat : Form
    {
        public SeriesCreat()
        {
            InitializeComponent();
        }

        private void SeriesCreat_Load(object sender, EventArgs e)
        {
            this.Icon = Properties.Resources.TSS;
        }

        private void CreatResourceBTN_Click(object sender, EventArgs e)
        {
            ResourceCreat rc = new ResourceCreat();
            rc.SeriesType = true;
            rc.ShowDialog(this);
        }
    }
}
