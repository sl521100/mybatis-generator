import org.junit.Test;
import org.mybatis.generator.api.ColumnInfo;
import org.mybatis.generator.api.MybatisGeneratorHelper;
import org.mybatis.generator.config.JDBCConnectionConfiguration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by shilin on 2016/5/3.
 */
public class commonTest {
    @org.junit.Test
    public void testSort() throws Exception {

        int[] arr = new int[]{5, 3, 6, 2, 9};
        quickSort(arr, 0, arr.length - 1);

        System.out.println(arr);
    }

    @Test
    public void testDB() throws Exception {
        JDBCConnectionConfiguration cfg=new JDBCConnectionConfiguration();
        cfg.setUserId("lgha");
        cfg.setPassword("lgha");
        cfg.setDriverClass("oracle.jdbc.driver.OracleDriver");
        cfg.setConnectionURL("jdbc:oracle:thin:@172.21.129.51:1521:orcl");

        Set s1=new HashSet<String>();
        s1.add("G_E_ACCEPT_RETURN");

        List<ColumnInfo> listCls= MybatisGeneratorHelper.GetDbData(cfg,s1,"lgha");
        for (ColumnInfo item : listCls) {

        }
    }

    private void quickSort(int[] arr, int start, int end) {


        if (arr != null && arr.length > 0) {
            int i = segSort(arr, start, end);
            if (start < i && i + 1 < end) {
                quickSort(arr, start, i);

                quickSort(arr, i + 1, end);
            }
        }
    }

    private int segSort(int[] arr, int start, int end) {
        int result = -1;
        if (arr != null && arr.length > 0) {


            int i = start;
            int temp = arr[i];
            int j = end;

            while (i < j) {

                while (i < j && arr[j] > temp) {
                    j--;
                }
                if (i < j) {
                    arr[i] = arr[j];
                    i++;
                }


                while (i < j && arr[i] < temp) {

                    i++;
                }
                if (i < j) {
                    arr[j] = arr[i];
                    j--;
                }
            }
            arr[i] = temp;
            result = i;
        }
        return result;
    }
}
