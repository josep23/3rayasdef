package sample;

import static sample.Controller.Anunciar_Ganador;

public class Partida {

    private static String ia;
    private static String modo;
    private static int WINSPLAYER1;
    private static int WINSPLAYER2;
    private static int LOSESPLAYER1;
    private static int LOSESPLAYER2;
    private int turno =0;
    private char[] Tabla = new char[9];
    private static boolean Empezar = false;
    private String value;

    public static void setIa(String ia) {
        Partida.ia = ia;
    }

    public static void setModo(String modo) {
        Partida.modo = modo;
    }

    public static void setWINSPLAYER1(int WINSPLAYER1) {
        Partida.WINSPLAYER1 = WINSPLAYER1;
    }

    public static void setWINSPLAYER2(int WINSPLAYER2) {
        Partida.WINSPLAYER2 = WINSPLAYER2;
    }

    public static void setLOSESPLAYER1(int LOSESPLAYER1) {
        Partida.LOSESPLAYER1 = LOSESPLAYER1;
    }

    public static void setLOSESPLAYER2(int LOSESPLAYER2) {
        Partida.LOSESPLAYER2 = LOSESPLAYER2;
    }

    public void setTabla(char[] tabla) {
        Tabla = tabla;
    }

    public static void setEmpezar(boolean empezar) {
        Partida.Empezar = empezar;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public static String getIa() {
        return ia;
    }

    public static String getModo() {
        return modo;
    }

    public static int getWINSPLAYER1() {
        return WINSPLAYER1;
    }

    public static int getWINSPLAYER2() {
        return WINSPLAYER2;
    }

    public static int getLOSESPLAYER1() {
        return LOSESPLAYER1;
    }

    public static int getLOSESPLAYER2() {
        return LOSESPLAYER2;
    }

    public char[] getTabla() {
        return Tabla;
    }

    public static boolean isEmpezar() {
        return Empezar;
    }

    public String getValue() {
        return value;
    }


    public boolean getEstado() { return Empezar; }

    public void setTurno(int turno) { this.turno = turno;}

    public int getTurno() {return turno;}



    //Comprovar quien gana
    public boolean ComprobarVictoria(char value) {
        boolean victoria = false;
        if(Tabla[0] == value && Tabla[1] == value && Tabla[2] == value) {
            victoria = true;
        }else if(Tabla[3] == value && Tabla[4] == value && Tabla[5] == value) {
            victoria = true;
        }else if(Tabla[6] == value && Tabla[7] == value && Tabla[8] == value) {
            victoria = true;
        }else if(Tabla[0] == value && Tabla[3] == value && Tabla[6] == value) {
            victoria = true;
        }else if(Tabla[1] == value && Tabla[4] == value && Tabla[7] == value) {
            victoria = true;
        }else if(Tabla[2] == value && Tabla[5] == value && Tabla[8] == value) {
            victoria = true;
        }else if(Tabla[0] == value && Tabla[4] == value && Tabla[8] == value) {
            victoria = true;
        }else if(Tabla[2] == value && Tabla[4] == value && Tabla[6] == value) {
            victoria = true;
        }
        return victoria;
    }


    public static void AnunciarGanador(char value) {
        if(value == 'X') {
            Anunciar_Ganador(0);
            WINSPLAYER1 = WINSPLAYER1 +1;
            LOSESPLAYER1 = LOSESPLAYER1 +1;
        }else {
            Anunciar_Ganador(1);
            WINSPLAYER2 = WINSPLAYER2 +1;
            LOSESPLAYER1 = LOSESPLAYER1 +1;
        }
    }


    //Empezar partida
    public void EmpezarPartida() {
        Empezar = true;
       turno=1;
    }

    //End
    public void FinalizarPartida() {
        Empezar = false;
        VaciarCuadricula();
    }


    //Comprueba si esta llena la tabla
    public boolean CuadriculaLLena() {
        boolean resultado = true;
        for(int i = 0; i< Tabla.length; i++) {
            if(Tabla[i] != 'X' && Tabla[i] != 'O') {
                resultado = false;
                break;
            }
        }
        return resultado;
    }

    public void PropiedadesTurno() {
        if(turno==0) {
            value = "X";
        }else {
            value = "O";
        }
    }

    //Coloca la ficha
    public void setPosCuadricula(int index,char value) {

        Tabla[index] = value;
    }

    public void VaciarCuadricula() {

        for (int i = 0; i< Tabla.length; i++) {
            Tabla[i] = ' ';
        }
    }

    //Para saber si esta ocupada
    public boolean EstadoCuadricula(char value) {
        boolean result;
        if(value != 'X' && value != 'O') {
            result = true;
        }else {
            result = false;
        }
        return result;
    }

}
