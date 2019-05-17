package com.loong.mnote.common.constants;

/**
 * @author: lhd
 * @date: 2019/1/5 19:20
 */
public interface BaseConstant {

    // 操作类型
    interface CRUD {
        /**
         * 新增
         */
        Integer CREATE = 0;
        /**
         * 查询
         */
        Integer RETRIEVE = 1;
        /**
         * 修改
         */
        Integer UPDATE = 2;
        /**
         * 删除
         */
        Integer DELETE = 3;
    }
}
