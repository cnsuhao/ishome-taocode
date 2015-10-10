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

            #region 读取配置文件 
            LoadPB.Value = 5;
            Thread.Sleep(500);
            ConfigUtils.LoadConfigFile();

            //获得系统基本配置文件
            LoadPB.Value = 10;
            Thread.Sleep(500);
            ConfigUtils.LoadBaseConfig();

            //获得个人关注
            LoadPB.Value = 15;
            Thread.Sleep(500);
            ConfigUtils.LoadMyConfig();
            #endregion

            #region 连接服务器获取更新
            //获取今日资源更新信息
            LoadPB.Value = 20;
            Thread.Sleep(500);
            ResourceUtils.LoadNewResources();

            //根据我的关注获取分类信息
            LoadPB.Value = 30;
            Thread.Sleep(500);
            TypeUtils.LoadNewTypes();

            //根据我的关注获得剧集信息
            LoadPB.Value = 50;
            Thread.Sleep(500);
            SeriesUtils.LoadNewSeriess();
            #endregion

            #region 保存最新内容到本地
            //保存分类信息
            LoadPB.Value = 70;
            Thread.Sleep(500);
            ResourceUtils.SaveNewResources();

            //保存资源信息
            LoadPB.Value = 80;
            Thread.Sleep(500);
            TypeUtils.SaveNewTypes();
            
            //保存关注剧集信息
            LoadPB.Value = 90;
            Thread.Sleep(500);
            SeriesUtils.SaveNewSeriess();
            #endregion

            #region 加载成功
            LoadPB.Value = 100;
            Thread.Sleep(1000);
            SystemParam.NetConnect = true;
            #endregion

            this.Close();
        }
    }
}
