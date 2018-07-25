package cn.dailymemory.dao.system;

import cn.dailymemory.dao.common.IBaseDao;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.system.ActionLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dm on 2017/2/14.
 */
public interface IActionLogDao extends IBaseDao<ActionLog> {

    List<ActionLog> listByPage(@Param("page") Page page, @Param("memberId") Integer memberId);

    List<ActionLog> memberActionLog(@Param("page") Page page, @Param("memberId") Integer memberId);
}
