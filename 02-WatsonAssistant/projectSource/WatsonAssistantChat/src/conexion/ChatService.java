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

import logicadenogocios.CodificacionBinaria;
import logicadenogocios.CodigoTelefonico;
import logicadenogocios.ICifrado;
import logicadenogocios.Mensaje;
import logicadenogocios.MensajeInverso;
import logicadenogocios.PalabraInversa;
import logicadenogocios.PorLlave;
import logicadenogocios.SustitucionCesar;
import logicadenogocios.Vigenere;

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
		//downCast de info obtenida del contexto
		String tipoEscogido = (String) context.get("tipoCifradoDescifrado");
		String tipoOperacion = (String) context.get("tipoOperacion");
		String mensaje = (String) context.get("mensajeNormal");
		String llave = (String) context.get("llave");
		String subtipo = (String) context.get("subtipo");
		String cifra = (String) context.get("cifra");
		String posiciones = (String) context.get("posiciones");
		

		if(tipoEscogido != null && tipoOperacion != null && mensaje != null) {
			
			ArrayList<String> nuevo = new ArrayList<String>();
			nuevo.add(tipoEscogido); // 0 para reconocer el tipo
			nuevo.add(tipoOperacion); // 1 para reconocer cifrado o descifrado
			nuevo.add(mensaje); // 2 para el mensaje
			nuevo.add(subtipo); // 3 para el subtipo
			nuevo.add(llave); // 4 apra la llave
			nuevo.add(cifra); // 5 apra la cifra
			nuevo.add(posiciones); // 6 apra la cantiadPosiciones
			
			if(tipoOperacion.equals("cifrado")) {
				System.out.println("holaa");
				context.put("mensajeCifrado",llamarCifrado(nuevo));
			}
			if(tipoOperacion.equals("descifrado")) {
				context.put("mensajeDescifrado",llamarDescifrado(nuevo));
			}
		}
		
		//obtenemos entidades
		List<RuntimeEntity> entidades= assistantResponse.getEntities();
		String entidad= obtenerEntidad(entidades, "cifrados");
		System.out.println("entidad "+entidad);
		System.out.println("tipo "+tipoEscogido);
		System.out.println("tipoOperacion "+tipoOperacion);
		System.out.println("subtipo  "+subtipo);
		System.out.println("mensaje "+mensaje);
		System.out.println("posiciones  "+posiciones);
		
		
		//RepeticiOn innecesaria (mete nuevo contexto a la conversaciOn)
		input = new InputData.Builder(conversationMsg).build();
        options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();
        
        assistantResponse = service.message(options).execute();    

		// Print the output from dialog, iif any.
		List<String> assistantResponseList = assistantResponse.getOutput().getText();
		JSONObject object = new JSONObject();
		
		String assistantResponseText = "";
		for (String tmpMsg : assistantResponseList)
			assistantResponseText = assistantResponseText + System.lineSeparator() + tmpMsg;
			
		object.put("response", assistantResponseText);
		object.put("context", assistantResponse.getContext());
		
		return Response.status(Status.OK).entity(object.toString()).build();
	}
	
	private String llamarCifrado(ArrayList<String> pLista) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		ICifrado nuevo;
		Mensaje mensaje = new Mensaje(pLista.get(2));
		
		switch(pLista.get(3)) {
		  case "MensajeInverso":
		    nuevo = new MensajeInverso();
		    nuevo.cifrar(mensaje);
		    break;
		  case "CodigoBinario":
			nuevo = new CodificacionBinaria();
			nuevo.cifrar(mensaje);
		    break;
		  case "CodigoTelefonico":
			nuevo = new CodigoTelefonico();
			nuevo.cifrar(mensaje);
			break;
		  case "PalabraInversa":
			nuevo = new PalabraInversa();
			nuevo.cifrar(mensaje);
			break;
		  case "llave":
			nuevo = new PorLlave(pLista.get(4));
			nuevo.cifrar(mensaje);
			break;
		  case "c�sar":
			nuevo = new SustitucionCesar(Integer.parseInt(pLista.get(6)));
			nuevo.cifrar(mensaje);
			break;
		  case "vigen�re":
			nuevo = new Vigenere(Integer.parseInt(pLista.get(5)));
			nuevo.cifrar(mensaje);
			break;
		  default:
		    // code block
		}
			
		return mensaje.getMensajeCifrado();
		
	}
	
	private String llamarDescifrado(ArrayList<String> pLista) {
		return "";
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
