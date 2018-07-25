package cn.dailymemory.service.group;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.group.GroupTopicComment;
import cn.dailymemory.model.member.Member;


/**
 * Created by dm on 2016/12/27.
 */
public interface IGroupTopicCommentService {

    GroupTopicComment findById(int id);

    ResultModel save(Member loginMember, String content, Integer groupTopicId, Integer commentId);

    ResultModel delete(Member loginMember, int id);

    ResultModel listByGroupTopic(Page page, int groupTopicId);

    void deleteByTopic(int groupTopicId);
}
