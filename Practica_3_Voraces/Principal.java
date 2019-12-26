import java.util.ArrayList;

/**
 * Práctica 3.
 * Clase formada íntegramente por métodos estáticos.
 */
public class Principal {

    /**
     * Método que recibe un conjunto de candidatos y un inventario, selecciona los de mejor valor y los inserta.
     *
     * @param candidatos Conjunto de candidatos.
     * @param inventario Inventario de destino.
     * @return Lista de los candidatos seleccionados en su orden de selección.
     */
    public static ArrayList<Objeto> llenarInventario(ArrayList<Objeto> candidatos, Inventario inventario) {
        ArrayList<Objeto> solucion = new ArrayList<>();

        Objeto candidatoActual;

        do {
            candidatoActual = seleccionarCandidato(candidatos);
            candidatos.remove(candidatoActual);
            if (esCandidatoFactible(candidatoActual, inventario))
                solucion.add(candidatoActual);
        } while (!candidatos.isEmpty());

        return solucion;
    }

    /**
     * Método privado que selecciona y devuelve el Objeto con mayor ponderación.
     * Para ponderar se usa la ecuación <b>ponderación = valor / sqrt(ancho*alto)</b> .
     *
     * @param candidatos Conjunto de candidatos.
     * @return Objeto con mayor ponderación.
     */
    private static Objeto seleccionarCandidato(ArrayList<Objeto> candidatos) {
        ArrayList<Double> pesos = new ArrayList<>();

        for (Objeto obj : candidatos) {
            pesos.add(obj.getValor() * (1 / Math.sqrt(obj.getAlto() * obj.getAncho()))); //La ecuación de peso usa sqrt para obtener números con mayor desviación.
        }

        double max = Double.MIN_VALUE;
        int maxI = 0;
        for (int i = 0; i < pesos.size(); i++) { //Buscamos el de mayor ponderación y lo devolvemos.
            if (pesos.get(i) > max) {
                max = pesos.get(i);
                maxI = i;
            }
        }
        return candidatos.get(maxI);
    }

    /**
     * Método privado que recibe un candidato, comprueba si es factible su inserción y en tal caso lo inserta.
     *
     * @param candidato  Candidato a insertar.
     * @param inventario Inventario de destino.
     * @return <i>true</i> Si se ha insertado.
     */
    private static boolean esCandidatoFactible(Objeto candidato, Inventario inventario) {
        boolean exitoX = false; //Resultado x.
        boolean exitoY = false; //Resultado y.

        int x = 0; //Iterador para las columnas.
        int y = 0; //Iterador para las filas.

        int i = 0; //Contador vertical de espacios libres.
        int j = 0; //Contador horizontal de espacios libres.

        int finX = 0; //Coordenada x factible para guardar.
        int finY = 0; //Coordenada y factible para guardar.

        while (y != inventario.getN() && j != candidato.getAlto()) {
            while (x != inventario.getN() && i != candidato.getAncho()) {
                if (inventario.getCelda(x, y) == -1) i++; //Si se encuentra un espacio vacío, lo contamos.
                else i = 0; //Si no, reiniciamos contador.
                x++;
            }
            if (i == candidato.getAncho()) { //¿Se han contado tantos espacios en ancho como se requieren?
                finX = x - 1; //Finalizamos la coordenada X.
                x = x - candidato.getAncho(); //Volvemos a la izquierda.
                exitoX = true;
                j++;
            } else { //Reiniciamos y seguimos probando.
                x = 0;
                i = 0;
                j = 0;
            }
            if (j != candidato.getAlto()) { //Probamos en la siguiente línea.
                y++;
                i = 0;
            }
        }

        if (j == candidato.getAlto()) { //¿Se han contado tantos espacios en alto como se requieren?
            finY = y; //Finalizamos la coordenada Y.
            exitoY = true;
        }


        boolean exito = exitoX && exitoY; //Comprobamos que se haya espacio de alto y ancho.

        if (exito) { //Si hay espacio, insertamos el candidato en el inventario.
            for (int k = finY; k > finY - candidato.getAlto(); k--) {
                for (int l = finX; l > finX - candidato.getAncho(); l--) {
                    inventario.setCelda(l, k, candidato.getId());
                }
            }
        }
        return exito;
    }

}