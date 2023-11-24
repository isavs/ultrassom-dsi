/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package dis.ultrassom;

import org.jblas.DoubleMatrix;

/**
 *
 * @author Isabela V. Santos
 */
public class Ultrassom {

    public static void main(String[] args) {
        //System.out.println("Hello World!");
        // Definindo matrizes M e N e vetor a
        /*DoubleMatrix M = new DoubleMatrix(new double[][]{{92,99,1,8,15,67,74,51,58,40},
                                                         {98,80,7,14,16,73,55,57,64,41},
                                                         {4,81,88,20,22,54,56,63,70,47},
                                                         {85,87,19,21,3,60,62,69,71,28},
                                                         {86,93,25,2,9,61,68,75,52,34},
                                                         {17,24,76,83,90,42,49,26,33,65},
                                                         {23,5,82,89,91,48,30,32,39,66},
                                                         {79,6,13,95,97,29,31,38,45,72},
                                                         {10,12,94,96,78,35,37,44,46,53},
                                                         {11,18,100,77,84,36,43,50,27,59}
                                                        });
        DoubleMatrix N = new DoubleMatrix(new double[][]{{92,99,1,8,15,67,74,51,58,40},
                                                         {98,80,7,14,16,73,55,57,64,41},
                                                         {4,81,88,20,22,54,56,63,70,47},
                                                         {85,87,19,21,3,60,62,69,71,28},
                                                         {86,93,25,2,9,61,68,75,52,34},
                                                         {17,24,76,83,90,42,49,26,33,65},
                                                         {23,5,82,89,91,48,30,32,39,66},
                                                         {79,6,13,95,97,29,31,38,45,72},
                                                         {10,12,94,96,78,35,37,44,46,53},
                                                         {11,18,100,77,84,36,43,50,27,59}
                                                        });
        DoubleMatrix a = new DoubleMatrix(new double[]{0.27603,0.6797,0.6551,0.16261,0.119,0.49836,0.95974,0.34039,0.58527,0.22381
});

        // Verificando as dimensões de M e N para multiplicação matricial
        if (M.columns != N.rows) {
            System.out.println("As dimensões de M e N não são compatíveis para multiplicação matricial.");
            return;
        }
        
        // Verificando as dimensões de M e a para multiplicação matricial
        if (M.columns != a.length) {
            System.out.println("As dimensões de M e a não são compatíveis para multiplicação matricial.");
            return;
        }
        
        // Verificando as dimensões de N e a para multiplicação matricial
        if (N.columns != a.length) {
            System.out.println("As dimensões de N e a não são compatíveis para multiplicação matricial.");
            return;
        }

        // Operação matricial MN = M * N
        DoubleMatrix MN = M.mmul(N);
        System.out.println("MN = M * N:\n" + MN);

        // Operação matricial aM = a * M
        DoubleMatrix aM = a.transpose().mmul(M);
        System.out.println("aM = a * M:\n" + aM);

        // Operação matricial Ma = M * a
        DoubleMatrix Ma = M.mmul(a);
        System.out.println("Ma = M * a:\n" + Ma);*/
        
        // Defina a matriz do sistema linear e o vetor do lado direito
        // H - Matriz de modelo
        // g - Vetor de sinal
        DoubleMatrix H = new DoubleMatrix(new double[][]{{4, 1}, {1, 3}});
        DoubleMatrix g = new DoubleMatrix(new double[]{1, 2});

        // Chute inicial para a solução
        DoubleMatrix f0 = DoubleMatrix.zeros(g.length);

        
        // Chame a função CG para resolver o sistema linear Ax = b
        DoubleMatrix solutionCGNR = CGNR(H, g, f0, 1e-10, 1000);
        DoubleMatrix solutionCGNE = CGNE(H, g, 100);

        // Imprima a solução
        System.out.println("Solução CGNR encontrada: ");
        System.out.println(solutionCGNR);
        System.out.println("Solução CGNE encontrada: ");
        System.out.println(solutionCGNE);
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

        // Inicialização de variáveis para CGNE
        DoubleMatrix f = DoubleMatrix.zeros(N);
        DoubleMatrix r = g.dup(); // r0 = g - Hf0
        DoubleMatrix p = H.transpose().mmul(r); // p0 = H^T * r0

        // Algoritmo CGNE
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
}
