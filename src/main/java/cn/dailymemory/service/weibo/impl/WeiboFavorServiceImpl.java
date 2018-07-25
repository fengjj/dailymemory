package cn.dailymemory.service.weibo.impl;

import cn.dailymemory.dao.weibo.IWeiboFavorDao;
import cn.dailymemory.model.weibo.WeiboFavor;
import cn.dailymemory.service.weibo.IWeiboFavorService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by dm on 2017/2/8.
 */
@Service("weiboFavorService")
public class WeiboFavorServiceImpl implements IWeiboFavorService {
    @Resource
    private IWeiboFavorDao weiboFavorDao;


    @Override
    public WeiboFavor find(Integer weiboId, Integer memberId) {
        return weiboFavorDao.find(weiboId,memberId);
    }

    @Override
    public void save(Integer weiboId, Integer memberId) {
        weiboFavorDao.save(weiboId,memberId);
    }

    @Override
    public void delete(Integer weiboId, Integer memberId) {
        weiboFavorDao.delete(weiboId,memberId);
    }
}
