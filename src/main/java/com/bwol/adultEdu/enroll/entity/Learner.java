/****************************************************
 * Description: Controller for 学员管理
 * Copyright:   Copyright (c) 2019
 * Company:     beiwaionline
 * @author      bfsu
 * @version     1.0
 * @see
 *  2019-01-06 bfsu Create File
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
* Date  2019-01-06
*/
public class Learner extends EntitySupport implements Entity {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long baseUserId; 
    private Long annual; 
    private Long studyCenter; 
    private Long specialty; 
    private Long level; 
    private String center; 
    private String documentType; 
    private String documentTypeCode; 
    private String idnumber; 
    private String name; 
    private String sex; 
    private String sexCode; 
    private String national; 
    private String nationalCode; 
    private String dateOfBirth; 
    private String birthPlace; 
    private String politicsStatus; 
    private String politicsStatusCode; 
    private String educationalLevel; 
    private String educationalLevelShort; 
    private String educationalLevelCode; 
    private String professionalCategory; 
    private String professionalCategoryCode; 
    private String postalCode; 
    private String postalAddress; 
    private String mobilePhoneNumber; 
    private String phoneNumber; 
    private String email; 
    private String originalHighestDegreeSchool; 
    private String originalMajorName; 
    private String certificateNumber; 
    private String graduationDateOfOriginalMajor; 
    private String careType; 
    private String careTypeCode; 
    private String exemptionReasons; 
    private String major; 
    private String majorCode; 
    private String candidateVolunteerCode; 
    private String adjustWay; 
    private String adjustCode; 
    private String examForeignLanguage; 
    private String foreignLanguages; 
    private String sports; 
    private String sportsLevel; 
    private String examinationLevel; 
    private String workUnits; 
    private String courseOne; 
    private String courseTwo; 
    private String courseThree; 
    private String courseFour; 
    private String courseFive; 
    private String courseSix; 
    private double bonusPoints; 
    private double totalScore; 
    private String examinationCardnumber; 
    private String candidateNumberOfBeijing; 
    private String candidateNumberOfMinistry; 
    private String dateOfApplication; 
    private String studentCode; 
    private String recruitStatus; 
    private String professionalExtraExam; 
    private String admissionResults; 
    private String userState; 
    private Long createUserId; 
    private String createUserName; 
    private Date createTime; 
    private Long updateUserId; 
    private String updateUserName; 
    private Date updateTime; 
    private Level level; 


    public Learner(){
    }

