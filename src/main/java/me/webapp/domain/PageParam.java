package me.webapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
public class PageParam {

    @Getter
    private int pageNo;
    @Getter
    private int pageSize;
    @Getter
    private int maxPageNo;
}
