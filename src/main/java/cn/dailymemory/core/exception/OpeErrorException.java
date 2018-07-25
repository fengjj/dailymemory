package cn.dailymemory.core.exception;


import cn.dailymemory.core.enums.Messages;

/**
 * @author dm
 */
public class OpeErrorException extends JeeException {

    public OpeErrorException(){
        super(Messages.ERROR);
    }

    public OpeErrorException(Messages message){
        super(message);
    }

    public OpeErrorException(String message){
        super(message);
    }
}
