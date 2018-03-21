package com.cabletech.common.util;

import com.cabletech.rms.proxy.model.Paging;

import java.util.List;


/**
 * @author YuLeyuan
 *
 */
public class PagingListUtil {
    /**
     * 对List进行分页
     * @param list list
     * @param paging paging
     * @param <T> t
     * @return
     */
    public static <T> List<T>  paging(List<T> list,Paging paging){
    	int num=paging.getPageNumber();
    	
    	int size=paging.getPageSize();
    	int fromIndex=(num-1)*size;
    	int toIndex=(num)*size;
    	if(toIndex>list.size()){
    		toIndex=list.size();
    	}
    	return list.subList(fromIndex, toIndex);
    }
}
