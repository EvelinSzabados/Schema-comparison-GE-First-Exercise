package model;

import controller.DataManager;

public class Column {

    private String name;

    private String dataType;
    private int maxCharLength;
    private boolean isNullable;

    public Column(String name, String dataType, int maxCharLength, boolean isNullable){
        this.name = name;
        this.dataType = dataType;
        this.maxCharLength = maxCharLength;
        this.isNullable = isNullable;
    }

    public String getName() {
        return name;
    }

    public String getDataType() {
        return dataType;
    }

    public int getMaxCharLength() {
        return maxCharLength;
    }

    public boolean isNullable() {
        return isNullable;
    }
}
