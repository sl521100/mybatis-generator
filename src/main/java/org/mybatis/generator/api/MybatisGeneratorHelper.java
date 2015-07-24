package org.mybatis.generator.api;

import org.mybatis.generator.config.*;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.NullProgressCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by shilin on 2015/7/24.
 */
public class MybatisGeneratorHelper {

    private static Configuration GetConfiguration(JDBCConnectionConfiguration config, Set<String> tbs) {
        if(!config.getProperties().containsKey("remarksReporting")){
            config.getProperties().put("remarksReporting", "true");
        }

        Configuration cfg = new Configuration();
        Context cxt = new Context(ModelType.CONDITIONAL);
        cxt.setId("my");
        cfg.getContexts().add(cxt);

        cxt.setJdbcConnectionConfiguration(config);
        for (String tb : tbs) {
            TableConfiguration tbCfg = new TableConfiguration(cxt);
            tbCfg.setTableName(tb);
            cxt.getTableConfigurations().add(tbCfg);
        }

        return cfg;
    }

    public static List<ColumnInfo> GetDbData(JDBCConnectionConfiguration config, Set<String> tbs) {
        Configuration cfg = GetConfiguration(config, tbs);

        List<ColumnInfo> listCols = new ArrayList<ColumnInfo>();

        try {
            Context cxt = cfg.getContexts().get(0);

            ProgressCallback callback = new NullProgressCallback();

            cxt.introspectTables(callback, null, tbs);
            List<IntrospectedTable> list = cxt.getIntrospectedTables();

            for (IntrospectedTable item : list) {
                ColumnInfo info=new ColumnInfo();
                info.setTableName(item.getTableConfiguration().getTableName());

                List<ColumnInfo.Column> pkeyCols=getColumnInfoList(true,false, item.primaryKeyColumns);
                List<ColumnInfo.Column> blobCols=getColumnInfoList(false,true, item.blobColumns);
                List<ColumnInfo.Column> baseCols=getColumnInfoList(false,false, item.baseColumns);
                info.getListColumn().addAll(pkeyCols);
                info.getListColumn().addAll(blobCols);
                info.getListColumn().addAll(baseCols);

                listCols.add(info);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return listCols;
    }

    private static List<ColumnInfo.Column>  getColumnInfoList(boolean isKey,boolean isBlob, List<IntrospectedColumn> list) {
        List<ColumnInfo.Column> results=new ArrayList<ColumnInfo.Column>();

        for (IntrospectedColumn icol : list) {
            ColumnInfo.Column col = new ColumnInfo.Column();
            col.setBlob(isBlob);
            col.setPKey(isKey);
            col.setDbColumnName(icol.getActualColumnName());
            col.setDbType(icol.getJdbcTypeName());
            col.setJavaProperty(icol.getJavaProperty());
            col.setJavaType(icol.getFullyQualifiedJavaType().getFullyQualifiedName());
            col.setLength(icol.getLength());
            col.setNullable(icol.isNullable());
            col.setRemarks(icol.getRemarks());
            col.setScale(icol.getScale());

            results.add(col);
        }
        return results;
    }
}
