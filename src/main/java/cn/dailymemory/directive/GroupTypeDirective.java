package cn.dailymemory.directive;

import cn.dailymemory.core.directive.BaseDirective;
import cn.dailymemory.core.handler.DirectiveHandler;
import cn.dailymemory.model.group.GroupType;
import cn.dailymemory.service.group.IGroupTypeService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author: dm
 * @date: 2018/5/16 下午16:14
 */
@Component
public class GroupTypeDirective extends BaseDirective {

    @Resource
    private IGroupTypeService groupTypeService;
    @Override
    public void execute(DirectiveHandler handler) throws TemplateException, IOException {
        List<GroupType> list = groupTypeService.list();
        handler.put("groupTypeList", list).render();
    }

}
