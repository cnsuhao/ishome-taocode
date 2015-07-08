package org.isotopes.jfp.framework.db.biz;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import org.isotopes.jfp.framework.db.bean.SQLNameListXMLBean;
import org.isotopes.jfp.framework.db.bean.SQLVersionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SQLListBuiness {
	@Autowired
	private SQLNameListXMLBean sqlList;

	/**
	 * @param args
	 */
	public List<SQLVersionBean> readSQLNameListFromXML() {
		ArrayList<SQLVersionBean> sqls = new ArrayList<SQLVersionBean>();
		// 解析XML获得全部SQL清单
		try {
			SQLVersionBean svb = null;
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLStreamReader reader = factory
					.createXMLStreamReader(new FileReader(sqlList
							.getConfigLocation().getFile()));
			while (reader.hasNext()) {
				int event = reader.next();
				// 如果是元素的开始
				if (event == XMLStreamConstants.START_ELEMENT) {
					// 列出所有用户名称
					// try{
					// System.out.println("=="+reader.getElementText());
					// }catch(Exception e){}

					if ("Config".equalsIgnoreCase(reader.getLocalName())) {
						if (svb != null)
							sqls.add(svb);
						svb = new SQLVersionBean();
					} else if ("Puk".equalsIgnoreCase(reader.getLocalName())) {
						svb.setPuk(reader.getElementText());
					} else if ("Key".equalsIgnoreCase(reader.getLocalName())) {
						String filename = reader.getElementText();
						svb.setSqlversion(filename.split("-")[0]);
						svb.setSqlname(filename);
					} else if ("Value".equalsIgnoreCase(reader.getLocalName())) {
						svb.setSqlsize(reader.getElementText());
					} else if ("Meno".equalsIgnoreCase(reader.getLocalName())) {
						svb.setSqlmeno(reader.getElementText());
					}
				}
			}
			System.out.println("====" + sqls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sqls;
	}
}
