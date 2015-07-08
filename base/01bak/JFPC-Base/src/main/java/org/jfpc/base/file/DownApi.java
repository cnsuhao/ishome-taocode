package org.jfpc.base.file;
import org.jfpc.base.bean.RESTResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
/** */
public class DownApi   {
    private static final Logger logger = LoggerFactory.getLogger(DownApi.class);
  
	@RequestMapping(value = "/00088000/{clientType}/{version}", method = RequestMethod.GET)
	@ResponseBody
    public RESTResultBean selectBuffetCabinetByArea(@PathVariable String clientType,@PathVariable String version){
    	RESTResultBean resultBean = new RESTResultBean();
    	resultBean.setResult("http://172.16.3.6/update1.0-1.1.zip");
    	resultBean.setToken("1.1");
    	resultBean.setResultcode("1");
    	resultBean.setResultmessage("需要更新");
		return resultBean;
    }

}
