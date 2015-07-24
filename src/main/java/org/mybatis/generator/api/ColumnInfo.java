package org.mybatis.generator.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shilin on 2015/7/24.
 */
public class ColumnInfo {

    private String tableName;
    private List<Column> listColumn;

    public ColumnInfo(){
        listColumn=new ArrayList<Column>();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getListColumn() {
        return listColumn;
    }


    public static class Column{
        private boolean isPKey;
        private boolean isBlob;
        private boolean isNullable;
        private String dbColumnName;
        private String dbType;
        private String javaProperty;
        private String javaType;
        private String remarks;
        private int length;
        private int scale;

        public boolean isPKey() {
            return isPKey;
        }

        public void setPKey(boolean isPKey) {
            this.isPKey = isPKey;
        }

        public boolean isBlob() {
            return isBlob;
        }

        public void setBlob(boolean isBlob) {
            this.isBlob = isBlob;
        }

        public boolean isNullable() {
            return isNullable;
        }

        public void setNullable(boolean isNullable) {
            this.isNullable = isNullable;
        }

        public String getDbColumnName() {
            return dbColumnName;
        }

        public void setDbColumnName(String dbColumnName) {
            this.dbColumnName = dbColumnName;
        }

        public String getDbType() {
            return dbType;
        }

        public void setDbType(String dbType) {
            this.dbType = dbType;
        }

        public String getJavaProperty() {
            return javaProperty;
        }

        public void setJavaProperty(String javaProperty) {
            this.javaProperty = javaProperty;
        }

        public String getJavaType() {
            return javaType;
        }

        public void setJavaType(String javaType) {
            this.javaType = javaType;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public int getScale() {
            return scale;
        }

        public void setScale(int scale) {
            this.scale = scale;
        }
    }
}
