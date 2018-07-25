package cn.dailymemory.core.exception;

import cn.dailymemory.core.enums.Messages;

/**
 * @author: dm
 * @date: 2018/5/16 下午8:36
 */
public class JeeException extends RuntimeException {
    private Messages jeeMessage;


    public JeeException(){
        super();
        jeeMessage = Messages.ERROR;
    }

    public JeeException(String msg){
        super(msg);
    }

    public JeeException(Messages lxiMessage){
        this.jeeMessage = lxiMessage;
    }

    public Messages getJeeMessage() {
        return jeeMessage;
    }
}