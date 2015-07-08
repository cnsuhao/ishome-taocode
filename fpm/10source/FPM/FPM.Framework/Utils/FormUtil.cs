using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Windows.Controls;
using System.Reflection;

namespace FPM.Framework.Utils
{
    /// <summary>
    /// 基底画面共通工具类
    /// </summary>
    public partial class FormUtil
    {
        public static void Login()
        {
        }
        public static void LoginOut()
        { 
        }

        #region 等待控制<Background>
        /// <summary>
        /// 设定主画面等待事件响应控件(只有主画面使用)
        /// </summary>
        public static BusyIndicator Indicator { get; set; }
        /// <summary>
        /// 系统等待设定(无限制)
        /// </summary>
        public static void FormBusy(Boolean Busy)
        {
            if (Indicator != null)
                Indicator.IsBusy = Busy;
        } 
        #endregion

        private static UserControl LastBusinessFrom { get; set; }
        private static Object LastBusinessFromTitle { get; set; }
        public static Object BusinessFromData { get; set; }

        #region 基底容器控制，仅在首页展示前使用<Background> 
        /// <summary>
        /// 系统基底画面控制器
        /// </summary>
        public static Grid MainUIControl { get; set; }
        public static void ShowMainFrom(UserControl NowUserControl)
        {
            ShowMainFrom(NowUserControl, false);
        }
        public static void ShowMainFrom(UserControl NowUserControl, Boolean LastControlForm)
        {
            if (LastControlForm == true)
            {
                LastBusinessFrom = NowUserControl;
            }
            if (MainUIControl != null)
            {
                MainUIControl.Children.Clear();
                MainUIControl.Children.Add(NowUserControl);
            }
        }
        /// <summary>
        /// 返回历史画面，需要指定最后画面（参考方法<ShowBusinessFrom(UserControl,Boolean)>）
        /// </summary>
        public static void ShowLastMainFrom()
        {
            if (LastBusinessFrom != null)
            {
                MainUIControl.Children.Clear();
                MainUIControl.Children.Add(LastBusinessFrom);
            }
        } 
        #endregion

        #region 业务操作容器控制<SM0401>
        public static Label BussnessTitle { get; set; }
        public static void ShowBussnessTitle(String Title)
        {
            BussnessTitle.Content = Title;
        }
        /// <summary>
        /// 系统业务画面容器
        /// </summary>
        public static Grid BusinessUIControl { get; set; }
        public static void ShowBusinessFrom(UserControl NowUserControl)
        {
            ShowBusinessFrom(NowUserControl, false);
        }
        /// <summary>
        /// 根据画面ID直接跳转
        /// </summary>
        /// <param name="BusinessFromID"></param>
        public static void ShowBusinessFrom(String BusinessFromID)
        {
            ShowBusinessFrom(BusinessFromID, false);
        }
        /// <summary>
        /// 设定当前业务操作画面进行跳转
        /// </summary>
        /// 
        public static void ShowBusinessFrom(String BusinessFromID, Boolean LastControlForm)
        {
            //TODO
            AssemblyName abn = new AssemblyName("FPM.Business");
            Assembly tempAssembly = Assembly.Load(abn);
            ShowBusinessFrom(Activator.CreateInstance(tempAssembly.GetType(BusinessFromID)) as UserControl, LastControlForm);
        }

        /// <summary>
        /// 设定当前业务操作画面进行跳转
        /// </summary>
        /// <param name="NowUserControl">设定显示窗体对象</param>
        /// <param name="LastControlForm">设定为返回窗体</param>
        public static void ShowBusinessFrom(UserControl NowUserControl, Boolean LastControlForm)
        {
            if (LastControlForm == true)
            {
                LastBusinessFrom = NowUserControl;
                LastBusinessFromTitle = BussnessTitle.Content;
            }
            if (NowUserControl != null)
            {
                BusinessUIControl.Children.Clear();
                BusinessUIControl.Children.Add(NowUserControl);
            }
        }
        /// <summary>
        /// 返回历史画面，需要指定最后画面（参考方法<ShowBusinessFrom(UserControl,Boolean)>）
        /// </summary>
        public static void ShowLastBusinessFrom()
        {
            if (LastBusinessFrom != null)
            {
                BusinessUIControl.Children.Clear();
                BusinessUIControl.Children.Add(LastBusinessFrom);
                BussnessTitle.Content = LastBusinessFromTitle ;
            }
        }
        #endregion

        ///// <summary>
        ///// 设定系统业务画面容器窗体对象
        ///// </summary>
        //public static void SetMainControlGrid(Grid UIControl)
        //{
        //    MainUserControl = UIControl;
        //    if (MainUserControl.Children.Count > 0)
        //    {
        //        if ((MainUserControl.Children[0]).GetType() == typeof(UserControl))
        //        {
        //            LastBusinessFrom = (UserControl)MainUserControl.Children[0];
        //        }
        //    }
        //}
        #region 加载进度条控件<SM0503>
        /// <summary>
        /// 交互加载进度
        /// </summary>
        public static ProgressBar BusinessProgress { get; set; } 
        #endregion

        #region 即时信息控制<SM0502>
        /// <summary>
        /// 提示信息控件
        /// </summary>
        public static TextBlock OnTimeMessage { get; set; }//通信显示
        /// <summary>
        /// 设定即时显示信息
        /// </summary>
        public static void ShowOnTimeMessge(String OnTimeMessge)
        {
            if (OnTimeMessage != null)
                OnTimeMessage.Text = OnTimeMessge;
        } 
        /// <summary>
        /// 设定即时显示信息
        /// </summary>
        public static void ShowOnTimeMessge(FPM.Common.Data.RESTResultBean ServiceRestlt)
        {
            if (ServiceRestlt == null)
                OnTimeMessage.Text = "系统异常";
            OnTimeMessage.Text = ServiceRestlt.resultmessage;
        }
        #endregion

        #region 右下角弹出信息容器<SM0402>
        private static System.Windows.Threading.DispatcherTimer MessagePopupControl;//右下角弹出信息显示
        /// <summary>
        /// 右下角弹出信息显示
        /// </summary>
        public static Grid PopupFormControl { get; set; }
        /// <summary>
        /// 设定当前业务操作画面进行跳转
        /// </summary>
        public static void ShowPopupMessage(UserControl NowUserControl)
        {
            if (MessagePopupControl != null && PopupFormControl != null && NowUserControl != null)
            {
                PopupFormControl.Children.Clear();
                PopupFormControl.Children.Add(NowUserControl);
                MessagePopupControl.Start();
            }
            else
            {
                try
                {
                    ((Canvas)((Canvas)((Grid)PopupFormControl.Parent).Parent).Parent).Visibility = System.Windows.Visibility.Collapsed;
                }
                catch (Exception) { }
            }
        }

        /// <summary>
        /// 设定系统业务画面容器窗体对象
        /// </summary>
        public static void SetMessageControl(System.Windows.Threading.DispatcherTimer UIPopup, Grid UIControl)
        {
            MessagePopupControl = UIPopup;
        }
        #endregion

        #region 中部弹出一览选择信息容器<SM0402>
        public static UserControl PopupListForm { get; set; }
        #endregion
    }
}
