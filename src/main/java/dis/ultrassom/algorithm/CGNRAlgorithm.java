package dis.ultrassom.algorithm;

import org.jblas.DoubleMatrix;

public class CGNRAlgorithm {
    public DoubleMatrix runCgnr(DoubleMatrix g, DoubleMatrix H, int maxIterations, double tolerance) {
        DoubleMatrix f = DoubleMatrix.zeros(H.columns); // Inicialização de f
        DoubleMatrix r = g.sub(H.mmul(f)); // r0 = g - Hf0
        DoubleMatrix z = H.transpose().mmul(r); // z0 = H^T r0
        DoubleMatrix p = z.dup(); // p0 = z0

        for (int i = 0; i < maxIterations; i++) {
            DoubleMatrix w = H.mmul(p);
            double alpha = z.dot(z) / w.dot(w);
            f = f.add(p.mul(alpha));
            DoubleMatrix rNext = r.sub(w.mul(alpha));
            DoubleMatrix zNext = H.transpose().mmul(rNext);
            double beta = zNext.dot(zNext) / z.dot(z);
            p = zNext.add(p.mul(beta));
            r = rNext;
            z = zNext;

            double error = rNext.norm2() - r.norm2();
            if (Math.abs(error) < tolerance) {
                break;
            }
        }

        return f;
    }
}
