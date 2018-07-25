package cn.dailymemory.service.group.impl;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.group.Group;
import cn.dailymemory.model.group.GroupFans;
import cn.dailymemory.model.member.Member;
import cn.dailymemory.service.group.IGroupFansService;
import cn.dailymemory.dao.group.IGroupFansDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dm on 2016/12/26.
 */
@Service("groupFansService")
public class GroupFansServiceImpl implements IGroupFansService {
    @Resource
    private IGroupFansDao groupFansDao;

    @Override
    public ResultModel listByPage(Page page, Integer groupId) {
        List<GroupFans> list = groupFansDao.listByPage(page, groupId);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public GroupFans findByMemberAndGroup(@Param("groupId") Integer groupId, @Param("memberId") Integer memberId) {
        return groupFansDao.findByMemberAndGroup(groupId,memberId);
    }

    /**
     * 关注
     * @param loginMember
     * @param groupId
     * @return
     */
    @Override
    public ResultModel save(Member loginMember, Integer groupId) {
        if(groupFansDao.findByMemberAndGroup(groupId,loginMember.getId()) == null){
            if(groupFansDao.save(groupId,loginMember.getId()) == 1){
                return new ResultModel(1,"关注成功");
            }
        }else {
            //已经关注了
            return new ResultModel(0,"关注成功");
        }
        return new ResultModel(-1,"关注失败");
    }

    /**
     * 取消关注
     * @param loginMember
     * @param groupId
     * @return
     */
    @Override
    public ResultModel delete(Member loginMember, Integer groupId) {
        if(groupFansDao.delete(groupId,loginMember.getId()) > 0){
            return new ResultModel(1,"取消关注成功");
        }
        return new ResultModel(-1,"取消关注失败");
    }


    @Override
    public ResultModel listByMember(Page page, Integer memberId) {
        List<Group> list = groupFansDao.listByMember(page, memberId);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

}
