package com.dao;

import com.entity.YuanjiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YuanjiView;

/**
 * 院级人员 Dao 接口
 *
 * @author 
 */
public interface YuanjiDao extends BaseMapper<YuanjiEntity> {

   List<YuanjiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
