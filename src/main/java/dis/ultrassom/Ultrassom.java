/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package dis.ultrassom;

import org.jblas.DoubleMatrix;

import com.opencsv.CSVReader;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

/**
 *
 * @author Isabela V. Santos
 */
public class Ultrassom {

    public static void main(String[] args) {
        
        List<String[]> stringH = convertFileToString("C:\\Users\\Isabela V. Santos\\Documents\\NetBeansProjects\\ultrassom\\arquivos teste\\M.csv");
        List<String[]> stringg = convertFileToString("C:\\Users\\Isabela V. Santos\\Documents\\NetBeansProjects\\ultrassom\\arquivos teste\\a.csv");

      
        DoubleMatrix H = convertToDoubleMatrix(stringH);  
        DoubleMatrix g = convertToDoubleMatrix(stringg);
              
            
        System.out.println("Matriz lida do arquivo CSV:");
        System.out.println(H);  
            
        System.out.println("Vetor de sinal lido do arquivo CSV:");
        System.out.println(g);
        
        // Chute inicial para a solução
        DoubleMatrix f0 = DoubleMatrix.zeros(g.length);

        System.out.println("g.length: ");
        System.out.println(g.length);
        
        DoubleMatrix solutionCGNR = CGNR(H, g, f0, 1e-10, 1000);
        //DoubleMatrix solutionCGNE = CGNE(H, g, 100);

        System.out.println("Solução CGNR encontrada: ");
        System.out.println(solutionCGNR);
        //System.out.println("Solução CGNE encontrada: ");
        //System.out.println(solutionCGNE);
    }

    private static List<String[]> convertFileToString (String filePath) {
        try {
            CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(';')
                        .build())
                .build();
            
            List<String[]> allData = reader.readAll();
            System.out.println(allData);
            
            
            return allData;
            
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static DoubleMatrix CGNR (DoubleMatrix H, DoubleMatrix g, DoubleMatrix f0, double tolerance, int maxIterations) {
        DoubleMatrix f = f0.dup();
        DoubleMatrix r = g.sub(H.mmul(f)); // r0 = g - Hf0
        DoubleMatrix z = H.transpose().mmul(r); // z0 = H^T * r0
        DoubleMatrix p = z.dup();
        int iteration = 0;

        while (r.norm2() > tolerance && iteration < maxIterations) {
            DoubleMatrix w = H.mmul(p);
            double alpha = z.dot(z) / w.dot(w);
            f.addi(p.mul(alpha));
            r.subi(w.mul(alpha));
            z = H.transpose().mmul(r);
            double beta = z.dot(z) / p.dot(p);
            p = z.add(p.mul(beta));

            iteration++;
        }

        return f;
    }

    private static DoubleMatrix CGNE (DoubleMatrix H, DoubleMatrix g, int maxIterations) {
        int N = H.columns;

        DoubleMatrix f = DoubleMatrix.zeros(N);
        DoubleMatrix r = g.dup(); // r0 = g - Hf0
        DoubleMatrix p = H.transpose().mmul(r); // p0 = H^T * r0

        for (int i = 0; i < maxIterations; i++) {
            DoubleMatrix Ap = H.mmul(p);
            //double tempr = r.dot(r);
            DoubleMatrix rb = r.dup();
            double alpha = r.dot(r) / p.dot(p);
            f.addi(p.mul(alpha));
            r.subi(H.mmul(p).mul(alpha));
        //System.out.println("rsubi dot: ");
        //System.out.println(r.dot(r));
            double beta = r.dot(r) / p.dot(Ap);
            p = H.transpose().mmul(r).addi(p.mul(beta));
        //System.out.println(r);
        }

       //System.out.println(r);
        return f;
    }
    
    private static DoubleMatrix convertToDoubleMatrix(List<String[]> data) {
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
}
