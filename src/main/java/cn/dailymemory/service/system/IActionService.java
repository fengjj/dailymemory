package cn.dailymemory.service.system;

import cn.dailymemory.model.system.Action;

import java.util.List;

/**
 * Created by dm on 2017/2/14.
 */
public interface IActionService {

    List<Action> list();

    Action findById(Integer id);

    boolean update(Action action);

    boolean isenable(Integer id);

    boolean canuse(Integer id);
}
