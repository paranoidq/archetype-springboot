package me.webapp.dao;


import me.webapp.domain.PageParam;
import me.webapp.domain.QueryParam;
import me.webapp.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Repository
@Mapper
public interface UserRepository {

    @Select("SELECT * FROM tbl_user")
    List<User> queryAll() throws Exception;

}
