package cn.dailymemory.service.group;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.group.GroupTopic;
import cn.dailymemory.model.member.Member;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by dm on 2016/12/26.
 */
public interface IGroupTopicService {
    GroupTopic findById(int id);

    GroupTopic findById(int id,Member loginMember);

    ResultModel save(Member member, GroupTopic groupTopic);

    ResultModel update(Member member, GroupTopic groupTopic);

    ResultModel delete(Member loginMember, int id);

    ResultModel indexDelete(HttpServletRequest request, Member loginMember, int id);

    ResultModel listByPage(Page page, String key, int groupId, int status, int memberId, int typeId);

    ResultModel audit(Member member, int id);

    ResultModel top(Member member, int id, int top);

    ResultModel essence(Member member, int id, int essence);

    ResultModel favor(Member loginMember, int id);

    List<GroupTopic> listByCustom(int gid, String sort, int num, int day, int thumbnail);
}
