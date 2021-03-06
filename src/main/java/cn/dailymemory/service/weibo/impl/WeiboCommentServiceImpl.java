package cn.dailymemory.service.weibo.impl;

import cn.dailymemory.core.consts.AppTag;
import cn.dailymemory.core.enums.MessageType;
import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.core.utils.*;
import cn.dailymemory.model.member.Member;
import cn.dailymemory.model.weibo.Weibo;
import cn.dailymemory.model.weibo.WeiboComment;
import cn.dailymemory.service.member.IMemberService;
import cn.dailymemory.service.member.IMessageService;
import cn.dailymemory.service.member.IScoreDetailService;
import cn.dailymemory.service.system.IActionLogService;
import cn.dailymemory.dao.weibo.IWeiboCommentDao;
import cn.dailymemory.service.weibo.IWeiboCommentService;
import cn.dailymemory.service.weibo.IWeiboService;
import cn.dailymemory.common.utils.ActionUtil;
import cn.dailymemory.common.utils.ScoreRuleConsts;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dm on 2016/12/22.
 */
@Service("weiboCommentService")
public class WeiboCommentServiceImpl implements IWeiboCommentService {
    @Resource
    private IWeiboCommentDao weiboCommentDao;
    @Resource
    private IWeiboService weiboService;
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private IScoreDetailService scoreDetailService;
    @Resource
    private IMessageService messageService;
    @Resource
    private IMemberService memberService;

    @Override
    public WeiboComment findById(int id) {
        WeiboComment weiboComment = weiboCommentDao.findById(id);
        atFormat(weiboComment);
        return weiboComment;
    }

    @Override
    public ResultModel save(Member loginMember, String content, Integer weiboId, Integer weiboCommentId) {
        Weibo weibo = weiboService.findById(weiboId,loginMember.getId());
        if(weibo == null){
            return new ResultModel(-1,"微博不存在");
        }
        if(StringUtils.isEmpty(content)){
            return new ResultModel(-1,"内容不能为空");
        }
        if(content.length() > 500){
            return new ResultModel(-1,"评论内容不能超过500长度");
        }
        WeiboComment weiboComment = new WeiboComment();
        weiboComment.setMemberId(loginMember.getId());
        weiboComment.setWeiboId(weiboId);
        weiboComment.setContent(content);
        weiboComment.setCommentId(weiboCommentId);
        int result = weiboCommentDao.save(weiboComment);
        if(result == 1){
            //@会员处理并发送系统消息
            messageService.atDeal(loginMember.getId(),content, AppTag.WEIBO, MessageType.WEIBO_COMMENT_REFER,weibo.getId());
            //回复微博发送系统信息
            messageService.diggDeal(loginMember.getId(), weibo.getMemberId(), content,AppTag.WEIBO, MessageType.WEIBO_REPLY, weibo.getId());
            if (weiboCommentId != null){
                WeiboComment replyWeiboComment = this.findById(weiboCommentId);
                if (replyWeiboComment != null){
                    //回复微博发送系统信息
                    messageService.diggDeal(loginMember.getId(), replyWeiboComment.getMemberId(), content, AppTag.WEIBO, MessageType.WEIBO_REPLY_REPLY, replyWeiboComment.getId());
                }
            }
            //微博评论奖励
            scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.COMMENT_WEIBO, weiboComment.getId());
            return new ResultModel(1,"评论成功");
        }else {
            return new ResultModel(-1,"评论失败");
        }
    }

    @Override
    public ResultModel listByWeibo(Page page, int weiboId) {
        List<WeiboComment> list = weiboCommentDao.listByWeibo(page, weiboId);
        atFormat(list);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public void deleteByWeibo(Integer weiboId) {
        weiboCommentDao.deleteByWeibo(weiboId);
    }

    @Override
    public ResultModel delete(Member loginMember, int id) {
        WeiboComment weiboComment = this.findById(id);
        if(weiboComment == null){
            return new ResultModel(-1,"评论不存在");
        }
        int result = weiboCommentDao.delete(id);
        if(result == 1){
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.DELETE_WEIBO_COMMENT,"ID："+weiboComment.getId()+"内容："+weiboComment.getContent());
            return new ResultModel(1,"删除成功");
        }
        return new ResultModel(-1,"删除失败");
    }

    public WeiboComment atFormat(WeiboComment weiboComment){
        weiboComment.setContent(memberService.atFormat(weiboComment.getContent()));
        return weiboComment;
    }

    public List<WeiboComment> atFormat(List<WeiboComment> weiboCommentList){
        for (WeiboComment weiboComment : weiboCommentList){
            atFormat(weiboComment);
        }
        return weiboCommentList;
    }
}
