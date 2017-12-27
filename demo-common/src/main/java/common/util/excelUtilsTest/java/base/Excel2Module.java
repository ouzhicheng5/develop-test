package common.util.excelUtilsTest.java.base;

import common.util.ExcelUtils;
import common.util.excelUtilsTest.java.moudles.Student1;
import common.util.excelUtilsTest.java.moudles.Student2;

import java.util.List;

public class Excel2Module {

    public void excel2Object() throws Exception {

        String path = "D:/ideaIU/Excel4J/src/test/resource/students_01.xlsx";

        System.out.println("读取全部：");
        List<Student1> students = ExcelUtils.getInstance().readExcel2Objects(path, Student1.class);
        for (Student1 stu : students) {
            System.out.println(stu);
        }

        System.out.println("读取指定行数：");
        students = ExcelUtils.getInstance().readExcel2Objects(path, Student1.class, 0, 3, 0);
        for (Student1 stu : students) {
            System.out.println(stu);
        }
    }

    public void excel2Object2() throws Exception {

        String path = "D:/Git_Repositories/ndt-backend/ndt-common/src/main/java/com/ndt/util/excelUtilsTest/resource/cooperation.xlsx";

        // 不基于注解,将Excel内容读至List<List<String>>对象内
//        List<List<String>> lists = ExcelUtils.getInstance().readExcel2List(path, 1, 2499, 0);
//        System.out.println("读取Excel至String数组：");
//        for (List<String> list : lists) {
//            System.out.println(list);
//        }


/*        // 基于注解,将Excel内容读至List<Student2>对象内
        List<CooperationImportVo> cooperations = ExcelUtils.getInstance().readExcel2Objects(path, CooperationImportVo.class, 0);
        System.out.println("读取Excel至对象数组(支持类型转换)：");
        for (CooperationImportVo cooperation : cooperations) {
            System.out.println(cooperation);
        }*/
    }

    public static void main(String[] args) {
        Excel2Module testObject = new Excel2Module();
        try {
//            testObject.excel2Object();
            testObject.excel2Object2();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }
}
