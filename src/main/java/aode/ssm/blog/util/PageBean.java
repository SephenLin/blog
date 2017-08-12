package aode.ssm.blog.util;

import java.util.Date;
import java.util.List;

/**
 * Created by 林进华 on 2017/7/26.
 */
public class PageBean {
    // 前台传进来的
    private int nowPage; // 当前页数
    private int size; // 每页显示的数据的数量
    private String searchText; // 查询的文本
    private Date selectStartTime; // 文章查询开始日期
    private Date selectEndTime; // 文章查询结束日期
    private String selectType; // 文章查询的文章类型
    /**
     * 排序字段
     */
    private String sort="";
    /**
     * 排序方法 ASC OR DESC
     */
    private String order="ASC";

    // 查询数据库
    private int recordCount; // 总记录数
    private List nowList; // 本页的数据列表

    // 通过计算得到数据，只需要接受前面几个
    private int pageCount; // 总页数
    private int startPageIndex; // 页码列表的开始索引
    private int endPageIndex; // 页码列表的结束索引

    public PageBean init(int size, int nowPage, int recordCount, List recordList) {
        this.nowPage = nowPage;
        this.size = size;
        this.recordCount = recordCount;
        this.pageCount = recordCount % size < 1 ? recordCount / size : (recordCount / size) + 1;
        this.pageCount = this.pageCount < 1 ? 1 : this.pageCount;
        this.startPageIndex = nowPage - 3;
        this.endPageIndex = nowPage + 3;
        if(pageCount <= 7) {
            this.endPageIndex = pageCount;
            this.startPageIndex = 1;
        }else {
            if(startPageIndex <= 1) {
                this.endPageIndex = 7;
            }
            if(endPageIndex > pageCount) {
                this.startPageIndex = pageCount - 7 + 1;
                this.endPageIndex = pageCount;
            }
        }
        if(nowPage > endPageIndex) {
            this.nowPage = endPageIndex;
        }
        return this;
    }

    public PageBean(int size, int nowPage, int recordCount, List recordList) {
        this.nowPage = nowPage;
        this.size = size;
        this.recordCount = recordCount;
        this.pageCount = this.recordCount % size == 0 ? recordCount / size : (this.recordCount / size) + 1;
        this.startPageIndex = nowPage - 3;
        this.endPageIndex = nowPage + 3;
        if(pageCount <= 7) {
            endPageIndex = pageCount;
        }else {
            if(startPageIndex <= 1) {
                endPageIndex = 7;
            }
            if(endPageIndex > pageCount) {
                startPageIndex = pageCount - 7 + 1;
                endPageIndex = pageCount;
            }
        }
    }

    public PageBean() {
    }

    public Date getSelectStartTime() {
        return selectStartTime;
    }

    public void setSelectStartTime(Date selectStartTime) {
        this.selectStartTime = selectStartTime;
    }

    public Date getSelectEndTime() {
        return selectEndTime;
    }

    public void setSelectEndTime(Date selectEndTime) {
        this.selectEndTime = selectEndTime;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public List getNowList() {
        return nowList;
    }

    public void setNowList(List nowList) {
        this.nowList = nowList;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getStartPageIndex() {
        return startPageIndex;
    }

    public void setStartPageIndex(int startPageIndex) {
        this.startPageIndex = startPageIndex;
    }

    public int getEndPageIndex() {
        return endPageIndex;
    }

    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "nowPage=" + nowPage +
                ", size=" + size +
                ", searchText='" + searchText + '\'' +
                ", selectStartTime=" + selectStartTime +
                ", selectEndTime=" + selectEndTime +
                ", selectType='" + selectType + '\'' +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                ", recordCount=" + recordCount +
                ", nowList=" + nowList +
                ", pageCount=" + pageCount +
                ", startPageIndex=" + startPageIndex +
                ", endPageIndex=" + endPageIndex +
                '}';
    }
}
