package com.upg.zx.capture.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;


public class CorpSearchName {

//	String placeWord[] = {"南京市","江宁区","溧水县","高淳县","六合县","江浦县","徐州市","邳州市","新沂市","铜山县","睢宁县","沛县",
//						  "丰县","连云港市","赣榆县","灌云县","东海县","灌南县","淮安","楚州区","清浦区 ","清河区","淮阴区","涟水县",
//						  "洪泽县","金湖县","盱眙县","宿迁市","宿豫县","沭阳县","泗阳县","泗洪县","盐城市","东台市","大丰市","盐都县",
//						  "建湖县","响水县","射阳县","阜宁县","滨海县","扬州市","高邮市","江都市","仪征市","胥浦","邢江区","宝应县",
//						  "泰州市","泰兴市","姜堰市","靖江市","兴化市","南通市","如皋市","通州市","海门市","启东市","海安县","如东县",
//						  "镇江市","扬州市","丹阳市","句容市","丹徒县","常州市","武进市","金坛市","溧阳市","无锡市","宜兴市","江阴市",
//						  "苏州市","吴江市","昆山市","太仓市","常熟市","张家港市"  };
	
	private static CorpSearchName CorpSearchInstance = null;

	
	private String placeWord[] = {"南京市","江宁区","溧水县","高淳县","六合县","江浦县","徐州市","邳州市","新沂市","铜山县","睢宁县","沛县",
			  			  "丰县","连云港市","赣榆县","灌云县","东海县","灌南县","淮安","楚州区","清浦区 ","清河区","淮阴区","涟水县",
						  "洪泽县","金湖县","盱眙县","宿迁市","宿豫县","沭阳县","泗阳县","泗洪县","盐城市","东台市","大丰市","盐都县",
						  "建湖县","响水县","射阳县","阜宁县","滨海县","扬州市","高邮市","江都市","仪征市","胥浦","邢江区","宝应县",
						  "泰州市","泰兴市","姜堰市","靖江市","兴化市","南通市","如皋市","通州市","海门市","启东市","海安县","如东县",
						  "镇江市","扬州市","丹阳市","句容市","丹徒县","常州市","武进市","金坛市","溧阳市","无锡市","宜兴市","江阴市",
						  "苏州市","吴江市","昆山市","太仓市","常熟市","张家港市", 
						  "南京","江宁","溧水","高淳","六合","江浦","徐州","邳州","新沂","铜山","睢宁","沛",
			  			  "丰","连云港","赣榆","灌云","东海","灌南","淮安","楚州","清浦 ","清河","淮阴","涟水",
						  "洪泽","金湖","盱眙","宿迁","宿豫","沭阳","泗阳","泗洪","盐城","东台","大丰","盐都",
						  "建湖","响水","射阳","阜宁","滨海","扬州","高邮","江都","仪征","胥浦","邢江","宝应",
						  "泰州","泰兴","姜堰","靖江","兴化","南通","如皋","通州","海门","启东","海安","如东",
						  "镇江","扬州","丹阳","句容","丹徒","常州","武进","金坛","溧阳","无锡","宜兴","江阴",
						  "苏州","吴江","昆山","太仓","常熟","张家港"};
	
	private String goodWord[] = {"一","二","三","四","五","六","七","八","九","十",
						 "电","奇","琪","晴","庆","兴","巧","俏","瑞","仁","同","婷",
						 "勇","玉","羽","阳","雅","胖","风","音","雨","朋","胖","盼",
						 "绿","爱","生","顺","素","火","胜","土","东","南","西","北",
						 "春","夏","秋","冬","多","福","丰","富","发","芳","高","贵",
						 "合","华","辉","慧","俊","洁","嘉","静","佳","景","吉","康",
						 "客","龙","兰","真","早","信","学","星","晓","香","秀","霞",
						 "晨","诚","财","宝","美","满","中","长","健","千","万","大",
						 "小"  };
	
	private String preWord[] = {"小","大", "老"};
	
	private String afterWord[] = {"氏"};
	
