package com.secusoft.ssw.model.viewobject.Response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName DeviceCameraVO
 * @Author jcyao
 * @Description
 * @Date 2018/9/5 17:47
 * @Version 1.0
 */
@ApiModel(value="工地组织架构树结构结果集")
public class DepartmentInfoVO {

    @ApiModelProperty("部门编号")
    private String id;

    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("上级部门编号")
    private String parentid;

    @ApiModelProperty("部门人数")
    private int peopleNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }
}
