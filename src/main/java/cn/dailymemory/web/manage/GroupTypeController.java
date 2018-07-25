package cn.dailymemory.web.manage;

import cn.dailymemory.core.annotation.Before;
import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.interceptor.AdminLoginInterceptor;
import cn.dailymemory.model.group.GroupType;
import cn.dailymemory.service.group.IGroupTypeService;
import cn.dailymemory.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author: dm
 * @date: 2018/5/15 下午1:17
 */
@Controller("manageGroupTypeController")
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class GroupTypeController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/group/type/";
    @Resource
    private IGroupTypeService groupTypeService;

    @RequestMapping(value = "${managePath}/group/type/list")
    public String index(Model model) {
        List<GroupType> list = groupTypeService.list();
        model.addAttribute("list", list);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping(value = "${managePath}/group/type/add", method = RequestMethod.GET)
    public Object add() {
        return MANAGE_FTL_PATH + "add";
    }

    @RequestMapping(value = "${managePath}/group/type/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(GroupType groupType) {
        return new ResultModel(groupTypeService.save(groupType));
    }


    @RequestMapping(value = "${managePath}/group/type/edit/{id}", method = RequestMethod.GET)
    public Object edit(@PathVariable("id") int id, Model model) {
        GroupType groupType = groupTypeService.findById(id);
        model.addAttribute("groupType", groupType);
        return MANAGE_FTL_PATH + "edit";
    }

    @RequestMapping(value = "${managePath}/group/type/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(GroupType groupType) {
        return new ResultModel(groupTypeService.update(groupType));
    }


    @RequestMapping(value = "${managePath}/group/type/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("id") int id) {
        return new ResultModel(groupTypeService.delete(id));
    }


}
