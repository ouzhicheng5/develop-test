package common.util.csv.util;

import com.csvreader.CsvWriter;
import common.util.csv.annotation.CsvField;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */
public class ExportCSV {
    private static <T> List<String[]> getStringArrayFromBean(List<T> beans) throws NoSuchMethodException,
            SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if(beans.size() < 1) {
            throw new IllegalArgumentException("长度不能小于1");
        }

        List<String[]> result=new ArrayList<String[]>();
        Class<? extends Object> cls=beans.get(0).getClass();//获取泛型类型
        Field[] declaredFields=cls.getDeclaredFields();
        List<Field> annoFields=new ArrayList<Field>();
        for(int i=0;i<declaredFields.length;i++){//筛选出拥有注解的字段
            CsvField anno=declaredFields[i].getAnnotation(CsvField.class);//获取注解
            if(anno!=null) {
                annoFields.add(declaredFields[i]);
            }
        }
        String[] title=new String[annoFields.size()];

        for(int i=0;i<annoFields.size();i++){
            title[i]=declaredFields[i].getAnnotation(CsvField.class).title();//获取注解的值
        }
        result.add(title);

        for(T each:beans){
            String[] item=new String[annoFields.size()];
            for(int i=0;i<annoFields.size();i++){
                String fieldName=annoFields.get(i).getName();
                String methodName="get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);

                Method method=each.getClass().getMethod(methodName, null);
                String val=method.invoke(each, null).toString();
                item[i]=val;
            }
            result.add(item);
        }
        return result;
    }

    public static <T> void  writeBeanToCsvFile(String csvFilePath,List<T> beans) throws IOException,
            NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException{
        File file =new File(csvFilePath);
        if(!file.exists()){     //如果文件不存在，创建文件
            file.createNewFile();
        }
        CsvWriter wr =new CsvWriter(csvFilePath,',', Charset.forName("utf-8"));
        List<String[]> contents=getStringArrayFromBean(beans);
        for(String[] each:contents){
            wr.writeRecord(each);
        }
        wr.close();
    }
}
