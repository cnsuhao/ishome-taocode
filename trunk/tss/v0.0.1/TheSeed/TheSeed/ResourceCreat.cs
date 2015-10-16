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
    public partial class ResourceCreat : Form
    {
        public ResourceCreat()
        {
            InitializeComponent();
        }

        private void SeedCreat_Load(object sender, EventArgs e)
        {
            this.Icon = Properties.Resources.TSS;
            if (SeriesType == true)
            {
                GXJS.Visible = true;
                GXJM.Visible = true;
            }
            else
            {
                MC.Visible = true;
            }
        }

        /// <summary>
        /// 是否进行剧集发布
        /// </summary>
        public Boolean SeriesType { get; set; }

        /// <summary>
        /// 创建的资源
        /// </summary>
        public DataSet.ResourceRow Resource { get; set; }
    }
}
