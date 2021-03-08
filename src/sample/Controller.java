package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.util.Optional;


public class Controller {
    private static Partida partida = new Partida();
    @FXML Button button_empezar_partida;
    @FXML TextArea Winsplayer1;
    @FXML TextArea Winsplayer2;
    @FXML TextArea Losesplayer1;
    @FXML TextArea Losesplayer2;
    @FXML Text textoplayer1, textoplayer2;
    @FXML Button b0, b1, b2, b3, b4, b5, b6, b7, b8,bc;
    @FXML Button[] listabotones = new Button[8];
    @FXML
    public void Empezar_Partida(ActionEvent event) {
        button_empezar_partida = (Button) event.getSource();
        listabotones = new Button[]{b0, b1, b2, b3, b4, b5, b6, b7, b8};

        partida.setModo(Elige_Modo());


        if(partida.getModo() != null) {
            if(partida.getModo().equals("VSHumano")) {
                partida.EmpezarPartida();
                OcultarBoton(button_empezar_partida);
            }else {
                    partida.EmpezarPartida();
                if(partida.getModo().equals("ComVSCom")) {
                    TurnoCOM(partida.getTabla());
                }

            }
        }
    }


    @FXML
    public void Marcar(ActionEvent event) throws InterruptedException {

        if(partida.getEstado()) {
            bc = (Button) event.getSource();
            String sid = bc.getId().replaceAll("[b]","");
            int id =Integer.valueOf(sid);
            char[] cuadricula = partida.getTabla();
            partida.PropiedadesTurno();

            //Si la casilla NO está marcada, podremos marcarla.
            if(partida.EstadoCuadricula(cuadricula[id])) {
                bc.setText(partida.getValue());
                partida.setPosCuadricula(id,partida.getValue().charAt(0));

                //Comprobamos si se dan las condiciones de Victoria.
                if(partida.ComprobarVictoria(partida.getValue().charAt(0))) {
                    Partida.AnunciarGanador(partida.getValue().charAt(0));
                    Restart();
                    partida.FinalizarPartida();
                }else {
                    if(partida.CuadriculaLLena()) {
                        Empate();
                        Restart();
                        partida.FinalizarPartida();
                    }
                    else {
                        if(partida.getTurno() == 0) {
                            SetTurno(1, textoplayer1, textoplayer2);
                        }else {
                            SetTurno(0, textoplayer2, textoplayer1);
                        }
                    }
                }
                if(!partida.getModo().equals("VSHumano") && partida.getEstado()) {
                    TurnoCOM(cuadricula);
                }
            }
        }
    }

    /*Método que se ejecuta cuando es el Turno de COM, seteando las propiedades del turno,
     * y eligiendo movimiento a realizar segun dificultad elegida.*/
    private void TurnoCOM(char[] cuadricula) {
        partida.PropiedadesTurno();
        Ia(cuadricula);

        if(partida.getEstado()) {
            if(partida.getModo().equals("ComVSCom")) {
                if(partida.getTurno()==0) {
                    SetTurno(1, textoplayer1, textoplayer2);
                }else {
                    SetTurno(0, textoplayer2, textoplayer1);
                }
                TurnoCOM(cuadricula);
            }else {
                SetTurno(0, textoplayer2, textoplayer1);
            }
        }
    }
    public static String Elige_Modo() {
        String modo = null;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Elige un modo de juego");
        alert.setHeaderText("Humano VS Humano: Partida humano contra humano.\nHumano VS COM: Partida humano contra el ordenador.\nCOM VS COM: Partida ordenador vs ordenador");

        ButtonType btHumanoVsHumano = new ButtonType("VSHumano");
        ButtonType btHumanoVsCom = new ButtonType("VSCom");
        ButtonType btComVsCom = new ButtonType("ComVSCom");

        alert.getButtonTypes().setAll(btHumanoVsHumano,btHumanoVsCom,btComVsCom,ButtonType.CLOSE);
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == btHumanoVsHumano) {
            modo = "VSHumano";
        }else if(result.get() == btHumanoVsCom) {
            modo = "VSCom";
        }else if(result.get() == btComVsCom) {
            modo = "ComVSCom";
        }else {
            alert.close();
            modo = null;
        }

        return modo;
    }

    public  static void Ganador(int ganador) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if(ganador == 0) {
            alert.setTitle("¡VICTORIA!");
            alert.setHeaderText("¡Enhorabuena jugador 1, has ganado!");
        }else {
            alert.setTitle("¡DERROTA!");
            alert.setHeaderText("¡Jugador 1, has perdido!");
        }
        alert.getButtonTypes().setAll(ButtonType.CLOSE);
        alert.showAndWait();
    }

    public  static void Empate() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("¡EMPATE!");
        alert.setHeaderText("¡Ningun jugador ha logrado ganar al otro!");
        alert.getButtonTypes().setAll(ButtonType.CLOSE);
        alert.showAndWait();
    }


    private void Ia(char[] cuadricula) {
        int random;
        do{
            random = (int) (Math.random()*10-1);
        }while (!partida.EstadoCuadricula(cuadricula[random]));
        listabotones[random].setText(partida.getValue());
        partida.setPosCuadricula(random,partida.getValue().charAt(0));
        if(partida.ComprobarVictoria(partida.getValue().charAt(0))) {
            Partida.AnunciarGanador(partida.getValue().charAt(0));
            Restart();
            partida.FinalizarPartida();
        }else {
            if(partida.CuadriculaLLena()) {
                Empate();
                Restart();
                partida.FinalizarPartida();
            }
        }
    }


    private void OcultarBoton(Button button) {
        button.styleProperty().setValue("Visibility: false");
    }

    private void MostrarBoton(Button button) {
        button.styleProperty().setValue("Visibility: true");
    }


    private void RefrescarMarcador(Partida partida, TextArea Winsplayer1, TextArea Winsplayer2, TextArea Losesplayer1, TextArea Losesplayer2) {
        Winsplayer1.setText(String.valueOf(partida.getWinsplayer1()));
        Winsplayer2.setText(String.valueOf(partida.getWinsplayer2()));
        Losesplayer1.setText(String.valueOf(partida.getLosesplayer1()));
        Losesplayer2.setText(String.valueOf(partida.getLosesplayer2()));
    }

    private void Limpiar() {
        for (Button button : listabotones) {
            button.textProperty().setValue("");
        }
    }
    private void Restart() {
        RefrescarMarcador(partida, Winsplayer1, Winsplayer2, Losesplayer1, Losesplayer2);
        MostrarBoton(button_empezar_partida);
        Limpiar();
    }

    //Comprueba los turnos
    private void SetTurno(int turno,Text turnoOcultar, Text turnoMostrar) {
        partida.setTurno(turno);
    }
}
