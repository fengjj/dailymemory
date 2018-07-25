package cn.dailymemory.dao.group;

import cn.dailymemory.dao.common.IBaseDao;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.group.GroupTopicComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dm on 16/12/27.
 */
public interface IGroupTopicCommentDao extends IBaseDao<GroupTopicComment> {

    List<GroupTopicComment> listByGroupTopic(@Param("page") Page page, @Param("groupTopicId") Integer groupTopicId);

    int deleteByTopic(@Param("groupTopicId") Integer groupTopicId);
}