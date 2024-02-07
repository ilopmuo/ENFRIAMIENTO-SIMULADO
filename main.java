package EnfriamientoSimulado;

public class main {

  //Declaramos variables finales

  public static final int T_INICIAL = 250;
  public static final int T_FINAL = 1;
  public static final int N_BITS = 5;
  public static final int N_VECINOS = 5;

  public static void main(String[] args) {
    solucion sol = enfriamientoSimulado();
    System.out.println("La solución del ejercicio es: \n");

    sol.toString();
  }

  public static solucion enfriamientoSimulado() {
    //Declaramos la matiz de vecinos, inicializamos los aux de soluciones, costes, delta, exitos y temperatura
    solucion[] vecinos = new solucion[N_VECINOS];

    solucion solActual = new solucion();
    solucion solMejor = solActual;
    double costeActual = solActual.calcularCoste();
    double costeMejor = costeActual;
    double costeVecino;
    double delta = 0;
    int exitos = -1;
    double T = T_INICIAL;

    //1er bucle, WHILE, como condición le damos que la temperatura no se haya enfriado y que haya éxitos
    while (T > T_FINAL && exitos != 0) {
      //Ponemos exitos a 0 por si no encontramos nada y generamos las posibles soluciones
      exitos = 0;
      generarVecinos(vecinos, solActual);
      //Bucle FOR, recorre toda la matriz de soluciones, comprobando si el coste es mayor
      for (int i = 0; i < N_VECINOS; i++) {
        costeVecino = vecinos[i].calcularCoste();
        delta = costeVecino - costeActual;
        //Si el coste es mayor o la prob acept se cumple, cambia la sol act y suma un éxito
        if (delta > 0 || probAceptacion(delta, T) == true) {
          exitos++;
          solActual = vecinos[i].clone();
          costeActual = costeVecino;
          //Mira a ver si la sol actual es mejor que la encontrada antes
          if (costeActual > costeMejor) {
            costeMejor = costeVecino;
            solMejor = vecinos[i].clone();
          }
        }
      }
      System.out.println(
        "\nsolMejor tras la iteracion: " + solMejor.toString() + "\n"
      );
      T -= 0.1 * T; // Enfriamiento por descenso geométrico, según enunciado
    }
    if (exitos == 0) {
      System.out.println(
        "\nSe termina el proceso de búsqueda por no haber mejores vecinos"
      );
    } else {
      System.out.println(
        "\nSe termina el proceso de búsqueda por enfriarse el proceso"
      );
    }
    return solMejor;
  }

  //Método para generar los vecinos, le mete 5 objetos de tipo solución a la matriz vecinos

  public static void generarVecinos(solucion[] vecinos, solucion solAct) {
    for (int i = 0; i < N_VECINOS; i++) {
      vecinos[i] = solAct.clone();
      permuta(vecinos[i], i);
      System.out.println(
        "\nVecino " + i + " generado: " + (vecinos[i]).toString()
      );
      System.out.println("\n");
    }
  }

  private static void permuta(solucion sVecino, int n) {
    // Metodo para permutar un bit
    if (sVecino.getSol()[n] == 1) {
      sVecino.setBitSol(n, 0);
    } else {
      sVecino.setBitSol(n, 1);
    }
  }

  private static boolean probAceptacion(double delta, double temperatura) {
    // Metodo para calcular la probabilidad de aceptacion
    // Como delta < 0 no hace falta poner el signo menos que hay en la fórmula de la
    // EB
    return (Math.exp((delta) / temperatura) > Math.random());
  }
}
