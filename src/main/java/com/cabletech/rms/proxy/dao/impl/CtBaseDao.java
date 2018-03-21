package com.cabletech.rms.proxy.dao.impl;

import java.util.Collections;
import java.util.List;

import com.cabletech.rms.proxy.dao.BaseDaoSupport;
import com.cabletech.rms.proxy.model.PagedResult;
import com.cabletech.rms.proxy.model.Paging;
import org.apache.ibatis.session.RowBounds;


/**
 * Dao基类
 *
 * @author YuLeyuan
 */
public abstract class CtBaseDao extends BaseDaoSupport {
    /**
     * 生成完成的SQL名
     *
     * @param shortName
     * @return
     */
    protected String fullSqlName(String shortName) {
        return getClass().getName() + "." + shortName;
    }

    /**
     * @param paging paging
     * @return
     */
    private RowBounds asRowBounds(Paging paging) {
        return new RowBounds((paging.getPageNumber() - 1) * paging.getPageSize(), paging.getPageSize());
    }


    /**
     * 分页查询
     *
     * @param countStmt 分页SQL定义
     * @param listStmt  列表SQL定义
     * @param parameter 参数
     * @param paging    分页信息
     * @param <T>       t
     * @return
     */
    protected <T> PagedResult<T> paging(String countStmt, String listStmt, Object parameter, Paging paging) {
        RowBounds bounds = asRowBounds(paging);
        PagedResult<T> result = new PagedResult<T>(paging);
        int total = paging.getTotal();
        if (total < 0) {
            total = getSqlSession().selectOne(countStmt, parameter);
            result.setTotal(total);
            paging.setTotal(total);
        }

        if (total > 0 && paging.getPageSize() > 0 && paging.getPageNumber() > 0) {
            List<T> rows = getSqlSession().selectList(listStmt, parameter, bounds);
            result.setRows(rows);
        } else {
            List<T> emptyList = Collections.emptyList();
            result.setRows(emptyList);
        }
        return result;
    }


    /**
     * 查找记录
     *
     * @param statementName statementName
     * @param parameter     parameter
     * @param <T>           t
     * @return
     */
    protected <T> T find(String statementName, Object parameter) {
        List<T> list = getSqlSession().selectList(statementName, parameter);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
