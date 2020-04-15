package com.mycompany.app;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

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
    public TableView<Grupo> Tbl_Grupos=new TableView<>();
    public TableColumn<Grupo,String> C_clave= new TableColumn<>();
    public TableColumn<Grupo,String> C_Cuatri= new TableColumn<>();
    public TableColumn<Grupo,String> C_Plan= new TableColumn<>();
    public TableColumn<Grupo,String> C_Profe= new TableColumn<>();
    public TableColumn<Grupo,String> C_Materia= new TableColumn<>();
    public TextField T_Cuatrimestre;
    public TextField T_Plan;
    public TextField T_Prof;
    public TextField T_Mat;
    public Button Btn_Crear;
    public TextField TM_Plan;

    static int Num_Materias=0;
    public Label Lbl_Grupo;
    List<Materia> L_Materias= new ArrayList<>();
    List<Profesor> L_Profesores= new ArrayList<>();
    SQL sqlClass= new SQL();

    @FXML
    public void initialize() throws SQLException {
            Ini_TablaUsuario();
            final ObservableList<Grupo> tablaPersonaSel = Tbl_Grupos.getSelectionModel().getSelectedItems();
            tablaPersonaSel.addListener(selectorTablaUsuario);

    }

    public void Btn_AdminGrupo() throws IOException, SQLException {
        //if(sqlClass.ConsultaTGrupo()==TRUE) {
          //  Ini_TablaUsuario();
            Stage stage = FXMLLoader.load(getClass().getResource("/V_Grupos.fxml"));
            stage.setTitle("Administración de Grupos");
            stage.show();
            stage = (Stage) B_AdminGrupo.getScene().getWindow();
            stage.close();
        //}
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

    public void B_GuardarGrupo(ActionEvent actionEvent) throws SQLException {
        if (Ch_Prof1.getValue()==null || Ch_Prof2.getValue()==null || Ch_Prof3.getValue()==null ||
            Ch_Prof4.getValue()==null || Ch_Prof5.getValue()==null || Ch_Prof6.getValue()==null) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar todos los profesores");
        } else if(Ch_Prof7.getValue()==null && Num_Materias==7 )
            JOptionPane.showMessageDialog(null, "Debes seleccionar todos los profesores");
          else {
            String[] Nom_Materias = new String[Num_Materias];
            String[] Nom_Profesores = new String[Num_Materias];

            Nom_Profesores[0] = String.valueOf(Ch_Prof1.getValue());
            Nom_Profesores[1] = String.valueOf(Ch_Prof2.getValue());
            Nom_Profesores[2] = String.valueOf(Ch_Prof3.getValue());
            Nom_Profesores[3] = String.valueOf(Ch_Prof4.getValue());
            Nom_Profesores[4] = String.valueOf(Ch_Prof5.getValue());
            Nom_Profesores[5] = String.valueOf(Ch_Prof6.getValue());
            if (Num_Materias == 7)
                Nom_Profesores[6] = String.valueOf(Ch_Prof7.getValue());
            boolean Repetido = FALSE;

            for (int x = 0; x < Num_Materias; x++) {
                for (int y = x+1; y < Num_Materias; y++) {
                    if (Nom_Profesores[x].equals(Nom_Profesores[y])) {
                        Repetido = TRUE;
                        y = x = Num_Materias;
                    }
                }
            }
            if (Repetido) {
                JOptionPane.showMessageDialog(null, "\tLo sentimos No es Psoible Guardar el Grupo.\n\t" +
                        "[Error:238] Los Profesores no pueden impartir mas de Una Materia por Grupo.");
            } else {

                for (int i = 0; i < Num_Materias; i++)
                    Nom_Materias[i] = L_Materias.get(i).Nombre;

                System.out.println(Arrays.toString(Nom_Materias));
                System.out.println(Arrays.toString(Nom_Profesores));
                String Prof_ID[] = new String[Num_Materias];
                String Mat_ID[] = new String[Num_Materias];
                //Encontrar IDs de Profesores y Maestros
                boolean HorasMax=FALSE;
                for (int i = 0; i < Num_Materias; i++) {
                    Mat_ID[i] = L_Materias.get(i).Materia_ID;
                    for (int j = 0; j < L_Profesores.size(); j++) {
                        if (Nom_Profesores[i].equals(L_Profesores.get(j).Nombre)) {
                            Prof_ID[i] = L_Profesores.get(j).Clave;
                            int H_Prof=Integer.parseInt(String.valueOf(L_Profesores.get(j).HorasDisp));
                            int H_Mat=Integer.parseInt(String.valueOf(L_Materias.get(i).Num_Horas ));
                            if( H_Prof - H_Mat < 0) {
                                JOptionPane.showMessageDialog(null, "El Profesor " + L_Profesores.get(j).Nombre +
                                        " Ha alcanzado el Num. Maximo de Horas para impartir clase");
                                HorasMax=TRUE;
                            }
                                break;
                        }
                    }
                }
                if(HorasMax==FALSE)
                sqlClass.GuardarGrupo(T_Clave.getText(), TM_Cuatri.getText(), TM_Plan.getText(), Mat_ID, Prof_ID);
            }
        }
          TM_Plan.setText("");
          TM_Cuatri.setText("");
          T_Clave.setText("");
    }

    public void BL_Eliminar(ActionEvent actionEvent) throws SQLException {
        if (JOptionPane.showConfirmDialog(null, "Deseas Eliminar el Grupo: "+T_Clave.getText()+"?",
                "Advertencia!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            sqlClass.EliminarGrupo(T_Clave.getText());
            T_Clave.setText("");
            T_Prof.setText("");
            T_Mat.setText("");
            T_Plan.setText("");
            T_Cuatrimestre.setText("");
        }
    }

    public void B_Crear(ActionEvent actionEvent) throws SQLException {
        L_Materias= sqlClass.ConsultarMaterias(TM_Cuatri.getText(),TM_Plan.getText());
        T_Mat1.setText( L_Materias.get(0).Nombre);
        T_Mat2.setText( L_Materias.get(1).Nombre);
        T_Mat3.setText( L_Materias.get(2).Nombre);
        T_Mat4.setText( L_Materias.get(3).Nombre);
        T_Mat5.setText( L_Materias.get(4).Nombre);
        T_Mat6.setText( L_Materias.get(5).Nombre);
        Num_Materias=L_Materias.size();

        if(Num_Materias==6) {
            T_Mat7.setText("------");
            Ch_Prof7.setDisable(TRUE);
        }else
        T_Mat7.setText( L_Materias.get(6).Nombre);

        int i=0;
        L_Profesores=sqlClass.ConsultarProfesores();
        while(L_Profesores.size()!=i){
            Ch_Prof1.getItems().add(L_Profesores.get(i).Nombre);
            i++;
        }
        Ch_Prof2.getItems().addAll(Ch_Prof1.getItems());
        Ch_Prof3.getItems().addAll(Ch_Prof1.getItems());
        Ch_Prof4.getItems().addAll(Ch_Prof1.getItems());
        Ch_Prof5.getItems().addAll(Ch_Prof1.getItems());
        Ch_Prof6.getItems().addAll(Ch_Prof1.getItems());
        if(Ch_Prof7.isDisable()==FALSE)
        Ch_Prof7.getItems().addAll(Ch_Prof1.getItems());
        Lbl_Grupo.setText("2. Asignar Materias a Profesores");
        JOptionPane.showMessageDialog(null,"Grupo Creado!\nPor favor selecciona a los Profesores.");
    }

    /**
     * Visualizar Tabla de Grupos
     */
    public int posicion=0;

    private final ListChangeListener<Grupo> selectorTablaUsuario = new ListChangeListener<Grupo>() {
        @Override
        public void onChanged(ListChangeListener.Change<? extends Grupo> c) { ponerUsuarioSelec(); }  };

    public Grupo getTablaUsuarioSeleccionada() {
        if (Tbl_Grupos != null) {
            List<Grupo> tabla = Tbl_Grupos.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Grupo competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    private void ponerUsuarioSelec() {
        final Grupo user = getTablaUsuarioSeleccionada();
        posicion = Lista_Grupo.indexOf(user);

        if (user != null) {
            T_Clave.setText(user.getID());
            T_Cuatrimestre.setText(user.getCuatri());
            T_Plan.setText(user.getPlan_ID());
            T_Mat.setText(user.getMat());
            T_Prof.setText(user.getProf());
        }
    }

    void Ini_TablaUsuario() throws SQLException {
        C_clave.setCellValueFactory(new PropertyValueFactory<Grupo, String>("Grupo_ID"));
        C_Cuatri.setCellValueFactory(new PropertyValueFactory<Grupo, String>("Cuatrimestre"));
        C_Plan.setCellValueFactory(new PropertyValueFactory<Grupo, String>("plan_ID"));
        C_Materia.setCellValueFactory(new PropertyValueFactory<Grupo, String>("Mat"));
        C_Profe.setCellValueFactory(new PropertyValueFactory<Grupo, String>("Prof"));
        Lista_Grupo=sqlClass.ConsultarGrupos();
        System.out.println("["+Lista_Grupo.get(0).Grupo_ID+" , "+Lista_Grupo.get(0).Cuatrimestre+"]");
        Tbl_Grupos.setItems(Lista_Grupo);
    }
    ObservableList<Grupo> Lista_Grupo= FXCollections.observableArrayList();
    //static ObservableList<Grupo> L_Usuario;

}
