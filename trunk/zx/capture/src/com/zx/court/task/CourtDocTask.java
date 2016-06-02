package com.zx.court.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.upg.zx.capture.bean.ServiceConfig;
import com.zx.court.common.Util;
import com.zx.court.impl.HttpClientImp;
import com.zx.court.pojo.CookiePojo;
import com.zx.court.pojo.CourtDoc;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

public class CourtDocTask {
	ServiceConfig serviceConfig;

	public ServiceConfig getServiceConfig() {
		return serviceConfig;
	}

	public void setServiceConfig(ServiceConfig serviceConfig) {
		this.serviceConfig = serviceConfig;
	}

	final private static String host = "wenshu.court.gov.cn";
	final private static String constUrl = "http://wenshu.court.gov.cn";

	String cookie = "";
	String refer = "";
	String porxy = "";
	int intPort = 0;

	//// ===================================
	// ****************************************************案件类型****************************
	// ****************************************************案件类型****************************
	public static String[] proType = { "民事", "刑事", "执行", "行政", "赔偿" };

	// ****************************************************各省份************************************************************************************************************************************
	// ****************************************************各省份************************************************************************************************************************************
	public static String[] province1 = { "浙江省", "上海市", "江西省", "江苏省", "安徽省", "天津市", "湖北省", "山西省", "四川省", "广东省", "重庆",
			"河北省", "河南省", "云南省", "宁夏回族自治区", "青海省", "西藏自治区", "新疆维吾尔自治区", "内蒙古自治区", "新疆维吾尔自治区高级人民法院生产建设兵团分院", "广西壮族自治区",
			"黑龙江省", "吉林省", "山东省", "海南省", "北京市", "湖南省", "陕西省", "福建省", "甘肃省", "贵州省", "辽宁省" };

