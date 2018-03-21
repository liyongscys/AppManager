package com.cabletech.rms.proxy.model;

/**
 * Created by liyong on 2018/3/19.
 */
public class Paging {
    /**
     * 分页大小
     */
    private int pageSize;
    /**
     * 当前页数
     */
    private int pageNumber;

    /**
     * 结果集总数
     */
    private int total = -1;

    /**
     * 无参数构造函数
     */
    public Paging() {

    }


    /**
     * 构造函数
     * @param size 页大小
     * @param number 页数
     */
    public Paging(int size, int number) {
        this.pageSize = size;
        this.pageNumber = number;

    }

    /**
     * 构造函数
     * @param size 页大小
     */
    public Paging(int size) {
        this.pageSize = size;
        this.pageNumber = 1;

    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }
    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    /**
     * @return the pageNumber
     */
    public int getPageNumber() {
        return pageNumber;
    }
    /**
     * @param pageNumber the pageNumber to set
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }
    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 翻到下一页
     * @return 下一页
     */
    public int nextPage() {
        pageNumber += 1;
        return pageNumber;
    }

    /**
     * 重置
     */
    public void reset() {
        pageNumber = 0;
        total = -1;
    }
}
