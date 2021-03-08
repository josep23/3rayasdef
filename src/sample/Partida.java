package sample;

import static sample.Controller.Ganador;

public class Partida {

    private static String ia;
    private static String modo;
    private static int Winsplayer1;
    private static int Winsplayer2;
    private static int Losesplayer1;
    private static int Losesplayer2;
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

    public static void setWinsplayer1(int winsplayer1) {
        Partida.Winsplayer1 = winsplayer1;
    }

    public static void setWinsplayer2(int winsplayer2) {
        Partida.Winsplayer2 = winsplayer2;
    }

    public static void setLosesplayer1(int losesplayer1) {
        Partida.Losesplayer1 = losesplayer1;
    }

    public static void setLosesplayer2(int losesplayer2) {
        Partida.Losesplayer2 = losesplayer2;
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

    public static int getWinsplayer1() {
        return Winsplayer1;
    }

    public static int getWinsplayer2() {
        return Winsplayer2;
    }

    public static int getLosesplayer1() {
        return Losesplayer1;
    }

    public static int getLosesplayer2() {
        return Losesplayer2;
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
            Ganador(0);
            Winsplayer1 = Winsplayer1 +1;
            Losesplayer1 = Losesplayer1 +1;
        }else {
            Ganador(1);
            Winsplayer2 = Winsplayer2 +1;
            Losesplayer1 = Losesplayer1 +1;
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
