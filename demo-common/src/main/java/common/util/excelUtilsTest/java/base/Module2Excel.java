package common.util.excelUtilsTest.java.base;


import common.util.ExcelUtils;
import common.util.excelUtilsTest.java.moudles.Student1;

import java.util.*;

public class Module2Excel {

    public void testObject2Excel() throws Exception {

        List<Student1> list = new ArrayList<>();
        list.add(new Student1("1010001", "盖伦", "六年级三班"));
        list.add(new Student1("1010002", "古尔丹", "一年级三班"));
        list.add(new Student1("1010003", "蒙多(被开除了)", "六年级一班"));
        list.add(new Student1("1010004", "萝卜特", "三年级二班"));
        list.add(new Student1("1010005", "奥拉基", "三年级二班"));
        list.add(new Student1("1010006", "得嘞", "四年级二班"));
        list.add(new Student1("1010007", "瓜娃子", "五年级一班"));
        list.add(new Student1("1010008", "战三", "二年级一班"));
        list.add(new Student1("1010009", "李四", "一年级一班"));
        Map<String, String> data = new HashMap<>();
        data.put("title", "战争学院花名册");
        data.put("info", "学校统一花名册");

        ExcelUtils.getInstance().exportObjects2Excel( list, Student1.class, true, null, true, "A.xlsx");
    }


    public void testList2Excel() throws Exception {

        List<List<String>> list2 = new ArrayList<>();
        List<String> header = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<String> _list = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                _list.add(i + " -- " + j);
            }
            list2.add(_list);
            header.add(i + "---栏");
        }
        ExcelUtils.getInstance().exportObjects2Excel(list2, header, "B.xlsx");
    }

    public static void main(String[] args) {
        Module2Excel testObject = new Module2Excel();
        try{
            testObject.testList2Excel();
            testObject.testObject2Excel();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
