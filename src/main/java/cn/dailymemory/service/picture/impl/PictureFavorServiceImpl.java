package cn.dailymemory.service.picture.impl;

import cn.dailymemory.dao.picture.IPictureFavorDao;
import cn.dailymemory.model.picture.PictureFavor;
import cn.dailymemory.service.picture.IPictureFavorService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by dm on 2017/11/16.
 */
@Service("pictureFavorService")
public class PictureFavorServiceImpl implements IPictureFavorService {
    @Resource
    private IPictureFavorDao pictureFavorDao;


    @Override
    public PictureFavor find(Integer pictureId, Integer memberId) {
        return pictureFavorDao.find(pictureId,memberId);
    }

    @Override
    public void save(Integer pictureId, Integer memberId) {
        pictureFavorDao.save(pictureId,memberId);
    }

    @Override
    public void delete(Integer pictureId, Integer memberId) {
        pictureFavorDao.delete(pictureId,memberId);
    }
}
