package com.loong.mnote.common.util;

import com.loong.mnote.common.domain.BaseQuery;
import com.loong.mnote.common.domain.Pager;

import java.util.List;

/**
 * @author: lhd
 * @date: 2019/1/7 17:51
 */
public class PagerUtil {

    public static <T> Pager<T> wrap(BaseQuery query, List<T> data) {
        Pager<T> result = new Pager();
        result.setDataList(data);
        result.setPageSize(query.getPageSize());
        result.setPageIndex(query.getPageIndex());
        return result;
    }

    public static <T> Pager<T> wrap(BaseQuery query, List<T> data, long total) {
        Pager<T> result = wrap(query, data);
        result.setTotal(total);
        return result;
    }
}
