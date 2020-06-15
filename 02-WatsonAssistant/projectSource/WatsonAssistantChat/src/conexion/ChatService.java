package conexion;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.BasicAuthenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.Context;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.assistant.v1.model.RuntimeEntity;
import com.ibm.watson.developer_cloud.assistant.v2.model.MessageInputOptions;
import com.ibm.watson.developer_cloud.discovery.v1.Discovery;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

import logicadenogocios.ICifrado;
import logicadenogocios.Mensaje;
import logicadenogocios.PorLlave;

@Path("/chatservice")
public class ChatService {

	private String urlDB;
	private String userDB;
	private String passwordDB;
	private String apiKey = "fOXpM_sJMpW1iEu8GiLFj_ygAfRYdCDZREb2fKoWBDOF";
	private String assistantURL = "https://api.us-south.assistant.watson.cloud.ibm.com/instances/71acf5a8-b786-4a4e-968d-058d5044dc80" ;
	private static String workspaceId = "6165712a-44c1-4f61-b215-1ec03809a760";
	
	public ChatService(){
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@GET
	@Produces("application/json")
	public Response getResponse(@QueryParam("conversationMsg") String conversationMsg, @QueryParam("conversationCtx") String conversationCtx) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		System.out.println("asdasdasdsaadasd");
		
		IamOptions iAmOptions = new IamOptions.Builder()
			.apiKey(apiKey)
		    .build();

		Assistant service = new Assistant("2018-09-20", iAmOptions);
		service.setEndPoint(assistantURL);
		
		// Initialize with empty value to start the conversation.
		JSONObject ctxJsonObj = new JSONObject(conversationCtx);
		Context context = new Context();
		context.putAll(ctxJsonObj.toMap());		
		
		InputData input = new InputData.Builder(conversationMsg).build();
		MessageOptions options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();
		
		MessageResponse assistantResponse = service.message(options).execute();
		System.out.println(assistantResponse);
		
		
		//DespuEs del assistant Response manipulamos el contexto
		//Metemos informaciOn
		context.put("pruebaVariable", "Soy un valor del cOdigo");
		context.put("nose", "hola");
//			//downCast de info obtenida del contexto
		String tipoCifradoDescrifrado = (String) context.get("tipoCifrado");
		String tipoOperacion = (String) context.get("tipoOperacion");
		String mensaje = (String) context.get("mensajeNormal");
		String tipoSustitucion = (String) context.get("tipoSustitucion");
		String llave = (String) context.get("llave");
		
		
		
		if(tipoCifradoDescrifrado != null && tipoOperacion != null && mensaje != null) {
			
			ArrayList<String> nuevo = new ArrayList<String>();
			nuevo.add(tipoCifradoDescrifrado);
			nuevo.add(tipoOperacion);
			nuevo.add(mensaje);
			nuevo.add(llave);
			context.put("mensajeCifrado",llamarOperacion(nuevo));
		}
		
		//obtenemos entidades
		List<RuntimeEntity> entidades= assistantResponse.getEntities();
		String entidad= obtenerEntidad(entidades, "cifrados");
		System.out.println("entidad "+entidad);
		System.out.println("tipo "+tipoCifradoDescrifrado);
		System.out.println("tipoOperacion "+tipoOperacion);

		System.out.println("mensaje "+mensaje);
		
		
		//RepeticiOn innecesaria (mete nuevo contexto a la conversaciOn)
		input = new InputData.Builder(conversationMsg).build();
        options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();
        
        assistantResponse = service.message(options).execute();    

		// Print the output from dialog, if any.
		List<String> assistantResponseList = assistantResponse.getOutput().getText();
		JSONObject object = new JSONObject();
		
		String assistantResponseText = "";
		for (String tmpMsg : assistantResponseList)
			assistantResponseText = assistantResponseText + System.lineSeparator() + tmpMsg;
			
		object.put("response", assistantResponseText);
		object.put("context", assistantResponse.getContext());
		
		return Response.status(Status.OK).entity(object.toString()).build();
	}
	
	private String llamarOperacion(ArrayList<String> pLista) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		ICifrado nuevo;
		Mensaje mensaje = new Mensaje(pLista.get(2));
//		if(pLista.get(0) != "sustitución") {
//		
//			
//			nuevo = (ICifrado) Class.forName(pLista.get(0)).newInstance();
//			
//			
//			if(pLista.get(1) == "cifrado") {
//				
//				return nuevo.cifrar(mensaje).getMensajeCifrado();
//
//			}
//			return nuevo.descifrar(mensaje).getMensajeCifrado();
//
//		}
		
		nuevo = new PorLlave(pLista.get(3));
		System.out.println(nuevo.cifrar(mensaje).getMensajeCifrado());
		return nuevo.cifrar(mensaje).getMensajeCifrado();

		
	}
	
	private String obtenerEntidad(List<RuntimeEntity> pEntidades,String pNombre) {
        String entidad="";
        for (int i = 0; i < pEntidades.size(); i++) {
            if(pEntidades.get(i).getEntity().equalsIgnoreCase(pNombre)) {
                entidad+= pEntidades.get(i).getValue();
                i= pEntidades.size();
            }
        }
        return entidad;
    }
		
}