	// ============================***************************************中级人民法院*************************************************==========================================
	// ============================***************************************中级人民法院*************************************************==========================================
	public static String[] province2 = { "天津市第一中级人民法院", "天津市第二中级人民法院", "天津海事法院", "江苏省徐州市中级人民法院", "江苏省淮安市中级人民法院",
			"江苏省苏州市中级人民法院", "江苏省镇江市中级人民法院", "江苏省南京市中级人民法院", "江苏省连云港市中级人民法院", "江苏省南通市中级人民法院", "江苏省盐城市中级人民法院",
			"江苏省扬州市中级人民法院", "江苏省泰州市中级人民法院", "江苏省宿迁市中级人民法院", "南京铁路运输法院", "徐州铁路运输法院", "广西壮族自治区南宁市中级人民法院",
			"广西壮族自治区柳州市中级人民法院", "广西壮族自治区桂林市中级人民法院", "广西壮族自治区梧州市中级人民法院", "广西壮族自治区北海市中级人民法院", "广西壮族自治区防城港市中级人民法院",
			"广西壮族自治区钦州市中级人民法院", "广西壮族自治区贵港市中级人民法院", "广西壮族自治区玉林市中级人民法院", "广西壮族自治区百色市中级人民法院", "广西壮族自治区贺州市中级人民法院",
			"广西壮族自治区河池市中级人民法院", "广西壮族自治区来宾市中级人民法院", "广西壮族自治区崇左市中级人民法院", "南宁铁路运输中级法院", "上海市第一中级人民法院", "上海市第二中级人民法院",
			"上海铁路运输中级法院", "上海海事法院", "上海知识产权法院", "浙江省杭州市中级人民法院", "浙江省宁波市中级人民法院", "浙江省温州市中级人民法院", "浙江省嘉兴市中级人民法院",
			"浙江省湖州市中级人民法院", "浙江省金华市中级人民法院", "浙江省衢州市中级人民法院", "浙江省舟山市中级人民法院", "浙江省台州市中级人民法院", "浙江省丽水市中级人民法院",
			"浙江省绍兴市中级人民法院", "湖北省汉江中级人民法院", "湖北省武汉市中级人民法院", "湖北省黄石市中级人民法院", "湖北省宜昌市中级人民法院", "湖北省襄阳市中级人民法院",
			"湖北省鄂州市中级人民法院", "湖北省荆门市中级人民法院", "湖北省随州市中级人民法院", "湖北省黄冈市中级人民法院", "湖北省孝感市中级人民法院", "湖北省十堰市中级人民法院",
			"湖北省荆州市中级人民法院", "湖北省荆州市中级人民法院", "湖北省咸宁市中级人民法院", "湖北省恩施土家族苗族自治州中级人民法院", "武汉海事法院", "武汉铁路运输中级法院",
			"湖南省株洲市中级人民法院", "湖南省常德市中级人民法院", "湖南省益阳市中级人民法院", "湖南省郴州市中级人民法院", "湖南省永州市中级人民法院", "湖南省怀化市中级人民法院",
			"湖南省娄底市中级人民法院", "湖南省湘西土家族苗族自治州中级人民法院", "吉林省长春市中级人民法院", "吉林省吉林市中级人民法院", "吉林省四平市中级人民法院", "吉林省辽源市中级人民法院",
			"吉林省通化市中级人民法院", "吉林省白山市中级人民法院", "吉林省长春铁路运输中级法院", "吉林省延边林区中级法院", "吉林省松原市中级人民法院", "吉林省白城市中级人民法院",
			"吉林省延边朝鲜族自治州中级人民法院", "吉林省长春林区中级法院", "河南省郑州市中级人民法院", "河南省开封市中级人民法院", "河南省洛阳市中级人民法院", "河南省平顶山市中级人民法院",
			"河南省安阳市中级人民法院", "河南省鹤壁市中级人民法院", "河南省许昌市中级人民法院", "河南省新乡市中级人民法院", "河南省焦作市中级人民法院", "河南省濮阳市中级人民法院",
			"河南省漯河市中级人民法院", "河南省三门峡市中级人民法院", "河南省南阳市中级人民法院", "河南省驻马店市中级人民法院", "河南省商丘市中级人民法院", "河南省信阳市中级人民法院",
			"河南省济源中级人民法院", "北京市第一中级人民法院", "北京市第二中级人民法院", "北京市第三中级人民法院", "北京市第四中级人民法院", "北京知识产权法院", "安徽省合肥市中级人民法院",
			"安徽省芜湖市中级人民法院", "安徽省蚌埠市中级人民法院", "安徽省淮南市中级人民法院", "安徽省安庆市中级人民法院", "安徽省黄山市中级人民法院", "安徽省铜陵市中级人民法院",
			"安徽省滁州市中级人民法院", "安徽省阜阳市中级人民法院", "安徽省宿州市中级人民法院", "安徽省六安市中级人民法院", "安徽省亳州市中级人民法院", "安徽省池州市中级人民法院",
			"安徽省宣城市中级人民法院", "安徽省马鞍山市中级人民法院", "安徽省淮北市中级人民法院", "河北省石家庄市中级人民法院", "河北省唐山市中级人民法院", "河北省邯郸市中级人民法院",
			"河北省邢台市中级人民法院", "河北省秦皇岛市中级人民法院", "河北省保定市中级人民法院", "河北省承德市中级人民法院", "河北省张家口市中级人民法院", "河北省沧州市中级人民法院",
			"河北省廊坊市中级人民法院", "河北省衡水市中级人民法院", "江西省高级人民法院", "江西省南昌市中级人民法院", "江西省景德镇市中级人民法院", "江西省萍乡市中级人民法院",
			"江西省九江市中级人民法院", "江西省新余市中级人民法院", "江西省鹰潭市中级人民法院", "江西省赣州市中级人民法院", "江西省宜春市中级人民法院", "江西省上饶市中级人民法院",
			"江西省吉安市中级人民法院", "江西省抚州市中级人民法院", "南昌铁路运输中级法院", "山东省济南市中级人民法院", "山东省枣庄市中级人民法院", "山东省青岛市中级人民法院",
			"山东省淄博市中级人民法院", "山东省烟台市中级人民法院", "山东省潍坊市中级人民法院", "山东省德州市中级人民法院", "山东省东营市中级人民法院", "山东省济宁市中级人民法院",
			"山东省泰安市中级人民法院", "山东省威海市中级人民法院", "山东省临沂市中级人民法院", "山东省聊城市中级人民法院", "山东省滨州市中级人民法院", "山东省莱芜市中级人民法院",
			"山东省菏泽市中级人民法院", "青岛海事法院", "济南铁路运输中级法院", "黑龙江省齐齐哈尔市中级人民法院", "黑龙江省鸡西市中级人民法院", "黑龙江省大庆市中级人民法院",
			"黑龙江省佳木斯市中级人民法院", "黑龙江省牡丹江市中级人民法院", "黑龙江省黑河市中级人民法院", "黑龙江省绥化江市中级人民法院", "黑龙江省伊春市中级人民法院", "黑龙江省七台河市中级人民法院",
			"黑龙江省大兴安岭地区中级人民法院", "黑龙江省林区中级人民法院", "黑龙江省农垦中级法院", "哈尔滨铁路运输法院", "辽宁省沈阳市中级人民法院", "辽宁省大连市中级人民法院",
			"辽宁省丹东市中级人民法院", "辽宁省锦州市中级人民法院", "辽宁省营口市中级人民法院", "辽宁省辽河中级人民法院", "辽宁省鞍山市中级人民法院", "辽宁省抚顺市中级人民法院",
			"辽宁省本溪市中级人民法院", "辽宁省盘锦市中级人民法院", "辽宁省阜新市中级人民法院", "辽宁省辽阳市中级人民法院", "辽宁省盘锦市中级人民法院", "辽宁省葫芦岛市中级人民法院",
			"辽宁省辽河市中级人民法院", "沈阳铁路运输中级法院", "辽宁省朝阳市中级人民法院", "辽宁省铁岭市中级人民法院", "大连海事法院", "广东省广州市中级人民法院", "广东省韶关市中级人民法院",
			"广东省深圳市中级人民法院", "广东省深圳珠海市中级人民法院", "广东省汕头市中级人民法院", "广东省江门市中级人民法院", "广东省佛山市中级人民法院", "广东省汕尾市中级人民法院",
			"广东省云浮市中级人民法院", "广东省湛江市中级人民法院", "广东省中山市中级人民法院", "广东省揭阳市中级人民法院", "广东省茂名市中级人民法院", "广东省梅州市中级人民法院",
			"广东省清远市中级人民法院", "广东省潮州市中级人民法院", "广东省东莞市中级人民法院", "广东省惠州市中级人民法院", "广东省肇庆市中级人民法院", "广东省河源市中级人民法院",
			"广东省阳江市中级人民法院", "广州铁路运输中级法院", "广州海事法院", "广州知识产权法院", "内蒙古自治区呼和浩特市中级人民法院", "内蒙古自治区包头市中级人民法院",
			"内蒙古自治区赤峰市中级人民法院", "内蒙古自治区通辽市中级人民法院", "内蒙古自治区巴彦淖尔市中级人民法院", "内蒙古自治区锡林郭勒盟中级人民法院", "云南省高级人民法院", "云南省昆明市中级人民法院",
			"云南省昭通市中级人民法院", "云南省曲靖市中级人民法院", "云南省玉溪市中级人民法院", "云南省普洱市中级人民法院", "云南省保山市中级人民法院", "云南省丽江市中级人民法院",
			"云南省临沧市中级人民法院", "云南省楚雄彝族自治州中级人民法院", "云南省红河哈尼族彝族自治州中级人民法院", "云南省文山壮族苗族自治州中级人民法院", "福建省高级人民法院",
			"福建省福州市中级人民法院", "福建省厦门市中级人民法院", "福建省莆田市中级人民法院", "福建省三明市中级人民法院", "福建省泉州市中级人民法院", "福建省漳州市中级人民法院",
			"福建省南平市中级人民法院", "福建省宁德市中级人民法院", "福建省龙岩市中级人民法院", "厦门海事法院", "福州铁路运输法院", "陕西省高级人民法院", "陕西省西安市中级人民法院",
			"陕西省铜川市中级人民法院", "陕西省宝鸡市中级人民法院", "陕西省咸阳市中级人民法院", "陕西省渭南市中级人民法院", "陕西省汉中市中级人民法院", "陕西省安康市中级人民法院",
			"陕西省商洛市中级人民法院", "陕西省延安市中级人民法院", "陕西省榆林市中级人民法院", "西安铁路运输中级法院", "山西省太原市中级人民法院", "山西省大同市中级人民法院",
			"山西省阳泉市中级人民法院", "山西省长治市中级人民法院", "山西省晋城市中级人民法院", "山西省朔州市中级人民法院", "山西省忻州市中级人民法院", "山西省吕梁市中级人民法院",
			"山西省市晋州中级人民法院", "山西省临汾市中级人民法院", "山西省运城市中级人民法院", "太原铁路运输中级法院", "四川省成都市中级人民法院", "四川省绵阳市中级人民法院",
			"四川省广元市中级人民法院", "四川省遂宁市中级人民法院", "四川省内江市中级人民法院", "四川省甘孜藏族自治州中级人民法院", "四川省自贡市中级人民法院", "四川省攀枝花市中级人民法院",
			"四川省泸州市中级人民法院", "四川省德阳市中级人民法院", "四川省凉山彝族自治州中级人民法院", "四川省乐山市中级人民法院", "四川省宜宾市中级人民法院", "四川省南充市中级人民法院",
			"四川省达州市中级人民法院", "四川省雅安市中级人民法院", "四川省宜宾阿坝藏族羌族自治州中级人民法院", "四川省广安市中级人民法院", "四川省巴中市中级人民法院", "四川省眉山市中级人民法院",
			"四川省资阳市中级人民法院", "成都铁路运输中级法院", "甘肃省高级人民法院", "甘肃省兰州市中级人民法院", "甘肃省嘉峪关市中级人民法院", "甘肃省金昌市中级人民法院", "甘肃省白银市中级人民法院",
			"甘肃省天水市中级人民法院", "甘肃省酒泉市中级人民法院", "甘肃省张掖市中级人民法院", "甘肃省武威市中级人民法院", "甘肃省定西市中级人民法院", "甘肃省陇南市中级人民法院",
			"甘肃省平凉市中级人民法院", "甘肃省庆阳市中级人民法院", "甘肃省临夏回族自治州中级人民法院", "甘肃省甘南藏族自治州中级人民法院", "甘肃矿区人民法院", "甘肃省林区中级法院",
			"兰州铁路运输中级法院", "贵州省贵阳市中级人民法院", "贵州省贵州六盘水市中级人民法院", "贵州省贵州市遵义市中级人民法院", "贵州省铜仁市中级人民法院", "贵州省黔西南布衣族苗族自治州中级人民法院",
			"贵州省毕节市中级人民法院", "贵州省安顺市中级人民法院", "贵州省黔东南苗族侗族自治州中级人民法院", "贵州省黔南布衣族苗族自治州中级人民法院", "重庆市高级人民法院", "重庆市第一中级人民法院",
			"重庆市第二中级人民法院", "重庆市第三中级人民法院", "重庆市第四中级人民法院", "重庆市第五中级人民法院" };

