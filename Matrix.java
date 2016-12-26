import java.util.Random;


public class Matrix {
  private int m;
  private int n;
  private double[][] entries;

  public Matrix(int m, int n) {
    this.m = m;
    this.n = n;
    entries = new double[m][n];
  }

  public Matrix(double[][] entries) {
    m = entries.length;
    n = entries[0].length;
    this.entries = new double[m][n];

    for (int i=0; i<m; i++) {
      for (int j=0; j<n; j++) {
        this.entries[i][j] = entries[i][j];
      }
    }
  }
  
  public static Matrix identity(int n) {
    Matrix I = new Matrix(n, n);

    for (int i=0; i<n; i++) {
      for (int j=0; j<n; j++) {
        I.entries[i][j] = 1.0;
      }
    }

    return I;
  }

  public static Matrix identity(int n, double a) {
    Matrix I = new Matrix(n, n);

    for (int i=0; i<n; i++) {
      for (int j=0; j<n; j++) {
        I.entries[i][j] = a;
      }
    }

    return I;
  }  

  public static Matrix random(int m, int n) {
    Matrix R = new Matrix(m, n);
    Random random = new Random();

    for (int i=0; i<m; i++) {
      for (int j=0; j<n; j++) {
        R.entries[i][j] = random.nextGaussian();
      }
    }

    return R;
  }

  public Matrix dot(Matrix B) {
    Matrix A = this;
    if (A.n != B.m) throw new RuntimeException("Improper matrix shape.");

    double [][] c = new double[A.m][B.n];
    Matrix C = new Matrix(c);

    for (int i=0; i<A.m; i++) {
      for (int j=0; j<B.n; j++) {
        for (int k=0; k<A.n; k++) {
          C.entries[i][j] += A.entries[i][k] * B.entries[k][j];
        }
      }
    }

    return C;
  }

  public void print() {
    for (int i=0; i<m; i++) {
      for (int j=0; j<n; j++) {
        System.out.printf("%.1f ", entries[i][j]);
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    double [][] a = {{1, 2}, {2, 1}};
    double [][] b = {{1, 0}, {0, 1}};

    Matrix A = new Matrix(a);
    Matrix B = new Matrix(b);
    Matrix C = new Matrix(a);

    C = A.dot(B);
    C.print();

    Matrix W = new Matrix(2, 2);
    W = Matrix.random(2, 2);
    W.print();

    Matrix I = new Matrix(2, 2);
    W = Matrix.identity(2);
    W.print();
  }
}