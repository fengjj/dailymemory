package cn.dailymemory.service.system;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.system.Tag;

public interface ITagService {
    ResultModel save(Tag tag);

    ResultModel listByPage(Page page, int funcType);

    ResultModel update(Tag tag);

    ResultModel delete(Integer id);

    Tag findById(Integer id);

}
