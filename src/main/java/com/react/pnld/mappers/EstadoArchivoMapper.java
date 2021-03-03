package com.react.pnld.mappers;

import com.react.pnld.model.EstadoArchivoDTO;
import com.react.pnld.model.ProcesaArchivoDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface EstadoArchivoMapper {

    @Select("SELECT * FROM pnld.pnld.estado_archivo WHERE id_estado = #{id_estado}")
    EstadoArchivoDTO getEstadoArchivo(Integer id_estado);

    @Insert("INSERT INTO pnld.procesa_archivo (fecha_carga, nombre_archivo, tipo_archivo, " +
            "fecha_procesado, registros_totales, registros_nuevos, registros_duplicados) " +
            "VALUES(#{fechaCarga},#{nombreArchivo},#{tipoArchivo},#{fechaProcesado},#{registrosTotales}," +
            "#{registrosNuevos},#{registrosDuplicados});")
    int insertProcessFile(ProcesaArchivoDTO procesaArchivoDTO);

}
