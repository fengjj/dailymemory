package cn.dailymemory.dao.picture;

import cn.dailymemory.core.model.Page;
import cn.dailymemory.dao.common.IBaseDao;
import cn.dailymemory.model.picture.Picture;
import cn.dailymemory.model.picture.PictureAlbum;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface IPictureAlbumDao extends IBaseDao<PictureAlbum> {

    List<PictureAlbum> listByMember(@Param("memberId") Integer memberId);

    List<PictureAlbum> listByPage(@Param("page") Page page);

    PictureAlbum findWeiboAlbum(@Param("memberId") Integer memberId);
}