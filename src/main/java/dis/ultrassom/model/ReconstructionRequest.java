package dis.ultrassom.model;

import org.jblas.DoubleMatrix;

public class ReconstructionRequest {
    private User user;
    private DoubleMatrix g;
    private DoubleMatrix H;
    private int algorithmChoice;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DoubleMatrix getSignalVector() {
        return g;
    }

    public void setSignalVector(DoubleMatrix g) {
        this.g = g;
    }

    public DoubleMatrix getModelMatrix() {
        return H;
    }

    public void setModelMatrix(DoubleMatrix H) {
        this.H = H;
    }

    public int getAlgorithmChoice() {
        return algorithmChoice;
    }

    public void setAlgorithmChoice(int algorithmChoice) {
        this.algorithmChoice = algorithmChoice;
    }
}
