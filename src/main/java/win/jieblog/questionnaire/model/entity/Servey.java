package win.jieblog.questionnaire.model.entity;

import java.util.Date;

public class Servey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column servey.serveyid
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    private Integer serveyid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column servey.surveyserialid
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    private String surveyserialid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column servey.creatorserialId
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    private String creatorserialid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column servey.title
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column servey.tag
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    private String tag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column servey.remark
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column servey.total
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    private Integer total;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column servey.createdatetime
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    private Date createdatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column servey.updatedatetime
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    private Date updatedatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column servey.serveyid
     *
     * @return the value of servey.serveyid
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public Integer getServeyid() {
        return serveyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column servey.serveyid
     *
     * @param serveyid the value for servey.serveyid
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public void setServeyid(Integer serveyid) {
        this.serveyid = serveyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column servey.surveyserialid
     *
     * @return the value of servey.surveyserialid
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public String getSurveyserialid() {
        return surveyserialid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column servey.surveyserialid
     *
     * @param surveyserialid the value for servey.surveyserialid
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public void setSurveyserialid(String surveyserialid) {
        this.surveyserialid = surveyserialid == null ? null : surveyserialid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column servey.creatorserialId
     *
     * @return the value of servey.creatorserialId
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public String getCreatorserialid() {
        return creatorserialid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column servey.creatorserialId
     *
     * @param creatorserialid the value for servey.creatorserialId
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public void setCreatorserialid(String creatorserialid) {
        this.creatorserialid = creatorserialid == null ? null : creatorserialid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column servey.title
     *
     * @return the value of servey.title
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column servey.title
     *
     * @param title the value for servey.title
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column servey.tag
     *
     * @return the value of servey.tag
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column servey.tag
     *
     * @param tag the value for servey.tag
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column servey.remark
     *
     * @return the value of servey.remark
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column servey.remark
     *
     * @param remark the value for servey.remark
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column servey.total
     *
     * @return the value of servey.total
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column servey.total
     *
     * @param total the value for servey.total
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column servey.createdatetime
     *
     * @return the value of servey.createdatetime
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public Date getCreatedatetime() {
        return createdatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column servey.createdatetime
     *
     * @param createdatetime the value for servey.createdatetime
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column servey.updatedatetime
     *
     * @return the value of servey.updatedatetime
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public Date getUpdatedatetime() {
        return updatedatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column servey.updatedatetime
     *
     * @param updatedatetime the value for servey.updatedatetime
     *
     * @mbg.generated Thu Nov 15 15:45:15 CST 2018
     */
    public void setUpdatedatetime(Date updatedatetime) {
        this.updatedatetime = updatedatetime;
    }
}