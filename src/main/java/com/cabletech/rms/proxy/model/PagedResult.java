package com.cabletech.rms.proxy.model;

import java.util.List;

/**
 * Created by liyong on 2018/3/19.
 */
public class PagedResult<T> extends Paging {

    /**
     * 无参数构造函数
     */
    public PagedResult() {

    }

    /**
     * 构造函数
     * @param size 页大小
     * @param number 页数
     */
    public PagedResult(int size, int number) {
        super(size, number);
    }

    /**
     * 构造函数
     * @param paging Paging
     */
    public PagedResult(Paging paging) {
        super(paging.getPageSize(), paging.getPageNumber());
        if(paging.getTotal()>=0){
            setTotal(paging.getTotal());
        }

    }


    /**
     * 当前页机内的结果集
     */
    private List<T> rows;


    /**
     * @return the rows
     */
    public List<T> getRows() {
        return rows;
    }
    /**
     * @param rows the rows to set
     */
    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}