package com.mcookies.qxy.base.News;
import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 新闻表*/
public class NewsDBO extends MyDataBaseObjectSupport
{
    /** 
     * 新闻id
     */
    private Long newsId = null;
 
    /** 
     * 学校id
     */
    private Long sid = null;
 
    /** 
     * 栏目id
     */
    private Long columnId = null;
 
    /** 
     * 新闻标题
     */
    private String title = null;
 
    /** 
     * 内容
     */
    private String content = null;
 
    /** 
     * 是否置顶
     */
    private Integer isTop = null;
 
    /** 
     * 是否包含图片
     */
    private Integer isPic = null;
 
    /** 
     * 幻灯图片
     */
    private String pic = null;
 
    /** 
     * 新闻阅读范围
     */
    private Integer newsType = null;
 
    /** 
     * 新闻可读教师
     */
    private String newsReader = null;
 
    /** 
     * 新闻可读班级
     */
    private String newsClasser = null;
 
    /** 
     * 发布时间
     */
    private String publishTime = null;
 
    /** 
     * 审核状态
     */
    private Integer isAudit = null;
 
    /** 
     * 阅读次数
     */
    private Long num = null;
 
    /** 
     * 作者
     */
    private Long author = null;
 
    /** 
     * 是否为首页轮播
     */
    private Integer isHomenews = null;
 
    /** 
     * 是否启用
     */
    private Integer isUse = null;
 
    /** 
     * 创建时间
     */
    private String createTime = null;
 
    /** 
     * 创建者
     */
    private Long creator = null;
 
    /** 
     * 更新时间
     */
    private String updateTime = null;
 
    /** 
     * 最后更新者
     */
    private Long updator = null;
 
    /** 
     * 获取新闻id
     *
     * @return News_id 新闻id
     */
    public Long getNewsId() {
        return this.newsId;
    }
 
    /** 
     * 获取学校id
     *
     * @return Sid 学校id
     */
    public Long getSid() {
        return this.sid;
    }
 
    /** 
     * 获取栏目id
     *
     * @return Column_id 栏目id
     */
    public Long getColumnId() {
        return this.columnId;
    }
 
    /** 
     * 获取新闻标题
     *
     * @return Title 新闻标题
     */
    public String getTitle() {
        return this.title;
    }
 
    /** 
     * 获取内容
     *
     * @return Content 内容
     */
    public String getContent() {
        return this.content;
    }
 
    /** 
     * 获取是否置顶
     *
     * @return Is_top 是否置顶
     */
    public Integer getIsTop() {
        return this.isTop;
    }
 
    /** 
     * 获取是否包含图片
     *
     * @return Is_pic 是否包含图片
     */
    public Integer getIsPic() {
        return this.isPic;
    }
 
    /** 
     * 获取幻灯图片
     *
     * @return Pic 幻灯图片
     */
    public String getPic() {
        return this.pic;
    }
 
    /** 
     * 获取新闻阅读范围
     *
     * @return News_type 新闻阅读范围
     */
    public Integer getNewsType() {
        return this.newsType;
    }
 
    /** 
     * 获取新闻可读教师
     *
     * @return News_reader 新闻可读教师
     */
    public String getNewsReader() {
        return this.newsReader;
    }
 
    /** 
     * 获取新闻可读班级
     *
     * @return News_classer 新闻可读班级
     */
    public String getNewsClasser() {
        return this.newsClasser;
    }
 
    /** 
     * 获取发布时间
     *
     * @return Publish_time 发布时间
     */
    public String getPublishTime() {
        return this.publishTime;
    }
 
    /** 
     * 获取审核状态
     *
     * @return Is_audit 审核状态
     */
    public Integer getIsAudit() {
        return this.isAudit;
    }
 
    /** 
     * 获取阅读次数
     *
     * @return Num 阅读次数
     */
    public Long getNum() {
        return this.num;
    }
 
    /** 
     * 获取作者
     *
     * @return Author 作者
     */
    public Long getAuthor() {
        return this.author;
    }
 
    /** 
     * 获取是否为首页轮播
     *
     * @return Is_homenews 是否为首页轮播
     */
    public Integer getIsHomenews() {
        return this.isHomenews;
    }
 
