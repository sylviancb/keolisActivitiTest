package com.silviu.activiti2.pojo;

public class Task {

    String partName;

    public Task(String partName) {
        this.partName = partName;
    }

    public Task() {
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }
}
