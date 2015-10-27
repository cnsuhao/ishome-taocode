using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
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
    public partial class Login : Form
    {
        public Login()
        {
            InitializeComponent();
        }

        private void LoginBTN_Click(object sender, EventArgs e)
        {
            //登录判断
            if (File.Exists(AdminConfigFile) == false)
            {
                if (this.UserID.Text.Equals(this.Password.Text) == false)
                {
                    MessageBox.Show("两次密码不一致");
                    return;
                }
                else
                {
                    //保存密码文件
                    File.WriteAllText(AdminConfigFile, this.Password.Text);
                }
            }
            else
            {
                if (this.Password.Text.Equals(PasswordConfig) == false)
                {
                    MessageBox.Show("请输入正确的密码");
                    return;
                }
            }
            ConfigUtils.Login = true;
            this.Close();
        }

        private void ConfigBTN_Click(object sender, EventArgs e)
        {
            ConfigSet c = new ConfigSet();
            c.ShowDialog(this);
        }

        private void Login_Load(object sender, EventArgs e)
        {
            //MessageBox.Show(DateTime.Now.ToString("yyyy/MM/dd HH:mm:ss"));
            this.Icon = Properties.Resources.TSS;
            //加载系统默认配置
            if( ConfigUtils.LoadConfigFile() == false)
            {
                ConfigSet c = new ConfigSet();
                c.ShowDialog(this);
            }
            AdminConfigFile = ConfigUtils.DataFileSavePath + @"\" + FilePathUtils.LOCAL_CFG + FilePathUtils.LOCAL_ADMIN_SYS + FilePathUtils.LOCAL_FILE_TYPE;
            if (File.Exists(AdminConfigFile) == false)//第一次使用
            {
            }
            else
            {
                this.Password.Text = "请输入密码";
                this.UserID.Visible = false;
                PasswordConfig = File.ReadAllText(AdminConfigFile);
                //加载系统配置信息
                ConfigUtils.LoadSysConfig();
            }
        }

        public String UserIDConfig { get; set; }
        public String PasswordConfig { get; set; }
        public String AdminConfigFile { get; set; }

        private void SystemDesineBTN_Click(object sender, EventArgs e)
        {
            SystemDesine s = new SystemDesine();
            s.ShowDialog(this);
        }
    }
}
