package me.webapp.service;

import me.webapp.domain.Account;
import me.webapp.exception.ServiceException;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public interface AccountService {

    /**
     * 登录
     * @param email
     * @param password
     * @return
     * @throws ServiceException
     */
    String login(String email, String password) throws ServiceException;

    /**
     * 注销
     * @param token
     * @throws ServiceException
     */
    void logout(String token) throws ServiceException;


    /**
     * 注册
     * @param email
     * @param username
     * @param password
     * @throws ServiceException
     */
    void register(String email, String username, String password) throws ServiceException;


    /**
     * 获取已登录用户
     * @param token
     * @throws ServiceException
     */
    Account getLoginAccount(String token) throws ServiceException;


    /**
     * 重置密码
     * @param email
     * @param username
     * @return
     * @throws ServiceException
     */
    String resetPassword(String email, String username, String newPassword) throws ServiceException;

}
