package cn.dailymemory.dao.cms;

import cn.dailymemory.dao.common.IBaseDao;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.cms.ArticleComment;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 文章评论DAO接口
 * Created by dm on 2016/11/26.
 */
public interface IArticleCommentDao extends IBaseDao<ArticleComment> {

    List<ArticleComment> listByArticle(@Param("page") Page page, @Param("articleId") Integer articleId);

    int deleteByArticle(@Param("articleId") Integer articleId);
}