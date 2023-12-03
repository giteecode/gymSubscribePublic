package com.entity.vo;

import com.entity.ChangdiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 场地
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("changdi")
public class ChangdiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 场地编号
     */

    @TableField(value = "changdi_uuid_number")
    private String changdiUuidNumber;


    /**
     * 场地名称
     */

    @TableField(value = "changdi_name")
    private String changdiName;


    /**
     * 场地照片
     */

    @TableField(value = "changdi_photo")
    private String changdiPhoto;


    /**
     * 场地类型
     */

    @TableField(value = "changdi_types")
    private Integer changdiTypes;


    /**
     * 场地原价
     */

    @TableField(value = "changdi_old_money")
    private Double changdiOldMoney;


    /**
     * 场地现价
     */

    @TableField(value = "changdi_new_money")
    private Double changdiNewMoney;


    /**
     * 时间段
     */

    @TableField(value = "shijianduan")
    private String shijianduan;


    /**
     * 人数
     */

    @TableField(value = "shijianduan_ren")
    private Integer shijianduanRen;


    /**
     * 点击次数
     */

    @TableField(value = "changdi_clicknum")
    private Integer changdiClicknum;


    /**
     * 半全场
     */

    @TableField(value = "banquan_types")
    private Integer banquanTypes;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 推荐吃饭地点
     */

    @TableField(value = "tuijian")
    private String tuijian;


    /**
     * 逻辑删除
     */

    @TableField(value = "changdi_delete")
    private Integer changdiDelete;


    /**
     * 场地简介
     */

    @TableField(value = "changdi_content")
    private String changdiContent;


    /**
     * 创建时间 show1 show2 photoShow
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
	 * 设置：场地编号
	 */
    public String getChangdiUuidNumber() {
        return changdiUuidNumber;
    }


    /**
	 * 获取：场地编号
	 */

    public void setChangdiUuidNumber(String changdiUuidNumber) {
        this.changdiUuidNumber = changdiUuidNumber;
    }
    /**
	 * 设置：场地名称
	 */
    public String getChangdiName() {
        return changdiName;
    }


    /**
	 * 获取：场地名称
	 */

    public void setChangdiName(String changdiName) {
        this.changdiName = changdiName;
    }
    /**
	 * 设置：场地照片
	 */
    public String getChangdiPhoto() {
        return changdiPhoto;
    }


    /**
	 * 获取：场地照片
	 */

    public void setChangdiPhoto(String changdiPhoto) {
        this.changdiPhoto = changdiPhoto;
    }
    /**
	 * 设置：场地类型
	 */
    public Integer getChangdiTypes() {
        return changdiTypes;
    }


    /**
	 * 获取：场地类型
	 */

    public void setChangdiTypes(Integer changdiTypes) {
        this.changdiTypes = changdiTypes;
    }
    /**
	 * 设置：场地原价
	 */
    public Double getChangdiOldMoney() {
        return changdiOldMoney;
    }


    /**
	 * 获取：场地原价
	 */

    public void setChangdiOldMoney(Double changdiOldMoney) {
        this.changdiOldMoney = changdiOldMoney;
    }
    /**
	 * 设置：场地现价
	 */
    public Double getChangdiNewMoney() {
        return changdiNewMoney;
    }


    /**
	 * 获取：场地现价
	 */

    public void setChangdiNewMoney(Double changdiNewMoney) {
        this.changdiNewMoney = changdiNewMoney;
    }
    /**
	 * 设置：时间段
	 */
    public String getShijianduan() {
        return shijianduan;
    }


    /**
	 * 获取：时间段
	 */

    public void setShijianduan(String shijianduan) {
        this.shijianduan = shijianduan;
    }
    /**
	 * 设置：人数
	 */
    public Integer getShijianduanRen() {
        return shijianduanRen;
    }


    /**
	 * 获取：人数
	 */

    public void setShijianduanRen(Integer shijianduanRen) {
        this.shijianduanRen = shijianduanRen;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getChangdiClicknum() {
        return changdiClicknum;
    }


    /**
	 * 获取：点击次数
	 */

    public void setChangdiClicknum(Integer changdiClicknum) {
        this.changdiClicknum = changdiClicknum;
    }
    /**
	 * 设置：半全场
	 */
    public Integer getBanquanTypes() {
        return banquanTypes;
    }


    /**
	 * 获取：半全场
	 */

    public void setBanquanTypes(Integer banquanTypes) {
        this.banquanTypes = banquanTypes;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：推荐吃饭地点
	 */
    public String getTuijian() {
        return tuijian;
    }


    /**
	 * 获取：推荐吃饭地点
	 */

    public void setTuijian(String tuijian) {
        this.tuijian = tuijian;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getChangdiDelete() {
        return changdiDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setChangdiDelete(Integer changdiDelete) {
        this.changdiDelete = changdiDelete;
    }
    /**
	 * 设置：场地简介
	 */
    public String getChangdiContent() {
        return changdiContent;
    }


    /**
	 * 获取：场地简介
	 */

    public void setChangdiContent(String changdiContent) {
        this.changdiContent = changdiContent;
    }
    /**
	 * 设置：创建时间 show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
