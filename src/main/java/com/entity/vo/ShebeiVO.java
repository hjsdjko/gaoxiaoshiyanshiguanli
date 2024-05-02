package com.entity.vo;

import com.entity.ShebeiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 实验室设备
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("shebei")
public class ShebeiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 设备名称
     */

    @TableField(value = "shebei_name")
    private String shebeiName;


    /**
     * 发布实验室人员
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 发布时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 设备类型
     */

    @TableField(value = "shebei_types")
    private Integer shebeiTypes;


    /**
     * 设备作用
     */

    @TableField(value = "shebei_content")
    private String shebeiContent;


    /**
     * 院级审核结果
     */

    @TableField(value = "yuanji_content")
    private String yuanjiContent;


    /**
     * 校级审核结果
     */

    @TableField(value = "xiaoji_content")
    private String xiaojiContent;


    /**
     * 审核状态
     */

    @TableField(value = "shenhe_types")
    private Integer shenheTypes;


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
	 * 设置：设备名称
	 */
    public String getShebeiName() {
        return shebeiName;
    }


    /**
	 * 获取：设备名称
	 */

    public void setShebeiName(String shebeiName) {
        this.shebeiName = shebeiName;
    }
    /**
	 * 设置：发布实验室人员
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：发布实验室人员
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：发布时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：发布时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：设备类型
	 */
    public Integer getShebeiTypes() {
        return shebeiTypes;
    }


    /**
	 * 获取：设备类型
	 */

    public void setShebeiTypes(Integer shebeiTypes) {
        this.shebeiTypes = shebeiTypes;
    }
    /**
	 * 设置：设备作用
	 */
    public String getShebeiContent() {
        return shebeiContent;
    }


    /**
	 * 获取：设备作用
	 */

    public void setShebeiContent(String shebeiContent) {
        this.shebeiContent = shebeiContent;
    }
    /**
	 * 设置：院级审核结果
	 */
    public String getYuanjiContent() {
        return yuanjiContent;
    }


    /**
	 * 获取：院级审核结果
	 */

    public void setYuanjiContent(String yuanjiContent) {
        this.yuanjiContent = yuanjiContent;
    }
    /**
	 * 设置：校级审核结果
	 */
    public String getXiaojiContent() {
        return xiaojiContent;
    }


    /**
	 * 获取：校级审核结果
	 */

    public void setXiaojiContent(String xiaojiContent) {
        this.xiaojiContent = xiaojiContent;
    }
    /**
	 * 设置：审核状态
	 */
    public Integer getShenheTypes() {
        return shenheTypes;
    }


    /**
	 * 获取：审核状态
	 */

    public void setShenheTypes(Integer shenheTypes) {
        this.shenheTypes = shenheTypes;
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
