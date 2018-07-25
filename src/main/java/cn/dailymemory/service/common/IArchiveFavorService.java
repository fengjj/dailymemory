package cn.dailymemory.service.common;


import cn.dailymemory.model.common.ArchiveFavor;

/**
 * 文章点赞Service接口
 * Created by dm on 2017/2/9.
 */
public interface IArchiveFavorService {

    ArchiveFavor find(Integer archiveId, Integer memberId);

    void save(Integer archiveId, Integer memberId);

    void delete(Integer archiveId, Integer memberId);
}