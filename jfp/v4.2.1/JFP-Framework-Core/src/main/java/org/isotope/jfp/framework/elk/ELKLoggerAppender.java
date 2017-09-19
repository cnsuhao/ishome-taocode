package org.isotope.jfp.framework.elk;

import java.util.ArrayList;
import java.util.List;

import org.isotope.jfp.framework.constants.ISLogConstants;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.HttpRequestHelper;
import org.isotope.jfp.framework.utils.PKHelper;

import com.alibaba.fastjson.JSONObject;

/**
 * 日志制作工具
 * 
 * @author Spook
 * @version 3.2.1 2016/08/17
 * @since 3.2.1 2016/08/17
 *
 */
public class ELKLoggerAppender implements ISLogConstants {

	private JSONObject _message_ = new JSONObject();

	private List<String> _keys_ = new ArrayList<String>();

	private ELKLogSendCollection _ELKLogSendCollection_;

	public void setKeys(List<String> keys) {
		this._keys_ = keys;
	}

	public final static String LOG_PUSH_KEY = "ELK_PUSH_LOG";
	// public final static String LOG_DEBUG_KEY = "ELK_DEBUG_LOG";
	// public final static String LOG_INFO_KEY = "ELK_INFO_LOG";
	// public final static String LOG_ERROR_KEY = "ELK_ERROR_LOG";
	// public final static String LOG_WARNING_KEY = "ELK_WARNING_LOG";
	// public final static String LOG_ALERT_KEY = "ELK_ALERT_LOG";

	public final static String LOG_DEBUG_KEY = "ELK_PUB_LOG";
	public final static String LOG_INFO_KEY = "ELK_PUB_LOG";
	public final static String LOG_ERROR_KEY = "ELK_PUB_LOG";
	public final static String LOG_WARNING_KEY = "ELK_PUB_LOG";
	public final static String LOG_ALERT_KEY = "ELK_PUB_LOG";

	public ELKLoggerAppender() {
		this.init();
	}

	public ELKLoggerAppender(ELKLogData log) {
		this.init();
		this.transLogBeanAppender(log);
	}

	public void init() {
		_ELKLogSendCollection_ = BeanFactoryHelper.getBean("ELKLogSendCollection");

		_keys_.add("系统名称");

		_keys_.add("日志等级");
		_keys_.add("流水号");
		_keys_.add("产生时间");

		_keys_.add("IP");
		_keys_.add("数据类别");// 表名
		_keys_.add("统计分类");
		_keys_.add("服务分类");
		_keys_.add("功能名称");
		_keys_.add("业务名称");
		_keys_.add("说明1");
		_keys_.add("说明2");
		_keys_.add("说明3");
		_keys_.add("备注0");

	}

	public void transLogBeanAppender(ELKLogData log) {
		if (EmptyHelper.isEmpty(log.getIpAdress()))
			_message_.put("IP", log.getIpAdress());
		_message_.put("数据类别", log.getDataType());
		_message_.put("统计分类", log.getCountType());
		_message_.put("服务分类", log.getServiceType());
		_message_.put("功能名称", log.getFunctionName());
		_message_.put("业务名称", log.getBusinessName());
		_message_.put("说明1", log.getExplain1());
		_message_.put("说明2", log.getExplain2());
		_message_.put("说明3", log.getExplain3());
		_message_.put("备注0", log.getComment());
	}

	/**
	 * 添加日志内容
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setLogData(String key, Object value, boolean replace) {
		if (_keys_.contains(key) == false)
			return false;
		else {
			if (_message_.containsKey(key)) {
				if (replace)
					_message_.put(key, value);
			} else {
				_message_.put(key, value);
			}
		}
		return true;
	}

	public boolean setLogData(String key, Object value) {
		return setLogData(key, value, false);
	}

	public void loadDefaultData(String type) {
		setLogData("系统名称", _ELKLogSendCollection_.getSystemName());
		setLogData("日志等级", type);
		setLogData("流水号", PKHelper.creatTimeKey());
		setLogData("产生日期", DateHelper.currentDate4());
		if (_message_.containsKey("IP") == false)
			setLogData("IP", HttpRequestHelper.getServerLocalIPAddr());

	}
	////////////////////////////////////////////////////////////

	public void debug() {
		loadDefaultData("调试");
		_ELKLogSendCollection_.sendLogData(LOG_DEBUG_KEY, _message_.toJSONString());
	}

	public void info() {
		loadDefaultData("普通");
		_ELKLogSendCollection_.sendLogData(LOG_INFO_KEY, _message_.toJSONString());
	}

	public void error() {
		loadDefaultData("错误");
		_ELKLogSendCollection_.sendLogData(LOG_ERROR_KEY, _message_.toJSONString());
	}

	public void warning() {
		loadDefaultData("注意");
		_ELKLogSendCollection_.sendLogData(LOG_WARNING_KEY, _message_.toJSONString());
	}

	/**
	 * 报警并推送消息
	 */
	public void alert() {
		loadDefaultData("警告");
		String msg = _message_.toJSONString();
		_ELKLogSendCollection_.sendLogData(LOG_ALERT_KEY, msg);
		_ELKLogSendCollection_.sendLogData(LOG_PUSH_KEY, msg);
	}

}
