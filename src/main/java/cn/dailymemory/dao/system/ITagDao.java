package cn.dailymemory.dao.system;

import cn.dailymemory.core.model.Page;
import cn.dailymemory.dao.common.IBaseDao;
import cn.dailymemory.model.system.Tag;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITagDao extends IBaseDao<Tag> {

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    List<Tag> listByPage(@Param("page") Page page, @Param("funcType") int funcType);

}
