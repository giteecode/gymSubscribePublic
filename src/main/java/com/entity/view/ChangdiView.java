package com.entity.view;

import com.entity.ChangdiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 场地
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("changdi")
public class ChangdiView extends ChangdiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 场地类型的值
		*/
		private String changdiValue;
		/**
		* 半全场的值
		*/
		private String banquanValue;
		/**
		* 是否上架的值
		*/
		private String shangxiaValue;



	public ChangdiView() {

	}

	public ChangdiView(ChangdiEntity changdiEntity) {
		try {
			BeanUtils.copyProperties(this, changdiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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










}
