package cn.dailymemory.service.common;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.common.Link;

/**
 * Created by dm on 2017-10-13.
 */
public interface ILinkService {
   
    ResultModel save(Link link);
   
    ResultModel listByPage(Page page);

    ResultModel allList();

    ResultModel recommentList();

    ResultModel update(Link link);

    ResultModel delete(Integer id);

    Link findById(Integer id);

    ResultModel enable(Integer id);
}
