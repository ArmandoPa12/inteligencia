import java.util.LinkedList;
import java.util.Queue;


public class ReglasAplicables {
    

    public static LinkedList<Regla> reglasAlfil(int[][] A, int fila, int columna) {
        LinkedList<Regla> colaDeReglas = new LinkedList<>();
        
        int i, j;
        i = fila - 1;
        j = columna - 1;
        while (posValida(A, i, j)){
            colaDeReglas.add(new Regla(i, j));
            // colaDeReglas.add(new Regla(i, j));
            i--;
            j--;
        }  
        //2 der arriba
        i = fila - 1;
        j = columna + 1;
        while (posValida(A, i, j)){
            colaDeReglas.add(new Regla(i, j));
            i--;
            j++;
        }
        //3 Der abajo
        i = fila + 1;
        j = columna + 1;
        while (posValida(A, i, j)){
            colaDeReglas.add(new Regla(i, j));
            i++;
            j++;
        }  
        //4 Izq abajo
        i = fila + 1;
        j = columna - 1;
        while (posValida(A, i, j)){
            colaDeReglas.add(new Regla(i, j));
            i++;
            j--;
        }
        return colaDeReglas; // 1 -> 4 -> 3 -> 2 AntiHorario
    }

    public static LinkedList<Regla> reglasTorre(int[][] A, int fila, int columna){
        LinkedList<Regla> colaDeReglas = new LinkedList<>();
        int i, j;
        // 1 Por izquierda
        j = columna - 1;
        while (posValida(A, fila, j)){
            colaDeReglas.add(new Regla(fila, j));
            j--;
        }
        // 2 Por arriba  
        i = fila - 1;
        while (posValida(A, i, columna)){
            colaDeReglas.add(new Regla(i, columna));
            i--;
        }
        // 3 Por derecha
        j = columna + 1;
        while (posValida(A, fila, j)){
            colaDeReglas.add(new Regla(fila, j));
            j++;
        }  
        // 4 Por abajo
        i = fila + 1;
        while (posValida(A, i, columna)){
            colaDeReglas.add(new Regla(i, columna));
            i++;
        }
        return colaDeReglas;
    }

    public static LinkedList<Regla> reglasReyna(int[][] A, int fila, int columna){
        
        LinkedList<Regla> colaDeReglas = new LinkedList<>();
        int i, j;

         // 1 Por izquierda
        j = columna - 1;
        while (posValida(A, fila, j)){
            colaDeReglas.add(new Regla(fila, j));
            j--;
        }

        //2 Izq arriba
        i = fila - 1;
        j = columna - 1;
        while (posValida(A, i, j)){
            colaDeReglas.add(new Regla(i, j));
            i--;
            j--;
        }  
        // 3 Por arriba
        i = fila - 1;
        while (posValida(A, i, columna)){
            colaDeReglas.add(new Regla(i, columna));
            i--;
        }
        //4 der arriba
        i = fila - 1;
        j = columna + 1;
        while (posValida(A, i, j)){
            colaDeReglas.add(new Regla(i, j));
            i--;
            j++;
        }
        // 5 Por derecha 
        j = columna + 1;
        while (posValida(A, fila, j)){
            colaDeReglas.add(new Regla(fila, j));
            j++;
        } 
        //6 Der abajo
        i = fila + 1;
        j = columna + 1;
        while (posValida(A, i, j)){
            colaDeReglas.add(new Regla(i, j));
            i++;
            j++;
        }  
        // 7 Por abajo
        i = fila + 1;
        while (posValida(A, i, columna)){
            colaDeReglas.add(new Regla(i, columna));
            i++;
        }
        //8 Izq abajo
        i = fila + 1;
        j = columna - 1;
        while (posValida(A, i, j)){
            colaDeReglas.add(new Regla(i, j));
            i++;
            j--;
        }
        return colaDeReglas; 
    }

    private static boolean posValida(int[][] m, int i, int j) {
        return i >= 0 && i < m.length && 
               j >= 0 && j < m[i].length &&
               m[i][j] == 0;
    }


    public static Regla mejorRegla(LinkedList<Regla> lista, int[][] A, int iFin, int jFin) {
        Regla r = lista.get(0);

        double menorDistancia = distancia(r.fil, r.col, iFin, jFin);
        
        int posMenor = 0;
       
        for (int k = 1; k < lista.size(); k++){
            r = lista.get(k);
            if (distancia(r.fil, r.col, iFin, jFin) < menorDistancia){
                menorDistancia = distancia(r.fil, r.col, iFin, jFin);
                posMenor = k;
            }
        }
        return lista.remove(posMenor);
    }



    private static double distancia(double x1, double y1, double x2, double y2) {
        double x = x2 - x1;
        double y = y2 - y1;
        double arg = Math.pow(x, 2) + Math.pow(y, 2);
        return Math.sqrt(arg);
    }
}
