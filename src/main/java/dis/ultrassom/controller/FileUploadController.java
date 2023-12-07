/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dis.ultrassom.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/upload")
public class FileUploadController {
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public String handleFileUpload(
        @RequestParam("file") MultipartFile file,
        @RequestParam("uniqueFileName") String uniqueFileName) {
        if (file != null && !file.isEmpty()) {
            try {
                // Escolha o diretório onde deseja salvar o arquivo localmente
                String uploadDir = "C:\\Users\\isabe\\Downloads\\ultrassom-dsi-untested-pseudo-architecture\\arquivos teste";
                
                // Certifique-se de que o diretório exista; crie-o se necessário
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                // Caminho completo para o arquivo no diretório de upload
                Path uploadPath = Path.of(uploadDir, uniqueFileName);

                // Salve o arquivo no diretório local
                Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);

                return "File uploaded successfully. Saved as: " + uniqueFileName;
            } catch (IOException e) {
                e.printStackTrace();
                return "Error uploading file: " + e.getMessage();
            }
        } else {
            return "No file uploaded";
        }
    }
}