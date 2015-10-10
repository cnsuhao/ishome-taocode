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
    public partial class LoadAll : Form
    {
        public LoadAll()
        {
            InitializeComponent();
        }

        private void LoadAll_Load(object sender, EventArgs e)
        {
            this.Icon = Properties.Resources.TSS;
        }
    }
}
