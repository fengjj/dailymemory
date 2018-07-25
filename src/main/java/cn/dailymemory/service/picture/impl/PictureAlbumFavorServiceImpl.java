package cn.dailymemory.service.picture.impl;

import cn.dailymemory.dao.picture.IPictureAlbumFavorDao;
import cn.dailymemory.dao.picture.IPictureFavorDao;
import cn.dailymemory.model.picture.PictureAlbumFavor;
import cn.dailymemory.model.picture.PictureFavor;
import cn.dailymemory.service.picture.IPictureAlbumFavorService;
import cn.dailymemory.service.picture.IPictureFavorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author dm
 * @date 2017/11/17
 */
@Service("pictureAlbumFavorService")
public class PictureAlbumFavorServiceImpl implements IPictureAlbumFavorService {
    @Resource
    private IPictureAlbumFavorDao pictureAlbumFavorDao;


    @Override
    public PictureAlbumFavor find(Integer pictureAlbumId, Integer memberId) {
        return pictureAlbumFavorDao.find(pictureAlbumId,memberId);
    }

    @Override
    public void save(Integer pictureAlbumId, Integer memberId) {
        pictureAlbumFavorDao.save(pictureAlbumId,memberId);
    }

    @Override
    public void delete(Integer pictureAlbumId, Integer memberId) {
        pictureAlbumFavorDao.delete(pictureAlbumId,memberId);
    }
}