    public void setId (Long id) {this.id = id;} 
    public Long getId(){ return id;} 
    public void setBaseUserId (Long baseUserId) {this.baseUserId = baseUserId;} 
    public Long getBaseUserId(){ return baseUserId;} 
    public void setAnnual (Long annual) {this.annual = annual;} 
    public Long getAnnual(){ return annual;} 
    public void setStudyCenter (Long studyCenter) {this.studyCenter = studyCenter;} 
    public Long getStudyCenter(){ return studyCenter;} 
    public void setSpecialty (Long specialty) {this.specialty = specialty;} 
    public Long getSpecialty(){ return specialty;} 
    public void setLevel (Long level) {this.level = level;} 
    public Long getLevel(){ return level;} 
    public void setCenter (String center) {this.center = center;} 
    public String getCenter(){ return center;} 
    public void setDocumentType (String documentType) {this.documentType = documentType;} 
    public String getDocumentType(){ return documentType;} 
    public void setDocumentTypeCode (String documentTypeCode) {this.documentTypeCode = documentTypeCode;} 
    public String getDocumentTypeCode(){ return documentTypeCode;} 
    public void setIdnumber (String idnumber) {this.idnumber = idnumber;} 
    public String getIdnumber(){ return idnumber;} 
    public void setName (String name) {this.name = name;} 
    public String getName(){ return name;} 
    public void setSex (String sex) {this.sex = sex;} 
    public String getSex(){ return sex;} 
    public void setSexCode (String sexCode) {this.sexCode = sexCode;} 
    public String getSexCode(){ return sexCode;} 
    public void setNational (String national) {this.national = national;} 
    public String getNational(){ return national;} 
    public void setNationalCode (String nationalCode) {this.nationalCode = nationalCode;} 
    public String getNationalCode(){ return nationalCode;} 
    public void setDateOfBirth (String dateOfBirth) {this.dateOfBirth = dateOfBirth;} 
    public String getDateOfBirth(){ return dateOfBirth;} 
    public void setBirthPlace (String birthPlace) {this.birthPlace = birthPlace;} 
    public String getBirthPlace(){ return birthPlace;} 
    public void setPoliticsStatus (String politicsStatus) {this.politicsStatus = politicsStatus;} 
    public String getPoliticsStatus(){ return politicsStatus;} 
    public void setPoliticsStatusCode (String politicsStatusCode) {this.politicsStatusCode = politicsStatusCode;} 
    public String getPoliticsStatusCode(){ return politicsStatusCode;} 
    public void setEducationalLevel (String educationalLevel) {this.educationalLevel = educationalLevel;} 
    public String getEducationalLevel(){ return educationalLevel;} 
    public void setEducationalLevelShort (String educationalLevelShort) {this.educationalLevelShort = educationalLevelShort;} 
    public String getEducationalLevelShort(){ return educationalLevelShort;} 
    public void setEducationalLevelCode (String educationalLevelCode) {this.educationalLevelCode = educationalLevelCode;} 
    public String getEducationalLevelCode(){ return educationalLevelCode;} 
    public void setProfessionalCategory (String professionalCategory) {this.professionalCategory = professionalCategory;} 
    public String getProfessionalCategory(){ return professionalCategory;} 
    public void setProfessionalCategoryCode (String professionalCategoryCode) {this.professionalCategoryCode = professionalCategoryCode;} 
    public String getProfessionalCategoryCode(){ return professionalCategoryCode;} 
    public void setPostalCode (String postalCode) {this.postalCode = postalCode;} 
    public String getPostalCode(){ return postalCode;} 
    public void setPostalAddress (String postalAddress) {this.postalAddress = postalAddress;} 
    public String getPostalAddress(){ return postalAddress;} 
    public void setMobilePhoneNumber (String mobilePhoneNumber) {this.mobilePhoneNumber = mobilePhoneNumber;} 
    public String getMobilePhoneNumber(){ return mobilePhoneNumber;} 
    public void setPhoneNumber (String phoneNumber) {this.phoneNumber = phoneNumber;} 
    public String getPhoneNumber(){ return phoneNumber;} 
    public void setEmail (String email) {this.email = email;} 
    public String getEmail(){ return email;} 
    public void setOriginalHighestDegreeSchool (String originalHighestDegreeSchool) {this.originalHighestDegreeSchool = originalHighestDegreeSchool;} 
    public String getOriginalHighestDegreeSchool(){ return originalHighestDegreeSchool;} 
    public void setOriginalMajorName (String originalMajorName) {this.originalMajorName = originalMajorName;} 
    public String getOriginalMajorName(){ return originalMajorName;} 
    public void setCertificateNumber (String certificateNumber) {this.certificateNumber = certificateNumber;} 
    public String getCertificateNumber(){ return certificateNumber;} 
    public void setGraduationDateOfOriginalMajor (String graduationDateOfOriginalMajor) {this.graduationDateOfOriginalMajor = graduationDateOfOriginalMajor;} 
    public String getGraduationDateOfOriginalMajor(){ return graduationDateOfOriginalMajor;} 
    public void setCareType (String careType) {this.careType = careType;} 
    public String getCareType(){ return careType;} 
    public void setCareTypeCode (String careTypeCode) {this.careTypeCode = careTypeCode;} 
    public String getCareTypeCode(){ return careTypeCode;} 
    public void setExemptionReasons (String exemptionReasons) {this.exemptionReasons = exemptionReasons;} 
    public String getExemptionReasons(){ return exemptionReasons;} 
    public void setMajor (String major) {this.major = major;} 
    public String getMajor(){ return major;} 
    public void setMajorCode (String majorCode) {this.majorCode = majorCode;} 
    public String getMajorCode(){ return majorCode;} 
    public void setCandidateVolunteerCode (String candidateVolunteerCode) {this.candidateVolunteerCode = candidateVolunteerCode;} 
    public String getCandidateVolunteerCode(){ return candidateVolunteerCode;} 
    public void setAdjustWay (String adjustWay) {this.adjustWay = adjustWay;} 
    public String getAdjustWay(){ return adjustWay;} 
    public void setAdjustCode (String adjustCode) {this.adjustCode = adjustCode;} 
    public String getAdjustCode(){ return adjustCode;} 
    public void setExamForeignLanguage (String examForeignLanguage) {this.examForeignLanguage = examForeignLanguage;} 
    public String getExamForeignLanguage(){ return examForeignLanguage;} 
    public void setForeignLanguages (String foreignLanguages) {this.foreignLanguages = foreignLanguages;} 
    public String getForeignLanguages(){ return foreignLanguages;} 
    public void setSports (String sports) {this.sports = sports;} 
    public String getSports(){ return sports;} 
    public void setSportsLevel (String sportsLevel) {this.sportsLevel = sportsLevel;} 
    public String getSportsLevel(){ return sportsLevel;} 
    public void setExaminationLevel (String examinationLevel) {this.examinationLevel = examinationLevel;} 
    public String getExaminationLevel(){ return examinationLevel;} 
    public void setWorkUnits (String workUnits) {this.workUnits = workUnits;} 
    public String getWorkUnits(){ return workUnits;} 
    public void setCourseOne (String courseOne) {this.courseOne = courseOne;} 
    public String getCourseOne(){ return courseOne;} 
    public void setCourseTwo (String courseTwo) {this.courseTwo = courseTwo;} 
    public String getCourseTwo(){ return courseTwo;} 
    public void setCourseThree (String courseThree) {this.courseThree = courseThree;} 
    public String getCourseThree(){ return courseThree;} 
    public void setCourseFour (String courseFour) {this.courseFour = courseFour;} 
    public String getCourseFour(){ return courseFour;} 
    public void setCourseFive (String courseFive) {this.courseFive = courseFive;} 
    public String getCourseFive(){ return courseFive;} 
    public void setCourseSix (String courseSix) {this.courseSix = courseSix;} 
    public String getCourseSix(){ return courseSix;} 
    public void setBonusPoints (double bonusPoints) {this.bonusPoints = bonusPoints;} 
    public double getBonusPoints(){ return bonusPoints;} 
    public void setTotalScore (double totalScore) {this.totalScore = totalScore;} 
    public double getTotalScore(){ return totalScore;} 
    public void setExaminationCardnumber (String examinationCardnumber) {this.examinationCardnumber = examinationCardnumber;} 
    public String getExaminationCardnumber(){ return examinationCardnumber;} 
    public void setCandidateNumberOfBeijing (String candidateNumberOfBeijing) {this.candidateNumberOfBeijing = candidateNumberOfBeijing;} 
    public String getCandidateNumberOfBeijing(){ return candidateNumberOfBeijing;} 
    public void setCandidateNumberOfMinistry (String candidateNumberOfMinistry) {this.candidateNumberOfMinistry = candidateNumberOfMinistry;} 
    public String getCandidateNumberOfMinistry(){ return candidateNumberOfMinistry;} 
    public void setDateOfApplication (String dateOfApplication) {this.dateOfApplication = dateOfApplication;} 
    public String getDateOfApplication(){ return dateOfApplication;} 
    public void setStudentCode (String studentCode) {this.studentCode = studentCode;} 
    public String getStudentCode(){ return studentCode;} 
    public void setRecruitStatus (String recruitStatus) {this.recruitStatus = recruitStatus;} 
    public String getRecruitStatus(){ return recruitStatus;} 
    public void setProfessionalExtraExam (String professionalExtraExam) {this.professionalExtraExam = professionalExtraExam;} 
    public String getProfessionalExtraExam(){ return professionalExtraExam;} 
    public void setAdmissionResults (String admissionResults) {this.admissionResults = admissionResults;} 
    public String getAdmissionResults(){ return admissionResults;} 
    public void setUserState (String userState) {this.userState = userState;} 
    public String getUserState(){ return userState;} 
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
    public void setLevel (Level level) {this.level = level;} 
    public Level getLevel(){ return this.level;} 


    /* (non-Javadoc)
	 * @see com.bfsuolframework.core.entity.EntitySupport#hashCode()
	 */
    public int hashCode() {
        return new HashCodeBuilder().append("com.bwol.adultEdu.base.entity.Learner").append(this.getId()).toHashCode();
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
        if (!(obj instanceof Learner))
            return false;
        final Learner that = (Learner)obj;
        return new EqualsBuilder().append(this.getId(), that.getId()).isEquals();
    }
    /* (non-Javadoc)
     * @see com.bfsuolframework.core.entity.EntitySupport#toString()
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.bwol.adultEdu.enroll.entity.Learner").append("ID="+this.getId()).toString();
    }

}