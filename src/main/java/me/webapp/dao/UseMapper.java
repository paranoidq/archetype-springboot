package me.webapp.dao;


import me.webapp.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Repository
public interface UseMapper {

    List<User> queryAll();

    User query(int id);

    int queryRows();

    void add(User user);

    void delete(User user);

    void update(User user);

}
