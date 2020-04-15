//27419_JoséErnestoRamírezCervantes_Final
package com.mycompany.app;

public class Profesor {
    protected String Nombre, Clave,Tipo,Carrera_ID,Contrato,Grado;
    protected int HorasDisp,P_Confianza[]=new int[20];

    public Profesor(String Clave,String nombre,int Hrs_Disp){
        this.Clave= Clave;
        this.Nombre=nombre;
        this.HorasDisp=Hrs_Disp;

    }
    public Profesor(String nombre, String clave, String tipo, String carrera_ID, String contrato, String grado, int horasDisp, int[] p_Confianza) {
        Nombre = nombre;
        Clave = clave;
        Tipo = tipo;
        Carrera_ID = carrera_ID;
        Contrato = contrato;
        Grado = grado;
        HorasDisp = horasDisp;
        P_Confianza = p_Confianza;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getCarrera_ID() {
        return Carrera_ID;
    }

    public void setCarrera_ID(String carrera_ID) {
        Carrera_ID = carrera_ID;
    }

    public String getContrato() {
        return Contrato;
    }

    public void setContrato(String contrato) {
        Contrato = contrato;
    }

    public String getGrado() {
        return Grado;
    }

    public void setGrado(String grado) {
        Grado = grado;
    }

    public int getHorasDisp() {
        return HorasDisp;
    }

    public void setHorasDisp(int horasDisp) {
        HorasDisp = horasDisp;
    }

    public int[] getP_Confianza() {
        return P_Confianza;
    }

    public void setP_Confianza(int[] p_Confianza) {
        P_Confianza = p_Confianza;
    }
}
