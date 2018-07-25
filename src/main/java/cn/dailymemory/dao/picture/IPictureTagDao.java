package cn.dailymemory.dao.picture;

import cn.dailymemory.model.picture.PictureTag;

public interface IPictureTagDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PictureTag record);

    int insertSelective(PictureTag record);

    PictureTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PictureTag record);

    int updateByPrimaryKey(PictureTag record);
}