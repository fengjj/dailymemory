package cn.dailymemory.service.picture.impl;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.dao.picture.IPictureAlbumCommentDao;
import cn.dailymemory.model.member.Member;
import cn.dailymemory.model.picture.PictureAlbum;
import cn.dailymemory.model.picture.PictureAlbumComment;
import cn.dailymemory.service.member.IMemberService;
import cn.dailymemory.service.picture.IPictureAlbumCommentService;
import cn.dailymemory.service.picture.IPictureAlbumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author dm
 * @date 2017/11/17
 */
@Service("pictureAlbumCommentService")
public class PictureAlbumCommentServiceImpl implements IPictureAlbumCommentService {
    @Resource
    private IPictureAlbumCommentDao pictureAlbumCommentDao;
    @Resource
    private IPictureAlbumService pictureAlbumService;
    @Resource
    private IMemberService memberService;

    @Override
    public PictureAlbumComment findById(int id) {
        PictureAlbumComment pictureAlbumComment = pictureAlbumCommentDao.findById(id);
        atFormat(pictureAlbumComment);
        return pictureAlbumComment;
    }

    @Override
    public ResultModel save(Member loginMember, String content, Integer pictureAlbumId) {
        PictureAlbum pictureAlbum = pictureAlbumService.findById(pictureAlbumId);
        if(pictureAlbum == null){
            return new ResultModel(-1,"相册不存在");
        }
        PictureAlbumComment pictureAlbumComment = new PictureAlbumComment();
        pictureAlbumComment.setMemberId(loginMember.getId());
        pictureAlbumComment.setPictureAlbumId(pictureAlbumId);
        pictureAlbumComment.setContent(content);
        int result = pictureAlbumCommentDao.save(pictureAlbumComment);
        if(result == 1){
            return new ResultModel(0,"评论成功");
        }else {
            return new ResultModel(-1,"评论失败");
        }
    }

    @Override
    public ResultModel listByPictureAlbum(Page page, int pictureAlbumId) {
        List<PictureAlbumComment> list = pictureAlbumCommentDao.listByPictureAlbum(page, pictureAlbumId);
        atFormat(list);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public void deleteByPictureAlbum(Integer pictureAlbumId) {
        pictureAlbumCommentDao.deleteByPictureAlbum(pictureAlbumId);
    }

    @Override
    public ResultModel delete(Member loginMember, int id) {
        PictureAlbumComment pictureAlbumComment = this.findById(id);
        if(pictureAlbumComment == null){
            return new ResultModel(-1,"评论不存在");
        }
        int result = pictureAlbumCommentDao.delete(id);
        if(result == 1){
            return new ResultModel(1,"删除成功");
        }
        return new ResultModel(-1,"删除失败");
    }

    public PictureAlbumComment atFormat(PictureAlbumComment pictureAlbumComment){
        pictureAlbumComment.setContent(memberService.atFormat(pictureAlbumComment.getContent()));
        return pictureAlbumComment;
    }

    public List<PictureAlbumComment> atFormat(List<PictureAlbumComment> pictureAlbumCommentList){
        for (PictureAlbumComment pictureAlbumComment : pictureAlbumCommentList){
            atFormat(pictureAlbumComment);
        }
        return pictureAlbumCommentList;
    }
}
