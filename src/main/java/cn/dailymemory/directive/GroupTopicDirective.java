package cn.dailymemory.directive;

import cn.dailymemory.core.directive.BaseDirective;
import cn.dailymemory.core.handler.DirectiveHandler;
import cn.dailymemory.model.group.GroupTopic;
import cn.dailymemory.service.group.IGroupTopicService;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by dm on 2017/5/18.
 */
@Component
public class GroupTopicDirective extends BaseDirective {
    @Resource
    private IGroupTopicService groupTopicService;
    @Override
    public void execute(DirectiveHandler handler) throws TemplateException, IOException {
        int gid = handler.getInteger("gid",0);
        int num = handler.getInteger("num",0);
        String sort = handler.getString("sort","id");
        int day = handler.getInteger("day",0);
        int thumbnail = handler.getInteger("thumbnail",0);
        List<GroupTopic> list = groupTopicService.listByCustom(gid,sort,num,day,thumbnail);
        handler.put("groupTopicList", list).render();
    }

}