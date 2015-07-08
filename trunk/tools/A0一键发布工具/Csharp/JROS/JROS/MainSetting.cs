using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Collections;
using System.IO;
using System.Diagnostics;
using JROS.Properties;
using System.ServiceProcess;

namespace JROS
{
    public partial class MainSetting : Form
    {
        public MainSetting()
        {
            InitializeComponent();
        }

        private void SetMessage(String msg,int val)
        {
            Message.Text = msg;
            progress.Value = val;
        }

        private void MainSetting_Load(object sender, EventArgs e)
        {
            //700*460
            this.Text = this.Text + " v1.1";
            this.Icon = Resources.logo;
            this.Step2.Visible = false;
            this.Step3.Visible = false;
            this.Step4.Visible = false;
            this.Step5.Visible = false;
            #region 默认设定
            splitContainer1.IsSplitterFixed = true;

            DIR_TOMCAT.Text = Application.StartupPath + "\\Tomcat";
            DIR_JDK.Text = Application.StartupPath + "\\Jdk";
            DIR_MYSQL.Text = Application.StartupPath + "\\Mysql";
            DIR_MEMCACHE.Text = Application.StartupPath + "\\Memcached";

            DIR_INSTALL.Text = Application.StartupPath + "\\Project\\install";
            DIR_PROJECT.Text = Application.StartupPath + "\\Project\\ROOT";
            DIR_LIB.Text = Application.StartupPath + "\\Project\\lib";
            DIR_SQL.Text = Application.StartupPath + "\\Project\\SQL";

            CONFIG_FILE.Text = Application.StartupPath + "\\Project\\ROOT\\WEB-INF\\classes\\config.properties";

            FileInfo f = new FileInfo(CONFIG_FILE.Text);

            if (f.Exists == true && File.Exists(Application.StartupPath + "\\Properties.XML") == false)
            {
                //读取配置文件
                String[] value = File.ReadAllLines(f.FullName);
                Dictionary<String, String> ht = new Dictionary<String, String>();
                foreach (String item in value)
                {
                    if (item.IndexOf('#') == 0 || item.IndexOf('=') == -1)
                        continue;
                    ht.Add(item.Substring(0, item.IndexOf('=')).Trim(), item.Substring(item.IndexOf('=') + 1).Trim());
                }


                foreach (KeyValuePair<String, String> item in ht)
                {
                    ConfigSetting.ConfigRow bcsr = this.configSetting.Config.NewConfigRow();
                    bcsr.Puk = this.configSetting.Config.Rows.Count;
                    bcsr.Key = item.Key;
                    bcsr.Value = item.Value;
                    this.configSetting.Config.AddConfigRow(bcsr);
                }
                this.configSetting.Config.WriteXml("Properties.XML");
                this.configSetting.Config.Clear();
            }

            //数据库配置
            try
            {
                FileInfo d = new FileInfo(Application.StartupPath + "\\Project\\install\\WEB-INF\\classes\\jdbc.properties");
                //读取配置文件
                String[] value = File.ReadAllLines(d.FullName);
                Dictionary<String, String> ht = new Dictionary<String, String>();
                foreach (String item in value)
                {
                    if (item.IndexOf('#') == 0 || item.IndexOf('=') == -1)
                        continue;
                    ht.Add(item.Substring(0, item.IndexOf('=')).Trim(), item.Substring(item.IndexOf('=') + 1).Trim());
                }
                DB_DRIVER.Text = ht["work.driverClassName"];
                DB_URI.Text = ht["work.url"];
                DB_USER.Text = ht["work.username"];
                DB_PWD.Text = ht["work.password"];
            }
            catch (Exception)
            {
            }

            #endregion

            try
            {
                this.configSetting.Config.ReadXml("Properties.XML");
            }
            catch (Exception)
            {
            }
            try
            {
                ConfigSetting.ConfigDataTable bcs = new ConfigSetting.ConfigDataTable();
                bcs.ReadXml("Config.XML");
                Dictionary<String, String> ht = new Dictionary<String, String>();
                foreach (ConfigSetting.ConfigRow item in bcs.Rows)
                {
                    ht.Add(item.Key, item.Value);
                }

                DIR_TOMCAT.Text = ht[DIR_TOMCAT.Name];
                DIR_JDK.Text = ht[DIR_JDK.Name];
                DIR_MYSQL.Text = ht[DIR_MYSQL.Name];
                DIR_MEMCACHE.Text = ht[DIR_MEMCACHE.Name];

                DIR_LIB.Text = ht[DIR_LIB.Name];
                DIR_INSTALL.Text = ht[DIR_INSTALL.Name];
                DIR_SQL.Text = ht[DIR_SQL.Name];
                DIR_PROJECT.Text = ht[DIR_PROJECT.Name];

                TOMCAT_OTHER.Text = ht[TOMCAT_OTHER.Name];
                TOMCAT_RUN.Text = ht[TOMCAT_RUN.Name];
                TOMCAT_RUN_SAFE.Text = ht[TOMCAT_RUN_SAFE.Name];
                TOMCAT_SHUTDOWN.Text = ht[TOMCAT_SHUTDOWN.Name];

                MEM_RUN.Text = ht[MEM_RUN.Name];
                MEM_SIZE.Text = ht[MEM_SIZE.Name];

                CONFIG_FILE.Text = ht[CONFIG_FILE.Name];

                DB_DRIVER.Text = ht[DB_DRIVER.Name];
                DB_URI.Text = ht[DB_URI.Name];
                DB_USER.Text = ht[DB_USER.Name];
                DB_PWD.Text = ht[DB_PWD.Name];
            }
            catch (Exception) { }
        }

