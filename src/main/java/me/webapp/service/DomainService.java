package me.webapp.service;

import me.webapp.domain.PageParam;
import me.webapp.domain.QueryParam;
import me.webapp.exception.ServiceException;

import java.util.List;

/**
 * @author paranoidq
 * @since 1.0.0
 */
public abstract class DomainService<T> implements AppService {


    /**
     * 新增记录
     * @param item
     * @throws ServiceException
     */
    public void add(T item) throws ServiceException {
        try {
            add0(item);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


    /**
     * 删除记录
     * @param item
     * @throws ServiceException
     */
    public void delete(T item) throws ServiceException {
        try {
            delete0(item);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }



    /**
     * 更新记录
     * @param item
     * @throws ServiceException
     */
    public void update(T item) throws ServiceException {
        try {
            update0(item);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


    /**
     * 获取所有记录
     * @throws ServiceException
     * @return
     */
    public List<T> queryAll() throws ServiceException {
        try {
            return queryAll0();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


    /**
     * 根据查询条件获取记录
     * @param queryParam
     * @throws ServiceException
     * @return
     */
    public List<T> query(QueryParam queryParam) throws ServiceException {
        try {
            return query0(queryParam);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


    /**
     * 根据分页条件获取记录
     * @param pageParam
     * @throws ServiceException
     * @return
     */
    public List<T> query(PageParam pageParam) throws ServiceException {
        try {
            return query0(pageParam);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


    /**
     * 根据查询条件和分页条件获取记录
     * @param queryParam
     * @param pageParam
     * @throws ServiceException
     * @return
     */
    public List<T> query(QueryParam queryParam, PageParam pageParam) throws ServiceException {
        try {
            return query0(queryParam, pageParam);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 获取记录记录数
     * @throws ServiceException
     * @return
     */
    public int queryRows() throws ServiceException {
        try {
            return queryRows0();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


    /**
     * 根据查询条件获取记录数
     * @param queryParam
     * @throws ServiceException
     * @return
     */
    public int queryRows(QueryParam queryParam) throws ServiceException {
        try {
            return queryRows0(queryParam);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


    protected abstract void add0(T item) throws Exception;
    protected abstract void delete0(T item) throws Exception;
    protected abstract void update0(T item) throws Exception;
    protected abstract List<T> queryAll0() throws Exception;
    protected abstract List<T> query0(QueryParam queryParam) throws Exception;
    protected abstract List<T> query0(PageParam pageParam) throws Exception;
    protected abstract List<T> query0(QueryParam queryParam, PageParam pageParam) throws Exception;
    protected abstract int queryRows0() throws Exception;
    protected abstract int queryRows0(QueryParam queryParam) throws Exception;

}
