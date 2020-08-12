package model;

import java.util.Objects;

public class Column {

    private String name;
    private String dataType;
    private int maxCharLength;
    private boolean isNullable;
    private String tableName;

    public Column(String name, String dataType, int maxCharLength, boolean isNullable, String tableName) {
        this.name = name;
        this.dataType = dataType;
        this.maxCharLength = maxCharLength;
        this.isNullable = isNullable;
        this.tableName = tableName;
    }

    public String getName() {
        return name;
    }

    public String getDataType() {
        return dataType;
    }

    public String getTableName() {
        return tableName;
    }

    public int getMaxCharLength() {
        return maxCharLength;
    }

    public boolean isNullable() {
        return isNullable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Column column = (Column) o;
        return maxCharLength == column.maxCharLength &&
                isNullable == column.isNullable &&
                name.equals(column.name) &&
                dataType.equals(column.dataType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dataType, maxCharLength, isNullable);
    }
}
