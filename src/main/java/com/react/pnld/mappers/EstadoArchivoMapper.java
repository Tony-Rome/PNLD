package com.react.pnld.mappers;

import com.react.pnld.model.EstadoArchivoDTO;
import com.react.pnld.model.ProcesaArchivoDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface EstadoArchivoMapper {

    @Insert("INSERT INTO pnld.procesa_archivo (fecha_carga, nombre_archivo, tipo_archivo, id_persona, id_estado, " +
            "fecha_procesado, registros_totales, registros_nuevos, registros_duplicados) " +
            "VALUES(#{fechaCarga},#{nombreArchivo},#{tipoArchivo},#{idPersona},#{idEstado},#{fechaProcesado}," +
            "#{registrosTotales},#{registrosNuevos},#{registrosDuplicados});")
    int insertProcessFile(ProcesaArchivoDTO procesaArchivoDTO);

}
