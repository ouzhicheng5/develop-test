package common.util.csv.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 功能说明: 用来在对象的属性上加入的annotation，通过该annotation说明某个属性所对应的标题<br/>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CsvField {

    /**
     * 属性的标题名称
     */
    String title();

    /**
     * 在csv的顺序
     */
    int order() default 9999;
}
