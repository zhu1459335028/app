package com.secusoft.ssw.model.viewobject.Response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("已审核表格分页对象")
public class TablePage<T> {
    @ApiModelProperty("当前页的页码")
    private int start;
    @ApiModelProperty("每页的记录数")
    private int pageSize;
    @ApiModelProperty("总的数据数")
    private int totalCount;
    @ApiModelProperty(hidden = true)
    private int limit;
    @ApiModelProperty("返回的当前页的数据")
    private List<T> dataList;
    @ApiModelProperty("开始时间")
    private String startTime;
    @ApiModelProperty("结束时间")
    private String endTime;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getLimit() {
        return (this.start-1)*this.pageSize;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
