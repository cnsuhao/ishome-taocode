import java.sql.Date;
import javax.inject.Named;
 
@Named
/** �豸�����*/
public class DeviceGroupDBO extends MyDataBaseObjectSupport
{
    /** 
     * ����id
     */
    private Long dgroupId = null;
 
    /** 
     * ѧУid
     */
    private Long sid = null;
 
    /** 
     * ��������
     */
    private String dgroupName = null;
 
    /** 
     * ����˵��
     */
    private String dgroupExplain = null;
 
    /** 
     * �Ƿ�����
     */
    private Integer isUse = null;
 
    /** 
     * ����ʱ��
     */
    private String createTime = null;
 
    /** 
     * ������
     */
    private Long creator = null;
 
    /** 
     * ����ʱ��
     */
    private String updateTime = null;
 
    /** 
     * ��������
     */
    private Long updator = null;
 
    /** 
     * ��ȡ����id
     *
     * @return Dgroup_id ����id
     */
    public Long getDgroupId() {
        return this.dgroupId;
    }
 
    /** 
     * ��ȡѧУid
     *
     * @return Sid ѧУid
     */
    public Long getSid() {
        return this.sid;
    }
 
    /** 
     * ��ȡ��������
     *
     * @return Dgroup_name ��������
     */
    public String getDgroupName() {
        return this.dgroupName;
    }
 
    /** 
     * ��ȡ����˵��
     *
     * @return Dgroup_explain ����˵��
     */
    public String getDgroupExplain() {
        return this.dgroupExplain;
    }
 
    /** 
     * ��ȡ�Ƿ�����
     *
     * @return Is_use �Ƿ�����
     */
    public Integer getIsUse() {
        return this.isUse;
    }
 
    /** 
     * ��ȡ����ʱ��
     *
     * @return Create_time ����ʱ��
     */
    public String getCreateTime() {
        return this.createTime;
    }
 
    /** 
     * ��ȡ������
     *
     * @return Creator ������
     */
    public Long getCreator() {
        return this.creator;
    }
 
    /** 
     * ��ȡ����ʱ��
     *
     * @return Update_time ����ʱ��
     */
    public String getUpdateTime() {
        return this.updateTime;
    }
 
    /** 
     * ��ȡ��������
     *
     * @return Updator ��������
     */
    public Long getUpdator() {
        return this.updator;
    }
 
    /** 
     * ���÷���id
     *
     * @param Dgroup_id ����id
     */
    public void setDgroupId(Long dgroupid) {
        this.dgroupId = dgroupid;
    }
 
    /** 
     * ����ѧУid
     *
     * @param Sid ѧУid
     */
    public void setSid(Long sid) {
        this.sid = sid;
    }
 
    /** 
     * ���÷�������
     *
     * @param Dgroup_name ��������
     */
    public void setDgroupName(String dgroupname) {
        this.dgroupName = dgroupname;
    }
 
    /** 
     * ���÷���˵��
     *
     * @param Dgroup_explain ����˵��
     */
    public void setDgroupExplain(String dgroupexplain) {
        this.dgroupExplain = dgroupexplain;
    }
 
    /** 
     * �����Ƿ�����
     *
     * @param Is_use �Ƿ�����
     */
    public void setIsUse(Integer isuse) {
        this.isUse = isuse;
    }
 
    /** 
     * ���ô���ʱ��
     *
     * @param Create_time ����ʱ��
     */
    public void setCreateTime(String createtime) {
        this.createTime = createtime;
    }
 
    /** 
     * ���ô�����
     *
     * @param Creator ������
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }
 
    /** 
     * ���ø���ʱ��
     *
     * @param Update_time ����ʱ��
     */
    public void setUpdateTime(String updatetime) {
        this.updateTime = updatetime;
    }
 
    /** 
     * ������������
     *
     * @param Updator ��������
     */
    public void setUpdator(Long updator) {
        this.updator = updator;
    }
 
}
