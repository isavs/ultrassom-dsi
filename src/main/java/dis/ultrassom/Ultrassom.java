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
        DoubleMatrix M = new DoubleMatrix(new double[][]{{92,99,1,8,15,67,74,51,58,40},
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
        System.out.println("Ma = M * a:\n" + Ma);
    }
}