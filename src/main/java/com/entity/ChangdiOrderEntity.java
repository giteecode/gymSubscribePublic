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
 * 场地预约
 *
 * @author 
 * @email
 */
@TableName("changdi_order")
public class ChangdiOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ChangdiOrderEntity() {

	}

	public ChangdiOrderEntity(T t) {
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
     * 订单号
     */
    @TableField(value = "changdi_order_uuid_number")

    private String changdiOrderUuidNumber;


    /**
     * 场地
     */
    @TableField(value = "changdi_id")

    private Integer changdiId;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 实付价格
     */
    @TableField(value = "changdi_order_true_price")

    private Double changdiOrderTruePrice;


    /**
     * 订单类型
     */
    @TableField(value = "changdi_order_types")

    private Integer changdiOrderTypes;


    /**
     * 预约时间段
     */
    @TableField(value = "shijianduan")

    private String shijianduan;


    /**
     * 预约日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @TableField(value = "buy_time")

    private Date buyTime;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
	 * 设置：订单号
	 */
    public String getChangdiOrderUuidNumber() {
        return changdiOrderUuidNumber;
    }


    /**
	 * 获取：订单号
	 */

    public void setChangdiOrderUuidNumber(String changdiOrderUuidNumber) {
        this.changdiOrderUuidNumber = changdiOrderUuidNumber;
    }
    /**
	 * 设置：场地
	 */
    public Integer getChangdiId() {
        return changdiId;
    }


    /**
	 * 获取：场地
	 */

    public void setChangdiId(Integer changdiId) {
        this.changdiId = changdiId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getChangdiOrderTruePrice() {
        return changdiOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setChangdiOrderTruePrice(Double changdiOrderTruePrice) {
        this.changdiOrderTruePrice = changdiOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getChangdiOrderTypes() {
        return changdiOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setChangdiOrderTypes(Integer changdiOrderTypes) {
        this.changdiOrderTypes = changdiOrderTypes;
    }
    /**
	 * 设置：预约时间段
	 */
    public String getShijianduan() {
        return shijianduan;
    }


    /**
	 * 获取：预约时间段
	 */

    public void setShijianduan(String shijianduan) {
        this.shijianduan = shijianduan;
    }
    /**
	 * 设置：预约日期
	 */
    public Date getBuyTime() {
        return buyTime;
    }


    /**
	 * 获取：预约日期
	 */

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
        return "ChangdiOrder{" +
            "id=" + id +
            ", changdiOrderUuidNumber=" + changdiOrderUuidNumber +
            ", changdiId=" + changdiId +
            ", yonghuId=" + yonghuId +
            ", changdiOrderTruePrice=" + changdiOrderTruePrice +
            ", changdiOrderTypes=" + changdiOrderTypes +
            ", shijianduan=" + shijianduan +
            ", buyTime=" + buyTime +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
