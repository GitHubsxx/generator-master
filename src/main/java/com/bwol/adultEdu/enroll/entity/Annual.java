/****************************************************
 * Description: Controller for 年度管理
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
 *  2019-01-01 bfsu Create File
 **************************************************/
package com.bwol.adultEdu.enroll.entity;

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
* Date  2019-01-01
*/
public class Annual extends EntitySupport implements Entity {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String simpleTitle;
    private Integer isEnroll;
    private Long createUserId;
    private String createUserName;
    private Date createTime;
    private Long updateUserId;
    private String updateUserName;
    private Date updateTime;


    public Annual(){
    }

    public void setId (Long id) {this.id = id;} 
    public Long getId(){ return id;} 
    public void setTitle (String title) {this.title = title;} 
    public String getTitle(){ return title;} 
    public void setSimpleTitle (String simpleTitle) {this.simpleTitle = simpleTitle;} 
    public String getSimpleTitle(){ return simpleTitle;} 
    public void setIsEnroll (Integer isEnroll) {this.isEnroll = isEnroll;} 
    public Integer getIsEnroll(){ return isEnroll;} 
    public void setCreateUserId (Long createUserId) {this.createUserId = createUserId;} 
    public Long getCreateUserId(){ return createUserId;} 
    public void setCreateUserName (String createUserName) {this.createUserName = createUserName;} 
    public String getCreateUserName(){ return createUserName;} 
    public void setCreateTime (Date createTime) {this.createTime = createTime;} 
    public Date getCreateTime(){ return createTime;} 
    public void setUpdateUserId (Long updateUserId) {this.updateUserId = updateUserId;} 
    public Long getUpdateUserId(){ return updateUserId;} 
    public void setUpdateUserName (String updateUserName) {this.updateUserName = updateUserName;} 
    public String getUpdateUserName(){ return updateUserName;} 
    public void setUpdateTime (Date updateTime) {this.updateTime = updateTime;} 
    public Date getUpdateTime(){ return updateTime;} 


    /* (non-Javadoc)
	 * @see com.bfsuolframework.core.entity.EntitySupport#hashCode()
	 */
    public int hashCode() {
        return new HashCodeBuilder().append("com.bwol.adultEdu.base.entity.Annual").append(this.getId()).toHashCode();
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
        if (!(obj instanceof Annual))
            return false;
        final Annual that = (Annual)obj;
        return new EqualsBuilder().append(this.getId(), that.getId()).isEquals();
    }
    /* (non-Javadoc)
     * @see com.bfsuolframework.core.entity.EntitySupport#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.bwol.adultEdu.enroll.entity.Annual").append("ID="+this.getId()).toString();
    }

}