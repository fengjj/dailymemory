package cn.dailymemory.service.common;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.common.Ads;

/**
 * Created by dm on 2017/9/07.
 */
public interface IAdsService {
    /**
     * 保存广告信息
     * @param ads
     * @return
     */
    boolean save(Ads ads);
    /**
     * 分页查询广告信息
     * @param page
     * @return
     */
    ResultModel listByPage(Page page);

    boolean update(Ads ads);

    boolean delete(Integer id);

    Ads findById(Integer id);

    boolean enable(Integer id);
}
