package cn.dailymemory.dao.picture;

import cn.dailymemory.dao.common.IBaseDao;
import cn.dailymemory.model.picture.PictureAlbumFavor;
import org.apache.ibatis.annotations.Param;

public interface IPictureAlbumFavorDao extends IBaseDao<PictureAlbumFavor> {
    PictureAlbumFavor find(@Param("pictureAlbumId") Integer pictureAlbumId, @Param("memberId") Integer memberId);

    Integer save(@Param("pictureAlbumId") Integer pictureAlbumId, @Param("memberId") Integer memberId);

    Integer delete(@Param("pictureAlbumId") Integer pictureAlbumId, @Param("memberId") Integer memberId);
}