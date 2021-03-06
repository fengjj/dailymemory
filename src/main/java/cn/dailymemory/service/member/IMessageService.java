package cn.dailymemory.service.member;

import cn.dailymemory.core.enums.MessageType;
import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.member.Message;

/**
 * 会员私信Service
 * Created by dm on 17/3/9.
 */
public interface IMessageService {

    ResultModel sentMsg(Integer fromMemberId, Integer toMemberId, String content);

    ResultModel systemMsgSave(Integer toMemberId, String content, Integer appTag, Integer type, Integer relateKeyId, Integer memberId, String description);

    ResultModel<Message> listByPage(Page page, Integer fromMemberId, Integer toMemberId);

    ResultModel<Message> messageRecords(Page page, Integer fromMemberId, Integer toMemberId);

    ResultModel<Message> systemMessage(Page page, Integer toMemberId, String basePath);

    /**
     * 删除某个会员的所有聊天记录
     * @param memberId
     * @return
     */
    int deleteByMember(Integer memberId);

    /**
     * 清空两个会员的聊天记录
     * @param fromMemberId
     * @param toMemberId
     * @return
     */
    int clearMessageByMember(Integer fromMemberId, Integer toMemberId);

    /**
     * 会员未读私信数量
     * @param memberId
     * @return
     */
    int countUnreadNum(int memberId);

    /**
     * 系统未读信息数量
     * @param memberId
     * @return
     */
    int countSystemUnreadNum(int memberId);

    /**
     * 设置已读状态
     * @param fromMemberId
     * @param toMemberId
     * @return
     */
    int setRead(Integer fromMemberId, Integer toMemberId);

    void atDeal(int loginMemberId,String content,int appTag,MessageType messageType,int relateKeyId);

    void diggDeal(int loginMemberId,int toMemberId, String content, int appTag,MessageType messageType,int relateKeyId);

    void diggDeal(int loginMemberId,int toMemberId, int appTag,MessageType messageType,int relateKeyId);
}