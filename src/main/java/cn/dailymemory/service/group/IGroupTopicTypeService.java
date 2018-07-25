package cn.dailymemory.service.group;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.model.group.GroupTopicType;
import cn.dailymemory.model.member.Member;

import java.util.List;

/**
 * @author: dm
 * @date: 2018/5/8 下午11:13
 */
public interface IGroupTopicTypeService {

    GroupTopicType findById(int id);

    List<GroupTopicType> list(int groupId);

    ResultModel delete(Member member, int id);

    ResultModel save(Member member, GroupTopicType groupTopicType);

    ResultModel update(Member member, GroupTopicType groupTopicType);
}
