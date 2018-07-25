package cn.dailymemory.listener;

import cn.dailymemory.core.utils.Const;
import cn.dailymemory.core.utils.JeesnsConfig;
import cn.dailymemory.core.utils.SpringContextHolder;
import cn.dailymemory.model.system.Config;
import cn.dailymemory.service.system.IConfigService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by dm on 2017/5/25.
 */
public class InitListener implements ServletContextListener {

    public InitListener() {
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Const.PROJECT_PATH = sce.getServletContext().getContextPath();
            sce.getServletContext().setAttribute("basePath", Const.PROJECT_PATH);
            JeesnsConfig jeesnsConfig = SpringContextHolder.getBean("jeesnsConfig");
            sce.getServletContext().setAttribute("jeesnsConfig",jeesnsConfig);
            String frontTemplate = jeesnsConfig.getFrontTemplate();
            sce.getServletContext().setAttribute("frontTemplate",frontTemplate);
            String managePath = Const.PROJECT_PATH + "/" + jeesnsConfig.getManagePath();
            Const.GROUP_PATH = Const.PROJECT_PATH + "/" + jeesnsConfig.getGroupPath();
            Const.WEIBO_PATH = Const.PROJECT_PATH + "/" + jeesnsConfig.getWeiboPath();
            sce.getServletContext().setAttribute("managePath",managePath);
            sce.getServletContext().setAttribute("groupPath",Const.GROUP_PATH);
            sce.getServletContext().setAttribute("weiboPath",Const.WEIBO_PATH);
            IConfigService configService = SpringContextHolder.getBean("configService");
            List<Config> configList = configService.allList();
            for (Config config : configList) {
                sce.getServletContext().setAttribute(config.getJkey().toUpperCase(),config.getJvalue());
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
