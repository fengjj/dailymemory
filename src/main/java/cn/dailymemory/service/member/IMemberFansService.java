package cn.dailymemory.service.member;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.member.MemberFans;


/**
 * Created by dm on 17/2/21.
 */
public interface IMemberFansService {

    ResultModel save(Integer whoFollowId, Integer followWhoId);

    ResultModel delete(Integer whoFollowId, Integer followWhoId);

    ResultModel followsList(Page page, Integer whoFollowId);

    ResultModel fansList(Page page, Integer followWhoId);

    MemberFans find(Integer whoFollowId, Integer followWhoId);
}
