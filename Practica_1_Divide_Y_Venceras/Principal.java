/**
 * Práctica 1 de la asignatura de Algorítmica y Complejidad.
 *
 * @author Eduardo San Segundo Jiménez
 * @author Alberto Zugazagoitia Rodríguez
 */
public class Principal {


    /**
     * Calcula en O(N) la suma de los números positivos del vector parámetro usando un esquema divide y vencerás.
     *
     * @param vector Vector de números enteros ordenados circularmente con un máximo de 10 números positivos.
     * @return Suma de todos los elementos positivos del vector.
     * @see #sumaPositivos1(int[], int, int)
     */
    public static int sumaPositivos1(int[] vector) {
        return sumaPositivos1(vector, 0, vector.length - 1);
    }

    /**
     * Calcula en O(N) la suma de los números positivos del subvector parámetro usando un esquema divide y vencerás.
     *
     * @param vector Vector de números enteros ordenados circularmente con un máximo de 10 números positivos.
     * @param i0     Primer índice del subvector a sumar.
     * @param iN     Último índice del subvector a sumar.
     * @return Suma de todos los elementos positivos del subvector.
     * @see #sumaPositivos1(int[])
     */
    public static int sumaPositivos1(int[] vector, int i0, int iN) {
        if (vector.length==0) return 0;
        else if (iN == i0) {
            if (vector[i0] > 0) return vector[i0];
            else return 0;  
        } else {
            int k = (i0 + iN) / 2;
            return sumaPositivos1(vector, i0, k) + sumaPositivos1(vector, k + 1, iN);
        }

    }

    /**
     * Calcula en O(log(N)) la suma de los números positivos del vector parámetro usando un esquema divide y vencerás.
     *
     * @param vector Vector de números enteros ordenados circularmente con un máximo de 10 números positivos.
     * @return Suma de todos los elementos positivos del vector.
     * @see #sumaPositivos2(int[], int, int)
     */
    public static int sumaPositivos2(int[] vector) {
        return sumaPositivos2(vector, 0, vector.length - 1);
    }

    /**
     * Calcula en O(log(N)) la suma de los números positivos del subvector parámetro usando un esquema divide y vencerás.
     *
     * @param vector Vector de números enteros ordenados circularmente con un máximo de 10 números positivos.
     * @param i0     Primer índice del subvector a sumar.
     * @param iN     Último índice del subvector a sumar.
     * @return Suma de todos los elementos positivos del subvector.
     * @see #sumaPositivos2(int[])
     */
    public static int sumaPositivos2(int[] vector, int i0, int iN) {
        int temp = 0;
        if (vector.length == 0) return temp;
        else if (iN == i0) {
            if (vector[i0] > 0) temp += vector[i0];
            return temp;

        } else {

            int k = (i0 + iN) / 2;

            //Descartamos todos los subvectores que solo tengan números negativos, es decir, que el primero sea menor que el último elemento y además sean ambos negativos.
            if (!((vector[i0] < 0) && (vector[k] < 0) && (vector[i0] < vector[k])))
                //Si no se cumple la condición anterior puede haber números positivos dentro del subvector, así que recursivamente probamos sobre la primera mitad.
                temp += sumaPositivos2(vector, i0, k);

            //Mismo proceso para la segunda mitad.
            if (!((vector[k + 1] < 0) && (vector[iN] < 0) && (vector[k + 1] < vector[iN])))
                temp += sumaPositivos2(vector, k + 1, iN);

            return temp;
            //Si llegamos a un vector enano (dos o menor), sumamos los positivos y listo.
        }

    }

}
