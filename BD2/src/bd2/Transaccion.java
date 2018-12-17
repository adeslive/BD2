package bd2;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import java.util.Map;
import java.util.stream.Collectors;

public class Transaccion {
    
    public static boolean ofertaComparacionSolicitud(ConexionMongo mongo, String idOf, String idSol){
        if (idOf.equals(idSol)){
            Empresa empresa = Empresa.docAempresa(Empresa.buscarEmpresaFiltro(mongo,"ofertas."+idOf, "1"));
            Oferta oferta = Oferta.docAoferta(empresa.getOfertas().get(idOf));
            Empleado empleado = Empleado.docAempleado(Empresa.buscarEmpresaFiltro(mongo,"solicitudes"+idSol, "1"));
            
            if (compararRequisitos(empleado.getDacademicos(), oferta.getRacademicos()) == 0 &&
                compararRequisitos(empleado.getDsanitarios(), oferta.getRsanitarios()) == 0 &&
                compararRequisitos(empleado.getDprofesionales(), oferta.getRprofesionales()) == 0 &&
                compararRequisitos(empleado.getDlegales(), oferta.getRlegales()) == 0){
                return true;
            }
        }
        return false;
    }
    
    public static int compararRequisitos(Map<String,String> datos, Map<String,String> requisitos){
        Map<String, Object> academicos = datos.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                                  e -> (e.getValue())));
        
        Map<String, Object> Racademicos = requisitos.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                                  e -> (e.getValue())));
        
        Map<String, Object> datosA = FlatMapUtil.flatten(academicos);
        Map<String, Object> datosB = FlatMapUtil.flatten(Racademicos);
        
        MapDifference<String, Object> difference = Maps.difference(datosA, datosB);
        
        return (difference.entriesInCommon().size());        
    }
    
}
