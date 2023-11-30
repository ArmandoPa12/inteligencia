import java.util.LinkedList;

public class App {

    public static int cant = 0;
    public static void main(String[] args) throws Exception {
        

        int b = 3;
        int a = 3;

        int m[][] = new int[a][b];

        // m[0][1] = -1;


        // laberintoA(m, 2, 1, 1);
        laberintoC(m, 1, 1, a-1, b-1, 1);
        System.out.println("cantidad de soluciones: " + cant);



    }

    //heulistica
    public static boolean laberintoC(int m[][], int i, int j,int i1, int j1, int paso){
        if(!posValida(m,i,j)){
            return false;
        }
        
        m[i][j] = paso;

        if (i == i1 && j == j1){
            mostrar(m);
            cant++;
        }

        //LinkedList<Regla> L1 = reglasAplicables(m,i,j);
        //LinkedList<Regla> L1 = reglasAplicablesCaballo(m,i,j);
        //LinkedList<Regla> L1 = ReglasAplicables.reglasAlfil(m,i,j);
        LinkedList<Regla> L1 = ReglasAplicables.reglasTorre(m,i,j);
        // LinkedList<Regla> L1 = ReglasAplicables.reglasReyna(m,i,j);



        Regla r;

        while (!L1.isEmpty()){

            r = ReglasAplicables.mejorRegla(L1, m, i, j);

            if (laberintoC(m, r.fil, r.col, i1, j1, paso + 1)){
                return true;
            }
            // laberintoC(m, r.fil, r.col, i1, j1, paso+1);
            m[r.fil][r.col] = 0;
        }
        return false;
    }


    //
    public static void laberintoCH(int m[][], int i, int j,int i1, int j1, int paso){
        if(!posValida(m,i,j)){
            return ;
        }
        
        m[i][j] = paso;

        if (i == i1 && j == j1){
            mostrar(m);
            cant++;
        }

        //LinkedList<Regla> L1 = reglasAplicables(m,i,j);
        // LinkedList<Regla> L1 = reglasAplicablesCaballo(m,i,j);
        // LinkedList<Regla> L1 = ReglasAplicables.reglasAlfil(m,i,j);
        LinkedList<Regla> L1 = ReglasAplicables.reglasTorre(m,i,j);
        // LinkedList<Regla> L1 = ReglasAplicables.reglasReyna(m,i,j);


        while (!L1.isEmpty()){
            Regla r = L1.removeFirst();
            // Regla r = elegirRegla(L1,m,i1,j1);
            laberintoC(m, r.fil, r.col, i1, j1, paso+1);
            m[r.fil][r.col] = 0;
        }

    }
    private static LinkedList<Regla> reglasAplicablesCaballo(int[][] m, int fi, int co) {

        LinkedList<Regla> l1 = new LinkedList<>();

        // arriba izq
        if(posValida(m, fi-1, co-2)){
            l1.add(new Regla(fi-1, co-2));
        }
        // arriba der
        if(posValida(m, fi+1, co-2)){
            l1.add(new Regla(fi+1, co-2));
        }

        // abajo izq
        if(posValida(m, fi-1, co+2)){
            l1.add(new Regla(fi-1, co+2));
        }
        // abajo der
        if(posValida(m, fi+1, co+2)){
            l1.add(new Regla(fi+1, co+2));
        }

        // derecha izq
        if(posValida(m, fi+2, co-1)){
            // l1.add(new Regla(fi+2, co-1));
            l1.add(new Regla(fi+2, co-1));
        }
        // derecha der
        if(posValida(m, fi-2, co+1)){
            l1.add(new Regla(fi-2, co+1));
        }

        // izquierda izq
        if(posValida(m, fi-2, co-1)){
            l1.add(new Regla(fi-2, co-1));
        }
        // izquierda der
        if(posValida(m, fi-2, co+1)){
            l1.add(new Regla(fi-2, co+1));
        }

        return l1;
    }


    private static LinkedList<Regla> reglasAplicables(int[][] m, int i, int j) {

        LinkedList<Regla> l1 = new LinkedList<>();
        
        if(posValida(m, i, j-1)){
            l1.add(new Regla(i, j-1));
        }

        if(posValida(m, i-1, j)){
            l1.add(new Regla(i-1, j));
        }

        if(posValida(m, i, j+1)){
            l1.add(new Regla(i, j+1));
        }

        if(posValida(m, i+1, j)){
            l1.add(new Regla(i+1, j));
        }

        return l1;
    }

    public static void laberintoB(int m[][], int i, int j,int i1, int j1, int paso){
        if(!posValida(m,i,j)){
            return ;
        }
        
        m[i][j] = paso;

        if (i == i1 && j == j1){
            mostrar(m);
            cant++;
        }

        laberintoB(m, i, j-1,i1,j1 ,paso+1);
        laberintoB(m, i-1, j,i1,j1 , paso+1);
        laberintoB(m, i, j+1,i1,j1 , paso+1);
        laberintoB(m, i+1, j,i1,j1 , paso+1);
    
        m[i][j] = 0;
    }

    private static boolean visitadoT(int[][] m) {
        boolean v = true;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == 0){
                    return !v;
                }
            }
            
        }
        
        return v;
    }

    public static void laberintoA(int m[][], int i, int j, int paso){
        if(!posValida(m,i,j)){
            return ;
        }

        m[i][j] = paso;
  
        mostrar(m);

        laberintoA(m, i, j-1, paso+1);
        laberintoA(m, i-1, j, paso+1);
        laberintoA(m, i, j+1, paso+1);
        laberintoA(m, i+1, j, paso+1);
        
        m[i][j] = 0;
    }

    private static void mostrar(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == -1){
                    System.out.print("X" + " ");
                }else{
                    System.out.print(m[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean posValida(int[][] m, int i, int j) {
        return i >= 0 && i < m.length && 
               j >= 0 && j < m[i].length &&
               m[i][j] == 0;
    }
}
