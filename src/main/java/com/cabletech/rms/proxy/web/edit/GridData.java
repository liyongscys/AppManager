package com.cabletech.rms.proxy.web.edit;

import java.util.List;

/**
 * @author YuLeyuan
 *
 */
public class GridData<T> {
	private int total;
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
		if(rows!=null){
			total=rows.size();
		}
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
}
