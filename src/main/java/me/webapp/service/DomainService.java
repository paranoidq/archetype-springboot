package me.webapp.service;

import me.webapp.domain.PageParam;
import me.webapp.exception.ServiceException;

import java.util.List;

/**
 *
 * Domain操作service的基类，提供基础的功能，子类根据需要增加更为复杂的查询功能
 *
 * TODO: 尝试了通用化（例如将参数统一传递，然后到SQL层进行判断处理），但是没有成功
 *
 * @author paranoidq
 * @since 1.0.0
 */
public interface DomainService<T> extends AppService {


    /**
     * 新增记录
     * @param item
     * @throws ServiceException
     */
    void add(T item) throws ServiceException;


    /**
     * 删除记录
     * @param item
     * @throws ServiceException
     */
    void delete(T item) throws ServiceException;



    /**
     * 更新记录
     * @param item
     * @throws ServiceException
     */
    void update(T item) throws ServiceException;


    /**
     * 获取所有记录
     * @throws ServiceException
     * @return
     */
    List<T> queryAll() throws ServiceException;


    /**
     * 根据id获取记录
     * @param id
     * @return
     * @throws ServiceException
     */
    T query(int id) throws ServiceException;


    /**
     * 根据分页条件获取记录
     * @param pageParam
     * @throws ServiceException
     * @return
     */
    List<T> query(PageParam pageParam) throws ServiceException;


    /**
     * 获取记录记录数
     * @throws ServiceException
     * @return
     */
    int queryRows() throws ServiceException;

}
