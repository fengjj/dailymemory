package cn.dailymemory.service.common.impl;

import cn.dailymemory.dao.common.IArchiveFavorDao;
import cn.dailymemory.model.common.ArchiveFavor;
import cn.dailymemory.service.common.IArchiveFavorService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by dm on 2017/2/9.
 */
@Service("archiveFavorService")
public class ArchiveFavorServiceImpl implements IArchiveFavorService {
    @Resource
    private IArchiveFavorDao archiveFavorDao;


    @Override
    public ArchiveFavor find(Integer archiveId, Integer memberId) {
        return archiveFavorDao.find(archiveId,memberId);
    }

    @Override
    public void save(Integer archiveId, Integer memberId) {
        archiveFavorDao.save(archiveId,memberId);
    }

    @Override
    public void delete(Integer archiveId, Integer memberId) {
        archiveFavorDao.delete(archiveId,memberId);
    }
}
