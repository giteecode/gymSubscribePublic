








package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 场地
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/changdi")
public class ChangdiController {
    private static final Logger logger = LoggerFactory.getLogger(ChangdiController.class);

    @Autowired
    private ChangdiService changdiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("changdiDeleteStart",1);params.put("changdiDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = changdiService.queryPage(params);

        //字典表数据转换
        List<ChangdiView> list =(List<ChangdiView>)page.getList();
        for(ChangdiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ChangdiEntity changdi = changdiService.selectById(id);
        if(changdi !=null){
            //entity转view
            ChangdiView view = new ChangdiView();
            BeanUtils.copyProperties( changdi , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ChangdiEntity changdi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,changdi:{}",this.getClass().getName(),changdi.toString());

        changdi.setShijianduanRen(1);
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");

        Wrapper<ChangdiEntity> queryWrapper = new EntityWrapper<ChangdiEntity>()
            .eq("changdi_uuid_number", changdi.getChangdiUuidNumber())
            .eq("changdi_name", changdi.getChangdiName())
            .eq("changdi_types", changdi.getChangdiTypes())
            .eq("shijianduan", changdi.getShijianduan())
            .eq("shijianduan_ren", changdi.getShijianduanRen())
            .eq("changdi_clicknum", changdi.getChangdiClicknum())
            .eq("banquan_types", changdi.getBanquanTypes())
            .eq("shangxia_types", changdi.getShangxiaTypes())
            .eq("tuijian", changdi.getTuijian())
            .eq("changdi_delete", changdi.getChangdiDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ChangdiEntity changdiEntity = changdiService.selectOne(queryWrapper);
        if(changdiEntity==null){
            changdi.setChangdiClicknum(1);
            changdi.setShangxiaTypes(1);
            changdi.setChangdiDelete(1);
            changdi.setCreateTime(new Date());
            changdiService.insert(changdi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ChangdiEntity changdi, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,changdi:{}",this.getClass().getName(),changdi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isEmpty(role))
//            return R.error(511,"权限为空");
        //根据字段查询是否有相同数据
        Wrapper<ChangdiEntity> queryWrapper = new EntityWrapper<ChangdiEntity>()
            .notIn("id",changdi.getId())
            .andNew()
            .eq("changdi_uuid_number", changdi.getChangdiUuidNumber())
            .eq("changdi_name", changdi.getChangdiName())
            .eq("changdi_types", changdi.getChangdiTypes())
            .eq("shijianduan", changdi.getShijianduan())
            .eq("shijianduan_ren", changdi.getShijianduanRen())
            .eq("changdi_clicknum", changdi.getChangdiClicknum())
            .eq("banquan_types", changdi.getBanquanTypes())
            .eq("shangxia_types", changdi.getShangxiaTypes())
            .eq("tuijian", changdi.getTuijian())
            .eq("changdi_delete", changdi.getChangdiDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ChangdiEntity changdiEntity = changdiService.selectOne(queryWrapper);
        if("".equals(changdi.getChangdiPhoto()) || "null".equals(changdi.getChangdiPhoto())){
                changdi.setChangdiPhoto(null);
        }
        if(changdiEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      changdi.set
            //  }
            changdiService.updateById(changdi);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<ChangdiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            ChangdiEntity changdiEntity = new ChangdiEntity();
            changdiEntity.setId(id);
            changdiEntity.setChangdiDelete(2);
            list.add(changdiEntity);
        }
        if(list != null && list.size() >0){
            changdiService.updateBatchById(list);
        }
        return R.ok();
    }

    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<ChangdiEntity> changdiList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            ChangdiEntity changdiEntity = new ChangdiEntity();
//                            changdiEntity.setChangdiUuidNumber(data.get(0));                    //场地编号 要改的
//                            changdiEntity.setChangdiName(data.get(0));                    //场地名称 要改的
//                            changdiEntity.setChangdiPhoto("");//照片
//                            changdiEntity.setChangdiTypes(Integer.valueOf(data.get(0)));   //场地类型 要改的
//                            changdiEntity.setChangdiOldMoney(data.get(0));                    //场地原价 要改的
//                            changdiEntity.setChangdiNewMoney(data.get(0));                    //场地现价 要改的
//                            changdiEntity.setShijianduan(data.get(0));                    //时间段 要改的
//                            changdiEntity.setShijianduanRen(Integer.valueOf(data.get(0)));   //人数 要改的
//                            changdiEntity.setChangdiClicknum(Integer.valueOf(data.get(0)));   //点击次数 要改的
//                            changdiEntity.setBanquanTypes(Integer.valueOf(data.get(0)));   //半全场 要改的
//                            changdiEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            changdiEntity.setTuijian(data.get(0));                    //推荐吃饭地点 要改的
//                            changdiEntity.setChangdiDelete(1);//逻辑删除字段
//                            changdiEntity.setChangdiContent("");//照片
//                            changdiEntity.setCreateTime(date);//时间
                            changdiList.add(changdiEntity);


                            //把要查询是否重复的字段放入map中
                                //场地编号
                                if(seachFields.containsKey("changdiUuidNumber")){
                                    List<String> changdiUuidNumber = seachFields.get("changdiUuidNumber");
                                    changdiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> changdiUuidNumber = new ArrayList<>();
                                    changdiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("changdiUuidNumber",changdiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //场地编号
                        List<ChangdiEntity> changdiEntities_changdiUuidNumber = changdiService.selectList(new EntityWrapper<ChangdiEntity>().in("changdi_uuid_number", seachFields.get("changdiUuidNumber")).eq("changdi_delete", 1));
                        if(changdiEntities_changdiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ChangdiEntity s:changdiEntities_changdiUuidNumber){
                                repeatFields.add(s.getChangdiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [场地编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        changdiService.insertBatch(changdiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = changdiService.queryPage(params);

        //字典表数据转换
        List<ChangdiView> list =(List<ChangdiView>)page.getList();
        for(ChangdiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ChangdiEntity changdi = changdiService.selectById(id);
            if(changdi !=null){

                //点击数量加1
                changdi.setChangdiClicknum(changdi.getChangdiClicknum()+1);
                changdiService.updateById(changdi);

                //entity转view
                ChangdiView view = new ChangdiView();
                BeanUtils.copyProperties( changdi , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody ChangdiEntity changdi, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,changdi:{}",this.getClass().getName(),changdi.toString());
        Wrapper<ChangdiEntity> queryWrapper = new EntityWrapper<ChangdiEntity>()
            .eq("changdi_uuid_number", changdi.getChangdiUuidNumber())
            .eq("changdi_name", changdi.getChangdiName())
            .eq("changdi_types", changdi.getChangdiTypes())
            .eq("shijianduan", changdi.getShijianduan())
            .eq("shijianduan_ren", changdi.getShijianduanRen())
            .eq("changdi_clicknum", changdi.getChangdiClicknum())
            .eq("banquan_types", changdi.getBanquanTypes())
            .eq("shangxia_types", changdi.getShangxiaTypes())
            .eq("tuijian", changdi.getTuijian())
            .eq("changdi_delete", changdi.getChangdiDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ChangdiEntity changdiEntity = changdiService.selectOne(queryWrapper);
        if(changdiEntity==null){
            changdi.setChangdiDelete(1);
            changdi.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      changdi.set
        //  }
        changdiService.insert(changdi);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
