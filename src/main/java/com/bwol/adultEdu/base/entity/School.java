/****************************************************
 * Description: Controller for 学校管理
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
 *  2019-03-13 bfsu Create File
 **************************************************/
package com.bwol.adultEdu.base.entity;

import java.io.Serializable;
import java.util.List;
import com.bwol.framework.entity.Entity;
import com.bwol.framework.entity.EntitySupport;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
* Author sxx
* Date  2019-03-13
*/
public class School extends EntitySupport implements Entity {
    private static final long serialVersionUID = 1L;
    private Long id;//主键
    private String title;//学校名称
    private String address;//学校所在地
    private String runSchoolForm;//办学形式
    private String runSchoolType;//办学类型
    private String approvalNumber;//批准文号
    private String planProperties;//计划属性
    private String createUserName;//创建人
    private Long createUserId;//创建人ID
    private String updateUserName;//更新人
    private Long updateUserId;//更新人ID


    public School(){
    }

     /**
     * @param 主键
     */
    public void setId (Long id) {this.id = id;} 

     /**
     * @return 主键
     */
    public Long getId(){ return id;} 

     /**
     * 设置学校名称
     * @param (title) (学校名称)
     */
    public void setTitle (String title) {this.title = title;} 

	/**
     * 返回学校名称
     * @return 学校名称
     */
    public String getTitle(){ return title;} 

     /**
     * 设置学校所在地
     * @param (address) (学校所在地)
     */
    public void setAddress (String address) {this.address = address;} 

	/**
     * 返回学校所在地
     * @return 学校所在地
     */
    public String getAddress(){ return address;} 

     /**
     * 设置办学形式
     * @param (runSchoolForm) (办学形式)
     */
    public void setRunSchoolForm (String runSchoolForm) {this.runSchoolForm = runSchoolForm;} 

	/**
     * 返回办学形式
     * @return 办学形式
     */
    public String getRunSchoolForm(){ return runSchoolForm;} 

     /**
     * 设置办学类型
     * @param (runSchoolType) (办学类型)
     */
    public void setRunSchoolType (String runSchoolType) {this.runSchoolType = runSchoolType;} 

	/**
     * 返回办学类型
     * @return 办学类型
     */
    public String getRunSchoolType(){ return runSchoolType;} 

     /**
     * 设置批准文号
     * @param (approvalNumber) (批准文号)
     */
    public void setApprovalNumber (String approvalNumber) {this.approvalNumber = approvalNumber;} 

	/**
     * 返回批准文号
     * @return 批准文号
     */
    public String getApprovalNumber(){ return approvalNumber;} 

     /**
     * 设置计划属性
     * @param (planProperties) (计划属性)
     */
    public void setPlanProperties (String planProperties) {this.planProperties = planProperties;} 

	/**
     * 返回计划属性
     * @return 计划属性
     */
    public String getPlanProperties(){ return planProperties;} 

     /**
     * 设置创建人
     * @param (createUserName) (创建人)
     */
    public void setCreateUserName (String createUserName) {this.createUserName = createUserName;} 

	/**
     * 返回创建人
     * @return 创建人
     */
    public String getCreateUserName(){ return createUserName;} 

     /**
     * 设置创建人ID
     * @param (createUserId) (创建人ID)
     */
    public void setCreateUserId (Long createUserId) {this.createUserId = createUserId;} 

	/**
     * 返回创建人ID
     * @return 创建人ID
     */
    public Long getCreateUserId(){ return createUserId;} 

     /**
     * 设置更新人
     * @param (updateUserName) (更新人)
     */
    public void setUpdateUserName (String updateUserName) {this.updateUserName = updateUserName;} 

	/**
     * 返回更新人
     * @return 更新人
     */
    public String getUpdateUserName(){ return updateUserName;} 

     /**
     * 设置更新人ID
     * @param (updateUserId) (更新人ID)
     */
    public void setUpdateUserId (Long updateUserId) {this.updateUserId = updateUserId;} 

	/**
     * 返回更新人ID
     * @return 更新人ID
     */
    public Long getUpdateUserId(){ return updateUserId;} 



    /* (non-Javadoc)
	 * @see com.bfsuolframework.core.entity.EntitySupport#hashCode()
	 */
    public int hashCode() {
        return new HashCodeBuilder().append("com.bwol.adultEdu.base.entity.School").append(this.getId()).toHashCode();
    }
    /* (non-Javadoc)
     * @see com.bfsuolframework.core.entity.EntitySupport#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
    	if(null == obj) {
    		return false;
    	}
        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;
        if (!(obj instanceof School))
            return false;
        final School that = (School)obj;
        return new EqualsBuilder().append(this.getId(), that.getId()).isEquals();
    }
    /* (non-Javadoc)
     * @see com.bfsuolframework.core.entity.EntitySupport#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.bwol.adultEdu.base.entity.School").append("ID="+this.getId()).toString();
    }

}