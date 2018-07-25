package cn.dailymemory.service.picture;


import cn.dailymemory.model.picture.PictureAlbumFavor;

/**
 * 图片点赞Service接口
 * Created by dm on 2017/11/17.
 */
public interface IPictureAlbumFavorService {

    PictureAlbumFavor find(Integer pictureAlbumId, Integer memberId);

    void save(Integer pictureAlbumId, Integer memberId);

    void delete(Integer pictureAlbumId, Integer memberId);
}