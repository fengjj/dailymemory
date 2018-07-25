package cn.dailymemory.service.picture;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.picture.PictureAlbum;

/**
 * Created by dm on 2017/11/03.
 */
public interface IPictureAlbumService {

    ResultModel<PictureAlbum> listByMember(Integer memberId);

    ResultModel<PictureAlbum> listByPage(Page page);

    ResultModel delete(Integer id);

    ResultModel save(PictureAlbum pictureAlbum);

    ResultModel update(PictureAlbum pictureAlbum);

    PictureAlbum findWeiboAlbum(Integer memberId);

    PictureAlbum findById(Integer id);
}