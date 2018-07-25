package cn.dailymemory.dao.picture;

import cn.dailymemory.dao.common.IBaseDao;
import cn.dailymemory.model.picture.PictureFavor;
import org.apache.ibatis.annotations.Param;

public interface IPictureFavorDao extends IBaseDao<PictureFavor> {

    PictureFavor find(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);

    Integer save(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);

    Integer delete(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);
}