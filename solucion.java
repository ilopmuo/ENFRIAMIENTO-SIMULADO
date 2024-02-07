package EnfriamientoSimulado;

//Clase para la solución, nuestra solución va a ser un vector de 5 bits y con ese vector vamos a hallar un coste
public class solucion implements Cloneable {

  int[] sol;
  double coste;

  //Constructores
  public solucion() {
    this.sol = new int[] { 0, 0, 0, 0, 0 };
    this.coste = calcularCoste();
  }

  public solucion(int[] sol, double coste) {
    this.sol = sol;
    this.coste = calcularCoste();
  }

  //Getters Setters
  public int[] getSol() {
    return this.sol;
  }

  public void setSol(int[] sol) {
    this.sol = sol;
    this.coste = calcularCoste();
  }

  public double getCoste() {
    return this.coste;
  }

  public void setCoste(double coste) {
    this.coste = coste;
  }

  //Binario a decimal
  public int binaryToDecimal(int[] sol) {
    int i;
    int n = 0;
    for (i = 0; i < sol.length; i++) {
      if (this.sol[i] != 0) {
        n += Math.pow(i, 2);
      }
    }
    return n;
  }

  //Calculo del coste
  public double calcularCoste() {
    int n = binaryToDecimal(this.sol);
    return Math.pow(n, 3) - 60 * Math.pow(n, 2) + 900 * n + 100;
  }

  //Método para cambiar de valor un bit
  public void setBitSol(int index, int value) {
    this.sol[index] = value;
    this.coste = calcularCoste();
  }

  //To string típico

  @Override
  public String toString() {
    return (
      "{" +
      " sol='" +
      binaryToDecimal(sol) +
      "'" +
      ", coste='" +
      coste +
      "'" +
      "}"
    );
  }

  //Clone de clonar
  public solucion clone() {
    solucion clon = new solucion();

    for (int i = 0; i < this.sol.length; i++) {
      clon.sol[i] = this.sol[i];
    }
    clon.coste = this.coste;

    return clon;
  }
}