	// ****************************************************基层法院************************************************************************************************************************************
	// ****************************************************基层法院************************************************************************************************************************************
	public static String[] province3 = { "上海市徐汇区人民法院", "上海市长宁区人民法院", "上海市闵行区人民法院", "上海市浦东新区人民法院", "上海市金山区人民法院",
			"上海市松江区人民法院", "上海市奉贤区人民法院", "上海市黄埔区人民法院", "上海市静安区人民法院", "上海市普陀区人民法院", "上海市闸北区人民法院", "上海市虹口区人民法院",
			"上海市杨浦区人民法院", "上海市宝山区人民法院", "上海市嘉定区人民法院", "上海市青浦区人民法院", "崇明县人民法院", "杭州市下城区人民法院", "杭州市上城区人民法院", "杭州市江干区人民法院",
			"杭州市拱墅区人民法院", "杭州市西湖区人民法院", "杭州市萧山区人民法院", "杭州市余杭区人民法院", "杭州市富阳区人民法院", "淳安县人民法院", "建德市人民法院", "临安市人民法院",
			"宁波市海曙区人民法院", "宁波市江东区人民法院", "宁波市江北区人民法院", "宁波市江北仑区人民法院", "宁波市镇海区人民法院", "宁波市鄞州区人民法院", "宁海县人民法院", "奉化市人民法院",
			"慈溪市人民法院", "象山县人民法院", "温州市鹿城区人民法院", "温州市龙湾区人民法院", "温州市瓯海区人民法院", "瑞安市人民法院", "洞头县人民法院", "乐清市人民法院", "永嘉县人民法院",
			"平阳县人民法院", "苍南县人民法院", "文成县人民法院", "泰顺县人民法院", "嘉兴市南湖区人民法院", "嘉兴市秀洲区人民法院", "海宁市人民法院", "平湖市人民法院", "嘉善县人民法院",
			"海盐县人民法院", "桐乡市人民法院", "湖州市吴兴区人民法院", "德清县人民法院", "长兴县人民法院", "安吉县人民法院", "湖州市南浔区人民法院", "绍兴市越城区人民法院", "诸暨市人民法院",
			"绍兴县人民法院", "上虞市人民法院", "嵊州市人民法院", "新昌县人民法院", "舟山市定海区人民法院", "舟山市普陀区人民法院", "岱山县人民法院", "嵊泗县人民法院", "台州市椒江区人民法院",
			"台州市黄岩区人民法院", "台州市路桥区人民法院", "临海市人民法院", "温岭市人民法院", "仙居县人民法院", "天台县人民法院", "三门县人民法院", "玉环县人民法院", "丽水市莲都区人民法院",
			"龙泉市人民法院", "青田县人民法院", "云和县人民法院", "庆元县人民法院", "缙云县人民法院", "遂昌县人民法院", "松阳县人民法院", "景宁畲族自治县人民法院", "衢州市柯城区人民法院",
			"江山市人民法院", "衢州市衢江区人民法院", "常山县人民法院", "开化县人民法院", "龙游县人民法院", "金华市婺城区人民法院", "金华市金东区人民法院", "兰溪市人民法院", "东阳市人民法院",
			"义乌市人民法院", "永康市人民法院", "武义县人民法院", "浦江县人民法院", "磐安县人民法院", "无锡市崇安区人民法院", "无锡市南长区人民法院", "无锡市北塘区人民法院",
			"无锡市惠山区人民法院", "无锡市滨湖区人民法院", "江阴市人民法院", "宜兴市人民法院", "无锡市锡山区人民法院", "无锡高新技术产业开发区人民法院", "徐州市鼓楼区人民法院",
			"徐州市云龙区人民法院", "徐州市贾汪区人民法院", "徐州市泉山区人民法院", "新沂市人民法院", "丰县人民法院", "沛县人民法院", "徐州市铜山区人民法院", "睢宁县人民法院", "邳州市人民法院",
			"徐州经济技术开发区人民法院", "常州市天宁区人民法院", "常州市钟楼区人民法院", "常州市戚墅堰区人民法院", "常州市新北区人民法院", "常州市武进区人民法院", "金坛市人民法院",
			"溧阳市人民法院", "常州高新技术产业开发区人民法院", "苏州市虎丘区人民法院", "张家港市人民法院", "常熟市人民法院", "昆山市人民法院", "太仓市人民法院", "苏州市吴中区人民法院",
			"苏州市吴江区人民法院", "苏州市相城区人民法院", "苏州工业园区人民法院", "苏州市姑苏区人民法院", "南通市崇川区人民法院", "南通市港闸区人民法院", "启东市人民法院", "如皋市人民法院",
			"海安县人民法院", "如东县人民法院", "南通市通州区人民法院", "海门市人民法院", "南通经济技术开发区人民法院", "连云港市连云区人民法院", "连云港市海州区人民法院", "连云港市赣榆区人民法院",
			"东海县人民法院", "灌云县人民法院", "灌南县人民法院", "淮安市清河区人民法院", "淮安市清浦区人民法院", "淮安市淮安区人民法院", "淮安市淮阴区人民法院", "涟水县人民法院",
			"洪泽县人民法院", "盱眙县人民法院", "金湖县人民法院", "淮安经济技术开发区人民法院", "盐城市亭湖区人民法院", "盐城市盐都区人民法院", "东台市人民法院", "响水县人民法院",
			"滨海县人民法院", "阜宁县人民法院", "射阳县人民法院", "建湖县人民法院", "大丰市人民法院", "扬州市广陵区人民法院", "仪征市人民法院", "高邮市人民法院", "宝应县人民法院",
			"扬州市邗江区人民法院", "扬州市江都区人民法院", "扬州经济技术开发区人民法院", "镇江市京口区人民法院", "镇江市润州区人民法院", "丹阳市人民法院", "镇江市丹徒区人民法院", "句容市人民法院",
			"扬中市人民法院", "镇江经济开发区人民法院", "泰州市海陵区人民法院", "兴化市人民法院", "靖江市人民法院", "泰兴市人民法院", "泰州市姜堰区人民法院", "泰州市高港区人民法院",
			"泰州医药高新技术产业开发区人民法院", "宿迁市宿城区人民法院", "宿迁市宿豫区人民法院", "泗阳县人民法院", "泗洪县人民法院", "沭阳县人民法院", "济南市历下区人民法院",
			"济南市市中区人民法院", "济南市槐荫区人民法院", "济南市天桥区人民法院", "济南市历城区人民法院", "章丘市人民法院", "济南市长清区人民法院", "平阴县人民法院", "商河县人民法院",
			"济阳县人民法院", "济南高新技术产业开发区人民法院", "青岛市市南区人民法院", "青岛市市北区人民法院", "青岛市李沧区人民法院", "青岛市城阳区人民法院", "青岛市黄岛区人民法院",
			"青岛市崂山区人民法院", "即墨市人民法院", "胶州市人民法院", "平度市人民法院", "莱西市人民法院", "淄博市淄川区人民法院", "淄博市张店区人民法院", "淄博市博山区人民法院",
			"淄博市临淄区人民法院", "淄博市周村区人民法院", "桓台县人民法院", "高青县人民法院", "沂源县人民法院", "淄博市高新技术产业开发区人民法院", "枣庄市市中区人民法院", "枣庄市薛城区人民法院",
			"枣庄市峄城区人民法院", "枣庄市台儿庄区人民法院", "枣庄市山亭区人民法院", "滕州市人民法院", "东营市东营区人民法院", "东营市河口区人民法院", "垦利县人民法院", "利津县人民法院",
			"广饶县人民法院", "东营经济技术开发区人民法院", "烟台市芝罘区人民法院", "烟台市福山区人民法院", "烟台市莱山区人民法院", "烟台市牟平区人民法院", "烟台经济技术开发区人民法院",
			"蓬莱市人民法院", "龙口市人民法院", "莱州市人民法院", "招远市人民法院", "栖霞市人民法院", "莱阳市人民法院", "海阳市人民法院", "长岛县人民法院", "烟台高新技术产业开发区人民法院",
			"潍坊市潍城区人民法院", "潍坊市寒亭区人民法院", "潍坊市坊子区人民法院", "诸城市人民法院", "青州市人民法院", "安丘市人民法院", "寿光市人民法院", "临朐县人民法院", "昌乐县人民法院",
			"昌邑市人民法院", "高密市人民法院", "潍坊市奎文区人民法院", "潍坊高新技术产业开发区人民法院", "潍坊滨海经济技术开发区人民法院", "济宁市任城区人民法院", "曲阜市人民法院",
			"济宁市兖州区人民法院", "邹城市人民法院", "微山县人民法院", "鱼台县人民法院", "金乡县人民法院", "嘉祥县人民法院", "汶上县人民法院", "泗水县人民法院", "梁山县人民法院",
			"济宁高新技术产业开发区人民法院", "泰安市泰山区人民法院", "泰安市岱岳区人民法院", "新泰市人民法院", "宁阳县人民法院", "肥城市人民法院", "东平县人民法院",
			"泰安高新技术产业开发区人民法院", "威海市环翠区人民法院", "荣成市人民法院", "威海市文登区人民法院", "乳山市人民法院", "威海火炬高技术产业开发区人民法院", "威海经济技术开发区人民法院",
			"日照市东港区人民法院", "莒县人民法院", "五莲县人民法院", "日照经济技术开发区人民法院", "日照市岚山区人民法院", "滨州市滨城区人民法院", "惠民县人民法院", "阳信县人民法院",
			"无棣县人民法院", "滨州市沾化区人民法院", "博兴县人民法院", "邹平县人民法院", "滨州经济技术开发区人民法院", "临沂市兰山区人民法院", "郯城县人民法院", "兰陵县人民法院",
			"莒南县人民法院", "沂水县人民法院", "蒙阴县人民法院", "平邑县人民法院", "费县人民法院", "沂南县人民法院", "临沭县人民法院", "临沂市罗庄区人民法院", "临沂市河东区人民法院",
			"临沂经济技术开发区人民法院", "临沂高新技术产业开发区人民法院", "菏泽市牡丹区人民法院", "曹县人民法院", "定陶县人民法院", "成武县人民法院", "单县人民法院", "巨野县人民法院",
			"郓城县人民法院", "鄄城县人民法院", "东明县人民法院", "菏泽经济开发区人民法院", "唐山市古冶区人民法院", "唐山市丰南区人民法院", "唐山市丰润区人民法院", "滦县人民法院",
			"乐亭县人民法院", "迁西县人民法院", "玉田县人民法院", "玉田县人民法院", "遵化市人民法院", "迁安市人民法院", "唐山高新技术开发区人民法院", "沭阳县人民法院", "泗阳县人民法院",
			"泗洪县人民法院", "徐州市铜山区人民法院", "沛县人民法院", "新沂市人民法院", "广州市荔湾区人民法院", "广州市越秀区人民法院", "广州市海珠区人民法院", "广州市天河区人民法院",
			"广州市黄埔区人民法院", "广州市白云区人民法院", "广州市花都区人民法院", "从化市人民法院", "增城市人民法院", "广州市番禺区人民法院", "广州市萝岗区人民法院", "广州市南沙区人民法院",
			"深圳市罗湖区人民法院", "深圳市福田区人民法院", "深圳市南山区人民法院", "深圳市宝安区人民法院", "深圳市龙岗区人民法院", "深圳市盐田区人民法院", "深圳市前海合作区人民法院",
			"郑州市中原区人民法院", "郑州市二七区人民法院", "郑州市金水区人民法院", "郑州市上街区人民法院", "郑州市惠济区人民法院", "郑州市管城回族区人民法院", "巩义市人民法院", "荥阳市人民法院",
			"中牟县人民法院", "新郑市人民法院", "登封市人民法院", "新密市人民法院", "郑州高新技术产业开发区人民法院", "郑州航空港经济综合实验区人民法院", "焦作市解放区人民法院",
			"焦作市中站区人民法院", "焦作市马村区人民法院", "焦作市山阳区人民法院", "沁阳市人民法院", "修武县人民法院", "博爱县人民法院", "武陟县人民法院", "温县人民法院", "孟州市人民法院",
			"开封市龙亭区人民法院", "开封市顺河回族区人民法院", "开封市鼓楼区人民法院", "开封市禹王台区人民法院", "开封市金明区人民法院", "杞县人民法院", "通许县人民法院", "开封市祥符区人民法院",
			"尉氏县人民法院", "兰考县人民法院", "沈阳市和平区人民法院", "沈阳市沈河区人民法院", "沈阳市大东区人民法院", "沈阳市皇姑区人民法院", "沈阳市铁西区人民法院", "沈阳市苏家屯区人民法院",
			"沈阳市浑南区人民法院", "沈阳市沈北新区人民法院", "沈阳市于洪区人民法院", "新民市人民法院", "辽中县人民法院", "康平县人民法院", "法库县人民法院", "沈阳经济技术开发区人民法院",
			"沈阳高新技术产业开发区人民法院", "大连市中山区人民法院", "大连市西岗区人民法院", "大连市沙河口区人民法院", "大连市金州区人民法院", "大连市甘井子区人民法院", "大连市旅顺口区人民法院",
			"大连经济技术开发区人民法院", "瓦房店市人民法院", "庄河市人民法院", "普兰店市人民法院", "长海县人民法院", "长春市南关区人民法院", "长春市宽城区人民法院", "长春市朝阳区人民法院",
			"长春市二道区人民法院", "长春市绿园区人民法院", "九台市人民法院", "榆树市人民法院", "农安县人民法院", "德惠市人民法院", "长春市双阳区人民法院", "长春经济技术开发区人民法院",
			"长春汽车经济技术开发区人民法院", "长春高新技术产业开发区人民法院", "长春净月高新技术产业开发区人民法院", "吉林市昌邑区人民法院", "吉林市龙潭区人民法院", "吉林市船营区人民法院",
			"吉林市丰满区人民法院", "桦甸市人民法院", "蛟河市人民法院", "永吉县人民法院", "舒兰市人民法院", "磐石市人民法院", "吉林高新技术产业开发区人民法院", "北京市石景山区人民法院",
			"北京市海淀区人民法院", "北京市门头沟区人民法院", "北京市昌平区人民法院", "延庆县人民法院", "北京市东城区人民法院", "北京市丰台区人民法院", "北京市西城区人民法院",
			"北京市房山区人民法院", "北京市大兴区人民法院", "北京市朝阳区人民法院", "北京市通州区人民法院", "北京市平谷区人民法院", "北京市怀柔区人民法院", "密云县人民法院",
			"大庆市让胡路区人民法院", "大庆市大同区人民法院", "杜尔伯特蒙古族自治县人民法院", "合肥市瑶海区人民法院", "合肥市庐阳区人民法院", "合肥市蜀山区人民法院", "合肥市包河区人民法院",
			"长丰区人民法院", "肥东县人民法院", "肥西县人民法院", "合肥高新技术产业开发区人民法院", "巢湖市人民法院", "庐江县人民法院", "合肥铁路运输法院", "芜湖市镜湖区人民法院",
			"芜湖市弋江区人民法院", "芜湖市鸠江区人民法院", "芜湖县人民法院", "繁昌县人民法院", "南陵县人民法院", "芜湖市三山区人民法院", "无为县人民法院", "芜湖经济技术开发区人民法院",
			"西安市新城区人民法院", "西安市碑林区人民法院", "西安市莲湖区人民法院", "西安市灞桥区人民法院", "西安市未央区人民法院", "西安市雁塔区人民法院", "西安市阎良区人民法院",
			"西安市长安区人民法院", "蓝田县人民法院", "西安市临潼区人民法院", "周至县人民法院", "户县人民法院", "高陵县人民法院", "天津市和平区人民法院", "天津市南开区人民法院",
			"天津市河北区人民法院", "天津市红桥区人民法院", "天津市西青区人民法院", "天津市北辰区人民法院", "天津市武清区人民法院", "天津市宝坻区人民法院", "静海县人民法院", "蓟县人民法院",
			"宁河县人民法院", "天津市河东区人民法院", "天津市河西区人民法院", "天津市东丽区人民法院", "天津市津南区人民法院", "天津市滨海新区人民法院", "福州市鼓楼区人民法院",
			"福州市台江区人民法院", "福州市仓山区人民法院", "福州市马尾区人民法院", "福州市晋安区人民法院", "福清市人民法院", "闽侯县人民法院", "连江县人民法院", "罗源县人民法院",
			"闽清县人民法院", "永泰县人民法院", "长乐市人民法院", "平潭县人民法院", "厦门市思明区人民法院", "厦门市湖里区人民法院", "厦门市集美区人民法院", "厦门市同安区人民法院",
			"厦门市海沧区人民法院", "厦门市翔安区人民法院", "莆田市城厢区人民法院", "莆田市涵江区人民法院", "仙游县人民法院", "莆田市荔城区人民法院", "莆田市秀屿区人民法院", "泉州市鲤城区人民法院",
			"泉州市丰泽区人民法院", "泉州市洛江区人民法院", "石狮市人民法院", "泉州市泉港区人民法院", "晋江市人民法院", "南安市人民法院", "安溪县人民法院", "永春县人民法院", "德化县人民法院",
			"惠安县人民法院", "漳州市芗城区人民法院", "漳州市龙文区人民法院", "龙海市人民法院", "云霄县人民法院", "漳浦县人民法院", "诏安县人民法院", "长泰县人民法院", "东山县人民法院",
			"南靖县人民法院", "平和县人民法院", "华安县人民法院", "龙岩市新罗区人民法院", "长汀县人民法院", "龙岩市永定区人民法院", "上杭县人民法院", "武平县人民法院", "漳平市人民法院",
			"连城县人民法院" };

