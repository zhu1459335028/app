package com.secusoft.ssw.model.viewobject.Response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName ConstructionOnWorkVO
 * @Author jcyao
 * @Description
 * @Date 2018/9/6 14:42
 * @Version 1.0
 */
@ApiModel(value="施工班在岗情况结果集")
public class ConstructionOnWorkVO {

    @ApiModelProperty("在岗人数")
    private int onLineCount;

    @ApiModelProperty("总人数")
    private int offLineCount;

    @ApiModelProperty("x轴数据")
    private List<String> x;

    @ApiModelProperty("y轴数据")
    private List<YValue> y;

    public class YValue {

        @ApiModelProperty("名称")
        private String name;
        @ApiModelProperty("数据")
        private List<Integer> data;
        @ApiModelProperty("人员")
        private List<String> persons;

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

        public List<String> getPersons() {
            return persons;
        }

        public void setPersons(List<String> persons) {
            this.persons = persons;
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

    public int getOnLineCount() {
        return onLineCount;
    }

    public void setOnLineCount(int onLineCount) {
        this.onLineCount = onLineCount;
    }

    public int getOffLineCount() {
        return offLineCount;
    }

    public void setOffLineCount(int offLineCount) {
        this.offLineCount = offLineCount;
    }
}
