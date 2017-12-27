package common.util.csv.util;


import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/9.

 */

public class ImportCSV{
    /**
     * Parses a csv file into a list of beans.
     *
     * @param <T> the type of the bean
     * @param filename the name of the csv file to parse
     * @param fieldDelimiter the field delimiter
     * @param beanClass the bean class to map csv records to
     * @param columnMapping the bean class method to map csv records to
     * @return the list of beans or an empty list there are none
     * @throws FileNotFoundException if the file does not exist
     */
    public static <T> List<T> parseCsvFileToBeans(final String filename,
                                                  final char fieldDelimiter,
                                                  final Class<T> beanClass,
                                                  final Map<String, String> columnMapping) throws FileNotFoundException {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new BufferedReader(new FileReader(filename)),
                    fieldDelimiter);
            final HeaderColumnNameTranslateMappingStrategy<T> strategy =
                    new HeaderColumnNameTranslateMappingStrategy<T>();
            strategy.setType(beanClass);
            strategy.setColumnMapping(columnMapping);

            final CsvToBean<T> csv = new CsvToBean<T>();
            return csv.parse(strategy, reader);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    // ignore
                }
            }
        }
    }

}

