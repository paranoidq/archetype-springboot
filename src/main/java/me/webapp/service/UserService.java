package me.webapp.service;

import me.webapp.dao.UserRepository;
import me.webapp.domain.PageParam;
import me.webapp.domain.QueryParam;
import me.webapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.List;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Service
public class UserService extends DomainService<User> {

    @Autowired
    UserRepository userRepository;


    @Override
    protected void add0(User item) throws Exception {

    }

    @Override
    protected void delete0(User item) throws Exception {

    }

    @Override
    protected void update0(User item) throws Exception {

    }

    @Override
    protected List<User> queryAll0() throws Exception {
        return userRepository.queryAll();
    }

    @Override
    protected List<User> query0(QueryParam queryParam) throws Exception {
        return userRepository.query(queryParam.getQueryParams());
    }

    @Override
    protected List<User> query0(PageParam pageParam) throws Exception {
        return null;
    }

    @Override
    protected List<User> query0(QueryParam queryParam, PageParam pageParam) throws Exception {
        return null;
    }

    @Override
    protected int queryRows0() throws Exception {
        return 0;
    }

    @Override
    protected int queryRows0(QueryParam queryParam) throws Exception {
        return 0;
    }
}
