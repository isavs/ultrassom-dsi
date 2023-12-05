package dis.ultrassom.algorithm;

import org.jblas.DoubleMatrix;

public class CGNEAlgorithm {
    public DoubleMatrix runCGNE(DoubleMatrix g, DoubleMatrix H, int maxIterations, double tolerance) {
        DoubleMatrix f = DoubleMatrix.zeros(H.columns); // Inicialização de f
        DoubleMatrix r = g.sub(H.mmul(f)); // r0 = g - Hf0
        DoubleMatrix p = H.transpose().mmul(r); // p0 = H^T r0

        for (int i = 0; i < maxIterations; i++) {
            double alpha = r.dot(r) / p.dot(H.mmul(p));
            f = f.add(p.mul(alpha));
            DoubleMatrix rNext = r.sub(H.mmul(p).mul(alpha));
            double beta = rNext.dot(rNext) / r.dot(r);
            p = H.transpose().mmul(rNext).add(p.mul(beta));
            r = rNext;

            double error = rNext.norm2() - r.norm2();
            if (Math.abs(error) < tolerance) {
                break;
            }
        }

        return f;
    }
}