    /** 
     * 获取是否启用
     *
     * @return Is_use 是否启用
     */
    public Integer getIsUse() {
        return this.isUse;
    }
 
    /** 
     * 获取创建时间
     *
     * @return Create_time 创建时间
     */
    public String getCreateTime() {
        return this.createTime;
    }
 
    /** 
     * 获取创建者
     *
     * @return Creator 创建者
     */
    public Long getCreator() {
        return this.creator;
    }
 
    /** 
     * 获取更新时间
     *
     * @return Update_time 更新时间
     */
    public String getUpdateTime() {
        return this.updateTime;
    }
 
    /** 
     * 获取最后更新者
     *
     * @return Updator 最后更新者
     */
    public Long getUpdator() {
        return this.updator;
    }
 
    /** 
     * 设置新闻id
     *
     * @param News_id 新闻id
     */
    public void setNewsId(Long newsid) {
        this.newsId = newsid;
    }
 
    /** 
     * 设置学校id
     *
     * @param Sid 学校id
     */
    public void setSid(Long sid) {
        this.sid = sid;
    }
 
    /** 
     * 设置栏目id
     *
     * @param Column_id 栏目id
     */
    public void setColumnId(Long columnid) {
        this.columnId = columnid;
    }
 
    /** 
     * 设置新闻标题
     *
     * @param Title 新闻标题
     */
    public void setTitle(String title) {
        this.title = title;
    }
 
    /** 
     * 设置内容
     *
     * @param Content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
 
    /** 
     * 设置是否置顶
     *
     * @param Is_top 是否置顶
     */
    public void setIsTop(Integer istop) {
        this.isTop = istop;
    }
 
    /** 
     * 设置是否包含图片
     *
     * @param Is_pic 是否包含图片
     */
    public void setIsPic(Integer ispic) {
        this.isPic = ispic;
    }
 
    /** 
     * 设置幻灯图片
     *
     * @param Pic 幻灯图片
     */
    public void setPic(String pic) {
        this.pic = pic;
    }
 
    /** 
     * 设置新闻阅读范围
     *
     * @param News_type 新闻阅读范围
     */
    public void setNewsType(Integer newstype) {
        this.newsType = newstype;
    }
 
    /** 
     * 设置新闻可读教师
     *
     * @param News_reader 新闻可读教师
     */
    public void setNewsReader(String newsreader) {
        this.newsReader = newsreader;
    }
 
    /** 
     * 设置新闻可读班级
     *
     * @param News_classer 新闻可读班级
     */
    public void setNewsClasser(String newsclasser) {
        this.newsClasser = newsclasser;
    }
 
    /** 
     * 设置发布时间
     *
     * @param Publish_time 发布时间
     */
    public void setPublishTime(String publishtime) {
        this.publishTime = publishtime;
    }
 
    /** 
     * 设置审核状态
     *
     * @param Is_audit 审核状态
     */
    public void setIsAudit(Integer isaudit) {
        this.isAudit = isaudit;
    }
 
    /** 
     * 设置阅读次数
     *
     * @param Num 阅读次数
     */
    public void setNum(Long num) {
        this.num = num;
    }
 
    /** 
     * 设置作者
     *
     * @param Author 作者
     */
    public void setAuthor(Long author) {
        this.author = author;
    }
 
    /** 
     * 设置是否为首页轮播
     *
     * @param Is_homenews 是否为首页轮播
     */
    public void setIsHomenews(Integer ishomenews) {
        this.isHomenews = ishomenews;
    }
 
    /** 
     * 设置是否启用
     *
     * @param Is_use 是否启用
     */
    public void setIsUse(Integer isuse) {
        this.isUse = isuse;
    }
 
    /** 
     * 设置创建时间
     *
     * @param Create_time 创建时间
     */
    public void setCreateTime(String createtime) {
        this.createTime = createtime;
    }
 
    /** 
     * 设置创建者
     *
     * @param Creator 创建者
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }
 
    /** 
     * 设置更新时间
     *
     * @param Update_time 更新时间
     */
    public void setUpdateTime(String updatetime) {
        this.updateTime = updatetime;
    }
 
    /** 
     * 设置最后更新者
     *
     * @param Updator 最后更新者
     */
    public void setUpdator(Long updator) {
        this.updator = updator;
    }
 
}
