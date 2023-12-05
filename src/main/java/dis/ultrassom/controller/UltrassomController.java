package dis.ultrassom.controller;

import org.jblas.DoubleMatrix;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dis.ultrassom.model.ReconstructionResult;
import dis.ultrassom.model.ReconstructionRequest;
import dis.ultrassom.model.User;
import dis.ultrassom.service.ImageReconstructionService;

public class UltrassomController {
    private ImageReconstructionService reconstructionService;

    public UltrassomController(ImageReconstructionService reconstructionService) {
        this.reconstructionService = reconstructionService;
    }

    @PostMapping("/upload")
    public ReconstructionResult reconstructImage(@RequestBody ReconstructionRequest request) {
        User user = request.getUser();
        DoubleMatrix g = request.getSignalVector();
        DoubleMatrix H = request.getModelMatrix();
        int algorithmChoice = request.getAlgorithmChoice();

        return reconstructionService.reconstructImage(user, g, H, algorithmChoice);
    }
}
