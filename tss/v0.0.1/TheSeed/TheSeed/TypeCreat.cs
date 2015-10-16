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
    public partial class TypeCreat : Form
    {
        public TypeCreat()
        {
            InitializeComponent();
        }

        private void TypeCreat_Load(object sender, EventArgs e)
        {
            this.Icon = Properties.Resources.TSS;
            BM.Text = Guid.NewGuid().ToString("N");
        }

        private void Find_Click(object sender, EventArgs e)
        {
            //获取分类列表
            TypeUtils.LoadAllTypes();
            //查看是否存在
            DataSet.TypeRow tr = TypeUtils.Types.FindByNR(NR.Text);
            if (tr == null)
                MessageBox.Show("该分类不存在");
        }
        private Boolean TypeCheck()
        {
            //获取分类列表
            TypeUtils.LoadAllTypes();
            //查看是否存在
            DataSet.TypeRow tr = TypeUtils.Types.FindByNR(NR.Text);
            if (tr == null)
                MessageBox.Show("该分类不存在");
            else
                return false;
            return true;
        }
        private void Save_Click(object sender, EventArgs e)
        {
            if (TypeCheck() == true)
            {
                DataSet.TypeRow tr = TypeUtils.Types.NewTypeRow();
                //发行到服务中心
               if( TypeUtils.CreatNewType(tr))
                    MessageBox.Show("创建成功");
               else
                    MessageBox.Show("创建失败，请稍后再试。");
            }
        }
    }
}
