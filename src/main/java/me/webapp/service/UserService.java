package me.webapp.service;

import me.webapp.dao.UseMapper;
import me.webapp.domain.PageParam;
import me.webapp.domain.User;
import me.webapp.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Service
public class UserService implements DomainService<User> {

    @Autowired
    private UseMapper useMapper;


    @Override
    public void add(User item) throws ServiceException {

    }

    @Override
    public void delete(User item) throws ServiceException {

    }

    @Override
    public void update(User item) throws ServiceException {

    }

    @Override
    public List<User> queryAll() throws ServiceException {
        try {
            return useMapper.queryAll();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User query(int id) throws ServiceException {
        try {
            return useMapper.query(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> query(PageParam pageParam) throws ServiceException {
        return null;
    }

    @Override
    public int queryRows() throws ServiceException {
        try {
            return useMapper.queryRows();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
