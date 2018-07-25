package cn.dailymemory.service.common;

import cn.dailymemory.model.common.Archive;
import cn.dailymemory.core.dto.ResultModel;
import cn.dailymemory.model.member.Member;


/**
 * Created by dm on 2016/10/14.
 */
public interface IArchiveService {

    Archive findByArchiveId(int id);

    boolean save(Member member, Archive archive);

    boolean update(Member member, Archive archive);

    boolean delete(int id);

    void updateViewCount(int id);

    ResultModel favor(Member loginMember, int archiveId);
}
