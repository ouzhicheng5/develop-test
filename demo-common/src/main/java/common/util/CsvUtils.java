package common.util;


import common.util.csv.util.ExportCSV;
import common.util.csv.util.ImportCSV;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/9/27.
 */
public class CsvUtils {
    /*----------------------------------------读取CSV操作基于Map映射---------------------------------------------*/
    /*  一. 操作流程 ：                                                                                          */
    /*      1) 读取表头信息,与给出的Map映射匹配                                                                   */
    /*      2) 读取表头下面的数据内容, 按行读取, 并映射至java对象                                                   */
    /*  二. 参数说明                                                                                             */
    /*      *) csvPath          =>      目标CSV路径                                                              */
    /*      *) beanClass        =>      java映射对象类                                                           */
    /*      *) fieldDelimiter   =>      CSV文件分隔符                                                            */
    /*      *) columnMapping    =>      Map映射                                                                 */
    public <T> List<T> readCsv2Objects(String csvPath, char fieldDelimiter, Class<T> beanClass,
                                       Map<String, String> columnMapping) {
        List<T> result = new ArrayList<>();
        try {
            result = ImportCSV.parseCsvFileToBeans(csvPath, fieldDelimiter, beanClass, columnMapping);
            return result;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return result;
        }

    }

    /*----------------------------------------基于模板、注解导出CSV-----------------------------------------------*/
    /*  一. 操作流程 ：                                                                                          */
    /*      1) 初始化模板                                                                                        */
    /*      2) 读取映射                                                                                          */
    /*      3) 写入数据                                                                                          */
    /*  二. 参数说明                                                                                             */
    /*      *) csvFilePath      =>      写入CSV路径                                                              */
    /*      *) beans            =>      java映射对象类                                                           */

    public <T> void writeObjects2Csv(String csvFilePath, List<T> beans) {
        try{
            ExportCSV.writeBeanToCsvFile(csvFilePath, beans);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }




}
