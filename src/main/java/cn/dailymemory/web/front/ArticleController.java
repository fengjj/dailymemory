package cn.dailymemory.web.front;

import cn.dailymemory.common.utils.MemberUtil;
import cn.dailymemory.interceptor.UserLoginInterceptor;
import cn.dailymemory.service.common.IArchiveService;
import cn.dailymemory.core.annotation.Before;
import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.core.utils.*;
import cn.dailymemory.web.common.BaseController;
import cn.dailymemory.model.cms.ArticleCate;
import cn.dailymemory.model.cms.Article;
import cn.dailymemory.service.cms.IArticleCateService;
import cn.dailymemory.service.cms.IArticleCommentService;
import cn.dailymemory.service.cms.IArticleService;
import cn.dailymemory.model.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 前台文章Controller
 * Created by dm on 16/9/29.
 */
@Controller("frontArticleController")
@RequestMapping("/article")
public class ArticleController extends BaseController {
    @Resource
    private JeesnsConfig jeesnsConfig;
    @Resource
    private IArticleCateService articleCateService;
    @Resource
    private IArticleService articleService;
    @Resource
    private IArchiveService archiveService;
    @Resource
    private IArticleCommentService articleCommentService;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(String key, @RequestParam(value = "cid",defaultValue = "0",required = false) Integer cid,
                       @RequestParam(value = "memberId",defaultValue = "0",required = false) Integer memberId, Model model) {
        if (StringUtils.isNotEmpty(key)){
            try {
                key = new String(key.getBytes("iso-8859-1"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        Page page = new Page(request);
        ResultModel resultModel = articleService.listByPage(page,key,cid,1,memberId);
        model.addAttribute("model", resultModel);
        List<ArticleCate> articleCateList = articleCateService.list();
        model.addAttribute("articleCateList",articleCateList);
        ArticleCate articleCate = articleCateService.findById(cid);
        model.addAttribute("articleCate",articleCate);
        return jeesnsConfig.getFrontTemplate() + "/cms/list";
    }

    @RequestMapping(value="/detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        Article article = articleService.findById(id,loginMember);
        //文章不存在或者访问未审核的文章，跳到错误页面，提示文章不存在
        if(article == null || article.getStatus() == 0){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1009,Const.INDEX_ERROR_FTL_PATH);
        }
        //更新文章访问次数
        archiveService.updateViewCount(article.getArchiveId());
        model.addAttribute("article",article);
        List<ArticleCate> articleCateList = articleCateService.list();
        model.addAttribute("articleCateList",articleCateList);
        model.addAttribute("loginUser",loginMember);
        return jeesnsConfig.getFrontTemplate() + "/cms/detail";
    }

    @RequestMapping(value="/add",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String add(Model model) {
        List<ArticleCate> cateList = articleCateService.list();
        model.addAttribute("cateList",cateList);
        String judgeLoginJump = MemberUtil.judgeLoginJump(request, RedirectUrlUtil.ARTICLE_ADD);
        if(StringUtils.isNotEmpty(judgeLoginJump)){
            return judgeLoginJump;
        }
        return jeesnsConfig.getFrontTemplate() + "/cms/add";
    }

    @RequestMapping(value="/save",method = RequestMethod.POST)
    @ResponseBody
    public Object save(@Valid Article article, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResultModel(-1,getErrorMessages(bindingResult));
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResultModel(-1,"请先登录");
        }
        ResultModel resultModel = articleService.save(loginMember,article);
        if(resultModel.getCode() == 0){
            resultModel.setCode(2);
            //文章需要审核就跳转到列表页面
            if(article.getStatus() == 0){
                resultModel.setUrl(request.getContextPath()+"/article/list");
            }else {
                resultModel.setUrl(request.getContextPath()+"/article/detail/"+article.getId());
            }
        }
        return resultModel;
    }

    @RequestMapping(value="/edit/{id}",method = RequestMethod.GET)
    @Before(UserLoginInterceptor.class)
    public String edit(@PathVariable("id") int id, Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        String judgeLoginJump = MemberUtil.judgeLoginJump(request, RedirectUrlUtil.ARTICLE_EDIT+"/"+id);
        if(StringUtils.isNotEmpty(judgeLoginJump)){
            return judgeLoginJump;
        }
        Article article = articleService.findById(id,loginMember);
        if(article.getMemberId().intValue() != loginMember.getId().intValue()){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1001,Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("article",article);
        List<ArticleCate> cateList = articleCateService.list();
        model.addAttribute("cateList",cateList);
        model.addAttribute("loginUser", loginMember);
        return jeesnsConfig.getFrontTemplate() + "/cms/edit";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(@Valid Article article,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            new ResultModel(-1,getErrorMessages(bindingResult));
        }
        if(article.getId() == null){
            return new ResultModel(-2);
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        ResultModel resultModel = articleService.update(loginMember,article);
        if(resultModel.getCode() == 0){
            resultModel.setCode(2);
            resultModel.setUrl(request.getContextPath() + "/article/detail/"+article.getId());
        }
        return resultModel;
    }

    /**
     * 评论文章
     * @param articleId
     * @param content
     * @return
     */
    @RequestMapping(value="/comment/{articleId}",method = RequestMethod.POST)
    @ResponseBody
    public Object comment(@PathVariable("articleId") Integer articleId, String content){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResultModel(-1,"请先登录");
        }
        return articleCommentService.save(loginMember,content,articleId);
    }


    @RequestMapping(value="/commentList/{articleId}.json",method = RequestMethod.GET)
    @ResponseBody
    public Object commentList(@PathVariable("articleId") Integer articleId){
        Page page = new Page(request);
        if(articleId == null){
            articleId = 0;
        }
        return articleCommentService.listByArticle(page,articleId);
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("id") int id){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResultModel(-1,"请先登录");
        }
        if(loginMember.getIsAdmin() == 0){
            return new ResultModel(-1,"权限不足");
        }
        ResultModel resultModel = articleService.delete(loginMember,id);
        if(resultModel.getCode() > 0){
            resultModel.setCode(2);
            resultModel.setUrl(request.getContextPath() + "/article/list");
        }
        return resultModel;
    }


    /**
     * 文章、喜欢
     * @param id
     * @return
     */
    @RequestMapping(value="/favor/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object favor(@PathVariable("id") Integer id){
        Member loginMember = MemberUtil.getLoginMember(request);
        if(loginMember == null){
            return new ResultModel(-1,"请先登录");
        }
        if(id == null) {
            return new ResultModel(-1, "非法操作");
        }
        return articleService.favor(loginMember,id);
    }
}