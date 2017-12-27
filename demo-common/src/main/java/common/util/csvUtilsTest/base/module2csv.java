package common.util.csvUtilsTest.base;



import common.util.CsvUtils;
import common.util.csvUtilsTest.modules.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */
public class module2csv {

    public static void main(String[] args) throws Exception {
        List<User> users=new ArrayList<User>();
        users.add(new User("刘夏楠", 23, "男"));
        users.add(new User("刘夏楠", 23, "男"));
        users.add(new User("刘夏楠", 23, "男"));

        new CsvUtils().writeObjects2Csv("D:\\workspace\\tmp\\csvtest1.csv", users);
    }
}
