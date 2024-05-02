package com.entity.view;

import com.entity.ShebeiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 实验室设备
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("shebei")
public class ShebeiView extends ShebeiEntity implements Serializable {
    private static final long serialVersionUID = 1L;
		/**
		* 设备类型的值
		*/
		private String shebeiValue;
		/**
		* 审核状态的值
		*/
		private String shenheValue;



		//级联表 yonghu
			/**
			* 实验室人员姓名
			*/
			private String yonghuName;
			/**
			* 身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 手机号
			*/
			private String yonghuPhone;
			/**
			* 邮箱
			*/
			private String yonghuEmail;
			/**
			* 照片
			*/
			private String yonghuPhoto;
			/**
			* 实验室
			*/
			private Integer shiyanshiTypes;
				/**
				* 实验室的值
				*/
				private String shiyanshiValue;

	public ShebeiView() {

	}

	public ShebeiView(ShebeiEntity shebeiEntity) {
		try {
			BeanUtils.copyProperties(this, shebeiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 设备类型的值
			*/
			public String getShebeiValue() {
				return shebeiValue;
			}
			/**
			* 设置： 设备类型的值
			*/
			public void setShebeiValue(String shebeiValue) {
				this.shebeiValue = shebeiValue;
			}
			/**
			* 获取： 审核状态的值
			*/
			public String getShenheValue() {
				return shenheValue;
			}
			/**
			* 设置： 审核状态的值
			*/
			public void setShenheValue(String shenheValue) {
				this.shenheValue = shenheValue;
			}












				//级联表的get和set yonghu
					/**
					* 获取： 实验室人员姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 实验室人员姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}
					/**
					* 获取： 照片
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 照片
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}
					/**
					* 获取： 实验室
					*/
					public Integer getShiyanshiTypes() {
						return shiyanshiTypes;
					}
					/**
					* 设置： 实验室
					*/
					public void setShiyanshiTypes(Integer shiyanshiTypes) {
						this.shiyanshiTypes = shiyanshiTypes;
					}


						/**
						* 获取： 实验室的值
						*/
						public String getShiyanshiValue() {
							return shiyanshiValue;
						}
						/**
						* 设置： 实验室的值
						*/
						public void setShiyanshiValue(String shiyanshiValue) {
							this.shiyanshiValue = shiyanshiValue;
						}




}
