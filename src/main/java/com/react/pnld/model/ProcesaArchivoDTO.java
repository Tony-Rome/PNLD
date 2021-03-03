package com.react.pnld.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class ProcesaArchivoDTO {

    private int idArchivo;
    private OffsetDateTime fechaCarga;
    private String nombreArchivo;
    private String tipoArchivo;
    private int idPersona;
    private int idEstado;
    private OffsetDateTime fechaProcesado;
    private int registrosTotales;
    private int registrosNuevos;
    private int registrosDuplicados;

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    public OffsetDateTime getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(OffsetDateTime fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public OffsetDateTime getFechaProcesado() {
        return fechaProcesado;
    }

    public void setFechaProcesado(OffsetDateTime fechaProcesado) {
        this.fechaProcesado = fechaProcesado;
    }

    public int getRegistrosTotales() {
        return registrosTotales;
    }

    public void setRegistrosTotales(int registrosTotales) {
        this.registrosTotales = registrosTotales;
    }

    public int getRegistrosNuevos() {
        return registrosNuevos;
    }

    public void setRegistrosNuevos(int registrosNuevos) {
        this.registrosNuevos = registrosNuevos;
    }

    public int getRegistrosDuplicados() {
        return registrosDuplicados;
    }

    public void setRegistrosDuplicados(int registrosDuplicados) {
        this.registrosDuplicados = registrosDuplicados;
    }

    @Override
    public String toString() {
        return "ProcesaArchivoDTO{" +
                "idArchivo=" + idArchivo +
                ", fechaCarga=" + fechaCarga +
                ", nombreArchivo='" + nombreArchivo + '\'' +
                ", tipoArchivo='" + tipoArchivo + '\'' +
                ", idPersona=" + idPersona +
                ", idEstado=" + idEstado +
                ", fechaProcesado=" + fechaProcesado +
                ", registrosTotales=" + registrosTotales +
                ", registrosNuevos=" + registrosNuevos +
                ", registrosDuplicados=" + registrosDuplicados +
                '}';
    }
}
