package cn.dailymemory.service.system;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.model.system.ScoreRule;

import java.util.List;

/**
 * Created by dm on 2017/2/14.
 */
public interface IScoreRuleService {

    List<ScoreRule> list();

    ScoreRule findById(Integer id);

    ResultModel update(ScoreRule scoreRule);

    ResultModel enabled(int id);

}