	HttpClientImp httpclientHandl = new HttpClientImp();

	public void loadProxy() {
//		porxy = "183.136.133.238";
//		intPort = 80;
//		// 更改代理=====================
//		System.out.println("=====proxy======" + porxy);
//		System.out.println("=====port======" + intPort);
	}

	/**
	 * 取得文书列表
	 * 
	 * @param url
	 * @throws Exception
	 * 
	 */
	public void getHtmlListSheng() throws Exception {
		int page = 1;

		String content = "";
		CookiePojo pojo = new CookiePojo();
		boolean flag = false;
		String cookie = "";

		int i = 0;
		// *****************上传日期********************
		String from = getDate(0);
		String to = getDate(1);
		// ******************************************
		int k = 0;

		for (int j = 0; j < proType.length;) {
			for (k = 0; k < province1.length;) {
				i = 0;
				System.out.println("======" + i + "==========page==" + page);
				String Url = "/list/list/?sorttype=1&conditions=searchWord+++" + from + "%20TO%20" + to + "+"
						+ Util.paraFormatEncode("上传日期") + ":" + from + "%20TO%20" + to
						+ "&conditions=searchWord+2+AJLX++" + Util.paraFormatEncode("案件类型") + ":"
						+ Util.paraFormatEncode(proType[j] + "案件") + "&conditions=searchWord+"
						+ Util.paraFormatEncode(province1[k]) + "+++" + Util.paraFormatEncode("法院地域") + ":"
						+ Util.paraFormatEncode(province1[k]);
				// http://wenshu.court.gov.cn/list/list/?sorttype=1&conditions=searchWord+2+AJLX++%E6%A1%88%E4%BB%B6%E7%B1%BB%E5%9E%8B:%E6%B0%91%E4%BA%8B%E6%A1%88%E4%BB%B6&conditions=searchWord+2005+++%E8%A3%81%E5%88%A4%E5%B9%B4%E4%BB%BD:2005
				// 取得代理
				// objProxy=getProxy();
				// porxy =objProxy.getIp();
				// intPort =objProxy.getPort();

				while (i < page) {

					String referer = "";
					// 省
					String type = "上传日期:" + from + " TO " + to + ",案件类型:" + proType[j] + "案件,法院地域:" + province1[k];
					System.out.println(type);
					Thread.sleep(1000);

					loadProxy();

					String url = constUrl + Url;
					System.out.println("=====>>>>>"+url);

					if (!flag) {
						cookie = httpclientHandl.getCookieByWebClient(url, porxy, intPort);
						Thread.sleep(500);

						if ("404".equals(cookie)) {
							cookie = "";
							continue;
						}
						flag = true;
					}

					if (flag) {// Cookie取得的场合
						url = "http://wenshu.court.gov.cn/List/ListContent";
						pojo = httpclientHandl.gethtmlByPost("UTF-8", url, referer, host, porxy, intPort, cookie, type,
								String.valueOf(i + 1));
						Thread.sleep(1000);

						content = pojo.getStrResult();
					}

					if ("".equals(content) || content == null) {
						cookie = "";
						flag = false;
						continue;
					}
					if (content.contains("</script>")) {
						cookie = "";
						flag = false;
						continue;
					} else if (content.contains("DocContent")) {
						List<NameValuePair> formparams = new ArrayList<NameValuePair>();
						formparams.add(new BasicNameValuePair("html", content));
						formparams.add(new BasicNameValuePair("start", String.valueOf(k)));
						formparams.add(new BasicNameValuePair("flag", "0"));

						httpclientHandl.httpPost("UTF-8", serviceConfig.getServiceConfig("submitCourtDocUrl"),
								formparams);

						ArrayList<CourtDoc> arrylist = new ArrayList<CourtDoc>();
						content = StringUtils.substringBetween(content, "[{", "}]");
						// 取得数据
						// 省=============
						if (!province1[k].contains("新疆维吾尔自治区高级人民法院生产建设兵团分院")) {
							arrylist = getjsonArray("[{" + content + "}]", province1[k]);
						} else {
							arrylist = getjsonArray("[{" + content + "}]", "新疆维吾尔自治区");
						}
						page = CalculatPage(Integer.parseInt(arrylist.get(0).getPage()), 20);
						System.out.println("======page=======" + page);
					} else if (content.contains("<html>") || "".equals(content) || content == null) {
						// objProxy.setFlag(1);
						// courtDaoHandl.updatePoxy(objProxy);
						// 取得代理
						// objProxy=getProxy();
						// porxy =objProxy.getIp();
						// intPort =objProxy.getPort();
						continue;
					} else if (content.contains("[]") || content.contains("[ ]")) {
						continue;
					}
					i++;
					System.out.println(i);
				}
				k++;
			}
			j++;
		}
	}

