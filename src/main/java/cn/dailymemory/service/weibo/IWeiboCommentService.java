package cn.dailymemory.service.weibo;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.member.Member;
import cn.dailymemory.model.weibo.WeiboComment;


/**
 * Created by dm on 2016/10/14.
 */
public interface IWeiboCommentService {

    WeiboComment findById(int id);

    ResultModel save(Member loginMember, String content, Integer weiboId, Integer weiboCommentId);

    ResultModel delete(Member loginMember, int id);

    ResultModel listByWeibo(Page page, int weiboId);

    void deleteByWeibo(Integer weiboId);
}
