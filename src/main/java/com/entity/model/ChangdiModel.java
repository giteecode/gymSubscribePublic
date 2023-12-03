package com.entity.model;

import com.entity.ChangdiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 场地
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ChangdiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


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
     * 是否上架
     */
    private Integer shangxiaTypes;


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


    /**
     * 创建时间 show1 show2 photoShow
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
	 * 获取：场地编号
	 */
    public String getChangdiUuidNumber() {
        return changdiUuidNumber;
    }


    /**
	 * 设置：场地编号
	 */
    public void setChangdiUuidNumber(String changdiUuidNumber) {
        this.changdiUuidNumber = changdiUuidNumber;
    }
    /**
	 * 获取：场地名称
	 */
    public String getChangdiName() {
        return changdiName;
    }


    /**
	 * 设置：场地名称
	 */
    public void setChangdiName(String changdiName) {
        this.changdiName = changdiName;
    }
    /**
	 * 获取：场地照片
	 */
    public String getChangdiPhoto() {
        return changdiPhoto;
    }


    /**
	 * 设置：场地照片
	 */
    public void setChangdiPhoto(String changdiPhoto) {
        this.changdiPhoto = changdiPhoto;
    }
    /**
	 * 获取：场地类型
	 */
    public Integer getChangdiTypes() {
        return changdiTypes;
    }


    /**
	 * 设置：场地类型
	 */
    public void setChangdiTypes(Integer changdiTypes) {
        this.changdiTypes = changdiTypes;
    }
    /**
	 * 获取：场地原价
	 */
    public Double getChangdiOldMoney() {
        return changdiOldMoney;
    }


    /**
	 * 设置：场地原价
	 */
    public void setChangdiOldMoney(Double changdiOldMoney) {
        this.changdiOldMoney = changdiOldMoney;
    }
    /**
	 * 获取：场地现价
	 */
    public Double getChangdiNewMoney() {
        return changdiNewMoney;
    }


    /**
	 * 设置：场地现价
	 */
    public void setChangdiNewMoney(Double changdiNewMoney) {
        this.changdiNewMoney = changdiNewMoney;
    }
    /**
	 * 获取：时间段
	 */
    public String getShijianduan() {
        return shijianduan;
    }


    /**
	 * 设置：时间段
	 */
    public void setShijianduan(String shijianduan) {
        this.shijianduan = shijianduan;
    }
    /**
	 * 获取：人数
	 */
    public Integer getShijianduanRen() {
        return shijianduanRen;
    }


    /**
	 * 设置：人数
	 */
    public void setShijianduanRen(Integer shijianduanRen) {
        this.shijianduanRen = shijianduanRen;
    }
    /**
	 * 获取：点击次数
	 */
    public Integer getChangdiClicknum() {
        return changdiClicknum;
    }


    /**
	 * 设置：点击次数
	 */
    public void setChangdiClicknum(Integer changdiClicknum) {
        this.changdiClicknum = changdiClicknum;
    }
    /**
	 * 获取：半全场
	 */
    public Integer getBanquanTypes() {
        return banquanTypes;
    }


    /**
	 * 设置：半全场
	 */
    public void setBanquanTypes(Integer banquanTypes) {
        this.banquanTypes = banquanTypes;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 设置：是否上架
	 */
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：推荐吃饭地点
	 */
    public String getTuijian() {
        return tuijian;
    }


    /**
	 * 设置：推荐吃饭地点
	 */
    public void setTuijian(String tuijian) {
        this.tuijian = tuijian;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getChangdiDelete() {
        return changdiDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setChangdiDelete(Integer changdiDelete) {
        this.changdiDelete = changdiDelete;
    }
    /**
	 * 获取：场地简介
	 */
    public String getChangdiContent() {
        return changdiContent;
    }


    /**
	 * 设置：场地简介
	 */
    public void setChangdiContent(String changdiContent) {
        this.changdiContent = changdiContent;
    }
    /**
	 * 获取：创建时间 show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