	public void getHtmlListZhongji() throws Exception {
		int page = 1;

		String content = "";
		CookiePojo pojo = new CookiePojo();
		boolean flag = false;
		String cookie = "";

		int i = 0;
		// *****************上传日期********************
		String from = getDate(0);
		String to = getDate(1);
		// ******************************************
		int k = 0;
		for (int j = 0; j < proType.length;) {
			for (k = 0; k < province2.length;) {
				i = 0;
				System.out.println("======" + i + "==========page==" + page);
				String Url = "/list/list/?sorttype=1&conditions=searchWord+++" + from + "%20TO%20" + to + "+"
						+ Util.paraFormatEncode("上传日期") + ":" + from + "%20TO%20" + to
						+ "&conditions=searchWord+2+AJLX++" + Util.paraFormatEncode("案件类型") + ":"
						+ Util.paraFormatEncode(proType[j] + "案件") + "&conditions=searchWord+"
						+ Util.paraFormatEncode(province2[k]) + "+++" + Util.paraFormatEncode("中级法院") + ":"
						+ Util.paraFormatEncode(province2[k]);
				// 取得代理
				// objProxy=getProxy();
				// porxy =objProxy.getIp();
				// intPort =objProxy.getPort();

				while (i < page) {

					String referer = "";
					String type = "上传日期:" + from + " TO " + to + ",案件类型:" + proType[j] + "案件,中级法院:" + province2[k];
					System.out.println(type);
					Thread.sleep(1000);
					loadProxy();
					String url = constUrl + Url;
					System.out.println(url);

					if (!flag) {
						cookie = httpclientHandl.getCookieByWebClient(url, porxy, intPort);
						Thread.sleep(1000);

						if ("404".equals(cookie)) {
							cookie = "";
							continue;
						}
						flag = true;
					}

					if (flag) {// Cookie取得的场合
						url = "http://wenshu.court.gov.cn/List/ListContent";
						pojo = httpclientHandl.gethtmlByPost("UTF-8", url, referer, host, porxy, intPort, cookie, type,
								String.valueOf(i + 1));
						Thread.sleep(1000);

						content = pojo.getStrResult();
					}

					if ("".equals(content) || content == null) {
						cookie = "";
						flag = false;
						continue;
					}
					System.out.println(content);
					if (content.contains("</script>")) {
						cookie = "";
						flag = false;
						continue;
					} else if (content.contains("DocContent")) {
						List<NameValuePair> formparams = new ArrayList<NameValuePair>();
						formparams.add(new BasicNameValuePair("html", content));
						formparams.add(new BasicNameValuePair("start", String.valueOf(k)));
						formparams.add(new BasicNameValuePair("flag", "1"));

						httpclientHandl.httpPost("UTF-8", serviceConfig.getServiceConfig("submitCourtDocUrl"),
								formparams);
						ArrayList<CourtDoc> arrylist = new ArrayList<CourtDoc>();
						content = StringUtils.substringBetween(content, "[{", "}]");
						// =======中级以及基层=============================================================
						arrylist = getjsonArray("[{" + content + "}]", "");
						page = CalculatPage(Integer.parseInt(arrylist.get(0).getPage()), 20);
						System.out.println("======page=======" + page);
					} else if (content.contains("<html>") || "".equals(content) || content == null) {
						// objProxy.setFlag(1);
						// courtDaoHandl.updatePoxy(objProxy);
						// 取得代理
						// objProxy=getProxy();
						// porxy =objProxy.getIp();
						// intPort =objProxy.getPort();
						continue;
					} else if (content.contains("[]") || content.contains("[ ]")) {
						continue;
					}
					i++;
					System.out.println(i);
				}
				k++;
			}
			j++;
		}
	}

