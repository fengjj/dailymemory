package cn.dailymemory.service.group.impl;

import cn.dailymemory.core.exception.OpeErrorException;
import cn.dailymemory.dao.group.IGroupTypeDao;
import cn.dailymemory.model.group.GroupType;
import cn.dailymemory.service.group.IGroupTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: dm
 * @date: 2018/5/15 下午1:17
 */
@Service
public class GroupTypeServiceImpl implements IGroupTypeService {
    @Resource
    private IGroupTypeDao groupTypeDao;

    @Override
    public GroupType findById(int id) {
        return groupTypeDao.findById(id);
    }

    @Override
    public List<GroupType> list() {
        return groupTypeDao.allList();
    }

    @Override
    public boolean delete(int id) {
        if (id == 1){
            throw new OpeErrorException("默认分类无法删除");
        }
        int result = groupTypeDao.delete(id);
        if (result == 0){
            throw new OpeErrorException();
        }
        return true;
    }

    @Override
    public boolean save(GroupType groupType) {
        int result = groupTypeDao.save(groupType);
        if (result == 0){
            throw new OpeErrorException();
        }
        return true;
    }

    @Override
    public boolean update(GroupType groupType) {
        int result = groupTypeDao.update(groupType);
        if (result == 0){
            throw new OpeErrorException();
        }
        return true;
    }
}
