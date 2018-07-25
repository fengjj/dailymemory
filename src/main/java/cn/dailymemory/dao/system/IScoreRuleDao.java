package cn.dailymemory.dao.system;

import cn.dailymemory.dao.common.IBaseDao;
import cn.dailymemory.model.system.ScoreRule;
import org.apache.ibatis.annotations.Param;

/**
 * Created by dm on 2017/3/24.
 */
public interface IScoreRuleDao extends IBaseDao<ScoreRule> {

    int enabled(@Param("id") int id);
}
