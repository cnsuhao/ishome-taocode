package com.upg.zx.domain.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.upg.zx.capture.bean.CorpBase;

 
/**
 * 接口返回公司列表Bean
 * @author lujf
 *
 */
public class CorpBaseRes implements Serializable{
	
	private String randomNum;
	
	private Map<String,Object> map_info;//公司基本信息
	
	private Map<String,Object> map;//公司信息
	
	private Map<String,Object> mapbeian;//公司备案信息
	//错误信息
	private String error;
	//用于app端判断下发数据类型(app_search:app端抓取数据;list:返回app端列表数据;bitmap:验证码)
	private String type;
	//数据列表
	private List<CorpBase> list;
	//session
	private String sessionId;
	//公司名称
	private String corp_name;
	
	private byte[] bitmap;
	
	private String bitmapBate64;
	
	private Long corp_id;
	
	private String html;
	
	public CorpBaseRes(String type,List<CorpBase>list,String sessionId){
		this.type = type;
		this.list = list;
		this.sessionId = sessionId;
	}
	
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public CorpBaseRes(){
		
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<CorpBase> getList() {
		return list;
	}
	public void setList(List<CorpBase> list) {
		this.list = list;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getCorp_name() {
		return corp_name;
	}

	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}

	public byte[] getBitmap() {
		return bitmap;
	}

	public void setBitmap(byte[] bitmap) {
		this.bitmap = bitmap;
	}

	public String getBitmapBate64() {
		return bitmapBate64;
	}

	public void setBitmapBate64(String bitmapBate64) {
		this.bitmapBate64 = bitmapBate64;
	}

	public Long getCorp_id() {
		return corp_id;
	}

	public void setCorp_id(Long corp_id) {
		this.corp_id = corp_id;
	}
	public String getRandomNum() {
		return randomNum;
	}

	public void setRandomNum(String randomNum) {
		this.randomNum = randomNum;
	}

	public Map<String, Object> getMapbeian() {
		return mapbeian;
	}

	public void setMapbeian(Map<String, Object> mapbeian) {
		this.mapbeian = mapbeian;
	}

	public Map<String, Object> getMap_info() {
		return map_info;
	}

	public void setMap_info(Map<String, Object> map_info) {
		this.map_info = map_info;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
	
	
	
}
