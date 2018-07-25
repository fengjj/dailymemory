package cn.dailymemory.web.manage;

import cn.dailymemory.core.annotation.Before;
import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.interceptor.AdminLoginInterceptor;
import cn.dailymemory.model.system.Tag;
import cn.dailymemory.service.system.ITagService;
import cn.dailymemory.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by dm on 2017/10/13.
 */
@Controller
@RequestMapping("/${managePath}/tag")
@Before(AdminLoginInterceptor.class)
public class TagController extends BaseController{
    private static final String MANAGE_FTL_PATH = "/manage/tag/";
    @Resource
    private ITagService tagService;

    @RequestMapping("/list/{funcType}")
    public String list(Model model,@PathVariable("funcType") Integer funcType){
        Page page = new Page(request);
        ResultModel resultModel = tagService.listByPage(page,funcType);
        model.addAttribute("funcType",funcType);
        model.addAttribute("model", resultModel);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping("/add/{funcType}")
    public String add(Model model,@PathVariable("funcType") Integer funcType){
        model.addAttribute("funcType",funcType);
        return MANAGE_FTL_PATH + "add";
    }

    @RequestMapping("/save")
    @ResponseBody
    public Object save(Tag tag){
        ResultModel resultModel = tagService.save(tag);
        if(resultModel.getCode() == 0){
            resultModel.setCode(3);
        }
        return resultModel;
    }


    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id){
        Tag tag = tagService.findById(id);
        model.addAttribute("tag",tag);
        return MANAGE_FTL_PATH + "edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(Tag tag){
        if (tag == null){
            return new ResultModel(-1,"参数错误");
        }
        ResultModel resultModel = tagService.update(tag);
        if(resultModel.getCode() == 0){
            resultModel.setCode(3);
        }
        return resultModel;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Object delete(@PathVariable("id") Integer id){
        return tagService.delete(id);
    }

}
