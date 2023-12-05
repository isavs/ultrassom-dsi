package dis.ultrassom.Parser;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jblas.DoubleMatrix;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class ParseCSV {

    public static DoubleMatrix toDoubleMatrix(List<String[]> data) {
        DoubleMatrix matrix = null;
        int numRows = data.size();
        int numCols = data.get(0).length;
        

        if (numRows < numCols) {
            matrix = new DoubleMatrix(numCols, 1);
            for (int i = 0; i < numCols; i++) {
                matrix.put(i, 0, Double.parseDouble(data.get(0)[i]));
            }
            return matrix;
        }
        else {
            matrix = new DoubleMatrix(numRows, numCols);
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    matrix.put(i, j, Double.parseDouble(data.get(i)[j]));
                }
            }
        }
        return matrix;
    }

    public static List<String[]> toString(String filePath, char separator) {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(separator)
                        .build())
                .build();
            
            List<String[]> allData = reader.readAll();
            //printa as strings da lista
            for (String[] row : allData) {
                System.out.println(StringUtils.join(row, ","));
            }   

            
            
            return allData;
            
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

    return null;
}
}
