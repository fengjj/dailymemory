package cn.dailymemory.service.system;

import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.model.system.Config;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by dm on 16/9/29.
 */
public interface IConfigService {
    List<Config> allList();

    Map<String,String> getConfigToMap();

    String getValue(String key);

    ResultModel update(Map<String,String> params, HttpServletRequest request);
}
