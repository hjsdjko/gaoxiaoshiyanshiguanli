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

import com.entity.YuanjiEntity;

import com.service.YuanjiService;
import com.entity.view.YuanjiView;
import com.service.YonghuService;
import com.service.XiaojiService;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 院级人员
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yuanji")
public class YuanjiController {
    private static final Logger logger = LoggerFactory.getLogger(YuanjiController.class);

    @Autowired
    private YuanjiService yuanjiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;



    //级联表service
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private XiaojiService xiaojiService;


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
        PageUtils page = yuanjiService.queryPage(params);

        //字典表数据转换
        List<YuanjiView> list =(List<YuanjiView>)page.getList();
        for(YuanjiView c:list){
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
        YuanjiEntity yuanji = yuanjiService.selectById(id);
        if(yuanji !=null){
            //entity转view
            YuanjiView view = new YuanjiView();
            BeanUtils.copyProperties( yuanji , view );//把实体数据重构到view中

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
    public R save(@RequestBody YuanjiEntity yuanji, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yuanji:{}",this.getClass().getName(),yuanji.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role)){
            return R.error(511,"权限为空");
        }
        Wrapper<YuanjiEntity> queryWrapper = new EntityWrapper<YuanjiEntity>()
            .eq("username", yuanji.getUsername())
            .or()
            .eq("yuanji_phone", yuanji.getYuanjiPhone())
            .or()
            .eq("yuanji_id_number", yuanji.getYuanjiIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YuanjiEntity yuanjiEntity = yuanjiService.selectOne(queryWrapper);
        if(yuanjiEntity==null){
            yuanji.setCreateTime(new Date());
            yuanji.setPassword("123456");
            yuanjiService.insert(yuanji);
            return R.ok();
        }else {
            return R.error(511,"账户或者身份证号或者手机号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YuanjiEntity yuanji, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yuanji:{}",this.getClass().getName(),yuanji.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role)){
            return R.error(511,"权限为空");
        }
        //根据字段查询是否有相同数据
        Wrapper<YuanjiEntity> queryWrapper = new EntityWrapper<YuanjiEntity>()
            .notIn("id",yuanji.getId())
            .andNew()
            .eq("username", yuanji.getUsername())
            .or()
            .eq("yuanji_phone", yuanji.getYuanjiPhone())
            .or()
            .eq("yuanji_id_number", yuanji.getYuanjiIdNumber())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YuanjiEntity yuanjiEntity = yuanjiService.selectOne(queryWrapper);
        if("".equals(yuanji.getYuanjiPhoto()) || "null".equals(yuanji.getYuanjiPhoto())){
                yuanji.setYuanjiPhoto(null);
        }
        if(yuanjiEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      yuanji.set
            //  }
            yuanjiService.updateById(yuanji);//根据id更新
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
        yuanjiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        YuanjiEntity yuanji = yuanjiService.selectOne(new EntityWrapper<YuanjiEntity>().eq("username", username));
        if(yuanji==null || !yuanji.getPassword().equals(password)) {
            return R.error("账号或密码不正确");
        }
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(yonghu.getRoleTypes());
        String token = tokenService.generateToken(yuanji.getId(),username, "yuanji", "院级人员");
        R r = R.ok();
        r.put("token", token);
        r.put("role","院级人员");
        r.put("username",yuanji.getYuanjiName());
        r.put("tableName","yuanji");
        r.put("userId",yuanji.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody YuanjiEntity yuanji){
    //    	ValidatorUtils.validateEntity(user);
        if(yuanjiService.selectOne(new EntityWrapper<YuanjiEntity>().eq("username", yuanji.getUsername()).orNew().eq("yuanji_phone",yuanji.getYuanjiPhone()).orNew().eq("yuanji_id_number",yuanji.getYuanjiIdNumber())) !=null) {
            return R.error("账户已存在或手机号或身份证号已经被使用");
        }
        yuanji.setCreateTime(new Date());
        yuanjiService.insert(yuanji);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        YuanjiEntity yuanji = new YuanjiEntity();
        yuanji.setPassword("123456");
        yuanji.setId(id);
        yuanjiService.updateById(yuanji);
        return R.ok();
    }

    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrYuanji(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        YuanjiEntity yuanji = yuanjiService.selectById(id);
        return R.ok().put("data", yuanji);
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
        PageUtils page = yuanjiService.queryPage(params);

        //字典表数据转换
        List<YuanjiView> list =(List<YuanjiView>)page.getList();
        for(YuanjiView c:list){
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
        YuanjiEntity yuanji = yuanjiService.selectById(id);
            if(yuanji !=null){
                //entity转view
                YuanjiView view = new YuanjiView();
                BeanUtils.copyProperties( yuanji , view );//把实体数据重构到view中

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
    public R add(@RequestBody YuanjiEntity yuanji, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yuanji:{}",this.getClass().getName(),yuanji.toString());
        Wrapper<YuanjiEntity> queryWrapper = new EntityWrapper<YuanjiEntity>()
            .eq("username", yuanji.getUsername())
            .or()
            .eq("yuanji_phone", yuanji.getYuanjiPhone())
            .or()
            .eq("yuanji_id_number", yuanji.getYuanjiIdNumber());
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YuanjiEntity yuanjiEntity = yuanjiService.selectOne(queryWrapper);
        if(yuanjiEntity==null){
            yuanji.setCreateTime(new Date());
        yuanji.setPassword("123456");
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      yuanji.set
        //  }
        yuanjiService.insert(yuanji);
            return R.ok();
        }else {
            return R.error(511,"账户或者身份证号或者手机号已经被使用");
        }
    }





}