        private void Install_Click(object sender, EventArgs e)
        {
            try
            {
                if (VERSION_TARGET.SelectedIndex < 0)
                {
                    MessageBox.Show("请选择发布版本", "警告", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
            #region 保存配置
            {
                this.configSetting.Config.WriteXml("Properties.XML");
                SetMessage("参数配置保存完成", 10);
                /////////////////////////////////////////////
                Dictionary<String, String> ht = new Dictionary<String, String>();
                ht.Add(DIR_TOMCAT.Name, DIR_TOMCAT.Text);
                ht.Add(DIR_JDK.Name, DIR_JDK.Text);
                ht.Add(DIR_MEMCACHE.Name, DIR_MEMCACHE.Text);
                ht.Add(DIR_MYSQL.Name, DIR_MYSQL.Text);                

                ht.Add(DIR_LIB.Name, DIR_LIB.Text);
                ht.Add(DIR_INSTALL.Name, DIR_INSTALL.Text);
                ht.Add(DIR_SQL.Name, DIR_SQL.Text);
                ht.Add(DIR_PROJECT.Name, DIR_PROJECT.Text);


                ht.Add(TOMCAT_OTHER.Name, TOMCAT_OTHER.Text);
                ht.Add(TOMCAT_RUN.Name, TOMCAT_RUN.Text);
                ht.Add(TOMCAT_RUN_SAFE.Name, TOMCAT_RUN_SAFE.Text);
                ht.Add(TOMCAT_SHUTDOWN.Name, TOMCAT_SHUTDOWN.Text);
                ht.Add(MEM_RUN.Name, MEM_RUN.Text);
                ht.Add(MEM_SIZE.Name, MEM_SIZE.Text);               

                ht.Add(CONFIG_FILE.Name, CONFIG_FILE.Text);

                ht.Add(DB_DRIVER.Name, DB_DRIVER.Text);
                ht.Add(DB_URI.Name, DB_URI.Text);
                ht.Add(DB_USER.Name, DB_USER.Text);
                ht.Add(DB_PWD.Name, DB_PWD.Text);

                ConfigSetting.ConfigDataTable bcs = new ConfigSetting.ConfigDataTable();
                foreach (KeyValuePair<String, String> item in ht)
                {
                    ConfigSetting.ConfigRow bcsr = bcs.NewConfigRow();
                    bcsr.Puk = bcs.Rows.Count;
                    bcsr.Key = item.Key;
                    bcsr.Value = item.Value;
                    bcs.AddConfigRow(bcsr);
                }
                bcs.WriteXml("Config.XML");
                SetMessage("环境配置保存完成", 20);
            }
            #endregion
            #region 设定配置
            {
                #region 设定系统参数
                FileInfo f = new FileInfo(CONFIG_FILE.Text);
                if (f.Exists == false)
                {
                    MessageBox.Show("请正确设定系统核心配置文件路径");
                    CONFIG_FILE.Focus();
                    return;
                }

                StringBuilder sb = new StringBuilder();                
                foreach (ConfigSetting.ConfigRow item in configSetting.Config.Rows)
                {
                    sb.AppendLine(item.Key + "=" + item.Value);
                }

                File.WriteAllText(CONFIG_FILE.Text, sb.ToString());
                SetMessage("系统参数设定成功", 30);
                #endregion
            }
            String ApplicationDriver = Application.StartupPath.Substring(0, 2);
            {
                #region 设定TOMCAT端口
                DirectoryInfo di = new DirectoryInfo(DIR_TOMCAT.Text);
                if(di.Exists == false)
                {
                    MessageBox.Show("请正确设定Tomcat目录");
                    DIR_TOMCAT.Focus();
                    return;
                }
                if (File.Exists(di.FullName + "\\conf\\server-bak.xml") == false)
                    File.Copy(di.FullName + "\\conf\\server.xml", di.FullName + "\\conf\\server-bak.xml");
                else
                    File.Copy(di.FullName + "\\conf\\server-bak.xml", di.FullName + "\\conf\\server.xml", true);
                FileInfo f = new FileInfo(di.FullName + "\\conf\\server.xml");
                String value = File.ReadAllText(f.FullName);
                value = value.Replace("8005", TOMCAT_SHUTDOWN.Text).Replace("8080", TOMCAT_RUN.Text).Replace("8443", TOMCAT_RUN_SAFE.Text).Replace("8009", TOMCAT_OTHER.Text);
                File.WriteAllText(f.FullName,value);
                SetMessage("TOMCAT端口设定成功", 40);
                #endregion
            }
            {
                DirectoryInfo di;
                ConfigSetting.ConfigDataTable scs = new ConfigSetting.ConfigDataTable();
                #region 获得SQL清单
                {
                    //发布路径设定
                    //org.isotopes.jfp.business.install.sql
                    String newName = DIR_INSTALL.Text + "\\WEB-INF\\classes\\org\\isotopes\\jfp\\business\\install\\sql\\";
                    Directory.CreateDirectory(newName);
                    //制作SQL清单配置（需要插入到数据库）
                    foreach (String ditem in Directory.GetDirectories(DIR_SQL.Text))
                    {
                        di = new DirectoryInfo(ditem);
                        foreach (FileInfo fitem in di.GetFiles("*.sql"))
                        {
                            ConfigSetting.ConfigRow scsr = scs.NewConfigRow();
                            scsr.Puk = scs.Rows.Count;
                            scsr.Key = fitem.Name;
                            scsr.Value = (fitem.Length / 1024).ToString();
                            scs.AddConfigRow(scsr);
                            //根据版本ID判断是否运行
                            fitem.CopyTo(newName + fitem.Name,true);
                        }
                        if (ditem.IndexOf(VERSION_TARGET.Text) > 0)
                        {
                            break;
                        }
                    }
                }
                //创建备份目录
                if(Directory.Exists(Application.StartupPath + "\\BAK"))
                    Directory.Delete(Application.StartupPath + "\\BAK", true);
                Directory.CreateDirectory(Application.StartupPath + "\\BAK");
                //Directory.Move(DIR_SQL.Text,Application.StartupPath+"\\BAK\\SQL");
                scs.WriteXml("Version.XML");
                SetMessage("版本升级发布清单制作成功", 45);
                //拷贝SQL清单
                //File.Move("Version.XML", DIR_INSTALL.Text + "\\WEB-INF\\classes\\Version.XML");
                SetMessage("版本发布准备成功", 50);
                #endregion
                #region 部署运行
                //拷贝ROOT
                SetMessage("开始发布项目...", 55);
                if (DIR_PROJECT.Text.EndsWith("ROOT"))
                    if (Directory.Exists(DIR_TOMCAT.Text + "\\webapps\\ROOT"))
                        Directory.Delete(DIR_TOMCAT.Text + "\\webapps\\ROOT", true);
                di = new DirectoryInfo(DIR_PROJECT.Text);
                CopyDirectory(DIR_PROJECT.Text, DIR_TOMCAT.Text + "\\webapps\\" + di.Name);//移动工程
                //Directory.Move(DIR_PROJECT.Text, Application.StartupPath + "\\BAK\\" + di.Name);//移动工程
                CopyDirectory(DIR_LIB.Text, DIR_TOMCAT.Text + "\\webapps\\" + di.Name + "\\WEB-INF\\lib");//拷贝lib 
                SetMessage("项目发布成功！", 60);
                //拷贝install
                FileInfo d = new FileInfo(Application.StartupPath + "\\Project\\install\\WEB-INF\\classes\\jdbc.properties");
                StringBuilder sb = new StringBuilder();
                sb.AppendLine("work.driverClassName="+DB_DRIVER.Text);
                sb.AppendLine("work.url="+DB_URI.Text);
                sb.AppendLine("work.username="+DB_USER.Text);
                sb.AppendLine("work.password="+DB_PWD.Text);
                File.WriteAllText(d.FullName, sb.ToString());
                SetMessage("开始发布安装环境...", 65);
                di = new DirectoryInfo(DIR_INSTALL.Text);
                CopyDirectory(DIR_INSTALL.Text, DIR_TOMCAT.Text + "\\webapps\\" + di.Name);
                //Directory.Move(DIR_INSTALL.Text, Application.StartupPath + "\\BAK\\" + di.Name);
                CopyDirectory(DIR_LIB.Text, DIR_TOMCAT.Text + "\\webapps\\" + di.Name + "\\WEB-INF\\lib");
                //Directory.Move(DIR_LIB.Text, Application.StartupPath + "\\BAK\\lib");
                SetMessage("安装环境发布成功", 70);

                #region 注册缓存服务
                sb = new StringBuilder();
                sb.AppendLine(ApplicationDriver);
                sb.AppendLine("cd " + DIR_MEMCACHE.Text);
                sb.AppendLine("memcached.exe -d install");
                DosCommandUtils.DoDosCMD(sb.ToString());
                if (WindowsServiceUtils.IsServiceIsExisted("memcached") == false)
                {
                    SetMessage("缓存服务注册失败，请手动解决！", 75);
                    MessageBox.Show("缓存服务注册失败，请手动解决！", "警告", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                } 
                SetMessage("缓存服务注册成功", 75);

                //修改注册表
                RegistUtils.SetMemcachedRegist(DIR_MEMCACHE.Text + "\\memcached.exe", Int32.Parse(MEM_RUN.Text), Int32.Parse(MEM_SIZE.Text));

                //ServiceController psc = new ServiceController("memcached");
                //String[] CommandLine = new String[1];
                //CommandLine[0] = " -p 8848 -m 256";
                //psc.Start(CommandLine);
                sb = new StringBuilder();
                sb.AppendLine("net start memcached");
                DosCommandUtils.DoDosCMD(sb.ToString());
                if (WindowsServiceUtils.IsServiceStart("memcached") == false)
                {
                    SetMessage("缓存服务启动失败，请手动解决！", 80);
                    MessageBox.Show("缓存服务启动失败，请手动解决！", "警告", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
                SetMessage("缓存服务启动成功", 80);
                #endregion

                //注册TOMCAT服务
                sb = new StringBuilder();
                sb.AppendLine(ApplicationDriver);
                sb.AppendLine("cd " + this.DIR_MEMCACHE.Text);
                sb.AppendLine("set JAVA_HOME=" + DIR_JDK.Text);
                sb.AppendLine("call service.bat install " + SERVICE_NAME.Text);
                DosCommandUtils.DoDosCMD(sb.ToString());

                if(WindowsServiceUtils.IsServiceIsExisted(SERVICE_NAME.Text)==false)
                {
                    SetMessage("TOMCAT注册失败，请手动解决！", 85);
                    MessageBox.Show("TOMCAT注册失败，请手动解决！", "警告", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                } 
                SetMessage("TOMCAT注册成功", 85);

                //启动TOMCAT 
                sb = new StringBuilder();
                sb.AppendLine("net start " + SERVICE_NAME.Text);
                DosCommandUtils.DoDosCMD(sb.ToString());
                if (WindowsServiceUtils.IsServiceStart(SERVICE_NAME.Text) == false)
                {
                    SetMessage("TOMCAT启动失败，请手动解决！", 90);
                    MessageBox.Show("TOMCAT启动失败，请手动解决！", "警告", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }
                SetMessage("TOMCAT启动成功", 90);
                //运行IE,请求安装SQL
                //http://127.0.0.1:8080/install/DBC/Init.go
                //http://127.0.0.1:8888/DBC/Init.go
                Boolean bk = ServerServiceUtils.ServerDownloadString("http://127.0.0.1:" + TOMCAT_RUN.Text + "/DBC/Init.go");
                if (bk)
                {
                    SetMessage("数据库安装成功", 95);
                    //安装成功后,删除SQL文件
                    di = new DirectoryInfo(DIR_INSTALL.Text);
                    String newName = DIR_TOMCAT.Text + "\\webapps\\" + di.Name + "\\WEB-INF\\classes\\org\\isotopes\\jfp\\business\\install\\sql\\";
                    Directory.Delete(newName,true);
                    SetMessage("部署成功，请登录系统", 100);
                    MessageBox.Show("部署成功，请登录系统", "成功", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    System.Diagnostics.Process.Start("http://127.0.0.1:" + TOMCAT_RUN.Text);
                    Application.Exit();
                }
                else
                {
                    MessageBox.Show("发生未知错误，请检查TOMCAT下面相关日志！\r\n", "警告", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                #endregion
            }
            #endregion
            }
            catch (Exception Ex)
            {
                MessageBox.Show("发生未知错误，请联系管理员！\r\n"+Ex.Message,"警告",MessageBoxButtons.OK,MessageBoxIcon.Error);
            }
        }
        private void CopyDirectory(string srcDir, string tgtDir)
        {
            DirectoryInfo source = new DirectoryInfo(srcDir);
            DirectoryInfo target = new DirectoryInfo(tgtDir);

            if (target.FullName.StartsWith(source.FullName, StringComparison.CurrentCultureIgnoreCase))
            {
                throw new Exception("父目录不能拷贝到子目录！");
            }

            if (!source.Exists)
            {
                return;
            }

            if (!target.Exists)
            {
                target.Create();
            }

            FileInfo[] files = source.GetFiles();

            for (int i = 0; i < files.Length; i++)
            {
                File.Copy(files[i].FullName, target.FullName + @"\" + files[i].Name, true);
            }

            DirectoryInfo[] dirs = source.GetDirectories();

            for (int j = 0; j < dirs.Length; j++)
            {
                CopyDirectory(dirs[j].FullName, target.FullName + @"\" + dirs[j].Name);
            }
        } 

        private void SelectPath(TextBox sender) 
        {
            folderBrowserDialog.SelectedPath = sender.Text;
            folderBrowserDialog.ShowDialog();
            sender.Text = folderBrowserDialog.SelectedPath;
        }

        private void DIR_JDK_BTN_Click(object sender, EventArgs e)
        {
            SelectPath(DIR_JDK);
        }

        private void DIR_TOMCAT_BTN_Click(object sender, EventArgs e)
        {
            SelectPath(DIR_TOMCAT);
        }

        private void DIR_MYSQL_BTN_Click(object sender, EventArgs e)
        {
            SelectPath(DIR_MYSQL);
        }

        private void DIR_INSTALL_BTN_Click(object sender, EventArgs e)
        {
            SelectPath(DIR_INSTALL);
        }

        private void DIR_LIB_BTN_Click(object sender, EventArgs e)
        {
            SelectPath(DIR_LIB);
        }

        private void DIR_SQL_BTN_Click(object sender, EventArgs e)
        {
            SelectPath(DIR_SQL);
        }

        private void DIR_PROJECT_BTN_Click(object sender, EventArgs e)
        {
            SelectPath(DIR_PROJECT);
        }

        private void CONFIG_FILE_BTN_Click(object sender, EventArgs e)
        {
            openFileDialog.FileName = CONFIG_FILE.Text;
            openFileDialog.ShowDialog();
            CONFIG_FILE.Text = openFileDialog.FileName;
        }

        private void Remove_Click(object sender, EventArgs e)
        {
            String ApplicationDriver = Application.StartupPath.Substring(0, 2);
            StringBuilder sb = new StringBuilder();
            sb.AppendLine(ApplicationDriver);
            sb.AppendLine("cd " + DIR_TOMCAT.Text + "\\bin");
            sb.AppendLine("set JAVA_HOME=" + DIR_JDK.Text);
            sb.AppendLine("call service.bat remove " + SERVICE_NAME.Text);

            sb.AppendLine("call service.bat remove " + SERVICE_NAME.Text);
            DosCommandUtils.DoDosCMD(sb.ToString());
            SetMessage("TOMCAT服务卸载成功", 100);
        }

        private void VersionRefresh()
        {
            if (VERSION_TARGET.Items.Count != 0)
                return;
            try
            {
                foreach (String ditem in Directory.GetDirectories(DIR_SQL.Text))
                {
                    String version = ditem.Split('-')[1];
                    if (!VERSION_TARGET.Items.Contains(version))
                        VERSION_TARGET.Items.Add(version);
                }
                if(VERSION_TARGET.Items.Count == 0)
                    SetMessage("选择的SQL目录内容为空", 0);
            }
            catch (Exception)
            {
            }
        }

        private void VERSION_TARGET_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (VERSION_TARGET.SelectedIndex > -1)
            {
                SQL_LIST.Items.Clear();
                foreach (String ditem in Directory.GetDirectories(DIR_SQL.Text))
                {
                    DirectoryInfo di = new DirectoryInfo(ditem);
                    foreach (FileInfo fitem in di.GetFiles("*.sql"))
                    {
                        SQL_LIST.Items.Add(
                            fitem.Name +
                            " (" +
                            (fitem.Length / 1024) +
                            "KB)");
                    }
                    if (ditem.IndexOf(VERSION_TARGET.Text) > 0)
                    {
                        break;
                    }
                }
                SetMessage("总计SQL文件数目为：" + SQL_LIST.Items.Count, 0);
            }
        }

        private void PRE_BTN_Click(object sender, EventArgs e)
        {
            VersionRefresh();
            if (this.Step5.Visible)
            {
                this.Install.Visible = false;
                this.NEX_BTN.Visible = true;
                this.PRE_BTN.Visible = true;
                this.Step5.Visible = false;
                this.Step4.Visible = true;
            }
            else if (this.Step2.Visible)
            {
                this.NEX_BTN.Visible = true;
                this.PRE_BTN.Visible = false;
                this.Install.Visible = false;
                this.Step2.Visible = false;
                this.Step1.Visible = true;
            }
            else if (this.Step3.Visible)
            {
                this.NEX_BTN.Visible = true;
                this.PRE_BTN.Visible = true;
                this.Install.Visible = false;
                this.Step3.Visible = false;
                this.Step2.Visible = true;
            }
            else if (this.Step4.Visible)
            {
                this.NEX_BTN.Visible = true;
                this.PRE_BTN.Visible = true;
                this.Step4.Visible = false;
                this.Step3.Visible = true;
            }
        }

        private void NEX_BTN_Click(object sender, EventArgs e)
        {
            VersionRefresh();
            if (this.Step1.Visible)
            {
                this.PRE_BTN.Visible = true;
                this.Install.Visible = false;
                this.Step1.Visible = false;
                this.Step2.Visible = true;
            }
            else if (this.Step2.Visible)
            {
                this.PRE_BTN.Visible = true; 
                this.NEX_BTN.Visible = true;
                this.Install.Visible = false;
                this.Step2.Visible = false;
                this.Step3.Visible = true;
            }
            else if (this.Step3.Visible)
            {
                this.PRE_BTN.Visible = true;
                this.NEX_BTN.Visible = true;
                this.Install.Visible = false;
                this.Step3.Visible = false;
                this.Step4.Visible = true;
            }
            else if (this.Step4.Visible)
            {
                this.NEX_BTN.Visible = false;
                this.PRE_BTN.Visible = true;
                this.Step4.Visible = false;
                this.Step5.Visible = true;
                this.Install.Visible = true;
            }
        }

        private void DIR_MEMCACHE_BTN_Click(object sender, EventArgs e)
        {
            SelectPath(DIR_MEMCACHE);
        }
    }
}
