package cn.dailymemory.service.system.impl;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.dao.system.IScoreRuleDao;
import cn.dailymemory.model.system.ScoreRule;
import cn.dailymemory.service.system.IScoreRuleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dm on 2017/3/24.
 */
@Service
public class ScoreRuleServiceImpl implements IScoreRuleService {
    @Resource
    private IScoreRuleDao scoreRuleDao;


    @Override
    public List<ScoreRule> list() {
        return scoreRuleDao.allList();
    }

    @Override
    public ScoreRule findById(Integer id) {
        return scoreRuleDao.findById(id);
    }

    @Override
    public ResultModel update(ScoreRule scoreRule) {
        if(scoreRuleDao.update(scoreRule) == 1){
            return new ResultModel(0, "操作成功");
        }
        return new ResultModel(-1, "操作失败");
    }

    @Override
    public ResultModel enabled(int id) {
        if(scoreRuleDao.enabled(id) == 1){
            return new ResultModel(0, "操作成功");
        }
        return new ResultModel(-1, "操作失败");
    }

}
