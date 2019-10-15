package com.secusoft.ssw.model.monitor;

/**
 * @ClassName CheckStandardItem
 * @Description
 * @Author jmGui
 * @Date 2018/10/18 10:29
 **/
public class CheckStandardItem {
    private int id;
    private int subDirectoryId;
    private String itemName;
    private String standard;
    private int type;
    private int severity;
    private int priority;
    private int isDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubDirectoryId() {
        return subDirectoryId;
    }

    public void setSubDirectoryId(int subDirectoryId) {
        this.subDirectoryId = subDirectoryId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
