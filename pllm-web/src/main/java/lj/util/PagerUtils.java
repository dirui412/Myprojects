package lj.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import lj.model.base.QueryContext;

/**
 * 分页工具类
 * 
 * @author samlv
 *
 */
public class PagerUtils {
	public static final String REQ_PAGE_DRAW = "draw";
	public static final String REQ_START_ROW = "start";
	public static final String REQ_PAGE_SIZE = "length";

	/**
	 * 从用户请求中解析分页请求
	 * 
	 * @param req
	 */
	public static void parseRequest(HttpServletRequest req) {
		Enumeration<String> paramNames = req.getParameterNames();
		try {
			int draw = Integer.parseInt(req.getParameter(REQ_PAGE_DRAW));
			int start = Integer.parseInt(req.getParameter(REQ_START_ROW));
			int length = Integer.parseInt(req.getParameter(REQ_PAGE_SIZE));
			int pageIndex = start / length;
			int sortFieldIndex = -1;
			String sortType = "asc";
			while (paramNames.hasMoreElements()) {
				String paramName = paramNames.nextElement();
				String paramValue = req.getParameter(paramName);
				// 解析排序field index
				if (paramName.equals("order[0][column]")==true) {
					sortFieldIndex = Integer.parseInt(paramValue);
				}
				if (paramName.equals("order[0][dir]")==true) {
					sortType = paramValue;
				}
			}
			
			String sortField = StringUtils.STR_EMPTY;
			if (sortFieldIndex >= 0) {
				String sortParamName = "columns[" + sortFieldIndex + "][data]";
				if (StringUtils.isNullOrEmpty(req.getParameter(sortParamName)) == false)
					sortField = req.getParameter(sortParamName);
			}
				
			QueryContext.setPageDraw(draw);
			QueryContext.setPageIndex(pageIndex);
			QueryContext.setPageSize(length);
			QueryContext.setSortField(sortField);
			QueryContext.setSortType(sortType);
			System.out.println("-----PagerUtils.parseRequest pageIndex:" + pageIndex + ",pageSize:" + length
					+ ",sortField:" + sortField + ",sortType:" + sortType);
		} catch (Exception e) {
			LogUtils.logError("PageUtils.parseRequest", e);
		}
	}

}
