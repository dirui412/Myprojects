package lj.model.base;

import java.util.List;

/*
 * 分页对象
 */
public class Pager<T> {
	private int pageSize;
	private int pageIndex;
	private List<T> datas;
	private long rowTotal;

	public long getRowTotal() {
		return rowTotal;
	}

	public void setRowTotal(long rowTotal) {
		this.rowTotal = rowTotal;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public Pager(long rowTotal, int pageSize, int pageIndex, List<T> datas) {
		super();
		this.rowTotal = rowTotal;
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
		this.datas = datas;
	}

	public Pager() {
		super();
		// TODO Auto-generated constructor stub
	}

}
