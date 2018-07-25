package cn.dailymemory.dao.picture;

import cn.dailymemory.core.model.Page;
import cn.dailymemory.dao.common.IBaseDao;
import cn.dailymemory.model.picture.PictureAlbumComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPictureAlbumCommentDao extends IBaseDao<PictureAlbumComment> {
    List<PictureAlbumComment> listByPictureAlbum(@Param("page") Page page, @Param("pictureAlbumId") Integer pictureAlbumId);

    int deleteByPictureAlbum(@Param("pictureAlbumId") Integer pictureAlbumId);
}