	private String familyName[] = {
					"赵","钱","孙","李","周","吴","郑","王",
					"冯","陈","楮","卫","蒋","沈","韩","杨",
					"朱","秦","尤","许","何","吕","施","张",
					"孔","曹","严","华","金","魏","陶","姜",
					"戚","谢","邹","喻","柏","水","窦","章",
					"云","苏","潘","葛","奚","范","彭","郎",
					"鲁","韦","昌","马","苗","凤","花","方",
					"俞","任","袁","柳","酆","鲍","史","唐",
					"费","廉","岑","薛","雷","贺","倪","汤",
					"滕","殷","罗","毕","郝","邬","安","常",
					"乐","于","时","傅","皮","卞","齐","康",
					"伍","余","元","卜","顾","孟","平","黄",
					"和","穆","萧","尹","姚","邵","湛","汪",
					"祁","毛","禹","狄","米","贝","明","臧",
					"计","伏","成","戴","谈","宋","茅","庞",
					"熊","纪","舒","屈","项","祝","董","梁",
					"杜","阮","蓝","闽","席","季","麻","强",
					"贾","路","娄","危","江","童","颜","郭",
					"梅","盛","林","刁","锺","徐","丘","骆",
					"高","夏","蔡","田","樊","胡","凌","霍",
					"虞","万","支","柯","昝","管","卢","莫",
					"经","房","裘","缪","干","解","应","宗",
					"丁","宣","贲","邓","郁","单","杭","洪",
					"包","诸","左","石","崔","吉","钮","龚",
					"程","嵇","邢","滑","裴","陆","荣","翁",
					"荀","羊","於","惠","甄","麹","家","封",
					"芮","羿","储","靳","汲","邴","糜","松",
					"井","段","富","巫","乌","焦","巴","弓",
					"牧","隗","山","谷","车","侯","宓","蓬",
					"全","郗","班","仰","秋","仲","伊","宫",
					"宁","仇","栾","暴","甘","斜","厉","戎",
					"祖","武","符","刘","景","詹","束","龙",
					"叶","幸","司","韶","郜","黎","蓟","薄",
					"印","宿","白","怀","蒲","邰","从","鄂",
					"索","咸","籍","赖","卓","蔺","屠","蒙",
					"池","乔","阴","郁","胥","能","苍","双",
					"闻","莘","党","翟","谭","贡","劳","逄",
					"姬","申","扶","堵","冉","宰","郦","雍",
					"郤","璩","桑","桂","濮","牛","寿","通",
					"边","扈","燕","冀","郏","浦","尚","农",
					"温","别","庄","晏","柴","瞿","阎","充",
					"慕","连","茹","习","宦","艾","鱼","容",
					"向","古","易","慎","戈","廖","庾","终",
					"暨","居","衡","步","都","耿","满","弘",
					"匡","国","文","寇","广","禄","阙","东",
					"欧","殳","沃","利","蔚","越","夔","隆",
					"师","巩","厍","聂","晁","勾","敖","融",
					"冷","訾","辛","阚","那","简","饶","空",
					"曾","毋","沙","乜","养","鞠","须","丰",
					"巢","关","蒯","相","查","后","荆","红",
					"游","竺","权","逑","盖","益","桓","公" };
	
	public static int areaTypeSeq = 0;
	public static String[] areaType = PropertyHelper.getKeyValue("areaType").split(",");
	
	public String corpSearchCorpCommon[] = null;
	
