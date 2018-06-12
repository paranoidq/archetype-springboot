package me.webapp.domain;

import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * SQL查询条件实体类
 * SQL查询条件通过key-value的形式放入该类的对象中，提供给SQL语句
 *
 * @author paranoidq
 * @since 1.0.0
 */
public class QueryParam {

    private Map<String, Object> queryParams;

    private interface ReversedKeys {
        String PARAM_ID = "id";
        String PARAM_SORTING = "sorting";
        String PARAM_ORDER_BY = "orderBy";
    }


    public QueryParam(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }

    public QueryParam() {
        this.queryParams = new HashMap<>();
    }

    public QueryParam(String id, String order) {
        this();
        put(ReversedKeys.PARAM_ID, id);
        put(ReversedKeys.PARAM_SORTING, order);
    }


    /**
     * 添加条件参数
     * 支持fluentAPI形式调用
     * @param key
     * @param value
     * @return
     */
    public QueryParam put(String key, Object value) {
        this.queryParams.put(key, value);
        return this;
    }

    /**
     * 移除条件参数
     * @param key
     */
    public QueryParam remove(String key) {
        this.queryParams.remove(key);
        return this;
    }

    /**
     * 覆盖条件参数
     * @param key
     * @param value
     */
    public QueryParam replace(String key, Object value) {
        this.put(key, value);
        return this;
    }

    /**
     * 获取查询参数集合
     * @return
     */
    public Map<String, Object> getQueryParams() {
        return Collections.unmodifiableMap(this.queryParams);
    }


    /**
     * 快捷方法：设置查询id
     * @param id
     * @return
     */
    public QueryParam id(String id) {
        put(ReversedKeys.PARAM_ID, id);
        return this;
    }

    /**
     * 快捷方法：设置查询顺序：升序、降序
     * @param sorting
     * @return
     */
    public QueryParam sorting(Sorting sorting) {
        put(ReversedKeys.PARAM_SORTING, sorting);
        return this;
    }


    /**
     * 快捷方法：设置orderBy参数
     * @param columns
     * @return
     */
    public QueryParam orderBy(String... columns) {
        put(ReversedKeys.PARAM_ORDER_BY, StringUtils.arrayToCommaDelimitedString(columns));
        return this;
    }


    /**
     * SQL查询排除参数
     */
    public enum Sorting {
        DESC,
        ASC
    }


    public static void main(String[] args) {
        // test
        QueryParam queryParam = new QueryParam();
        queryParam.orderBy("a", "b", "c");
        System.out.println(queryParam.getQueryParams().get("orderBy"));

    }

}
