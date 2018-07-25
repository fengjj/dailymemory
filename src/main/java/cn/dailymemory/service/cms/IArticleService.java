package cn.dailymemory.service.cms;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.cms.Article;
import cn.dailymemory.model.member.Member;

import java.util.List;


/**
 * Created by dm on 2016/10/14.
 */
public interface IArticleService {

    Article findById(int id);

    Article findById(int id,Member loginMember);

    ResultModel save(Member member, Article article);

    ResultModel update(Member member, Article article);

    ResultModel delete(Member member, int id);

    ResultModel listByPage(Page page, String key, int cateid, int status, int memberId);

    void updateViewCount(int id);

    ResultModel audit(int id);

    ResultModel favor(Member loginMember, int articleId);

    List<Article> listByCustom(int cid,String sort,int num,int day,int thumbnail);
}
