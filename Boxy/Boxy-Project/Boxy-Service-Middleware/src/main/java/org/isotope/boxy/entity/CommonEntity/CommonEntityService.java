package org.isotope.boxy.entity.CommonEntity;

import java.util.HashMap;

import org.isotope.jfp.framework.support.MyDataBaseOperateSupport2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 数据信息*/
@Service
public class CommonEntityService extends MyDataBaseOperateSupport2<CommonEntityDBO> {
    protected static final Logger logger = LoggerFactory.getLogger(CommonEntityService.class);

    public CommonEntityDao getDao(){
        return getMySqlSession().getMapper(CommonEntityDao.class);
    }
    public void doCall(HashMap<String,String> map) {
		getDao().doCall(map);
	}
    
	public void test(){
		CommonEntityDBO ced = new CommonEntityDBO();
		ced.setPuk("1");
		doInsert(ced);
		ced.setK01("2");
		doUpdate(ced);

		ced = new CommonEntityDBO();
		ced.setPuk("1");
		ced.setK01("2");

		System.out.println(doRead(ced));
		System.out.println(doSelectData(ced));
		
	}
	
//	public List<CommonEntityDBO> loadTypeDataList(String type){
//		CommonEntityDBO ced = new CommonEntityDBO();
//		ced.setGgg(paramData.getString("type"));
//	 	return doSelectData(ced);
//	}

}
