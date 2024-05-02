package com.entity.vo;

import com.entity.XiaojiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 校级人员
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("xiaoji")
public class XiaojiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 校级人员姓名
     */

    @TableField(value = "xiaoji_name")
    private String xiaojiName;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 身份证号
     */

    @TableField(value = "xiaoji_id_number")
    private String xiaojiIdNumber;


    /**
     * 手机号
     */

    @TableField(value = "xiaoji_phone")
    private String xiaojiPhone;


    /**
     * 邮箱
     */

    @TableField(value = "xiaoji_email")
    private String xiaojiEmail;


    /**
     * 照片
     */

    @TableField(value = "xiaoji_photo")
    private String xiaojiPhoto;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：校级人员姓名
	 */
    public String getXiaojiName() {
        return xiaojiName;
    }


    /**
	 * 获取：校级人员姓名
	 */

    public void setXiaojiName(String xiaojiName) {
        this.xiaojiName = xiaojiName;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：身份证号
	 */
    public String getXiaojiIdNumber() {
        return xiaojiIdNumber;
    }


    /**
	 * 获取：身份证号
	 */

    public void setXiaojiIdNumber(String xiaojiIdNumber) {
        this.xiaojiIdNumber = xiaojiIdNumber;
    }
    /**
	 * 设置：手机号
	 */
    public String getXiaojiPhone() {
        return xiaojiPhone;
    }


    /**
	 * 获取：手机号
	 */

    public void setXiaojiPhone(String xiaojiPhone) {
        this.xiaojiPhone = xiaojiPhone;
    }
    /**
	 * 设置：邮箱
	 */
    public String getXiaojiEmail() {
        return xiaojiEmail;
    }


    /**
	 * 获取：邮箱
	 */

    public void setXiaojiEmail(String xiaojiEmail) {
        this.xiaojiEmail = xiaojiEmail;
    }
    /**
	 * 设置：照片
	 */
    public String getXiaojiPhoto() {
        return xiaojiPhoto;
    }


    /**
	 * 获取：照片
	 */

    public void setXiaojiPhoto(String xiaojiPhoto) {
        this.xiaojiPhoto = xiaojiPhoto;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
