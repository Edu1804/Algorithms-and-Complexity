public class Principal {

    /**
     * Calcula el numero mínimo de ediciones necesarias para obtener la cadena de destino.
     * Las operaciones disponibles son: Inserción, eliminación y sustitución.
     * Para conseguir esto se sobrecarga el método para llamar recursivamente.
     *
     * @param cadena1 Cadena de origen.
     * @param cadena2 Cadena de destino.
     * @return Numero mínimo de ediciones necesarias para obtener la cadena de destino.
     * @see #numMinMutaciones(String, String, int, int) Método recursivo
     */
    public static int numMinMutaciones(String cadena1, String cadena2) {
        return numMinMutaciones(cadena1, cadena2, 0, Integer.MAX_VALUE);
    }

    /**
     * Método recursivo
     * Calcula el numero mínimo de ediciones necesarias para obtener la subcadena de destino.
     * Las operaciones disponibles son: Inserción, eliminación y sustitución.
     *
     * @param cadenaOrigen   Cadena de origen.
     * @param cadenaObjetivo Cadena de destino.
     * @param actual         Número de movimientos acumulados.
     * @param mejor          Mejor solución encontrada.
     * @return El menor número de elementos para convertir la substring cadenaOrigen en cadenaObjetivo.
     * @see #numMinMutaciones(String, String) Método de entrada
     */
    private static int numMinMutaciones(String cadenaOrigen, String cadenaObjetivo, int actual, int mejor) {
        //Si ya hemos acabado con la palabra volvemos y guardamos la solución en caso de ser mejor que la anterior.
        if (cadenaObjetivo.length() == 0 && cadenaOrigen.length() == 0) mejor = Math.min(mejor, actual);
            /*
             * Si alguna de las dos, pero no ambas están terminadas de recorrer,
             * anotamos las inserciones necesarias para terminar y guardamos la solución en caso de ser mejor que la anterior
             */
        else if (cadenaObjetivo.length() == 0) mejor = Math.min(mejor, actual + cadenaOrigen.length());
        else if (cadenaOrigen.length() == 0) mejor = Math.min(mejor, actual + cadenaObjetivo.length());

            //En cualquier otro caso, si no llevamos más ediciones de la mejor marca, seguimos
        else if (actual < mejor) {
            //Se comprueba el primer carácter de los substrings.
            if (cadenaOrigen.charAt(0) == cadenaObjetivo.charAt(0))
                /*
                 * Y en caso de ser iguales anotamos deshaciéndonos del primer carácter,
                 * llamamos recursivamente y guardamos la solución en caso de ser mejor que la anterior.
                 */
                mejor = Math.min(numMinMutaciones(cadenaOrigen.substring(1), cadenaObjetivo.substring(1), actual, mejor), mejor);

            else {

                if (cadenaOrigen.length() == 1)
                    mejor = Math.min(numMinMutaciones(cadenaObjetivo.substring(0, 1), cadenaObjetivo, actual + 1, mejor), mejor );
                else
                    //Sustituimos, llamamos recursivamente y guardamos la solución en caso de ser mejor que la anterior.
                    mejor = Math.min(numMinMutaciones(cadenaObjetivo.charAt(0) + cadenaOrigen.substring(1), cadenaObjetivo, actual + 1, mejor), mejor);
                    //Insertamos, llamamos recursivamente y guardamos la solución en caso de ser mejor que la anterior.
                    mejor = Math.min(numMinMutaciones(cadenaObjetivo.charAt(0) + cadenaOrigen, cadenaObjetivo, actual + 1, mejor), mejor);
                    //Eliminamos, llamamos recursivamente y guardamos la solución en caso de ser mejor que la anterior.
                    mejor = Math.min(numMinMutaciones(cadenaOrigen.substring(1), cadenaObjetivo, actual + 1, mejor), mejor);
            }
        }
        return mejor;
    }
}