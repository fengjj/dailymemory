package cn.dailymemory.dao.member;

import cn.dailymemory.dao.common.IBaseDao;
import cn.dailymemory.model.member.MemberToken;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by dm on 2017/7/15.
 */
public interface IMemberTokenDao extends IBaseDao<MemberToken> {

    MemberToken getByToken(@Param("token") String token);

    Integer save(@Param("memberId") Integer memberId, @Param("token") String token, @Param("expireTime") Date expireTime);

}