package cn.dailymemory.service.common.impl;

import cn.dailymemory.dao.common.ICommonDao;
import cn.dailymemory.service.common.ICommonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dm on 2017/2/6.
 */
@Service("commonService")
public class CommonServiceImpl implements ICommonService {
    @Resource
    private ICommonDao commonDao;

    @Override
    public String getMysqlVsesion() {
        return commonDao.getMysqlVsesion();
    }
}
