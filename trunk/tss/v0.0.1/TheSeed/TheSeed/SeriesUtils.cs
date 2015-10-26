using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

/// <summary>
/// TSS资源分析
/// 作者：同位素
/// 时间：2015/10/1
/// </summary>
namespace TheSeed
{
    class SeriesUtils
    {
        public static DataSet.SeriesDataTable Series { get; set; }

        /// <summary>
        /// 获得所有剧集清单
        /// </summary>
        /// <returns></returns>
        public static Boolean LoadAllSeries(String DateTime)
        {
            //按照年/月/日.XML格式保存（文件内保存左右资源信息列表）
            //按天保存更新剧集清单
            //根据本地清单获取最新发布（7天内）
            #region 获得网络数据

            #endregion

            #region 保存到本地

            #endregion
            return true;
        }
        /// <summary>
        /// 获得每日资源列表（全体）
        /// </summary>
        /// <param name="DateTime"></param>
        /// <returns></returns>
        public static Boolean LoadResources(String DateTime)
        {
            //按照年/月/日.XML格式保存（文件内保存左右资源信息列表）
            return true;
        }

        /// <summary>
        /// 发布新的剧集
        /// </summary>
        /// <returns></returns>
        public static Boolean SaveNewSeriess()
        {
            return true;
        }

        /// <summary>
        /// 加载我订阅的剧集更新
        /// </summary>
        /// <returns></returns>
        public static Boolean LoadMyOrderSeriess()
        {
            return true;
        }

        /// <summary>
        /// 保存我订阅的剧集资源
        /// </summary>
        /// <returns></returns>
        public static Boolean SaveMyOrderSeriess()
        {
            return true;
        }
    }
}
