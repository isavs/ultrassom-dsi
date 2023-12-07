package dis.ultrassom.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dis.ultrassom.model.ReconstructionResult;
import dis.ultrassom.model.User;
import dis.ultrassom.service.ImageReconstructionService;

@RestController
@RequestMapping("/reconstruct")
public class UltrassomController {
    private ImageReconstructionService reconstructionService;

    public UltrassomController(ImageReconstructionService reconstructionService) {
        this.reconstructionService = reconstructionService;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public ReconstructionResult reconstructImage(
            @RequestParam("username") String username,
            @RequestParam("uniqueFileName") String uniqueFileName,
            @RequestParam("algorithmChoice") int algorithmChoice) {

        User user = new User(username);

        ReconstructionResult result = reconstructionService.reconstructImage(user, uniqueFileName, algorithmChoice);

        reconstructionService.generateImage(result.getImage(), "C:\\Users\\isabe\\Downloads\\ultrassom-dsi-untested-pseudo-architecture\\arquivos teste");

        return result;
    }
}
