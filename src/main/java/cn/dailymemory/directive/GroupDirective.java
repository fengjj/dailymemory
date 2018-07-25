package cn.dailymemory.directive;

import cn.dailymemory.core.directive.BaseDirective;
import cn.dailymemory.core.handler.DirectiveHandler;
import cn.dailymemory.model.group.Group;
import cn.dailymemory.service.group.IGroupService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by dm on 2017/5/18.
 */
@Component
public class GroupDirective extends BaseDirective {
    @Resource
    private IGroupService groupService;
    @Override
    public void execute(DirectiveHandler handler) throws TemplateException, IOException {
        int num = handler.getInteger("num",0);
        String sort = handler.getString("sort","id");
        int status = handler.getInteger("status",-1);
        List<Group> list = groupService.listByCustom(status,num,sort);
        handler.put("groupList", list).render();
    }

}
