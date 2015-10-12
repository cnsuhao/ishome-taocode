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

namespace TheSeed
{
    public partial class LoadNew : Form
    {
        public LoadNew()
        {
            InitializeComponent();
        }

        private void LoadNew_Load(object sender, EventArgs e)
        {
            this.Icon = Properties.Resources.TSS;
            timer.Start();
        }

        private void timer_Tick(object sender, EventArgs e)
        {
            timer.Stop();
            //获取今日资源更新信息TOP10
            MessageLBL.Text = "连接服务器获取更新";
            LoadPB.Value = 10;
            Thread.Sleep(500);
            ResourceUtils.LoadNewResources();

            //保存最新内容到本地
            MessageLBL.Text = "保存最新内容到本地";
            LoadPB.Value = 20;
            Thread.Sleep(500);
            ResourceUtils.SaveNewResources();

            //根据我的关注获取分类信息
            MessageLBL.Text = "获取我关注的分类信息";
            LoadPB.Value = 30;
            Thread.Sleep(500);
            TypeUtils.LoadMyOrderTypes();
           
            //保存最新内容到本地
            MessageLBL.Text = "保存我关注的分类资源信息";
            LoadPB.Value = 50;
            Thread.Sleep(500);
            TypeUtils.SaveMyOrderTypes();

            //根据我的关注获得剧集信息
            MessageLBL.Text = "获得我关注的剧集信息";
            LoadPB.Value = 70;
            Thread.Sleep(500);
            SeriesUtils.LoadMyOrderSeriess();           

            MessageLBL.Text = "保存我关注的剧集资源信息";
            LoadPB.Value = 90;
            Thread.Sleep(500);
            SeriesUtils.SaveMyOrderSeriess();

            MessageLBL.Text = "加载成功";
            LoadPB.Value = 100;
            Thread.Sleep(1000);
            SystemParam.NetConnect = true;

            this.Close();
        }
    }
}
