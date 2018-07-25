package cn.dailymemory.service.picture;


import cn.dailymemory.model.picture.PictureFavor;

/**
 * 图片点赞Service接口
 * Created by dm on 2017/11/16.
 */
public interface IPictureFavorService {

    PictureFavor find(Integer pictureId, Integer memberId);

    void save(Integer pictureId, Integer memberId);

    void delete(Integer pictureId, Integer memberId);
}