package dis.ultrassom.model;

import java.time.LocalDateTime;

import org.jblas.DoubleMatrix;

public class ReconstructionResult {
    private User user;
    private DoubleMatrix image;
    private int iterations;
    private String reconstructionTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DoubleMatrix getImage() {
        return image;
    }

    public void setImage(DoubleMatrix image) {
        this.image = image;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public String getReconstructionTime() {
        return reconstructionTime;
    }

    public void setReconstructionTime(String reconstructionTime) {
        this.reconstructionTime = reconstructionTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}