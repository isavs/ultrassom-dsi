package dis.ultrassom.service;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.jblas.DoubleMatrix;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import dis.ultrassom.algorithm.CGNEAlgorithm;
import dis.ultrassom.algorithm.CGNRAlgorithm;
import dis.ultrassom.model.ReconstructionResult;
import dis.ultrassom.model.User;

public class ImageReconstructionService {
    private CGNEAlgorithm cgneAlgorithm;
    private CGNRAlgorithm cgnrAlgorithm;

    public ImageReconstructionService() {
        this.cgneAlgorithm = new CGNEAlgorithm();
        this.cgnrAlgorithm = new CGNRAlgorithm();
    }


    public DoubleMatrix getRandomModelMatrix() {
        String[] options = { "H-1.csv", "H-2.csv" };
        String randomOption = options[new Random().nextInt(options.length)];

        List<String[]> randomModelMatrix = readMatrixFromCSV(randomOption);

        DoubleMatrix H = convertToDoubleMatrix(randomModelMatrix);  

        return H;
    }

    private List<String[]> readMatrixFromCSV(String filename) {
        String filepath = String.format("C:\\Users\\Isabela V. Santos\\Documents\\NetBeansProjects\\ultrassom\\arquivos teste\\%s", filename);

        List<String[]> matrixFromCSV = convertFileToString(filepath);

        return matrixFromCSV;
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

    public ReconstructionResult reconstructImage(User user, DoubleMatrix g, DoubleMatrix H, int algorithmChoice) {
        LocalDateTime startTime = LocalDateTime.now();

        DoubleMatrix reconstructedImage;
        if (algorithmChoice == 1) {
            reconstructedImage = cgneAlgorithm.runCGNE(g, H, 100, 1e-4);
        } else if (algorithmChoice == 2) {
            reconstructedImage = cgnrAlgorithm.runCgnr(g, H, 100, 1e-4);
        } else {
            throw new IllegalArgumentException("Escolha de algoritmo inválida.");
        }

        LocalDateTime endTime = LocalDateTime.now();

        ReconstructionResult result = new ReconstructionResult();
        result.setUser(user);
        result.setImage(reconstructedImage);
        result.setIterations(100); // Valor de exemplo
        result.setStartTime(startTime);
        result.setEndTime(endTime);

        return result;
    }
}