	//华北
//	public String corpSearchCorpBJ[] = null;
	public String corpSearchCorpTJ[] = null;
	public String corpSearchCorpHeB[] = null;   // 河北
	public String corpSearchCorpSX2[] = null; //山西
	public String corpSearchCorpNMG[] = null;
//	public static int seqCorpBJ = 0;
	public static int seqCorpTJ = 0;
	public static int seqCorpHeB = 0;
	public static int seqCorpSX2 = 0;
//	public static int seqCorpNMG = 0;
	//东北
	public String corpSearchCorpLN[] = null;
//	public String corpSearchCorpJL[] = null;
	public String corpSearchCorpHLJ[] = null;
	public static int seqCorpLN = 0;
	public static int seqCorpHLJ = 0;
	//华东
	public String corpSearchCorpSH[] = null;
	public String corpSearchCorpJS[] = null;
//	public String corpSearchCorpZJ[] = null;
	public String corpSearchCorpAH[] = null;
	public String corpSearchCorpFJ[] = null;
	public String corpSearchCorpJX[] = null;
	public String corpSearchCorpSD[] = null;
	public static int seqCorpSH = 0;
	public static int seqCorpJS = 0;
	public static int seqCorpSD = 0;
	public static int seqCorpAH = 0;
	public static int seqCorpJX = 0;
	public static int seqCorpFJ = 0;
	//华南
//	public String corpSearchCorpGD[] = null;
	public String corpSearchCorpGX[] = null;
//	public String corpSearchCorpHaiN[] = null;
//	public static int seqCorpGD = 0;
	public static int seqCorpGX = 0;
//	public static int seqCorpHaiN = 0;
	//华中
	public String corpSearchCorpHeN[] = null; //河南
	public String corpSearchCorpHB[] = null;  //湖北
	public String corpSearchCorpHN[] = null;  //湖南
	public static int seqCorpHeN = 0;
	public static int seqCorpHB = 0;
	public static int seqCorpHN = 0;
	//西南
	public String corpSearchCorpCQ[] = null;
	public String corpSearchCorpSC[] = null;
//	public String corpSearchCorpGZ[] = null;
	public String corpSearchCorpYN[] = null;
	public String corpSearchCorpXZ[] = null;
	public static int seqCorpCQ = 0;
	public static int seqCorpSC = 0;
	public static int seqCorpYN = 0;
	public static int seqCorpXZ = 0;
	//西北
	public String corpSearchCorpSX1[] = null; //陕西
	public String corpSearchCorpGS[] = null;
	public String corpSearchCorpQH[] = null;
	public String corpSearchCorpNX[] = null;
	public String corpSearchCorpXJ[] = null;
	public static int seqCorpSX1 = 0;
	public static int seqCorpGS = 0;
	public static int seqCorpQH = 0;
	public static int seqCorpNX = 0;
	public static int seqCorpXJ = 0;
	
	public String corpSearchGoodWord[];
	public String corpSearchNameXing[];
	public String corpSearchNameXiao[];
	public String corpSearchNameDa[];
	public String corpSearchNameLao[];
//	public String corpSearchNameShi[];
	
	public static int seqGoodWord = 0;
	public static int seqXing = 0;
	public static int seqXiao = 0;
	public static int seqDa = 0;
	public static int seqLao = 0;
	public static int seqShi = 0;

	
	public HashMap<String, String> areacodeMap = new HashMap<String , String>();      
 
	private CorpSearchName() {
		areaType = PropertyHelper.getKeyValue("areaType").split(",");

		// seqCorpBJ = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpBJ"));
		seqCorpTJ = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpTJ"));
		seqCorpHeB = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpHeB"));
		seqCorpSX2 = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpSX2"));
		// seqCorpNMG = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpNMG"));
		//东北
		seqCorpLN = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpLN"));
		seqCorpHLJ = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpHLJ"));
		//华东
		seqCorpSH = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpSH"));
		seqCorpJS = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpJS"));
		seqCorpSD = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpSD"));
		seqCorpAH = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpAH"));
		seqCorpJX = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpJX"));
		seqCorpFJ = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpFJ"));
		//华南
		//seqCorpGD = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpGD"));
		seqCorpGX = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpGX"));
		//seqCorpHaiN = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpHaiN"));
		//华中
		seqCorpHeN = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpHeN"));
		seqCorpHB = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpHB"));
		seqCorpHN = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpHN"));
		//西南
		seqCorpCQ = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpCQ"));
		seqCorpSC = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpSC"));
		seqCorpYN = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpYN"));
		seqCorpXZ = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpXZ"));
		//西北
		seqCorpSX1 = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpSX1"));
		seqCorpGS = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpGS"));
		seqCorpQH = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpQH"));
		seqCorpNX = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpNX"));
		seqCorpXJ = Integer.valueOf(PropertyHelper.getKeyValue("seqCorpXJ"));

		seqGoodWord = Integer.valueOf(PropertyHelper.getKeyValue("seqGoodWord"));
		seqXing = Integer.valueOf(PropertyHelper.getKeyValue("seqXing"));
		seqXiao = Integer.valueOf(PropertyHelper.getKeyValue("seqXiao"));
		seqDa = Integer.valueOf(PropertyHelper.getKeyValue("seqDa"));
		seqLao = Integer.valueOf(PropertyHelper.getKeyValue("seqLao"));
		seqShi = Integer.valueOf(PropertyHelper.getKeyValue("seqShi"));
		initialAreaCode();
		//initial();
	}

