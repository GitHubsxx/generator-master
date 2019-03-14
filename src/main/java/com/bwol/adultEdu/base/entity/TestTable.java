/****************************************************
 * Description: Controller for 测试管理
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
 *  2019-03-14 bfsu Create File
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
* Date  2019-03-14
*/
public class TestTable extends EntitySupport implements Entity {
    private static final long serialVersionUID = 1L;
    private Long id;//主键
    private String name;//名称
    private Integer size;//大小
    private School school; 


    public TestTable(){
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
     * 设置名称
     * @param (name) (名称)
     */
    public void setName (String name) {this.name = name;} 
	/**
     * 返回名称
     * @return 名称
     */
    public String getName(){ return name;} 
     /**
     * 设置大小
     * @param (size) (大小)
     */
    public void setSize (Integer size) {this.size = size;} 
	/**
     * 返回大小
     * @return 大小
     */
    public Integer getSize(){ return size;} 
	/**
     * @param 外键
     */
    public void setSchool (School school) {this.school = school;} 
     /**
     * @return 外键
     */
    public School getSchool(){ return this.school;} 


    /* (non-Javadoc)
	 * @see com.bfsuolframework.core.entity.EntitySupport#hashCode()
	 */
    public int hashCode() {
        return new HashCodeBuilder().append("com.bwol.adultEdu.base.entity.TestTable").append(this.getId()).toHashCode();
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
        if (!(obj instanceof TestTable))
            return false;
        final TestTable that = (TestTable)obj;
        return new EqualsBuilder().append(this.getId(), that.getId()).isEquals();
    }
    /* (non-Javadoc)
     * @see com.bfsuolframework.core.entity.EntitySupport#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.bwol.adultEdu.base.entity.TestTable").append("ID="+this.getId()).toString();
    }

}