using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;

namespace WindowsFormsApplication2
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }
        public void loadFile(){
            String[] html = File.ReadAllLines("E:/2.txt");
            String[] lines = new string[4];
            for (int i = 0; i < html.Length; i++)			
            {
                var item = html[i];
                if (item.IndexOf("<TD>") >= 0)
                {
                    var line = item.Substring(item.IndexOf("<TD>") + "<TD>".Length);
                    line = line.Substring(0, line.IndexOf("<"));
                    if ("".Equals(line) == false)
                        lines[0] = line;
                }
                else
                    continue;
                i = i + 1;
                item = html[i];
                if (item.IndexOf("target=_blank>") > 0)
                {
                    var line = item.Substring(item.IndexOf("target=_blank>") + "target=_blank>".Length);
                    line = line.Substring(0, line.IndexOf("<"));
                    if ("".Equals(line) == false)
                        lines[1] = line;
                }
                i = i + 1;
                item = html[i];
                if (item.IndexOf("<TD>") >= 0)
                {
                    var line = item.Substring(item.IndexOf("<TD>") + "<TD>".Length);
                    line = line.Substring(0, line.IndexOf("<"));
                    if ("".Equals(line) == false)
                        lines[2] = line;
                }
                i = i + 1;
                item = html[i];
                if (item.IndexOf("align=center>") >= 0)
                {
                    var line = item.Substring(item.IndexOf("align=center>") + "align=center>".Length);
                    line = line.Substring(0,line.IndexOf("<"));
                    if ("".Equals(line) == false)
                        lines[3] = line;
                }

                File.AppendAllText("E:/3.txt", lines[0] + "===" + lines[1] + "===" + lines[2] + "===" + lines[3] + "\r\n");
            }
        }
    }
}