	public static CorpSearchName getInstance(){
		if (CorpSearchInstance == null) {
			CorpSearchInstance = new CorpSearchName();
		}
		return CorpSearchInstance;
	}
	private void initial(){

		// 从企业列表文件中读取
		// 概率大
		readCommonFile();
		
		// initial areacode
		initialAreaCode();
				
		// 地区 + goodword
		// 概率大
		corpSearchGoodWord = new String[placeWord.length * goodWord.length];
		for(int i = 0; i<placeWord.length; i++){
			for(int j = 0; j<goodWord.length; j++){
				corpSearchGoodWord[i*goodWord.length + j] = placeWord[i]+goodWord[j];
			}
		}
		
		// 地区 + 姓
		// 概率大
		corpSearchNameXing = new String[placeWord.length * familyName.length];
		for(int i = 0; i<placeWord.length; i++){
			for(int j = 0; j<familyName.length; j++){
				corpSearchNameXing[i*familyName.length+j] = placeWord[i]+familyName[j];
			}
		}
		
		// 地区 + 小 + 姓
		// 概率小
		corpSearchNameXiao = new String[placeWord.length * familyName.length];
		for(int i = 0; i<placeWord.length; i++){
			for(int j = 0; j<familyName.length; j++){
				corpSearchNameXiao[i*placeWord.length+j] = placeWord[i]+"小"+familyName[j];
			}
		}
		
		// 地区 + 大 + 姓
		// 概率小
		corpSearchNameDa = new String[placeWord.length * familyName.length];
		for(int i = 0; i<placeWord.length; i++){
			for(int j = 0; j<familyName.length; j++){
				corpSearchNameDa[i*placeWord.length+j] = placeWord[i]+"大"+familyName[j];
			}
		}
			
		// 地区 + 老 + 姓
		// 概率小
		corpSearchNameLao = new String[placeWord.length * familyName.length];
		for(int i = 0; i<placeWord.length; i++){
			for(int j = 0; j<familyName.length; j++){
				corpSearchNameLao[i*placeWord.length+j] = placeWord[i]+"老"+familyName[j];
			}
		}
		
		// 地区 + 姓 + 氏
		// 概率小
//		corpSearchNameShi = new String[placeWord.length * familyName.length];
//		for(int i = 0; i<placeWord.length; i++){
//			for(int j = 0; j<familyName.length; j++){
//				corpSearchNameShi[i*placeWord.length+j] = placeWord[i]+familyName[j]+"氏";
//			}
//		}
		
	}
	
	/**
	 * areaCode initial
	 */
	public void initialAreaCode() {
		//华北
		areacodeMap.put("110000" , "BJ");  
		areacodeMap.put("120000" , "TJ"); 
		areacodeMap.put("130000" , "HeB");  
		areacodeMap.put("140000" , "SX2"); 
		areacodeMap.put("150000" , "NMG"); 
		
		//东北
		areacodeMap.put("210000" , "LN");  
		areacodeMap.put("220000" , "JL"); 
		areacodeMap.put("230000" , "HLJ"); 
		
		//华东
		areacodeMap.put("310000" , "SH");  
		areacodeMap.put("320000" , "JS"); 
		areacodeMap.put("330000" , "ZJ");  
		areacodeMap.put("340000" , "AH"); 
		areacodeMap.put("350000" , "FJ"); 
		areacodeMap.put("360000" , "JX");  
		areacodeMap.put("370000" , "SD"); 
		//华南
		areacodeMap.put("410000" , "HeN"); 
		areacodeMap.put("420000" , "HB");  
		areacodeMap.put("430000" , "HN"); 
		//华中
		areacodeMap.put("440000" , "GD");  
		areacodeMap.put("450000" , "GX");  
		areacodeMap.put("460000" , "HaiN"); 
		
		//西南
		areacodeMap.put("500000" , "CQ"); 
		areacodeMap.put("510000" , "SC"); 
		areacodeMap.put("520000" , "GZ"); 
		areacodeMap.put("530000" , "YN"); 
		areacodeMap.put("540000" , "XZ");
		
		//西北
		areacodeMap.put("610000" , "SX1"); 
		areacodeMap.put("620000" , "GS"); 
		areacodeMap.put("630000" , "QH"); 
		areacodeMap.put("640000" , "NX"); 
		areacodeMap.put("650000" , "XJ"); 
	}
	
