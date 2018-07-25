package cn.dailymemory.core.exception;

import cn.dailymemory.core.enums.Messages;

/**
 * 未登录异常
 * Created by dm on 2017/11/15.
 */
public class NotLoginException extends JeeException {

    public NotLoginException(){
        super(Messages.UN_LOGIN);
    }
}
