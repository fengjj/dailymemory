package cn.dailymemory.dao.weibo;

import cn.dailymemory.dao.common.IBaseDao;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.weibo.WeiboComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 微博评论DAO接口
 * Created by dm on 16/12/22.
 */
public interface IWeiboCommentDao extends IBaseDao<WeiboComment> {

    List<WeiboComment> listByWeibo(@Param("page") Page page, @Param("weiboId") Integer weiboId);

    /**
     * 根据微博ID删除评论
     * @param weiboId
     * @return
     */
    int deleteByWeibo(@Param("weiboId") Integer weiboId);
}