	/**
	 * get area by areaCode
	 */
	public String getAreaByCode(String areaCode) {
		return areacodeMap.get(areaCode);  
	}
	
	/**
	 * get areaCode by area
	 */
	public String getAreaCodeByArea(String area) {
		Set<String> mapSet =  areacodeMap.keySet();	
		//获取所有的key值 为set的集合		
		Iterator<String> itor =  mapSet.iterator();
		//获取key的Iterator便利		
		while(itor.hasNext()){
			//存在下一个值			
			String key = itor.next();
			//当前key值		
			if(areacodeMap.get(key).equals(area)){
				//获取value 与 所知道的value比较			
				return key;	
			}
		}
		return "";
	}
	
	private void readCommonFile(){
		try { 
            String encoding="UTF-8"; 
            File file=new File("corp_list0121.txt"); 
            if(file.isFile() && file.exists()){ //判断文件是否存在 
                InputStreamReader read = new InputStreamReader( new FileInputStream(file),encoding);//考虑到编码格式 
                BufferedReader bufferedReader = new BufferedReader(read); 
                String lineTxt = null, corpName=null;
                ArrayList arrayList = new ArrayList();
                while((lineTxt = bufferedReader.readLine()) != null){ 
                	corpName = lineTxt;
					arrayList.add(corpName);
                } 
                read.close(); 

                corpSearchCorpCommon = new String[arrayList.size()];
                for(int k=0; k<arrayList.size(); k++){   
                	corpSearchCorpCommon[k]=(String)arrayList.get(k);   
                } 
		    }else{ 
		        System.out.println("找不到指定的文件"); 
		    } 
	    } catch (Exception e) { 
	        System.out.println(e.toString()); 
	        e.printStackTrace(); 
	    } 
	}
	
