package dis.ultrassom.model;

public class Image {
    private String imageId;
    private String userId;
    private int numIterations;
    private String reconstructionTime;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getNumIterations() {
        return numIterations;
    }

    public void setNumIterations(int numIterations) {
        this.numIterations = numIterations;
    }

    public String getReconstructionTime() {
        return reconstructionTime;
    }

    public void setReconstructionTime(String reconstructionTime) {
        this.reconstructionTime = reconstructionTime;
    }
}