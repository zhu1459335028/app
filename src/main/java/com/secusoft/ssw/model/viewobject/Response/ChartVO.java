package com.secusoft.ssw.model.viewobject.Response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName ChartVO
 * @Author jcyao
 * @Description
 * @Date 2018/9/6 14:42
 * @Version 1.0
 */
@ApiModel(value="统计图结果集")
public class ChartVO {

    @ApiModelProperty("x轴数据")
    private List<String> x;

    @ApiModelProperty("y轴数据")
    private List<YValue> y;

    public class YValue {

        @ApiModelProperty("名称")
        private String name;
        @ApiModelProperty("数据")
        private List<Integer> data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Integer> getData() {
            return data;
        }

        public void setData(List<Integer> data) {
            this.data = data;
        }

    }

    public List<String> getX() {
        return x;
    }

    public void setX(List<String> x) {
        this.x = x;
    }

    public List<YValue> getY() {
        return y;
    }

    public void setY(List<YValue> y) {
        this.y = y;
    }
}