	public void getHtmlListJicheng() throws Exception {
		int page = 1;

		String content = "";
		CookiePojo pojo = new CookiePojo();
		boolean flag = false;
		String cookie = "";

		int i = 0;
		// *****************上传日期********************
		String from = getDate(0);
		String to = getDate(1);
		// ******************************************
		int k = 0;

		for (int j = 0; j < proType.length;) {
			for (k = 0; k < province3.length;) {
				i = 0;
				System.out.println("======" + i + "==========page==" + page);
				String Url = "/list/list/?sorttype=1&conditions=searchWord+++" + from + "%20TO%20" + to + "+"
						+ Util.paraFormatEncode("上传日期") + ":" + from + "%20TO%20" + to
						+ "&conditions=searchWord+2+AJLX++" + Util.paraFormatEncode("案件类型") + ":"
						+ Util.paraFormatEncode(proType[j] + "案件") + "&conditions=searchWord+"
						+ Util.paraFormatEncode(province3[k]) + "+++" + Util.paraFormatEncode("基层法院") + ":"
						+ Util.paraFormatEncode(province3[k]);

				while (i < page) {

					String referer = "";
					String type = "上传日期:" + from + " TO " + to + ",案件类型:" + proType[j] + "案件,基层法院:" + province3[k];
					System.out.println(type);
					Thread.sleep(1000);
					loadProxy();
					String url = constUrl + Url;
					System.out.println(url);

					if (!flag) {
						cookie = httpclientHandl.getCookieByWebClient(url, porxy, intPort);
						Thread.sleep(1000);

						if ("404".equals(cookie)) {
							cookie = "";
							continue;
						}
						flag = true;
					}

					if (flag) {// Cookie取得的场合
						url = "http://wenshu.court.gov.cn/List/ListContent";
						pojo = httpclientHandl.gethtmlByPost("UTF-8", url, referer, host, porxy, intPort, cookie, type,
								String.valueOf(i + 1));
						Thread.sleep(1000);

						content = pojo.getStrResult();
					}

					if ("".equals(content) || content == null) {
						cookie = "";
						flag = false;
						continue;
					}
					System.out.println(content);
					if (content.contains("</script>")) {
						cookie = "";
						flag = false;
						continue;
					} else if (content.contains("DocContent")) {
						List<NameValuePair> formparams = new ArrayList<NameValuePair>();
						formparams.add(new BasicNameValuePair("html", content));
						formparams.add(new BasicNameValuePair("start", String.valueOf(k)));
						formparams.add(new BasicNameValuePair("flag", "2"));

						httpclientHandl.httpPost("UTF-8", serviceConfig.getServiceConfig("submitCourtDocUrl"),
								formparams);
						ArrayList<CourtDoc> arrylist = new ArrayList<CourtDoc>();
						content = StringUtils.substringBetween(content, "[{", "}]");
						// =======中级以及基层=============================================================
						arrylist = getjsonArray("[{" + content + "}]", "");
						page = CalculatPage(Integer.parseInt(arrylist.get(0).getPage()), 20);
						System.out.println("======page=======" + page);
					} else if (content.contains("<html>") || "".equals(content) || content == null) {
						// objProxy.setFlag(1);
						// courtDaoHandl.updatePoxy(objProxy);
						// 取得代理
						// objProxy=getProxy();
						// porxy =objProxy.getIp();
						// intPort =objProxy.getPort();
						continue;
					} else if (content.contains("[]") || content.contains("[ ]")) {
						continue;
					}
					i++;
					System.out.println(i);
				}
				k++;
			}
			j++;
		}
	}

