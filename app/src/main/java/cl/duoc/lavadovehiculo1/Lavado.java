package cl.duoc.lavadovehiculo1;

import java.util.Calendar;

/**
 * Created by Alberto on 06-11-2016.
 */

public class Lavado {

    public String tipoV;
    public String patente;
    public String valorLavado;
    public  String fechaLavado;



    public Lavado() {
    }

    public Lavado(String tipoV, String patente, String valorLavado, String fechaLavado) {
        this.tipoV = tipoV;
        this.patente = patente;
        this.valorLavado = valorLavado;
        this.fechaLavado = fechaLavado;
    }

    public String getTipoV() {
        return tipoV;
    }

    public void setTipoV(String tipoV) {
        this.tipoV = tipoV;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getValorLavado() {
        return valorLavado;
    }

    public void setValorLavado(String valorLavado) {
        this.valorLavado = valorLavado;
    }

    public String getFechaLavado() {
        return fechaLavado;
    }

    public void setFechaLavado(String fechaLavado) {
        this.fechaLavado = fechaLavado;
    }
}
