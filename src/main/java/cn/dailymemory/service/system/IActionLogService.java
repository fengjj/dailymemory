package cn.dailymemory.service.system;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.system.ActionLog;

/**
 * Created by dm on 2017/2/14.
 */
public interface IActionLogService {

    ResultModel<ActionLog> listByPage(Page page, Integer memberId);

    ResultModel<ActionLog> memberActionLog(Page page, Integer memberId);

    ActionLog findById(Integer id);

    void save(String actionIp,Integer memberId, Integer actionId);

    void save(String actionIp,Integer memberId, Integer actionId,String remark);

    void save(String actionIp,Integer memberId, Integer actionId,String remark, Integer type, Integer foreignId);

}
