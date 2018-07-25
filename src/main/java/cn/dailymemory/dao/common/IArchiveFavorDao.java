package cn.dailymemory.dao.common;

import cn.dailymemory.model.common.ArchiveFavor;
import org.apache.ibatis.annotations.Param;

/**
 * 文章点赞DAO接口
 * Created by dm on 2017/2/9.
 */
public interface IArchiveFavorDao extends IBaseDao<ArchiveFavor> {

    ArchiveFavor find(@Param("archiveId") Integer archiveId, @Param("memberId") Integer memberId);

    Integer save(@Param("archiveId") Integer archiveId, @Param("memberId") Integer memberId);

    Integer delete(@Param("archiveId") Integer archiveId, @Param("memberId") Integer memberId);
}