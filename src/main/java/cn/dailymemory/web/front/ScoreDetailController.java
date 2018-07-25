package cn.dailymemory.web.front;

import cn.dailymemory.common.utils.MemberUtil;
import cn.dailymemory.interceptor.UserLoginInterceptor;
import cn.dailymemory.core.annotation.Before;
import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.web.common.BaseController;
import cn.dailymemory.model.member.Member;
import cn.dailymemory.model.member.ScoreDetail;
import cn.dailymemory.service.member.IScoreDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by dm on 2017/4/7.
 */
@Controller("scoreDetailFrontController")
@RequestMapping("/member/scoreDetail")
@Before(UserLoginInterceptor.class)
public class ScoreDetailController extends BaseController {
    private static final String INDEX_FTL_PATH = "/member/scoreDetail/";
    @Resource
    private IScoreDetailService scoreDetailService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        Page page = new Page(request);
        ResultModel<ScoreDetail> resultModel = scoreDetailService.list(page,loginMember.getId());
        model.addAttribute("model", resultModel);
        return INDEX_FTL_PATH + "list";
    }
}
