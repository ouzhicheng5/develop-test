package common.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Vo和Record互转工具类
 * Created by wangjin on 2017/08/31.
 */
public class VoReTraversalUtil {

    /**
     * vo和record互转
     *
     * @param src
     * @param toClass
     * @return
     * @throws Exception
     */
    public static Object traversal(Object src, Class<?> toClass) {

        Class fromClass;
        Object interfaces = null;
        try {
            interfaces = toClass.newInstance();
            fromClass = Class.forName(src.getClass().getName());
            //获取所有的方法
            Method[] m = fromClass.getDeclaredMethods();

            for (int i = 0; i < m.length; i++) {   //获取方法名
                String method = m[i].getName();
                //获取get开始的方法名
                if (method.startsWith("get") && !method.contains("getClass")) {
                    //获取对应对应get方法的value值
                    Object value = m[i].invoke(src);
                    if (value != null) {
                        //截取get方法除get意外的字符 如getUserName-->UserName
                        String key = method.substring(3);
                        String typeNamea = fromClass.getMethod(method).getGenericReturnType().getTypeName();
                        try{
                            String typeNameb = toClass.getMethod(method).getGenericReturnType().getTypeName();
                            if (!typeNamea.equals(typeNameb)) {
                                throw new Exception("类型不匹配");
                            }
                            String methodName = "set" + key;
                            Method method1 = toClass.getMethod(methodName, value.getClass());
                            method1.invoke(interfaces, value);
                        }catch (Exception e){
                            continue;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return interfaces;
    }
    /**
     * vo和record互转,忽略类型不匹配，直接跳到下一个
     *
     * @param src
     * @param toClass
     * @return
     * @throws Exception
     */
    public static Object traversalTwo(Object src, Class<?> toClass) {

        Class fromClass;
        Object interfaces = null;
        try {
            interfaces = toClass.newInstance();
            fromClass = Class.forName(src.getClass().getName());
            //获取所有的方法
            Method[] m = fromClass.getDeclaredMethods();

            for (int i = 0; i < m.length; i++) {   //获取方法名
                String method = m[i].getName();
                //获取get开始的方法名
                if (method.startsWith("get") && !method.contains("getClass")) {
                    //获取对应对应get方法的value值
                    Object value = m[i].invoke(src);
                    if (value != null) {
                        //截取get方法除get意外的字符 如getUserName-->UserName
                        String key = method.substring(3);
                        String typeNamea = fromClass.getMethod(method).getGenericReturnType().getTypeName();
                        try{
                            String typeNameb = toClass.getMethod(method).getGenericReturnType().getTypeName();
                            if (!typeNamea.equals(typeNameb)) {
                                System.out.println("traversalTwo转换出现了类型不匹配！请检查*****："+typeNameb);
                                continue;
                            }
                            String methodName = "set" + key;
                            Method method1 = toClass.getMethod(methodName, value.getClass());
                            method1.invoke(interfaces, value);
                        }catch (NoSuchMethodException e){
                            continue;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return interfaces;
    }
    /**
     * @param src
     * @param target
     * @throws Exception
     */
    public static void mergeObj(Object src, Object target) throws Exception {

        Class<?> aClass = src.getClass();
        Class<?> bClass = target.getClass();
        Method[] m = aClass.getDeclaredMethods();
        for (int i = 0; i < m.length; i++) {   //获取方法名
            String method = m[i].getName();
            //获取get开始的方法名
            if (method.startsWith("get")) {
                //获取对应对应get方法的value值
                Object value = m[i].invoke(src);
                if (value != null) {
                    //截取get方法除get以外的字符 如getUserName-->UserName
                    String key = method.substring(3);
                    String typeNamea = aClass.getMethod(method).getGenericReturnType().getTypeName();
                    String typeNameb = bClass.getMethod(method).getGenericReturnType().getTypeName();
                    if (!typeNamea.equals(typeNameb)) {
                        throw new Exception("类型不匹配");
                    }
                    String methodName = "set" + key;
                    Method method1 = bClass.getMethod(methodName, value.getClass());
                    method1.invoke(target, value);
                }
            }
        }
    }

    /**
     * vo转record
     *
     * @param src
     * @param clazz
     * @return
     */
    public static Object voToRecordTraversal(Object src, Class clazz) {
        String json = JSON.toJSONString(src);
        Object target = JSON.parseObject(json, clazz);
        return target;
    }


    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null){
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     *
     * @param src
     * @param target
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }


}