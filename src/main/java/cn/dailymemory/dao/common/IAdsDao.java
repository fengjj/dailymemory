package cn.dailymemory.dao.common;

import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.common.Ads;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dm on 2017/9/07.
 */
public interface IAdsDao extends IBaseDao<Ads>{

    /**
     * 分页查询广告信息
     * @param page
     * @return
     */
    List<Ads> listByPage(@Param("page") Page page);

    int enable(@Param("id") Integer id);
}
