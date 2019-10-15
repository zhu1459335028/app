package com.secusoft.ssw.model.viewobject.Response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName PatrolReportDataCountVO
 * @Author jcyao
 * @Description
 * @Date 2018/11/29 13:15
 * @Version 1.0
 */
@ApiModel("运营报告违规项数据统计结果集")
public class PatrolReportDataCountVO {

    @ApiModelProperty("顶层数据")
    private List<Top> tops;
    @ApiModelProperty("下钻数据")
    private List<Down> downs;

    public class Top {
        private String name;
        private int y;
        private int drilldown;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getDrilldown() {
            return drilldown;
        }

        public void setDrilldown(int drilldown) {
            this.drilldown = drilldown;
        }
    }

    public class Down {
        private String name;
        private int id;
        private List<List<Object>> data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<List<Object>> getData() {
            return data;
        }

        public void setData(List<List<Object>> data) {
            this.data = data;
        }
    }

    public List<Top> getTops() {
        return tops;
    }

    public void setTops(List<Top> tops) {
        this.tops = tops;
    }

    public List<Down> getDowns() {
        return downs;
    }

    public void setDowns(List<Down> downs) {
        this.downs = downs;
    }
}
