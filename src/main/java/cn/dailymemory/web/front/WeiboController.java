package cn.dailymemory.web.front;

import cn.dailymemory.common.utils.MemberUtil;
import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.core.utils.Const;
import cn.dailymemory.core.utils.ErrorUtil;
import cn.dailymemory.core.utils.JeesnsConfig;
import cn.dailymemory.web.common.BaseController;
import cn.dailymemory.model.member.Member;
import cn.dailymemory.model.weibo.Weibo;
import cn.dailymemory.service.weibo.IWeiboCommentService;
import cn.dailymemory.service.weibo.IWeiboService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dm on 2016/12/20.
 */
@Controller("frontWeiboController")
@RequestMapping("/${weiboPath}")
public class WeiboController extends BaseController {
    @Resource
    private IWeiboService weiboService;
    @Resource
    private IWeiboCommentService weiboCommentService;
    @Resource
    private JeesnsConfig jeesnsConfig;

    @RequestMapping(value = "/publish",method = RequestMethod.POST)
    @ResponseBody
    public ResultModel publish(String content, String pictures){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResultModel(-1,"请先登录");
        }
        return weiboService.save(request, loginMember,content, pictures);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(@RequestParam(value = "key",required = false,defaultValue = "") String key, Model model){
        Page page = new Page(request);
        Member loginMember = MemberUtil.getLoginMember(request);
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        ResultModel resultModel = weiboService.listByPage(page,0,loginMemberId,key);
        model.addAttribute("model", resultModel);
        List<Weibo> hotList = weiboService.hotList(loginMemberId);
        model.addAttribute("hotList",hotList);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/weibo/list";
    }

    @RequestMapping(value = "/detail/{weiboId}",method = RequestMethod.GET)
    public String detail(@PathVariable("weiboId") Integer weiboId, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        Weibo weibo = weiboService.findById(weiboId,loginMemberId);
        if(weibo == null){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,1007, Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("weibo",weibo);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/weibo/detail";
    }

    @RequestMapping(value="/delete/{weiboId}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("weiboId") Integer weiboId){
        Member loginMember = MemberUtil.getLoginMember(request);
        ResultModel resultModel = weiboService.userDelete(request, loginMember,weiboId);
        if(resultModel.getCode() >= 0){
            resultModel.setCode(2);
            resultModel.setUrl(Const.WEIBO_PATH + "/list");
        }
        return resultModel;
    }


    @RequestMapping(value="/comment/{weiboId}",method = RequestMethod.POST)
    @ResponseBody
    public Object comment(@PathVariable("weiboId") Integer weiboId, String content, Integer weiboCommentId){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResultModel(-1,"请先登录");
        }
        return weiboCommentService.save(loginMember,content,weiboId,weiboCommentId);
    }

    @RequestMapping(value="/commentList/{weiboId}.json",method = RequestMethod.GET)
    @ResponseBody
    public Object commentList(@PathVariable("weiboId") Integer weiboId){
        Page page = new Page(request);
        if(weiboId == null){
            weiboId = 0;
        }
        return weiboCommentService.listByWeibo(page,weiboId);
    }

    @RequestMapping(value="/favor/{weiboId}",method = RequestMethod.GET)
    @ResponseBody
    public Object favor(@PathVariable("weiboId") Integer weiboId){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResultModel(-1,"请先登录");
        }
        if(weiboId == null) {
            return new ResultModel(-1, "非法操作");
        }
        return weiboService.favor(loginMember,weiboId);
    }
}
