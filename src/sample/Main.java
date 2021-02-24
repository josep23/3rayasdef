package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;
import java.util.stream.Stream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("3 En raya");
        primaryStage.setScene(new Scene(root, 600, 400));

        primaryStage.show();

        


    }

}

