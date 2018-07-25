package cn.dailymemory.service.picture;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.member.Member;
import cn.dailymemory.model.picture.PictureAlbumComment;


/**
 *
 * @author dm
 * @date 2017/11/17
 */
public interface IPictureAlbumCommentService {

    PictureAlbumComment findById(int id);

    ResultModel save(Member loginMember, String content, Integer pictureAlbumId);

    ResultModel delete(Member loginMember, int id);

    ResultModel listByPictureAlbum(Page page, int pictureAlbumId);

    void deleteByPictureAlbum(Integer pictureAlbumId);
}
