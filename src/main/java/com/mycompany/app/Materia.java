package com.mycompany.app;

public class Materia {

    protected String Materia_ID,Nombre,plan_ID;
    protected int Cuatrimestre,Num_Horas;


    public Materia(String Mat_ID,String nombre,int num_Horas){
        this.Materia_ID = Mat_ID;
        this.Nombre = nombre;
        this.Num_Horas = num_Horas;
    }
    public Materia(String ID, String nombre, String plan_ID, int cuatri, int num_Horas) {
        this.Materia_ID = ID;
        this.Nombre = nombre;
        this.plan_ID = plan_ID;
        this.Cuatrimestre = cuatri;
        this.Num_Horas = num_Horas;
    }

    public String getMateria_ID() {
        return Materia_ID;
    }

    public void setMateria_ID(String materia_ID) {
        Materia_ID = materia_ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPlan_ID() {
        return plan_ID;
    }

    public void setPlan_ID(String plan_ID) {
        this.plan_ID = plan_ID;
    }

    public int getCuatri() {
        return Cuatrimestre;
    }

    public void setCuatri(int cuatri) {
        Cuatrimestre = cuatri;
    }

    public int getNum_Horas() {
        return Num_Horas;
    }

    public void setNum_Horas(int num_Horas) {
        Num_Horas = num_Horas;
    }
}
