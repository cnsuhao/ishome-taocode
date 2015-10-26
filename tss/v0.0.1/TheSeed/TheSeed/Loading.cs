using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

/// <summary>
/// TSS资源分析
/// 作者：同位素
/// 时间：2015/10/1
/// </summary>
namespace TheSeed
{
    public partial class Loading : Form
    {
        public Loading()
        {
            InitializeComponent();
        }

        private void Loading_Load(object sender, EventArgs e)
        {
            this.Icon = Properties.Resources.TSS;
            timer.Start();
        }

        private void timer_Tick(object sender, EventArgs e)
        {
            timer.Stop();
            //服务器连接判断
            if (ConfigUtils.NetConnect == true)
            {
                #region 读取配置文件 
                MessageLBL.Text = "读取个人关注";
                LoadPB.Value = 10;
                MessageLBL.Refresh();
                Thread.Sleep(1000);
                ConfigUtils.LoadUsrConfig();
                #endregion

                #region 获取最新服务器列表
                MessageLBL.Text = "获取最新服务器列表";
                LoadPB.Value = 20;
                MessageLBL.Refresh();
                Thread.Sleep(1000);
                ConfigUtils.LoadServerListConfig();
                #endregion

                #region 获取资源更新
                MessageLBL.Text = "获取今日热播信息";
                LoadPB.Value = 30;
                Thread.Sleep(2000);
                ResourceUtils.LoadResourcesTop10();

                MessageLBL.Text = "获取全部分类信息";
                LoadPB.Value = 40;
                Thread.Sleep(2000);
                TypeUtils.LoadAllTypes();

                MessageLBL.Text = "获取最新资源信息";
                LoadPB.Value = 50;
                Thread.Sleep(2000);
                ResourceUtils.LoadAllResources("");

                MessageLBL.Text = "获取最新剧集信息";
                LoadPB.Value = 60;
                Thread.Sleep(2000);
                SeriesUtils.LoadAllSeries("");
                #endregion
                
                #region 更新个人订阅
                MessageLBL.Text = "获取我关注的分类信息";
                LoadPB.Value = 70;
                MessageLBL.Refresh();
                Thread.Sleep(1000);
                TypeUtils.LoadMyOrderTypes();
                
                MessageLBL.Text = "获得我关注的剧集信息";
                LoadPB.Value = 80;
                MessageLBL.Refresh();
                Thread.Sleep(1000);
                SeriesUtils.LoadMyOrderSeriess();
                #endregion
            }

            #region 加载成功
            LoadPB.Value = 90;
            MessageLBL.Text = "完成最后加载内容...";
            MessageLBL.Refresh();
            #endregion

            this.Close();
        }
    }
}
