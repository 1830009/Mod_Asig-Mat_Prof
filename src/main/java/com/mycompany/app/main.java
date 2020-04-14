package com.mycompany.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage = FXMLLoader.load(getClass().getResource("/M_Inicio.fxml"));
        stage.setTitle("Módulo de Asignación de Grupos y Materias a Profesores");
        stage.show();
    }

    public static void main(String[] args)  {
        launch(args);
    }
}
