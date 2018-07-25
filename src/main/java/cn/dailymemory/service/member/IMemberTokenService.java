package cn.dailymemory.service.member;


import cn.dailymemory.model.member.MemberToken;

/**
 * Created by dm on 2017/7/15.
 */
public interface IMemberTokenService {

    MemberToken getByToken(String token);

    void save(Integer memberId,String token);

}