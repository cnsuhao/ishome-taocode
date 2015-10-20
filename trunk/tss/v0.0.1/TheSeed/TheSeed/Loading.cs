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
                //获得个人关注
                MessageLBL.Text = "读取个人关注";
                LoadPB.Value = 10;
                MessageLBL.Refresh();
                Thread.Sleep(1000);
                ConfigUtils.LoadUsrConfig();
                #endregion

                #region 连接服务器获取更新TOP10
                //获取今日资源更新信息
                MessageLBL.Text = "连接服务器获取更新";
                LoadPB.Value = 20;
                MessageLBL.Refresh();
                Thread.Sleep(1000);
                ResourceUtils.LoadResourcesTop10();

                //根据我的关注获取分类信息
                MessageLBL.Text = "获取分类信息";
                LoadPB.Value = 30;
                MessageLBL.Refresh();
                Thread.Sleep(1000);
                TypeUtils.LoadAllTypes();

                //根据我的关注获取分类信息
                MessageLBL.Text = "获取我关注的分类信息";
                LoadPB.Value = 40;
                MessageLBL.Refresh();
                Thread.Sleep(1000);
                TypeUtils.LoadMyOrderTypes();

                //根据我的关注获得剧集信息
                MessageLBL.Text = "获得我关注的剧集信息";
                LoadPB.Value = 50;
                MessageLBL.Refresh();
                Thread.Sleep(1000);
                SeriesUtils.LoadMyOrderSeriess();
                #endregion

                #region 保存最新内容到本地
                //保存分类信息
                MessageLBL.Text = "保存分类信息";
                LoadPB.Value = 60;
                MessageLBL.Refresh();
                Thread.Sleep(1000);
                TypeUtils.SaveAllTypes();

                //保存分类信息
                MessageLBL.Text = "保存最新资源信息";
                LoadPB.Value = 70;
                MessageLBL.Refresh();
                Thread.Sleep(1000);
                ResourceUtils.SaveResourcesTop10();

                //保存分类资源信息
                MessageLBL.Text = "保存我关注的分类资源信息";
                LoadPB.Value = 80;
                MessageLBL.Refresh();
                Thread.Sleep(1000);
                TypeUtils.SaveMyOrderTypes();

                //保存关注剧集信息
                MessageLBL.Text = "保存我关注的剧集资源信息";
                LoadPB.Value = 90;
                MessageLBL.Refresh();
                Thread.Sleep(1000);
                SeriesUtils.SaveMyOrderSeriess();
                #endregion
            }

            #region 加载成功
            LoadPB.Value = 100;
            MessageLBL.Text = "完成最后加载内容...";
            MessageLBL.Refresh();
            #endregion

            this.Close();
        }
    }
}
