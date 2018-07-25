package cn.dailymemory.service.common.impl;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.core.enums.Messages;
import cn.dailymemory.core.exception.NotFountException;
import cn.dailymemory.core.exception.OpeErrorException;
import cn.dailymemory.core.model.Page;
import cn.dailymemory.dao.common.IAdsDao;
import cn.dailymemory.model.common.Ads;
import cn.dailymemory.service.common.IAdsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dm on 2017/9/07.
 */
@Service("adsService")
public class AdsServiceImpl implements IAdsService {
    @Resource
    private IAdsDao adsDao;

    /**
     * 保存广告信息
     *
     * @param ads
     * @return
     */
    @Override
    public boolean save(Ads ads) {
        if (adsDao.save(ads) == 0) {
            throw new OpeErrorException();
        }
        return true;
    }

    @Override
    public ResultModel listByPage(Page page) {
        List<Ads> list = adsDao.listByPage(page);
        ResultModel model = new ResultModel(0, page);
        model.setData(list);
        return model;
    }

    @Override
    public boolean update(Ads ads) {
        Ads findAds = this.findById(ads.getId());
        if (findAds == null){
            throw new NotFountException(Messages.AD_NOT_EXISTS);
        }
        if (adsDao.update(ads) == 0) {
            throw new OpeErrorException();
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        if (adsDao.delete(id) == 0) {
            throw new OpeErrorException();
        }
        return true;
    }

    @Override
    public Ads findById(Integer id) {
        return adsDao.findById(id);
    }

    @Override
    public boolean enable(Integer id) {
        if (adsDao.enable(id) == 0){
            throw new OpeErrorException();
        }
        return true;
    }
}