	public String[] getCorpSearchCorp2() {
		// get the corp name from different file one bye one
		if (areaTypeSeq == areaType.length)
			areaTypeSeq = 0;

		String area = areaType[areaTypeSeq];

		Class<?> clazz = this.getClass();
		Field fSeq = null, fCorp = null;
		String corpName = null;
		String areaCode = "";
		try {
			fSeq = clazz.getField("seqCorp" + area);
			int seqCorp = (int) fSeq.get(this);

			// fCorp = clazz.getDeclaredField("corpSearchCorp"+area);
			// String[] array = (String[]) fCorp.get(this);

 			PropertyHelper.writeProperties("seqCorp" + area,
					String.valueOf(seqCorp + 1));
 			
			fSeq.set(this, seqCorp + 1);
			areaCode = getAreaCodeByArea(areaType[areaTypeSeq++]);
			// corpName = array[seqCorp];
			corpName = getCorpNameForService(areaCode);

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String[] { areaCode, corpName, area };
	}
/**
	 * 从数据库获取打码关键字
	 * 
	 * @author 001362
	 * @param seqCorp
	 * @date 2016年4月29日
	 */
	public String getCorpNameForService(String areaCode) {
		String corpName = "";
		// 10.10.168.15 /corp/getKeyWord
		try {
			System.out.println("getCorpNameForService===============>>>>>>>>>>>>>>>>>>"+areaCode+"<<<<<<<<<<<<<<<===============");
			String json = HttpClientUtil
					.getRequest("http://testcapture.wzyrz.cn/Zheng/corp/getKeyWord?size=1&areaCode="
							+ areaCode);
			JSONObject json_Obj = JSONObject.fromObject(json);
			if (json_Obj.getJSONArray("data") != null) {
				List<String> list = (List<String>) json_Obj
						.getJSONArray("data");
				if (list != null && list.size() > 0) {
					String[] temp = list.toArray(new String[] {});
					if (temp.length > 0) {
						corpName = temp[0];
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return corpName;
	}

	
	private void readCorpFile(){
		for (int i=0; i<areaType.length ; i++){
			try { 
	            String encoding="UTF-8"; 
	            File file=new File("Corp"+areaType[i]+".txt"); 
//	            File file=new File("corp_list0121.txt"); 
	            if(file.isFile() && file.exists()){ //判断文件是否存在 
	                InputStreamReader read = new InputStreamReader( new FileInputStream(file),encoding);//考虑到编码格式 
	                BufferedReader bufferedReader = new BufferedReader(read); 
	                String lineTxt = null, corpName=null;
	                ArrayList arrayList = new ArrayList();
	                while((lineTxt = bufferedReader.readLine()) != null){ 
	                	try{
	                		if (lineTxt.indexOf("个体")!=-1 || lineTxt.indexOf("个人")!=-1 )
	                			corpName = lineTxt.substring(0, 2);
	                		else if(lineTxt.indexOf("区")!=-1 && (lineTxt.indexOf("区") < lineTxt.length() -2) ){
                					corpName=lineTxt.substring(lineTxt.indexOf("区")+1, lineTxt.indexOf("区")+3);
		    					}
	                		else if(lineTxt.indexOf("县")!=-1){
                				corpName=lineTxt.substring(lineTxt.indexOf("县")+1, lineTxt.indexOf("县")+3);
	    					}else if(lineTxt.indexOf("市")!=-1 && (lineTxt.indexOf("市") < lineTxt.length() -2) ){
    							corpName=lineTxt.substring(lineTxt.indexOf("市")+1, lineTxt.indexOf("市")+3);
	    					}
	    					else if(lineTxt.indexOf("省")!=-1 && (lineTxt.indexOf("省") < lineTxt.length() -2) ){
    							corpName=lineTxt.substring(lineTxt.indexOf("省")+1, lineTxt.indexOf("省")+3);
	    					}
	    					else{
	    						if (lineTxt.length() < 2)
	    							;
	    						else if (lineTxt.length() < 4)
	    							corpName = lineTxt;
	    						else {
    								corpName = lineTxt.substring(2, 4);
	    							
	    							if ("（".equals( lineTxt.substring(2, 3)) ) {
	    								corpName = lineTxt.substring(3, 5);
	    							}
	    							else if ("（".equals( lineTxt.substring(3, 4)) ) {
	    								corpName = lineTxt.substring(1, 3);
	    							}
	    						}
	    					}
	                	} catch (Exception e) { 
	            	        System.out.println("读取文件内容出错"); 
	            	        e.printStackTrace(); 
	            	    }

						arrayList.add(corpName);
	                } 
	                read.close(); 

	                Class<?> clazz = this.getClass(); 
	                Field fCorp = clazz.getDeclaredField("corpSearchCorp"+areaType[i]);
//	                corpSearchCorpCommon = new String[arrayList.size()];
	                String[] CorpArray = new String[arrayList.size()];
	                for(int k=0; k<arrayList.size(); k++){   
	                	CorpArray[k]=(String)arrayList.get(k);   
	                } 
	                fCorp.set(this, CorpArray );
			    }else{ 
			        System.out.println("找不到指定的文件"); 
			    } 
		    } catch (Exception e) { 
		        System.out.println(e.toString()); 
		        e.printStackTrace(); 
		    } 
        }
	}
	
	// 企业列表名称
	// 概率大
	public String[] getCorpSearchCorp() {
		// get the corp name from different file one bye one 
		if ( areaTypeSeq == areaType.length ) areaTypeSeq = 0;
		
		String area = areaType[areaTypeSeq];
		
        Class<?> clazz = this.getClass();   
        Field fSeq = null, fCorp = null;
        String corpName = null;
        
        try {
        	fSeq = clazz.getField("seqCorp"+area);
			int seqCorp = (int) fSeq.get(this);
			
//			fCorp = clazz.getDeclaredField("corpSearchCorp"+area);
//			String[] array = (String[]) fCorp.get(this);
			
	        boolean isSearched = false;
			do {
				if ( seqCorp == corpSearchCorpCommon.length) {
					System.out.println("it  has been got to the end, return null. seqCorp: " + seqCorp);
					return new String[]{area,""};
				}
				isSearched = false;
    			System.out.println("seqCorp"+area+":   " + seqCorp);
    			PropertyHelper.writeProperties("seqCorp"+area, String.valueOf(seqCorp+1) );
    			fSeq.set(this, seqCorp+1 );

//    			corpName = array[seqCorp]; 
    			corpName = corpSearchCorpCommon[seqCorp]; 
    			
    			// check whether the name has already been searched 
    			for(int i = 0; i < seqCorp; i++ ){
    				if ( corpName.equals(corpSearchCorpCommon[i]) ) {
    					isSearched = true;
    					seqCorp += 1;
    					System.out.println(corpName + "  has already been searched, get next one.");
    					break;
    				}
    			}
	        }while(isSearched);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

    	String areaCode = getAreaCodeByArea(areaType[areaTypeSeq++]);
		return new String[]{areaCode, corpName, area};
	}
	
	public void decreaseAreaSeqCorp(String area) {
		// decrease Area SeqCorp 
		
        Class<?> clazz = this.getClass();   
        Field fSeq = null;
        
        try {
        	fSeq = clazz.getField("seqCorp"+area);
			int seqCorp = (int) fSeq.get(this);
					
			System.out.println("decrease seqCorp"+area+":   " + seqCorp);
			PropertyHelper.writeProperties("seqCorp"+area, String.valueOf(seqCorp-1) );
			fSeq.set(this, seqCorp-1 );

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	// 地区 + goodword
	// 概率大
	public String getCorpSearchGoodWord() {
		if ( seqGoodWord == placeWord.length * goodWord.length) {
			return "";
		}
		else {
			System.out.println(seqGoodWord);
			PropertyHelper.writeProperties("seqGoodWord", String.valueOf(seqGoodWord) );
			return corpSearchGoodWord[seqGoodWord++];
		}
	}
	
	// 地区 + 姓
	// 概率大
	public String getCorpSearchNameXing() {	
		if ( seqXing == placeWord.length * familyName.length) {
			return "";
		}
		else {
			System.out.println(seqXing);
			PropertyHelper.writeProperties("seqXing", String.valueOf(seqXing) );
			return corpSearchNameXing[seqXing++];
		}
	}
	
	// 地区 + 小 + 姓
	// 概率小
	public String getCorpSearchNameXiao() {
		if ( seqXiao == placeWord.length * familyName.length) {
			return "";
		}
		else{
			System.out.println(seqXiao);
			PropertyHelper.writeProperties("seqXiao", String.valueOf(seqXiao) );
			return corpSearchNameXiao[seqXiao++];
		}
	}
	
	// 地区 + 大 + 姓
	// 概率小
	public String getCorpSearchNameDa() {
		if ( seqDa == placeWord.length * familyName.length) {
			return "";
		}
		else{
			System.out.println(seqDa);
			PropertyHelper.writeProperties("seqDa", String.valueOf(seqDa) );
			return corpSearchNameDa[seqDa++];
		}
	}
	
	// 地区 + 老 + 姓
	// 概率小
	public String getCorpSearchNameLao() {
		if ( seqLao == placeWord.length * familyName.length) {
			return "";
		}
		else{
			System.out.println(seqLao);
			PropertyHelper.writeProperties("seqLao", String.valueOf(seqLao) );
			return corpSearchNameLao[seqLao++];
		}
	}
	
	// 地区 + 姓 + 氏  //
	// 概率小
//	public String getCorpSearchNameShi() {	
//		if ( seqShi == placeWord.length * familyName.length) {
//			return "";
//		}
//		else{
//			System.out.println(seqShi);
//	        PropertyHelper.writeProperties("seqShi", String.valueOf(seqShi) );
//			return corpSearchNameShi[seqShi++];
//		}
//	}
	
	public static void main(String[] args)  { 

		CorpSearchName corpSearchName = new CorpSearchName();
		corpSearchName.initial();
//		for(int i = 1; i < 2; i++){
//			String[] tmp = corpSearchName.getCorpSearchCorp();
//			System.out.println(tmp[0]+ "     -----   "+ tmp[1]);
//		}
		corpSearchName.readCorpFile();
		
		File file = new File("d:/tmp.txt");
		FileOutputStream in = null;
		try {
			in = new FileOutputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		byte bt[] = new byte[1024];
		String line = "";
        for(int k=0; k < corpSearchName.corpSearchCorpCommon.length; k++){   
        	line = corpSearchName.corpSearchCorpCommon[k] + "\n"; 
        	
        	
    		bt = line.getBytes();
    			
    			try {
    				in.write(bt, 0, bt.length);
    				
    				// boolean success=true;
    				// System.out.println("写入文件成功");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        } 
        try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(corpSearchName.getAreaCodeByArea("HaiN"));
	}
}
