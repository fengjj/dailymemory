package cn.dailymemory.service.picture;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.member.Member;
import cn.dailymemory.model.picture.PictureComment;


/**
 * Created by dm on 2017/11/14.
 */
public interface IPictureCommentService {

    PictureComment findById(int id);

    ResultModel save(Member loginMember, String content, Integer pictureId);

    ResultModel delete(Member loginMember, int id);

    ResultModel listByPicture(Page page, int pictureId);

    void deleteByPicture(Integer pictureId);
}