	private ArrayList<CourtDoc> getjsonArray(String jsonString, String province) {
		ArrayList<CourtDoc> arrylist = new ArrayList<CourtDoc>();
		// String strContent =jsonString;
		// System.out.println("%%%%%%%%%%"+strContent);
		jsonString = jsonString.replaceAll("\"裁判要旨段原文(.(?!\"DocContent))*.", "");
		jsonString = jsonString.replace("\\\\\\\"", "'");
		// System.out.println("---0-----"+jsonString);
		jsonString = jsonString.replace("\\\"", "\"");
		// System.out.println("---1-----"+jsonString);
		jsonString = jsonString.replace("\\n", "");
		// System.out.println("---2-----"+jsonString);
		jsonString = jsonString.replace("\\", "");

		// System.out.println("---3-----"+jsonString);
		jsonString = jsonString.replaceAll("(?<=DocContent\":\\s?\")(?!\")[\\s\\w=!\\-{}\\[\\];:./\"]+", "");
		jsonString = jsonString.replace("u0026amp;", "");
		jsonString = jsonString.replace("#xA;", "");
		jsonString = jsonString.replace("lt;/title", "");

		// System.out.println("======转换==="+jsonString);

		System.out.println("======编码===" + jsonString);
		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(jsonString);

		for (int j = 0; j < jsonArray.size(); j++) {
			CourtDoc court = new CourtDoc();
			if (j == 0) {
				court.setPage((String) jsonArray.getJSONObject(j).get("Count"));
			} else {
				// 案号
				court.setCaseNo((String) jsonArray.getJSONObject(j).get("案号"));
				// 法庭
				court.setStrCourt((String) jsonArray.getJSONObject(j).get("法院名称"));
				// 裁判日期
				court.setDepolyTime((String) jsonArray.getJSONObject(j).get("裁判日期"));
				// 内容
				court.setStrContent((String) jsonArray.getJSONObject(j).get("DocContent"));
				// 案件名称
				court.setStrTitle((String) jsonArray.getJSONObject(j).get("案件名称"));
				// 文书Id
				court.setDocId((String) jsonArray.getJSONObject(j).get("文书ID"));
				court.setDataFrom("中国裁判文书网");
				court.setProvince(province);
				System.out.println("===========province=======" + court.getProvince());
			}
			arrylist.add(court);
		}

		return arrylist;
	}

	private int CalculatPage(int num, int pagesize) {

		int number = num % pagesize;

		if (num % pagesize != 0) {
			number = num / pagesize + 1;
		} else {
			number = num / pagesize;
		}

		return number;
	}

	private String getDate(int num) {

		Calendar cald = Calendar.getInstance();
		SimpleDateFormat sim = new java.text.SimpleDateFormat("yyyy-MM-dd");
		cald.add(Calendar.DAY_OF_MONTH, num);

		return sim.format(cald.getTime());

	}
}