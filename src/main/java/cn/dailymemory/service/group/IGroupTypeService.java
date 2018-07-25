package cn.dailymemory.service.group;

import cn.dailymemory.model.group.GroupType;
import java.util.List;

/**
 * @author: dm
 * @date: 2018/5/15 下午11:13
 */
public interface IGroupTypeService {

    GroupType findById(int id);

    List<GroupType> list();

    boolean delete(int id);

    boolean save(GroupType groupType);

    boolean update(GroupType groupType);
}
