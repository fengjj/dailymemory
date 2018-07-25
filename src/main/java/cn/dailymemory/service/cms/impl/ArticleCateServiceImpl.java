package cn.dailymemory.service.cms.impl;

import cn.dailymemory.core.enums.Messages;
import cn.dailymemory.core.exception.JeeException;
import cn.dailymemory.core.exception.OpeErrorException;
import cn.dailymemory.core.exception.ParamException;
import cn.dailymemory.dao.cms.IArticleDao;
import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.dao.cms.IArticleCateDao;
import cn.dailymemory.model.cms.ArticleCate;
import cn.dailymemory.service.cms.IArticleCateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dm on 16/9/29.
 */
@Service("articleCateService")
public class ArticleCateServiceImpl implements IArticleCateService {

    @Resource
    private IArticleCateDao articleCateDao;
    @Resource
    private IArticleDao articleDao;

    @Override
    public ArticleCate findById(int id) {
        ArticleCate articleCate = articleCateDao.findById(id);
        return articleCate;
    }

    @Override
    public boolean save(ArticleCate articleCate) {
        if(articleCate.getFid() == null){
            articleCate.setFid(0);
        }
        if(articleCate.getFid() != 0){
            ArticleCate fatherArticleCate = this.findById(articleCate.getFid());
            if(fatherArticleCate == null){
                throw new ParamException(Messages.PARENT_CATE_NOT_EXISTS);
            }
            if(fatherArticleCate.getFid() != 0){
                throw new JeeException(Messages.ONLY_TOP_CATE_CAN_ADD);
            }
        }
        if (articleCateDao.save(articleCate) == 0){
            throw new OpeErrorException();
        }
        return true;
    }

    @Override
    public boolean update(ArticleCate articleCate) {
        ArticleCate findArticleCate =this.findById(articleCate.getId());
        if(findArticleCate == null){
            throw new ParamException(Messages.CATE_NOT_EXISTS);
        }
        if(articleCate.getFid() == null){
            articleCate.setFid(0);
        }
        if(articleCate.getFid().intValue() == articleCate.getId().intValue()){
            throw new ParamException(Messages.PARENT_CONNOT_BE_SELF);
        }
        if(articleCate.getFid() != 0){
            ArticleCate fatherArticleCate = this.findById(articleCate.getFid());
            if(fatherArticleCate == null){
                throw new ParamException(Messages.PARENT_CATE_NOT_EXISTS);
            }
            if(fatherArticleCate.getFid() != 0){
                throw new JeeException(Messages.ONLY_TOP_CATE_CAN_ADD);
            }
        }
        findArticleCate.setFid(articleCate.getFid());
        findArticleCate.setName(articleCate.getName());
        findArticleCate.setSort(articleCate.getSort());
        if (articleCateDao.update(articleCate) == 0){
            throw new OpeErrorException();
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        List sonList = this.findListByFid(id);
        if(sonList.size() > 0){
            throw new JeeException(Messages.DELETE_SUB_CATE_FIRST);
        }
        int result = articleCateDao.delete(id);
        if(result == 0){
            throw new OpeErrorException();
        }
        return true;
    }

    @Override
    public List<ArticleCate> list() {
        return articleCateDao.list();
    }

    @Override
    public List<ArticleCate> findListByFid(int fid) {
        return articleCateDao.findListByFid(fid);
    }

}
