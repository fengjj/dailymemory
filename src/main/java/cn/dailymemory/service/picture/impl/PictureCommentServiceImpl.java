package cn.dailymemory.service.picture.impl;

import cn.dailymemory.core.consts.AppTag;
import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.enums.MessageType;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.dao.picture.IPictureCommentDao;
import cn.dailymemory.model.member.Member;
import cn.dailymemory.model.picture.Picture;
import cn.dailymemory.model.picture.PictureComment;
import cn.dailymemory.service.member.IMemberService;
import cn.dailymemory.service.member.IMessageService;
import cn.dailymemory.service.picture.IPictureCommentService;
import cn.dailymemory.service.picture.IPictureService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dm on 2017/11/14.
 */
@Service("pictureCommentService")
public class PictureCommentServiceImpl implements IPictureCommentService {
    @Resource
    private IPictureCommentDao pictureCommentDao;
    @Resource
    private IPictureService pictureService;
    @Resource
    private IMessageService messageService;
    @Resource
    private IMemberService memberService;

    @Override
    public PictureComment findById(int id) {
        PictureComment PictureComment = pictureCommentDao.findById(id);
        atFormat(PictureComment);
        return PictureComment;
    }

    @Override
    public ResultModel save(Member loginMember, String content, Integer pictureId) {
        Picture picture = pictureService.findById(pictureId,loginMember.getId());
        if(picture == null){
            return new ResultModel(-1,"图片不存在");
        }
        PictureComment pictureComment = new PictureComment();
        pictureComment.setMemberId(loginMember.getId());
        pictureComment.setPictureId(pictureId);
        pictureComment.setContent(content);
        int result = pictureCommentDao.save(pictureComment);
        if(result == 1){
            //@会员处理并发送系统消息
            messageService.atDeal(loginMember.getId(),content, AppTag.PICTURE, MessageType.PICTURE_COMMENT_REFER,picture.getPictureId());
            //回复微博发送系统信息
            messageService.diggDeal(loginMember.getId(), picture.getMemberId(), content,AppTag.PICTURE, MessageType.PICTURE_REPLY, picture.getPictureId());
            return new ResultModel(0,"评论成功");
        }else {
            return new ResultModel(-1,"评论失败");
        }
    }

    @Override
    public ResultModel listByPicture(Page page, int pictureId) {
        List<PictureComment> list = pictureCommentDao.listByPicture(page, pictureId);
        atFormat(list);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public void deleteByPicture(Integer pictureId) {
        pictureCommentDao.deleteByPicture(pictureId);
    }

    @Override
    public ResultModel delete(Member loginMember, int id) {
        PictureComment pictureComment = this.findById(id);
        if(pictureComment == null){
            return new ResultModel(-1,"评论不存在");
        }
        int result = pictureCommentDao.delete(id);
        if(result == 1){
            return new ResultModel(1,"删除成功");
        }
        return new ResultModel(-1,"删除失败");
    }

    public PictureComment atFormat(PictureComment pictureComment){
        pictureComment.setContent(memberService.atFormat(pictureComment.getContent()));
        return pictureComment;
    }

    public List<PictureComment> atFormat(List<PictureComment> pictureCommentList){
        for (PictureComment pictureComment : pictureCommentList){
            atFormat(pictureComment);
        }
        return pictureCommentList;
    }
}
