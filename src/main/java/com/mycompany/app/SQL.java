package com.mycompany.app;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class SQL {
     Connection conectar = null;

    public void generarConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//.newInstance();
            conectar = DriverManager.getConnection("jdbc:mysql://localhost/POO?serverTimezone=UTC",
                                                    "root", "1386");
            System.out.println("Se ha conectado");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public boolean ConsultaTGrupo() throws SQLException {
        generarConexion();
        Statement s = conectar.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM Grupos");
        if(rs.getRow()!=0)
        return TRUE;
        else{
            return FALSE;
        }
    }
    public void Insertar() throws SQLException {
        generarConexion();
        Statement s= conectar.createStatement();
         s.executeUpdate("INSERT INTO `POO`.`Profesores` (`Profesor_ID`,`Nombre`,`Hrs_Disponible`)\n" +
                 "VALUES ('04','Edith Gonzalez','15')");
    }
    public void GuardarGrupo(String Clave,String Cuatrimestre,String Plan_Est, String[] Materias,String[] Profesores) throws SQLException {
        System.out.println(Arrays.toString(Materias));
        System.out.println(Arrays.toString(Profesores));
        try {
            generarConexion();
            Statement s = conectar.createStatement();
            int i;
            for (i = 0; i < 7; i++) {
                s.executeUpdate("INSERT INTO POO.Grupos (Clave,Cuatrimestre,Plan_ID,Materia_ID,Profesor_ID)\n" +
                        "VALUES('" + Clave + "','" + Cuatrimestre + "','" + Plan_Est + "','" + Materias[i] + "','" + Profesores[i] + "');");
                s.close();
                s = conectar.createStatement();
            }
            JOptionPane.showMessageDialog(null,"El Grupo "+ Clave+"\nHa sido GUardado Exitosamente!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lo sentimos No fue posible Guardar el Grupo\nIntente de nuevo");
        }
    }

      public ObservableList<Grupo> ConsultarGrupos() throws SQLException {
        generarConexion();
        ObservableList<Grupo> lista = FXCollections.observableArrayList();
          Statement s= conectar.createStatement();
          ResultSet rs= s.executeQuery("SELECT Clave,Cuatrimestre,Plan_ID,Profesor_ID,Materia_ID FROM Grupos;");
          while(rs.next()){
              lista.add(new Grupo(rs.getString(1),rs.getString(2),rs.getString(3),
                                  rs.getString(4),rs.getString(5)));
          }

        return lista;
    }
    public void EliminarGrupo(String Clave_ID) throws SQLException {
        try {
            generarConexion();
            Statement s = conectar.createStatement();
            s.executeUpdate("DELETE Grupos.* FROM Grupos WHERE Clave= '" + Clave_ID + "';");
            JOptionPane.showMessageDialog(null,"Grupo Eliminado Correctamente!");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Lo sentimos\nNO fue Posible Borrar el Grupo");
        }
    }

    public List<Materia> ConsultarMaterias(String Cuatri,String Plan) throws SQLException {
        generarConexion();
        List<Materia> L_Materias= new ArrayList<Materia>();
        Statement s= conectar.createStatement();
        ResultSet rs= s.executeQuery("SELECT Materia_ID,Nombre,Horas FROM Materias " +
                                       "WHERE Cuatrimestre= '"+Cuatri+"' AND Plan_ID='"+Plan+"';");
        while(rs.next()){
            L_Materias.add(new Materia(rs.getString(1),rs.getString(2),rs.getInt(3)));
            System.out.println("Materia: "+rs.getString(2));
        }
        return L_Materias;
    }

    public List<Profesor> ConsultarProfesores() throws SQLException {
        generarConexion();
        List<Profesor> L_Profesores= new ArrayList<Profesor>();
        Statement s= conectar.createStatement();
        ResultSet rs= s.executeQuery("SELECT Profesor_ID,Nombre,Hrs_Disponible FROM Profesores");
        while(rs.next()){
            L_Profesores.add(new Profesor(rs.getString(1),rs.getString(2),rs.getInt(3)));
            System.out.println("Profe: "+rs.getString(2));
        }
        return L_Profesores;
    }
}
