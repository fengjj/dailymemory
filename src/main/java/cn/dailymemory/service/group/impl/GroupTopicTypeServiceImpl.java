package cn.dailymemory.service.group.impl;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.dao.group.IGroupTopicTypeDao;
import cn.dailymemory.model.group.GroupTopicType;
import cn.dailymemory.model.member.Member;
import cn.dailymemory.service.group.IGroupTopicTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: dm
 * @date: 2018/5/9 下午1:17
 */
@Service
public class GroupTopicTypeServiceImpl implements IGroupTopicTypeService {
    @Resource
    private IGroupTopicTypeDao groupTopicTypeDao;

    @Override
    public GroupTopicType findById(int id) {
        return groupTopicTypeDao.findById(id);
    }

    @Override
    public List<GroupTopicType> list(int groupId) {
        return groupTopicTypeDao.list(groupId);
    }

    @Override
    public ResultModel delete(Member member, int id) {
        int result = groupTopicTypeDao.delete(id);
        if (result == 1){
            return new ResultModel(0);
        }
        return new ResultModel(-1);
    }

    @Override
    public ResultModel save(Member member, GroupTopicType groupTopicType) {
        int result = groupTopicTypeDao.save(groupTopicType);
        if (result == 1){
            return new ResultModel(0);
        }
        return new ResultModel(-1);
    }

    @Override
    public ResultModel update(Member member, GroupTopicType groupTopicType) {
        int result = groupTopicTypeDao.update(groupTopicType);
        if (result == 1){
            return new ResultModel(0);
        }
        return new ResultModel(-1);
    }
}
