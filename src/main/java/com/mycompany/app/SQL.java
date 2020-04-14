package com.mycompany.app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void Consultar() throws SQLException {
        generarConexion();
        Statement s= conectar.createStatement();
        ResultSet rs= s.executeQuery("SELECT * FROM Profesores");
                while(rs.next()){
                    System.out.println("Clave:"+rs.getString(1)+" Name: "+rs.getString(2));
                }
    }
    public void Insertar() throws SQLException {
        generarConexion();
        Statement s= conectar.createStatement();
         s.executeUpdate("INSERT INTO `POO`.`Profesores` (`Profesor_ID`,`Nombre`,`Hrs_Disponible`)\n" +
                 "VALUES ('04','Edith Gonzalez','15')");
    }
    //Guardar Grupo(String Clave,String Cuatrimestre,String Plan_Est, String[] Materias,String[] Profesores)
    public void GuardarGrupo(String Clave,String Cuatrimestre,String Plan_Est, String[] Materias,String[] Profesores){

    }
    public void EliminarGrupo(String Clave_ID){

    }
    public void EditarGrupo(String Clave_ID,String Plan_ID,String Cuatrimestre,String[] Profesores,String[] Materias){

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
