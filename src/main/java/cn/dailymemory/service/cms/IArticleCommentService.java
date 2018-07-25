package cn.dailymemory.service.cms;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.cms.ArticleComment;
import cn.dailymemory.model.member.Member;


/**
 * Created by dm on 2016/10/14.
 */
public interface IArticleCommentService {

    ArticleComment findById(int id);

    ResultModel save(Member loginMember, String content, Integer articleId);

    ResultModel delete(Member loginMember, int id);

    ResultModel listByArticle(Page page, int articleId);

    void deleteByArticle(Integer articleId);
}
