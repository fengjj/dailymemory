package cn.dailymemory.core.exception;

import cn.dailymemory.core.enums.Messages;

/**
 * 参数异常
 * Created by dm on 2017/5/17.
 */
public class ParamException extends JeeException {

    public ParamException(String msg){
        super(msg);
    }

    public ParamException(Messages messages){
        super(messages);
    }

    public ParamException(){
        super(Messages.PARAM_ERROR);
    }
}
