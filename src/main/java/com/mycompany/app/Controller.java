package com.mycompany.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    public Button B_AdminGrupo;
    public Button B_AsigMyP;
    public Button B_Regresa;
    public Button Btn_GuardarGrupo;
    public ChoiceBox Ch_Prof7;
    public ChoiceBox Ch_Prof6;
    public ChoiceBox Ch_Prof5;
    public ChoiceBox Ch_Prof4;
    public ChoiceBox Ch_Prof3;
    public ChoiceBox Ch_Prof2;
    public ChoiceBox Ch_Prof1= new ChoiceBox();
    public TextField T_Mat7;
    public TextField T_Mat6;
    public TextField T_Mat5;
    public TextField T_Mat4;
    public TextField T_Mat3;
    public TextField T_Mat2;
    public TextField T_Mat1;
    public TextField TM_Cuatri;
    public TextField T_Clave;
    public TableView Tbl_Grupos;
    public TableColumn C_clave;
    public TableColumn C_Cuatri;
    public TableColumn C_Plan;
    public TableColumn C_Profe;
    public TableColumn C_Materia;
    public TextField T_Cuatrimestre;
    public TextField T_Plan;
    public TextField T_Prof;
    public TextField T_Mat;
    public Button Btn_Crear;
    public TextField TM_Plan;

    SQL sqlClass= new SQL();

    public void Btn_AdminGrupo() throws IOException {
        Stage stage = FXMLLoader.load(getClass().getResource("/V_Grupos.fxml"));
        stage.setTitle("Administración de Grupos");
        stage.show();
        stage = (Stage) B_AdminGrupo.getScene().getWindow();
        stage.close();
    }

    public void Btn_AsigMyP() throws IOException {
        Stage stage = FXMLLoader.load(getClass().getResource("/V_MatYProf.fxml"));
        stage.setTitle("Asignar Materias a Profesores");
        stage.show();
        stage = (Stage) B_AsigMyP.getScene().getWindow();
        stage.close();
    }

    public void B_Back() throws IOException {
        Stage op = FXMLLoader.load(getClass().getResource("/M_Inicio.fxml"));
        op.setTitle("Módulo de Asignación de Grupos y Materias a Profesores");
        op.show();
        Stage stage = (Stage) B_Regresa.getScene().getWindow();
        stage.close();
    }

    public void B_GuardarGrupo(ActionEvent actionEvent) {
        //SQL.Guardar Grupo(String Clave,String Cuatrimestre,String Plan_Est, String[] Materias,String[] Profesores)
    }

    public void BL_Eliminar(ActionEvent actionEvent) {
    }

    public void BL_Modificar(ActionEvent actionEvent) {
    }

    public void B_Crear(ActionEvent actionEvent) throws SQLException {
        List<Materia> L_Materias= sqlClass.ConsultarMaterias(TM_Cuatri.getText(),TM_Plan.getText());
        T_Mat1.setText( L_Materias.get(0).Nombre);
        T_Mat2.setText( L_Materias.get(1).Nombre);
        T_Mat3.setText( L_Materias.get(2).Nombre);
        T_Mat4.setText( L_Materias.get(3).Nombre);
        T_Mat5.setText( L_Materias.get(4).Nombre);
        T_Mat6.setText( L_Materias.get(5).Nombre);

        if(L_Materias.size()==6)
            T_Mat7.setText("------");
        else
        T_Mat7.setText( L_Materias.get(6).Nombre);

        int i=0;
        List<Profesor> L_Profesores=sqlClass.ConsultarProfesores();
        while(L_Profesores.size()!=i){
            Ch_Prof1.getItems().add(L_Profesores.get(i).Nombre);
            i++;
        }

    }
}
