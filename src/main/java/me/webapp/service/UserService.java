package me.webapp.service;

import me.webapp.dao.UserRepository;
import me.webapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserRepository repository;

}
