package cn.dailymemory.service.group;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.group.GroupFans;
import cn.dailymemory.model.member.Member;
import org.apache.ibatis.annotations.Param;


/**
 * Created by dm on 16/12/26.
 */
public interface IGroupFansService {

    ResultModel save(Member loginMember, Integer groupId);

    ResultModel delete(Member loginMember, Integer groupId);

    ResultModel listByPage(Page page, Integer groupId);

    GroupFans findByMemberAndGroup(@Param("groupId") Integer groupId, @Param("memberId") Integer memberId);

    /**
     * 获取用户关注的群组列表
     * @param page
     * @param memberId
     * @return
     */
    ResultModel listByMember(Page page, Integer memberId);
}
