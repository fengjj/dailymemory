package cn.dailymemory.dao.system;

import cn.dailymemory.dao.common.IBaseDao;
import cn.dailymemory.model.system.Action;
import org.apache.ibatis.annotations.Param;

/**
 * Created by dm on 2017/2/14.
 */
public interface IActionDao extends IBaseDao<Action> {
    int isenable(@Param("id") Integer id);
}
