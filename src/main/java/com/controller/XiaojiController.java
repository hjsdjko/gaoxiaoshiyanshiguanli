package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.StringUtil;
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

import com.entity.XiaojiEntity;

import com.service.XiaojiService;
import com.entity.view.XiaojiView;
import com.service.YonghuService;
import com.service.YuanjiService;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 校级人员
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xiaoji")
public class XiaojiController {
    private static final Logger logger = LoggerFactory.getLogger(XiaojiController.class);

    @Autowired
    private XiaojiService xiaojiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private YuanjiService yuanjiService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role)){
            return R.error(511,"权限为空");
        }
        else if("实验室人员".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        else if("校级人员".equals(role)){
            params.put("xiaojiId",request.getSession().getAttribute("userId"));
        }
        else if("院级人员".equals(role)){
            params.put("yuanjiId",request.getSession().getAttribute("userId"));
        }
        params.put("orderBy","id");
        PageUtils page = xiaojiService.queryPage(params);

        //字典表数据转换
        List<XiaojiView> list =(List<XiaojiView>)page.getList();
        for(XiaojiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        XiaojiEntity xiaoji = xiaojiService.selectById(id);
        if(xiaoji !=null){
            //entity转view
            XiaojiView view = new XiaojiView();
            BeanUtils.copyProperties( xiaoji , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody XiaojiEntity xiaoji, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xiaoji:{}",this.getClass().getName(),xiaoji.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role)){
            return R.error(511,"权限为空");
        }
        Wrapper<XiaojiEntity> queryWrapper = new EntityWrapper<XiaojiEntity>()
            .eq("username", xiaoji.getUsername())
            .or()
            .eq("xiaoji_phone", xiaoji.getXiaojiPhone())
            .or()
            .eq("xiaoji_id_number", xiaoji.getXiaojiIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XiaojiEntity xiaojiEntity = xiaojiService.selectOne(queryWrapper);
        if(xiaojiEntity==null){
            xiaoji.setCreateTime(new Date());
            xiaoji.setPassword("123456");
            xiaojiService.insert(xiaoji);
            return R.ok();
        }else {
            return R.error(511,"账户或者身份证号或者手机号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XiaojiEntity xiaoji, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,xiaoji:{}",this.getClass().getName(),xiaoji.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role)){
            return R.error(511,"权限为空");
        }
        //根据字段查询是否有相同数据
        Wrapper<XiaojiEntity> queryWrapper = new EntityWrapper<XiaojiEntity>()
            .notIn("id",xiaoji.getId())
            .andNew()
            .eq("username", xiaoji.getUsername())
            .or()
            .eq("xiaoji_phone", xiaoji.getXiaojiPhone())
            .or()
            .eq("xiaoji_id_number", xiaoji.getXiaojiIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XiaojiEntity xiaojiEntity = xiaojiService.selectOne(queryWrapper);
        if("".equals(xiaoji.getXiaojiPhoto()) || "null".equals(xiaoji.getXiaojiPhoto())){
                xiaoji.setXiaojiPhoto(null);
        }
        if(xiaojiEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      xiaoji.set
            //  }
            xiaojiService.updateById(xiaoji);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者身份证号或者手机号已经被使用");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        xiaojiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        XiaojiEntity xiaoji = xiaojiService.selectOne(new EntityWrapper<XiaojiEntity>().eq("username", username));
        if(xiaoji==null || !xiaoji.getPassword().equals(password)) {
            return R.error("账号或密码不正确");
        }
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(yonghu.getRoleTypes());
        String token = tokenService.generateToken(xiaoji.getId(),username, "xiaoji", "校级人员");
        R r = R.ok();
        r.put("token", token);
        r.put("role","校级人员");
        r.put("username",xiaoji.getXiaojiName());
        r.put("tableName","xiaoji");
        r.put("userId",xiaoji.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody XiaojiEntity xiaoji){
    //    	ValidatorUtils.validateEntity(user);
        if(xiaojiService.selectOne(new EntityWrapper<XiaojiEntity>().eq("username", xiaoji.getUsername()).orNew().eq("xiaoji_phone",xiaoji.getXiaojiPhone()).orNew().eq("xiaoji_id_number",xiaoji.getXiaojiIdNumber())) !=null) {
            return R.error("账户已存在或手机号或身份证号已经被使用");
        }
        xiaoji.setCreateTime(new Date());
        xiaojiService.insert(xiaoji);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        XiaojiEntity xiaoji = new XiaojiEntity();
        xiaoji.setPassword("123456");
        xiaoji.setId(id);
        xiaojiService.updateById(xiaoji);
        return R.ok();
    }

    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrXiaoji(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        XiaojiEntity xiaoji = xiaojiService.selectById(id);
        return R.ok().put("data", xiaoji);
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



    /**
    * 前端列表
    */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role)){
            return R.error(511,"权限为空");
        }
        else if("实验室人员".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        else if("校级人员".equals(role)){
            params.put("xiaojiId",request.getSession().getAttribute("userId"));
        }
        else if("院级人员".equals(role)){
            params.put("yuanjiId",request.getSession().getAttribute("userId"));
        }
        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = xiaojiService.queryPage(params);

        //字典表数据转换
        List<XiaojiView> list =(List<XiaojiView>)page.getList();
        for(XiaojiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        XiaojiEntity xiaoji = xiaojiService.selectById(id);
            if(xiaoji !=null){
                //entity转view
                XiaojiView view = new XiaojiView();
                BeanUtils.copyProperties( xiaoji , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody XiaojiEntity xiaoji, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,xiaoji:{}",this.getClass().getName(),xiaoji.toString());
        Wrapper<XiaojiEntity> queryWrapper = new EntityWrapper<XiaojiEntity>()
            .eq("username", xiaoji.getUsername())
            .or()
            .eq("xiaoji_phone", xiaoji.getXiaojiPhone())
            .or()
            .eq("xiaoji_id_number", xiaoji.getXiaojiIdNumber());
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XiaojiEntity xiaojiEntity = xiaojiService.selectOne(queryWrapper);
        if(xiaojiEntity==null){
            xiaoji.setCreateTime(new Date());
        xiaoji.setPassword("123456");
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      xiaoji.set
        //  }
        xiaojiService.insert(xiaoji);
            return R.ok();
        }else {
            return R.error(511,"账户或者身份证号或者手机号已经被使用");
        }
    }





}

