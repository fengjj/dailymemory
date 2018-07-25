package cn.dailymemory.dao.group;

import cn.dailymemory.dao.common.IBaseDao;
import cn.dailymemory.model.group.GroupTopicType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: dm
 * @date: 2018/5/8 下午10:59
 */
public interface IGroupTopicTypeDao extends IBaseDao<GroupTopicType> {

    List<GroupTopicType> list(@Param("groupId") Integer groupId);

}