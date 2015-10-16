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
    /// <summary>
    /// 参数配置
    /// 主要用于设定服务器地址和本地文件保存地址
    /// 画面使用依赖于基本信息加载界面运行结束，参考<Loading>
    /// </summary>
    public partial class ConfigSet : Form
    {
        public ConfigSet()
        {
            InitializeComponent();
        }

        private void ConfigSet_Load(object sender, EventArgs e)
        {
            this.Icon = Properties.Resources.TSS;
                      
            //判断用户是否登录
            if (ConfigUtils.Login == false)
            {
                NeedAdminBTN.Visible = false;
                ChangePasswordBTN.Visible = false;
            }

            //展示设定值
            DataFileSavePath.Text = ConfigUtils.DataFileSavePath;
            FirstServerAdress.Text = ConfigUtils.FirstServerAdress;
            SecondServerAdress.Text = ConfigUtils.SecondServerAdress;
        }

        private void SavePathBTN_Click(object sender, EventArgs e)
        {
            folderBrowserDialog.ShowDialog(this);
            DataFileSavePath.Text = folderBrowserDialog.SelectedPath;
        }

        private void UploadConfigBTN_Click(object sender, EventArgs e)
        {


            //TODO 2.0云化版本处理
        }
    }
}
