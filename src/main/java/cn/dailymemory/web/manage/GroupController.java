package cn.dailymemory.web.manage;

import cn.dailymemory.common.utils.MemberUtil;
import cn.dailymemory.core.annotation.Before;
import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.interceptor.AdminLoginInterceptor;
import cn.dailymemory.model.group.Group;
import cn.dailymemory.model.member.Member;
import cn.dailymemory.service.group.IGroupService;
import cn.dailymemory.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dm on 16/12/23.
 */
@Controller("manageGroupController")
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class GroupController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/group/";
    @Resource
    private IGroupService groupService;

    @RequestMapping(value = "${managePath}/group/index")
    public String index(@RequestParam(value = "status",required = false,defaultValue = "-1") Integer status,
                        String key,
                        Model model) {
        Page page = new Page(request);
        List<Group> list = groupService.list(status,key);
        model.addAttribute("list",list);
        model.addAttribute("key",key);
        return MANAGE_FTL_PATH + "index";
    }

    @RequestMapping(value = "${managePath}/group/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("id") int id){
        Member loginMember = MemberUtil.getLoginMember(request);
        ResultModel response = groupService.delete(loginMember,id);
        return response;
    }

    @RequestMapping(value = "${managePath}/group/changeStatus/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object changeStatus(@PathVariable("id") int id){
        ResultModel response = groupService.changeStatus(id);
        return response;
    }



}
