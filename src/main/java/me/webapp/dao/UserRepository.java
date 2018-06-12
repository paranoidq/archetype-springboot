package me.webapp.dao;


import me.webapp.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Repository
@Mapper
public interface UserRepository {

    @Select("SELECT * FROM tbl_user")
    List<User> queryAll();


    @Select("SELECT * FROM tbl_user ORDER BY #{orderBy} ${sorting}")
    List<User> query(Map<String, Object> queryParam);


}
