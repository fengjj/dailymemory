package cn.dailymemory.utils;

import cn.dailymemory.core.utils.LocaleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * @author dm
 */
@Component
public class StaticFieldInjection {

    @Autowired
    private MessageSource messageSource;

    @PostConstruct
    private void init(){
        LocaleUtil.setMessageSource(messageSource);
    }
}
