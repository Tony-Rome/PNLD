package com.react.pnld.mappers;

import com.react.pnld.model.EstadoArchivoDTO;
import org.apache.ibatis.annotations.Select;

public interface EstadoArchivoMapper {

    @Select("SELECT * FROM pnld.pnld.estado_archivo WHERE id_estado = #{id_estado}")
    EstadoArchivoDTO getEstadoArchivo(Integer id_estado);

}
