package cn.dailymemory.web.manage;

import cn.dailymemory.common.utils.MemberUtil;
import cn.dailymemory.core.annotation.Before;
import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.interceptor.AdminLoginInterceptor;
import cn.dailymemory.model.member.Member;
import cn.dailymemory.service.weibo.IWeiboService;
import cn.dailymemory.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by dm on 2016/11/22.
 */
@Controller("mamageWeiboController")
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class WeiboController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/weibo/";
    @Resource
    private IWeiboService weiboService;

    @RequestMapping("${managePath}/weibo/index")
    @Before(AdminLoginInterceptor.class)
    public String index(@RequestParam(value = "key",required = false,defaultValue = "") String key, Model model) {
        Page page = new Page(request);
        ResultModel resultModel = weiboService.listByPage(page,0,0,key);
        model.addAttribute("model", resultModel);
        return MANAGE_FTL_PATH + "index";
    }

    @RequestMapping(value = "${managePath}/weibo/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("id") int id) {
        Member loginMember = MemberUtil.getLoginMember(request);
        return weiboService.delete(request, loginMember,id);
    }
}
