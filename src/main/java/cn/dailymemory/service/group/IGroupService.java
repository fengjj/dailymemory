package cn.dailymemory.service.group;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.model.group.Group;
import cn.dailymemory.model.member.Member;

import java.util.List;


/**
 * Created by dm on 16/12/23.
 */
public interface IGroupService {

    Group findById(int id);

    ResultModel save(Member loginMember, Group group);

    ResultModel update(Member loginMember, Group group);

    ResultModel delete(Member loginMember, int id);

    List<Group> list(int status, String key);

    ResultModel follow(Member loginMember, Integer groupId, int type);

    ResultModel changeStatus(int id);

    List<Group> listByCustom(int status, int num, String sort);
}
