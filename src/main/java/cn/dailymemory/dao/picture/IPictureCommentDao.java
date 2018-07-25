package cn.dailymemory.dao.picture;

import cn.dailymemory.core.model.Page;
import cn.dailymemory.dao.common.IBaseDao;
import cn.dailymemory.model.picture.PictureComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPictureCommentDao extends IBaseDao<PictureComment> {

    List<PictureComment> listByPicture(@Param("page") Page page, @Param("pictureId") Integer pictureId);

    int deleteByPicture(@Param("pictureId") Integer pictureId);
}