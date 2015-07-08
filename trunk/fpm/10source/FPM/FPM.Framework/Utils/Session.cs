using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace FPM.Framework.Utils
{
    /// <summary>
    /// 系统运行时期内部缓存
    /// </summary>
    public partial class FormUtil
    {
        private static Dictionary<String, Object> Sessions = new Dictionary<String, Object>();

        public Dictionary<String, Object> GetAllSession()
        {
            return Sessions;
        }
        public void SessionsClear()
        {
            Sessions.Clear();
        }
        public void SessionRemove(String Key)
        {
            Sessions.Remove(Key);
        }
        public void SessionCreat(String Key, Object Value)
        {
            if (Sessions.ContainsKey(Key))
                Sessions[Key] = Value;
            else
                Sessions.Add(Key, Value);
        }
        public Object SessionGet(String Key)
        {
            if (Sessions.ContainsKey(Key))
            {
                return Sessions[Key];
            }
            return "";
        }
    }
}
