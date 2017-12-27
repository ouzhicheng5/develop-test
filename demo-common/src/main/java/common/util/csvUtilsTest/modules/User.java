package common.util.csvUtilsTest.modules;



import common.util.csv.annotation.CsvField;

import java.util.HashMap;
import java.util.Map;


public class User {

    @CsvField(title = "姓名") private String name;
    @CsvField(title = "年龄") private Integer age;
    @CsvField(title = "性别") private String sex;


    public  User(){}

    public User(String name, Integer age, String sex) {
        super();
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "User{" +
                "name ='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public Map<String, String> csvMap() {
        Map<String, String> columnMapping = new HashMap<String, String>();
        columnMapping.put("姓名", "name");
        columnMapping.put("年龄", "age");
        columnMapping.put("性别", "sex");

        return columnMapping;
    }




}
