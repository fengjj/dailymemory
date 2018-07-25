package cn.dailymemory.dao.member;

import cn.dailymemory.dao.common.IBaseDao;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.model.member.MemberFans;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dm on 2017/2/16.
 */
public interface IMemberFansDao extends IBaseDao<MemberFans> {

    List<MemberFans> followsList(@Param("page") Page page, @Param("whoFollowId") Integer whoFollowId);

    List<MemberFans> fansList(@Param("page") Page page, @Param("followWhoId") Integer followWhoId);

    MemberFans find(@Param("whoFollowId") Integer whoFollowId, @Param("followWhoId") Integer followWhoId);

    Integer save(@Param("whoFollowId") Integer whoFollowId, @Param("followWhoId") Integer followWhoId);

    Integer delete(@Param("whoFollowId") Integer whoFollowId, @Param("followWhoId") Integer followWhoId);
}