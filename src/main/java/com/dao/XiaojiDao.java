package com.dao;

import com.entity.XiaojiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.XiaojiView;

/**
 * 校级人员 Dao 接口
 *
 * @author 
 */
public interface XiaojiDao extends BaseMapper<XiaojiEntity> {

   List<XiaojiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
