package cn.dailymemory.web.front;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.core.utils.JeesnsConfig;
import cn.dailymemory.web.common.BaseController;
import cn.dailymemory.model.system.ActionLog;
import cn.dailymemory.service.system.IActionLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 动态
 * Created by dm on 2017/3/8.
 */
@Controller("frontActionController")
@RequestMapping("/action/")
public class ActionController extends BaseController {
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private JeesnsConfig jeesnsConfig;

    @RequestMapping("list")
    public String list(Model model){
        Page page = new Page(request);
        ResultModel<ActionLog> actionList = actionLogService.memberActionLog(page,0);
        model.addAttribute("model", actionList);
        return jeesnsConfig.getFrontTemplate() + "/action/list";
    }


}
