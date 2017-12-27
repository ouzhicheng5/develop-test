package common.util;

import java.util.*;

public class CollectionUtil {

    public static <T> Set<T> intersection(Collection<T> c1, Collection<T> c2){
        if(c1==null||c1.isEmpty()||c2 == null || c2.isEmpty()){
            return new HashSet<>();
        }
        Set<T> resultSet=new HashSet<>();
        for(T ele:c1){
            if(c2.contains(ele)){
                resultSet.add(ele);
            }
        }
        return resultSet;
    }

    public static <T> T randomGet(List<T> list){
        if(list==null||list.isEmpty()){
            return null;
        }
        Random  random=new Random();
        return list.get(random.nextInt(list.size()));
    }

    public static <T> List<T> toList(Set<T> set){
        if(set==null||set.isEmpty()){
            return new ArrayList<>();
        }
        List<T> list=new ArrayList<>();
        for(T t:set){
            list.add(t);
        }
        return list;
    }

    public static <K,V> List<V> getValues(Collection<K> kList,Map<K,V> map,boolean skipNullValue){
        if(kList==null||kList.isEmpty()||map.isEmpty()){
            return new ArrayList<>();
        }
        List<V> values=new ArrayList<>();
        V value;
        for(K k:kList){
            value=map.get(k);
            if(skipNullValue&&value==null){
                continue;
            }
            values.add(value);
        }
        return values;
    }

    public static <K,V> List<V> getValues(K[] kArr,Map<K,V> map,boolean skipNullValue){
        if(kArr==null||kArr.length==0||map.isEmpty()){
            return new ArrayList<>();
        }
        List<V> values=new ArrayList<>();
        V value;
        for(K k:kArr){
            value=map.get(k);
            if(skipNullValue&&value==null){
                continue;
            }
            values.add(value);
        }
        return values;
    }

}
