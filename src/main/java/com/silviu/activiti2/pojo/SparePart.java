package com.silviu.activiti2.pojo;

public class SparePart {

    private String partName;
    private String price;

    public SparePart(String partName, String price) {
        this.partName = partName;
        this.price = price;
    }

    public SparePart() {
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
