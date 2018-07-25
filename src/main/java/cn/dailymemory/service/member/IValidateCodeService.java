package cn.dailymemory.service.member;


import cn.dailymemory.model.member.ValidateCode;

/**
 * 验证码Service接口
 * Created by dm on 17/01/20.
 */
public interface IValidateCodeService {

    boolean save(ValidateCode validateCode);

    /**
     * 验证，30分钟以内有效
     * @param email
     * @param code
     * @param type
     * @return
     */
    ValidateCode valid(String email, String code, int type);

    boolean used(Integer id);
}