package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 院级人员
 *
 * @author 
 * @email
 */
@TableName("yuanji")
public class YuanjiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YuanjiEntity() {

	}

	public YuanjiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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
     * 院级人员姓名
     */
    @TableField(value = "yuanji_name")

    private String yuanjiName;


    /**
     * 性别
     */
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 身份证号
     */
    @TableField(value = "yuanji_id_number")

    private String yuanjiIdNumber;


    /**
     * 手机号
     */
    @TableField(value = "yuanji_phone")

    private String yuanjiPhone;


    /**
     * 邮箱
     */
    @TableField(value = "yuanji_email")

    private String yuanjiEmail;


    /**
     * 照片
     */
    @TableField(value = "yuanji_photo")

    private String yuanjiPhoto;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：院级人员姓名
	 */
    public String getYuanjiName() {
        return yuanjiName;
    }


    /**
	 * 获取：院级人员姓名
	 */

    public void setYuanjiName(String yuanjiName) {
        this.yuanjiName = yuanjiName;
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
    public String getYuanjiIdNumber() {
        return yuanjiIdNumber;
    }


    /**
	 * 获取：身份证号
	 */

    public void setYuanjiIdNumber(String yuanjiIdNumber) {
        this.yuanjiIdNumber = yuanjiIdNumber;
    }
    /**
	 * 设置：手机号
	 */
    public String getYuanjiPhone() {
        return yuanjiPhone;
    }


    /**
	 * 获取：手机号
	 */

    public void setYuanjiPhone(String yuanjiPhone) {
        this.yuanjiPhone = yuanjiPhone;
    }
    /**
	 * 设置：邮箱
	 */
    public String getYuanjiEmail() {
        return yuanjiEmail;
    }


    /**
	 * 获取：邮箱
	 */

    public void setYuanjiEmail(String yuanjiEmail) {
        this.yuanjiEmail = yuanjiEmail;
    }
    /**
	 * 设置：照片
	 */
    public String getYuanjiPhoto() {
        return yuanjiPhoto;
    }


    /**
	 * 获取：照片
	 */

    public void setYuanjiPhoto(String yuanjiPhoto) {
        this.yuanjiPhoto = yuanjiPhoto;
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

    @Override
    public String toString() {
        return "Yuanji{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", yuanjiName=" + yuanjiName +
            ", sexTypes=" + sexTypes +
            ", yuanjiIdNumber=" + yuanjiIdNumber +
            ", yuanjiPhone=" + yuanjiPhone +
            ", yuanjiEmail=" + yuanjiEmail +
            ", yuanjiPhoto=" + yuanjiPhoto +
            ", createTime=" + createTime +
        "}";
    }
}
