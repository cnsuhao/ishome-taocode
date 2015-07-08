package org.isotope.fpm.framework.page.bean;

import java.util.List;

import javax.annotation.Resource;

import org.isotope.fpm.framework.mybatis.plugin.dialect.DefaultDialect;
import org.springframework.stereotype.Service;


//@Named("PageModel")
@Service
public class PageVO {
	//-----------SQL 相关处理-----------------------
    /**
	 * @serial
	 */
    private String SQLState;

	/**
	 * @serial
	 */
    private String SQLVendorCode;
    /**
     * Specific details about the Throwable.  For example, for
     * <tt>FileNotFoundException</tt>, this contains the name of
     * the file that could not be found.
     *
     * @serial
     */
    private String SQLDetailMessage;
	
    //-----------页面相关-----------------------
    /**
     * 总页数
     */
    private int PageCount;
    /**
     * 总记录数
     */
    private int ResultCount;
    /**
     * 当前页码
     */
    private int PageCurrent;
    /**
     * 每页数目
     */
    private int PageLimit;
    /**
     * 当前页面提示信息
     */
    private List<PageMessage> PageMessage;
    
    //-------------------------------------------
    /**
     * 当前页面提示信息
     */
    @Resource
    private DefaultDialect defaultDialect;
  
    //-----------数据相关-----------------------
    /**
     * 当前页面查询结果数据/查询操作参数
     */
    private Object PageData;
    
    //--------------------------------------------
    /**
     * 最终页面显示的底部翻页导航
     */
    public String getPageHtml() {  
        StringBuffer sb = new StringBuffer();  
        if(ResultCount>0){  
            sb.append(" <ul>\n");  
            if(PageCurrent==1){  
                sb.append(" <li class=\"pageinfo\">首页</li>\n");  
                sb.append(" <li class=\"pageinfo\">上页</li>\n");  
            }else{    
                sb.append(" <li><a href=\"#@\" onclick=\"nextPage(1)\">首页</a></li>\n");  
                sb.append(" <li><a href=\"#@\" onclick=\"nextPage("+(PageCurrent-1)+")\">上页</a></li>\n");  
            }  
            int showTag = 3;    //分页标签显示数量  
            int startTag = 1;  
            if(PageCurrent>showTag){  
                startTag = PageCurrent-1;  
            }  
            int endTag = startTag+showTag-1;  
            for(int i=startTag; i<=PageCount && i<=endTag; i++){  
                if(PageCurrent==i)  
                    sb.append("<li class=\"current\">"+i+"</li>\n");  
                else  
                    sb.append(" <li><a href=\"#@\" onclick=\"nextPage("+i+")\">"+i+"</a></li>\n");  
            }  
            if(PageCurrent==PageCount){  
                sb.append(" <li class=\"pageinfo\">下页</li>\n");  
                sb.append(" <li class=\"pageinfo\">尾页</li>\n");  
            }else{  
                sb.append(" <li><a href=\"#@\" onclick=\"nextPage("+(PageCurrent+1)+")\">下页</a></li>\n");  
                sb.append(" <li><a href=\"#@\" onclick=\"nextPage("+PageCount+")\">尾页</a></li>\n");  
            }  
            sb.append(" <li class=\"pageinfo\">第"+PageCurrent+"页</li>\n");  
            sb.append(" <li class=\"pageinfo\">共"+PageCount+"页</li>\n");  
            sb.append("</ul>\n");  
            sb.append("<script type=\"text/javascript\">\n");  
            sb.append("function nextPage(page){");  
            sb.append(" if(true && document.forms[0]){\n");  
            sb.append("     var url = document.forms[0].getAttribute(\"action\");\n");  
            sb.append("     if(url.indexOf('?')>-1){url += \"&PageCurrent=\";}\n");  
            sb.append("     else{url += \"?PageCurrent=\";}\n");  
            sb.append("     document.forms[0].action = url+page;\n");  
            sb.append("     document.forms[0].submit();\n");  
            sb.append(" }else{\n");  
            sb.append("     var url = document.location+';\n");  
            sb.append("     if(url.indexOf('?')>-1){\n");  
            sb.append("         if(url.indexOf('PageCurrent')>-1){\n");  
            sb.append("             var reg = /PageCurrent=\\d*/g;\n");  
            sb.append("             url = url.replace(reg,'PageCurrent=');\n");  
            sb.append("         }else{\n");  
            sb.append("             url += \"&PageCurrent=\";\n");  
            sb.append("         }\n");  
            sb.append("     }else{url += \"?entityOrField=\";}\n");  
            sb.append("     document.location = url + page;\n");  
            sb.append(" }\n");  
            sb.append("}\n");  
            sb.append("</script>\n");  
        }  
        return sb.toString();
    }
    //-----------基本操作-----------------------

	public String getSQLState() {
		return SQLState;
	}

	public void setSQLState(String sQLState) {
		SQLState = sQLState;
	}

	public String getSQLVendorCode() {
		return SQLVendorCode;
	}

	public void setSQLVendorCode(String sQLVendorCode) {
		SQLVendorCode = sQLVendorCode;
	}

	public String getSQLDetailMessage() {
		return SQLDetailMessage;
	}

	public void setSQLDetailMessage(String sQLDetailMessage) {
		SQLDetailMessage = sQLDetailMessage;
	}

	public int getPageCount() {
		return PageCount;
	}

	public void setPageCount(int pageCount) {
		PageCount = pageCount;
	}

	public int getResultCount() {
		return ResultCount;
	}

	public void setResultCount(int resultCount) {
		ResultCount = resultCount;
	}

	public int getPageCurrent() {
		return PageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {
		PageCurrent = pageCurrent;
	}

	public int getPageLimit() {
		return PageLimit;
	}

	public void setPageLimit(int pageLimit) {
		PageLimit = pageLimit;
	}

	public List<PageMessage> getPageMessage() {
		return PageMessage;
	}

	public void setPageMessage(List<PageMessage> pageMessage) {
		PageMessage = pageMessage;
	}

	public DefaultDialect getDefaultDialect() {
		return defaultDialect;
	}

	public void setDefaultDialect(DefaultDialect defaultDialect) {
		this.defaultDialect = defaultDialect;
	}

	public Object getPageData() {
		return PageData;
	}

	public void setPageData(Object pageData) {
		PageData = pageData;
	}
    
}
