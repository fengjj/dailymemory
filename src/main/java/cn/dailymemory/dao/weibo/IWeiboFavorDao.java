package cn.dailymemory.dao.weibo;

import cn.dailymemory.dao.common.IBaseDao;
import cn.dailymemory.model.weibo.WeiboFavor;
import org.apache.ibatis.annotations.Param;

/**
 * 微博点赞DAO接口
 * Created by dm on 2017/2/8.
 */
public interface IWeiboFavorDao extends IBaseDao<WeiboFavor> {

    WeiboFavor find(@Param("weiboId") Integer weiboId, @Param("memberId") Integer memberId);

    Integer save(@Param("weiboId") Integer weiboId, @Param("memberId") Integer memberId);

    Integer delete(@Param("weiboId") Integer weiboId, @Param("memberId") Integer memberId);
}