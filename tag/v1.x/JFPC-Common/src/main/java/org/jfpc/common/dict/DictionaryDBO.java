package org.jfpc.common.dict;
import javax.inject.Named;

import org.jfpc.framework.support.MyDataBaseObjectSupport;
 
@Named
/** 数据字典管理*/
public class DictionaryDBO extends MyDataBaseObjectSupport
{
    /** 
     * 大分类ID
     */
    private String k01_max_type_id = "";
 
    /** 
     * 中分类ID
     */
    private String k02_middle_type_id = "";
 
    /** 
     * 小分类ID
     */
    private String k03_small_type_id = "";
 
    /** 
     * 字典名称
     */
    private String f01_dictionary_name = "";
 
    /** 
     * 大分类名称
     */
    private String f02_max_type_name = "";
 
    /** 
     * 中分类名称
     */
    private String f03_middle_type_name = "";
 
    /** 
     * 小分类名称
     */
    private String f04_small_type_name = "";
 
    /** 
     * 排序位置
     */
    private String f05_sort = "";
 
    /** 
     * 获取大分类ID
     *
     * @return K01_max_type_id 大分类ID
     */
    public String getK01_max_type_id() {
        return this.k01_max_type_id;
    }
 
    /** 
     * 获取中分类ID
     *
     * @return K02_middle_type_id 中分类ID
     */
    public String getK02_middle_type_id() {
        return this.k02_middle_type_id;
    }
 
    /** 
     * 获取小分类ID
     *
     * @return K03_small_type_id 小分类ID
     */
    public String getK03_small_type_id() {
        return this.k03_small_type_id;
    }
 
    /** 
     * 获取字典名称
     *
     * @return F01_dictionary_name 字典名称
     */
    public String getF01_dictionary_name() {
        return this.f01_dictionary_name;
    }
 
    /** 
     * 获取大分类名称
     *
     * @return F02_max_type_name 大分类名称
     */
    public String getF02_max_type_name() {
        return this.f02_max_type_name;
    }
 
    /** 
     * 获取中分类名称
     *
     * @return F03_middle_type_name 中分类名称
     */
    public String getF03_middle_type_name() {
        return this.f03_middle_type_name;
    }
 
    /** 
     * 获取小分类名称
     *
     * @return F04_small_type_name 小分类名称
     */
    public String getF04_small_type_name() {
        return this.f04_small_type_name;
    }
 
    /** 
     * 获取排序位置
     *
     * @return F05_sort 排序位置
     */
    public String getF05_sort() {
        return this.f05_sort;
    }
 
    /** 
     * 设置大分类ID
     *
     * @param K01_max_type_id 大分类ID
     */
    public void setK01_max_type_id(String K01_max_type_id) {
        this.k01_max_type_id = K01_max_type_id;
    }
 
    /** 
     * 设置中分类ID
     *
     * @param K02_middle_type_id 中分类ID
     */
    public void setK02_middle_type_id(String K02_middle_type_id) {
        this.k02_middle_type_id = K02_middle_type_id;
    }
 
    /** 
     * 设置小分类ID
     *
     * @param K03_small_type_id 小分类ID
     */
    public void setK03_small_type_id(String K03_small_type_id) {
        this.k03_small_type_id = K03_small_type_id;
    }
 
    /** 
     * 设置字典名称
     *
     * @param F01_dictionary_name 字典名称
     */
    public void setF01_dictionary_name(String F01_dictionary_name) {
        this.f01_dictionary_name = F01_dictionary_name;
    }
 
    /** 
     * 设置大分类名称
     *
     * @param F02_max_type_name 大分类名称
     */
    public void setF02_max_type_name(String F02_max_type_name) {
        this.f02_max_type_name = F02_max_type_name;
    }
 
    /** 
     * 设置中分类名称
     *
     * @param F03_middle_type_name 中分类名称
     */
    public void setF03_middle_type_name(String F03_middle_type_name) {
        this.f03_middle_type_name = F03_middle_type_name;
    }
 
    /** 
     * 设置小分类名称
     *
     * @param F04_small_type_name 小分类名称
     */
    public void setF04_small_type_name(String F04_small_type_name) {
        this.f04_small_type_name = F04_small_type_name;
    }
 
    /** 
     * 设置排序位置
     *
     * @param F05_sort 排序位置
     */
    public void setF05_sort(String F05_sort) {
        this.f05_sort = F05_sort;
    }
 
}
