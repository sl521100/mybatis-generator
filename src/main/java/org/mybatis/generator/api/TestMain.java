package org.mybatis.generator.api;

import org.mybatis.generator.config.JDBCConnectionConfiguration;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by shilin on 2015/7/24.
 */
public class TestMain {
    public static void main(String[] args) {
        try {
            JDBCConnectionConfiguration cfg=new JDBCConnectionConfiguration();
            cfg.setConnectionURL("jdbc:oracle:thin:@10.6.144.162:1522:CCSP");
            cfg.setDriverClass("oracle.jdbc.driver.OracleDriver");
            cfg.setPassword("c1d2s300");
            cfg.setUserId("cds");

String schema="CDS";
            Set<String>  sets=new HashSet<String>();
            sets.add("C_CMS_3201");

            MybatisGeneratorHelper.GetDbData(cfg,sets,schema );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
