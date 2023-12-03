package com.entity.view;

import com.entity.ChangdiOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 场地预约
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("changdi_order")
public class ChangdiOrderView extends ChangdiOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 订单类型的值
		*/
		private String changdiOrderValue;



		//级联表 changdi
			/**
			* 场地编号
			*/
			private String changdiUuidNumber;
			/**
			* 场地名称
			*/
			private String changdiName;
			/**
			* 场地照片
			*/
			private String changdiPhoto;
			/**
			* 场地类型
			*/
			private Integer changdiTypes;
				/**
				* 场地类型的值
				*/
				private String changdiValue;
			/**
			* 场地原价
			*/
			private Double changdiOldMoney;
			/**
			* 场地现价
			*/
			private Double changdiNewMoney;
			/**
			* 时间段
			*/
			private String shijianduan;
			/**
			* 人数
			*/
			private Integer shijianduanRen;
			/**
			* 点击次数
			*/
			private Integer changdiClicknum;
			/**
			* 半全场
			*/
			private Integer banquanTypes;
				/**
				* 半全场的值
				*/
				private String banquanValue;
			/**
			* 是否上架
			*/
			private Integer shangxiaTypes;
				/**
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 推荐吃饭地点
			*/
			private String tuijian;
			/**
			* 逻辑删除
			*/
			private Integer changdiDelete;
			/**
			* 场地简介
			*/
			private String changdiContent;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;
			/**
			* 余额
			*/
			private Double newMoney;

	public ChangdiOrderView() {

	}

	public ChangdiOrderView(ChangdiOrderEntity changdiOrderEntity) {
		try {
			BeanUtils.copyProperties(this, changdiOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 订单类型的值
			*/
			public String getChangdiOrderValue() {
				return changdiOrderValue;
			}
			/**
			* 设置： 订单类型的值
			*/
			public void setChangdiOrderValue(String changdiOrderValue) {
				this.changdiOrderValue = changdiOrderValue;
			}




				//级联表的get和set changdi
					/**
					* 获取： 场地编号
					*/
					public String getChangdiUuidNumber() {
						return changdiUuidNumber;
					}
					/**
					* 设置： 场地编号
					*/
					public void setChangdiUuidNumber(String changdiUuidNumber) {
						this.changdiUuidNumber = changdiUuidNumber;
					}
					/**
					* 获取： 场地名称
					*/
					public String getChangdiName() {
						return changdiName;
					}
					/**
					* 设置： 场地名称
					*/
					public void setChangdiName(String changdiName) {
						this.changdiName = changdiName;
					}
					/**
					* 获取： 场地照片
					*/
					public String getChangdiPhoto() {
						return changdiPhoto;
					}
					/**
					* 设置： 场地照片
					*/
					public void setChangdiPhoto(String changdiPhoto) {
						this.changdiPhoto = changdiPhoto;
					}
					/**
					* 获取： 场地类型
					*/
					public Integer getChangdiTypes() {
						return changdiTypes;
					}
					/**
					* 设置： 场地类型
					*/
					public void setChangdiTypes(Integer changdiTypes) {
						this.changdiTypes = changdiTypes;
					}


						/**
						* 获取： 场地类型的值
						*/
						public String getChangdiValue() {
							return changdiValue;
						}
						/**
						* 设置： 场地类型的值
						*/
						public void setChangdiValue(String changdiValue) {
							this.changdiValue = changdiValue;
						}
					/**
					* 获取： 场地原价
					*/
					public Double getChangdiOldMoney() {
						return changdiOldMoney;
					}
					/**
					* 设置： 场地原价
					*/
					public void setChangdiOldMoney(Double changdiOldMoney) {
						this.changdiOldMoney = changdiOldMoney;
					}
					/**
					* 获取： 场地现价
					*/
					public Double getChangdiNewMoney() {
						return changdiNewMoney;
					}
					/**
					* 设置： 场地现价
					*/
					public void setChangdiNewMoney(Double changdiNewMoney) {
						this.changdiNewMoney = changdiNewMoney;
					}
					/**
					* 获取： 时间段
					*/
					public String getShijianduan() {
						return shijianduan;
					}
					/**
					* 设置： 时间段
					*/
					public void setShijianduan(String shijianduan) {
						this.shijianduan = shijianduan;
					}
					/**
					* 获取： 人数
					*/
					public Integer getShijianduanRen() {
						return shijianduanRen;
					}
					/**
					* 设置： 人数
					*/
					public void setShijianduanRen(Integer shijianduanRen) {
						this.shijianduanRen = shijianduanRen;
					}
					/**
					* 获取： 点击次数
					*/
					public Integer getChangdiClicknum() {
						return changdiClicknum;
					}
					/**
					* 设置： 点击次数
					*/
					public void setChangdiClicknum(Integer changdiClicknum) {
						this.changdiClicknum = changdiClicknum;
					}
					/**
					* 获取： 半全场
					*/
					public Integer getBanquanTypes() {
						return banquanTypes;
					}
					/**
					* 设置： 半全场
					*/
					public void setBanquanTypes(Integer banquanTypes) {
						this.banquanTypes = banquanTypes;
					}


						/**
						* 获取： 半全场的值
						*/
						public String getBanquanValue() {
							return banquanValue;
						}
						/**
						* 设置： 半全场的值
						*/
						public void setBanquanValue(String banquanValue) {
							this.banquanValue = banquanValue;
						}
					/**
					* 获取： 是否上架
					*/
					public Integer getShangxiaTypes() {
						return shangxiaTypes;
					}
					/**
					* 设置： 是否上架
					*/
					public void setShangxiaTypes(Integer shangxiaTypes) {
						this.shangxiaTypes = shangxiaTypes;
					}


						/**
						* 获取： 是否上架的值
						*/
						public String getShangxiaValue() {
							return shangxiaValue;
						}
						/**
						* 设置： 是否上架的值
						*/
						public void setShangxiaValue(String shangxiaValue) {
							this.shangxiaValue = shangxiaValue;
						}
					/**
					* 获取： 推荐吃饭地点
					*/
					public String getTuijian() {
						return tuijian;
					}
					/**
					* 设置： 推荐吃饭地点
					*/
					public void setTuijian(String tuijian) {
						this.tuijian = tuijian;
					}
					/**
					* 获取： 逻辑删除
					*/
					public Integer getChangdiDelete() {
						return changdiDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setChangdiDelete(Integer changdiDelete) {
						this.changdiDelete = changdiDelete;
					}
					/**
					* 获取： 场地简介
					*/
					public String getChangdiContent() {
						return changdiContent;
					}
					/**
					* 设置： 场地简介
					*/
					public void setChangdiContent(String changdiContent) {
						this.changdiContent = changdiContent;
					}



















				//级联表的get和set yonghu
					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}
					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}
					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}



}
