package com.mycompany.app;

public class Grupo {
    protected String Grupo_ID,plan_ID;
    protected String Cuatrimestre;
    protected String Prof, Mat;

    public Grupo(String ID, String cuatri,String plan_ID,String prof,String mat) {
        this.Grupo_ID = ID;
        this.plan_ID = plan_ID;
        this.Cuatrimestre = cuatri;
        this.Prof=prof;
        this.Mat=mat;
    }

    public Grupo(String ID, String plan_ID, String cuatri) {
        this.Grupo_ID = ID;
        this.plan_ID = plan_ID;
        this.Cuatrimestre = cuatri;
    }

    public String getID() { return Grupo_ID; }

    public void setID(String ID) {
        this.Grupo_ID = ID;
    }

    public String getPlan_ID() {
        return plan_ID;
    }

    public void setPlan_ID(String plan_ID) {
        this.plan_ID = plan_ID;
    }

    public String getCuatri() {
        return Cuatrimestre;
    }

    public void setCuatri(String cuatri) {
        Cuatrimestre = cuatri;
    }

    public String getProf() {
        return Prof;
    }

    public void setProf(String prof) {
        Prof = prof;
    }

    public String getMat() {
        return Mat;
    }

    public void setMat(String mat) {Mat = mat; }
}
