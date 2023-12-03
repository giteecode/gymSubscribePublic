package com.entity.model;

import com.entity.ChangdiOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 场地预约
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ChangdiOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String changdiOrderUuidNumber;


    /**
     * 场地
     */
    private Integer changdiId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 实付价格
     */
    private Double changdiOrderTruePrice;


    /**
     * 订单类型
     */
    private Integer changdiOrderTypes;


    /**
     * 预约时间段
     */
    private String shijianduan;


    /**
     * 预约日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date buyTime;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单号
	 */
    public String getChangdiOrderUuidNumber() {
        return changdiOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setChangdiOrderUuidNumber(String changdiOrderUuidNumber) {
        this.changdiOrderUuidNumber = changdiOrderUuidNumber;
    }
    /**
	 * 获取：场地
	 */
    public Integer getChangdiId() {
        return changdiId;
    }


    /**
	 * 设置：场地
	 */
    public void setChangdiId(Integer changdiId) {
        this.changdiId = changdiId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getChangdiOrderTruePrice() {
        return changdiOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setChangdiOrderTruePrice(Double changdiOrderTruePrice) {
        this.changdiOrderTruePrice = changdiOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getChangdiOrderTypes() {
        return changdiOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setChangdiOrderTypes(Integer changdiOrderTypes) {
        this.changdiOrderTypes = changdiOrderTypes;
    }
    /**
	 * 获取：预约时间段
	 */
    public String getShijianduan() {
        return shijianduan;
    }


    /**
	 * 设置：预约时间段
	 */
    public void setShijianduan(String shijianduan) {
        this.shijianduan = shijianduan;
    }
    /**
	 * 获取：预约日期
	 */
    public Date getBuyTime() {
        return buyTime;
    }


    /**
	 * 设置：预约日期
	 */
    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
