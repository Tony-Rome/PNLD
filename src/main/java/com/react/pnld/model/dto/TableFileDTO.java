package com.react.pnld.model.dto;

import java.time.OffsetDateTime;

public class TableFileDTO {

    private String responsable;
    private String nombreArchivo;
    private String tipoArchivo;
    private OffsetDateTime fechaCarga;
    private int registrosTotales;
    private int registrosDuplicados;

    public void setResponsable(String responsable){
        this.responsable = responsable;
    }

    public String getResponsable(){return responsable;}

    public void setNombreArchivo(String nombreArchivo){
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivo(){return nombreArchivo;}

    public void setTipoArchivo(String tipoArchivo){
        this.tipoArchivo = tipoArchivo;
    }

    public String getTipoArchivo(){return tipoArchivo;}

    public void setFechaCarga(OffsetDateTime fechaCarga){
        this.fechaCarga = fechaCarga;
    }

    public OffsetDateTime getFechaCarga(){return fechaCarga;}

    public void setRegistrosTotales(int registrosTotales){
        this.registrosTotales = registrosTotales;
    }

    public int getRegistrosTotales(){return registrosTotales;}

    public void setRegistrosDuplicados(int registrosDuplicados){
        this.registrosDuplicados = registrosDuplicados;
    }

    public int getRegistrosDuplicados(){return registrosDuplicados;}

    @Override
    public String toString() {
        return "TableFileDTO{" +
                "responsable=" + responsable +
                ", fechaCarga=" + fechaCarga +
                ", nombreArchivo='" + nombreArchivo + '\'' +
                ", tipoArchivo='" + tipoArchivo + '\'' +
                ", registrosTotales=" + registrosTotales +
                ", registrosDuplicados=" + registrosDuplicados +
                '}';
    }
}
