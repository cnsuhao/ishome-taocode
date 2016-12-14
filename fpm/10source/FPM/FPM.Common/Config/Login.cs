using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FPM.Common.Config;

namespace FPM.Common.Config
{
    class Login
    {
        public int ToLogin(String UserName,String Password)
        {
            ProtocolURL.GetInstance().GetProtocol(ProtocolConfig.Login);

            return 0;
        }
    }
}
