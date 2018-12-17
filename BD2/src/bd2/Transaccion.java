package bd2;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;


public class Transaccion {
    
    public static void ofertaComparacionSolicitud(ConexionMongo mongo,String idOf, String idSolicitud){
        Empresa e = Empresa.docAempresa(Empresa.buscarEmpresaFiltro(mongo,"solicitudes."+idOf, "1"));
        
    }
    
    
}
