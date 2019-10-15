package com.secusoft.ssw.model.monitor;

/**
 * @ClassName CheckStandardItem
 * @Description
 * @Author jmGui
 * @Date 2018/10/18 10:29
 **/
public class CheckStandardSubdirectory {
    private int id;
    private int directoryId;
    private String subdirectoryName;
    private int isDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(int directoryId) {
        this.directoryId = directoryId;
    }

    public String getSubdirectoryName() {
        return subdirectoryName;
    }

    public void setSubdirectoryName(String subdirectoryName) {
        this.subdirectoryName = subdirectoryName;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
