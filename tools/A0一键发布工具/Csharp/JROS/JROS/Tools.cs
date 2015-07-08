using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Collections;
using System.ServiceProcess;
using Microsoft.Win32;
using System.Configuration.Install;
using System.Diagnostics;
using System.Runtime.InteropServices;
using System.Net;
namespace JROS
{ 
    /// <summary>
    /// 启动退出视觉效果
    /// </summary>
    class WindowStyle
    {
        /// <summary>
        /// 窗体动画函数    注意：要引用System.Runtime.InteropServices;
        /// </summary>
        /// <param name="hwnd">指定产生动画的窗口的句柄</param>
        /// <param name="dwTime">指定动画持续的时间</param>
        /// <param name="dwFlags">指定动画类型，可以是一个或多个标志的组合。</param>
        /// <returns></returns>
        [DllImport("user32")]
        public static extern bool AnimateWindow(IntPtr hwnd, int dwTime, int dwFlags);

        //下面是可用的常量，根据不同的动画效果声明自己需要的
        public const int AW_HOR_POSITIVE = 0x0001;//自左向右显示窗口，该标志可以在滚动动画和滑动动画中使用。使用AW_CENTER标志时忽略该标志
        public const int AW_HOR_NEGATIVE = 0x0002;//自右向左显示窗口，该标志可以在滚动动画和滑动动画中使用。使用AW_CENTER标志时忽略该标志
        public const int AW_VER_POSITIVE = 0x0004;//自顶向下显示窗口，该标志可以在滚动动画和滑动动画中使用。使用AW_CENTER标志时忽略该标志
        public const int AW_VER_NEGATIVE = 0x0008;//自下向上显示窗口，该标志可以在滚动动画和滑动动画中使用。使用AW_CENTER标志时忽略该标志该标志
        public const int AW_CENTER = 0x0010;//若使用了AW_HIDE标志，则使窗口向内重叠；否则向外扩展
        public const int AW_HIDE = 0x10000;//隐藏窗口
        public const int AW_ACTIVE = 0x20000;//激活窗口，在使用了AW_HIDE标志后不要使用这个标志
        public const int AW_SLIDE = 0x40000;//使用滑动类型动画效果，默认为滚动动画类型，当使用AW_CENTER标志时，这个标志就被忽略
        public const int AW_BLEND = 0x80000;//使用淡入淡出效果
    }
    class ServerServiceUtils
    {
        /// <summary>
        /// 调用服务器
        /// </summary>
        /// <param name="URL"></param>
        /// <param name="Method"></param>
        /// <param name="Data"></param>
        /// <returns></returns>
        public static Boolean ServerDownloadString(String URL)
        {
            try
            {
                WebClient client = new WebClient();
                client.Encoding = Encoding.UTF8;
                client.Headers.Add("Content-Type", "application/x-www-form-urlencoded");//采取POST方式必须加的
                String result = client.DownloadString(URL);
                if (!String.IsNullOrEmpty(result) && result.Equals("true"))
                {
                    return true;
                }
            }
            catch (Exception e)
            {
                throw e;
            }
            return false;
        }

        ///// <summary>
        ///// 调用服务器
        ///// </summary>
        ///// <param name="URL"></param>
        ///// <param name="Method"></param>
        ///// <param name="Data"></param>
        ///// <returns></returns>
        //public static Boolean DFBUploadString(String URL, String Data)
        //{
        //    try
        //    {
        //        WebClient client = new WebClient();
        //        client.Encoding = Encoding.UTF8;
        //        client.Headers.Add("Content-Type", "application/x-www-form-urlencoded");//采取POST方式必须加的
        //        String result = client.UploadString(URL, Data);
        //        if (!String.IsNullOrEmpty(result))
        //        {
        //            return true;
        //        }
        //    }
        //    catch (Exception e)
        //    {
        //        throw e;
        //    }
        //    return false;
        //}
    }

    class DosCommandUtils
    {
        public static void DoDosCMD(String DosCommand)
        {
            //注册TOMCAT服务
            try
            {
                Process p = null;
                p = new Process();
                p.StartInfo.FileName = "cmd.exe";
                p.StartInfo.UseShellExecute = false;
                p.StartInfo.RedirectStandardInput = true;
                p.StartInfo.RedirectStandardOutput = true;
                p.StartInfo.RedirectStandardError = true;
                p.StartInfo.CreateNoWindow = true;
                p.Start();
                p.StandardInput.WriteLine(DosCommand);
                p.StandardInput.WriteLine("exit");
                //while (p.StandardOutput.EndOfStream)
                //{
                //    strOutput = p.StandardOutput.ReadLine();
                //    Console.WriteLine(strOutput);
                //}
                p.WaitForExit();
                p.Close();
            }
            catch (Exception)
            {
               
            }

        }
    }


    class RegistUtils
    {
        public static void SetMemcachedRegist(String MemExePath,int port,int size)
        {
            //String registData;
            RegistryKey key = Registry.LocalMachine.OpenSubKey("SYSTEM", true).OpenSubKey("CurrentControlSet", true)
                .OpenSubKey("Services", true).OpenSubKey("memcached", true);
            //registData = key.GetValue("ImagePath").ToString();
            key.SetValue("ImagePath", "\"" + MemExePath + "\" -d runservice -p " + port + " -m " + size);
        }
    }

