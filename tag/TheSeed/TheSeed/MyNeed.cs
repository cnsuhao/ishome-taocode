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
    public partial class MyNeed : Form
    {
        public MyNeed()
        {
            InitializeComponent();
        }

        private void MyNeed_Load(object sender, EventArgs e)
        {
            this.Icon = Properties.Resources.TSS;
        }
    }
}
