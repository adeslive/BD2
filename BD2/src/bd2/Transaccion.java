package bd2;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import java.util.Map;
import java.util.stream.Collectors;

public class Transaccion {
    
    public static void ofertaComparacionSolicitud(ConexionMongo mongo, String idOf, String idSol){
        boolean rSa, rAcad, rLeg, rProf;
        if (idOf.equals(idSol)){
            Empresa empresa = Empresa.docAempresa(Empresa.buscarEmpresaFiltro(mongo,"ofertas."+idOf, "1"));
            Empleado empleado = Empleado.docAempleado(Empresa.buscarEmpresaFiltro(mongo,"solicitudes"+idSol, "1"));
            
            Map<String, Object> academicos = empleado.getDacademicos().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                                  e -> (e.getValue())));
    
            Map<String, Object> dacademicos = FlatMapUtil.flatten(academicos);
            System.out.println(dacademicos);
        }
        
    }
    
    public static void compararRequisitos(Map<String,String> datos, Map<String,String> requisitos){
        Map<String, Object> academicos = datos.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                                  e -> (e.getValue())));
        
        Map<String, Object> Racademicos = requisitos.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                                  e -> (e.getValue())));
        
        Map<String, Object> datosA = FlatMapUtil.flatten(academicos);
        Map<String, Object> datosB = FlatMapUtil.flatten(academicos);
        
        MapDifference<String, Object> difference = Maps.difference(datosA, datosB);
        
        System.out.println(difference.entriesInCommon());        
        
    }
    
}