    class WindowsServiceUtils
    {
        #region 安装服务
        /// <summary>   
        /// 安装服务   
        /// </summary>   
        private bool InstallService(string NameService)
        {
            bool flag = true;
            if (!IsServiceIsExisted(NameService))
            {
                try
                {
                    string location = System.Reflection.Assembly.GetExecutingAssembly().Location;
                    string serviceFileName = location.Substring(0, location.LastIndexOf('\\') + 1) + NameService + ".exe";
                    InstallmyService(null, serviceFileName);
                }
                catch
                {
                    flag = false;
                }

            }
            return flag;
        }
        #endregion

        #region 卸载服务
        /// <summary>   
        /// 卸载服务   
        /// </summary>   
        private bool UninstallService(string NameService)
        {
            bool flag = true;
            if (IsServiceIsExisted(NameService))
            {
                try
                {
                    string location = System.Reflection.Assembly.GetExecutingAssembly().Location;
                    string serviceFileName = location.Substring(0, location.LastIndexOf('\\') + 1) + NameService + ".exe";
                    UnInstallmyService(serviceFileName);
                }
                catch
                {
                    flag = false;
                }
            }
            return flag;
        }
        #endregion

        #region 检查服务存在的存在性
        /// <summary>   
        /// 检查服务存在的存在性   
        /// </summary>   
        /// <param name=" NameService ">服务名</param>   
        /// <returns>存在返回 true,否则返回 false;</returns>   
        public static bool IsServiceIsExisted(string NameService)
        {
            ServiceController[] services = ServiceController.GetServices();
            foreach (ServiceController s in services)
            {
                if (s.ServiceName.ToLower() == NameService.ToLower())
                {
                    return true;
                }
            }
            return false;
        }
        #endregion

        #region 安装Windows服务
        /// <summary>   
        /// 安装Windows服务   
        /// </summary>   
        /// <param name="stateSaver">集合</param>   
        /// <param name="filepath">程序文件路径</param>   
        public static void InstallmyService(IDictionary stateSaver, string filepath)
        {
            AssemblyInstaller AssemblyInstaller1 = new AssemblyInstaller();
            AssemblyInstaller1.UseNewContext = true;
            AssemblyInstaller1.Path = filepath;
            AssemblyInstaller1.Install(stateSaver);
            AssemblyInstaller1.Commit(stateSaver);
            AssemblyInstaller1.Dispose();
        }
        #endregion

        #region 卸载Windows服务
        /// <summary>   
        /// 卸载Windows服务   
        /// </summary>   
        /// <param name="filepath">程序文件路径</param>   
        public static void UnInstallmyService(string filepath)
        {
            AssemblyInstaller AssemblyInstaller1 = new AssemblyInstaller();
            AssemblyInstaller1.UseNewContext = true;
            AssemblyInstaller1.Path = filepath;
            AssemblyInstaller1.Uninstall(null);
            AssemblyInstaller1.Dispose();
        }
        #endregion

        #region 判断window服务是否启动
        /// <summary>   
        /// 判断某个Windows服务是否启动   
        /// </summary>   
        /// <returns></returns>   
        public static bool IsServiceStart(string serviceName)
        {
            ServiceController psc = new ServiceController(serviceName);
            bool bStartStatus = false;
            try
            {
                if (!psc.Status.Equals(ServiceControllerStatus.Stopped))
                {
                    bStartStatus = true;
                }

                return bStartStatus;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }
        #endregion

        #region  修改服务的启动项
        /// <summary>     
        /// 修改服务的启动项 2为自动,3为手动     
        /// </summary>     
        /// <param name="startType"></param>     
        /// <param name="serviceName"></param>     
        /// <returns></returns>     
        public static bool ChangeServiceStartType(int startType, string serviceName)
        {
            try
            {
                RegistryKey regist = Registry.LocalMachine;
                RegistryKey sysReg = regist.OpenSubKey("SYSTEM");
                RegistryKey currentControlSet = sysReg.OpenSubKey("CurrentControlSet");
                RegistryKey services = currentControlSet.OpenSubKey("Services");
                RegistryKey servicesName = services.OpenSubKey(serviceName, true);
                servicesName.SetValue("Start", startType);
            }
            catch (Exception ex)
            {

                return false;
            }
            return true;


        }
        #endregion

        #region 启动服务
        private bool StartService(string serviceName)
        {
            bool flag = true;
            if (IsServiceIsExisted(serviceName))
            {
                System.ServiceProcess.ServiceController service = new System.ServiceProcess.ServiceController(serviceName);
                if (service.Status != System.ServiceProcess.ServiceControllerStatus.Running && service.Status != System.ServiceProcess.ServiceControllerStatus.StartPending)
                {
                    service.Start();
                    for (int i = 0; i < 60; i++)
                    {
                        service.Refresh();
                        System.Threading.Thread.Sleep(1000);
                        if (service.Status == System.ServiceProcess.ServiceControllerStatus.Running)
                        {
                            break;
                        }
                        if (i == 59)
                        {
                            flag = false;
                        }
                    }
                }
            }
            return flag;
        }
        #endregion

        #region 停止服务
        private bool StopService(string serviceName)
        {
            bool flag = true;
            if (IsServiceIsExisted(serviceName))
            {
                System.ServiceProcess.ServiceController service = new System.ServiceProcess.ServiceController(serviceName);
                if (service.Status == System.ServiceProcess.ServiceControllerStatus.Running)
                {
                    service.Stop();
                    for (int i = 0; i < 60; i++)
                    {
                        service.Refresh();
                        System.Threading.Thread.Sleep(1000);
                        if (service.Status == System.ServiceProcess.ServiceControllerStatus.Stopped)
                        {
                            break;
                        }
                        if (i == 59)
                        {
                            flag = false;
                        }
                    }
                }
            }
            return flag;
        }
        #endregion

    }
}
