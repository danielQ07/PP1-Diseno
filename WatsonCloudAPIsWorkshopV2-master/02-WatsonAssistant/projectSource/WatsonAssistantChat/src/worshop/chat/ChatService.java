package worshop.chat;

import java.io.InputStream;
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

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.Context;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

@Path("/chatservice")
public class ChatService {

	private String urlDB;
	private String userDB;
	private String passwordDB;
	private String apiKey = "qx6RR1xHfRS8VN3lzgJycoN4ZRgcbGD9yS_vUFrUB2dr";
	private String assistantURL = "https://api.us-south.assistant.watson.cloud.ibm.com/instances/71acf5a8-b786-4a4e-968d-058d5044dc80";
	private static String workspaceId = "6165712a-44c1-4f61-b215-1ec03809a760";
	
	public ChatService(){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadProperties() throws Exception{
		
		Map<String,String> env = System.getenv();
		
		JSONObject vcap = null;
		
		//VCAP_SERVICES is loaded as an environment variable if running in from IBM Cloud.
		//Otherwise,  it is read locally from configuration.properties.
		if (env.containsKey("VCAP_SERVICES")) {
			vcap = new JSONObject(env.get("VCAP_SERVICES"));
		} else {
			InputStream in = null;

			try {
				Properties localEnv = new Properties();
				in = getClass().getResourceAsStream("/configuration.properties");
				localEnv.load(in);
				in.close();
				vcap = new JSONObject(localEnv.getProperty("VCAP_SERVICES"));
			} finally {
				if (in != null) {
					in.close();
				}
			}
		}

		// DB2 entry
		JSONObject service = (JSONObject) vcap.getJSONArray("dashDB For Transactions").getJSONObject(0);

		if (service != null) {
			JSONObject creds = service.getJSONObject("credentials");
			urlDB = creds.getString("jdbcurl");
			userDB = creds.getString("username");
			passwordDB = creds.getString("password");
		}
		
		// Watson Assistant entry
		service = (JSONObject) vcap.getJSONArray("conversation").getJSONObject(0);

		if (service != null) {
			JSONObject creds = service.getJSONObject("credentials");
			apiKey = creds.getString("apikey");
			assistantURL = creds.getString("url");
		}
		
		System.out.println(urlDB);
		System.out.println(userDB);
		System.out.println(passwordDB);
		System.out.println(apiKey);
		System.out.println(assistantURL);
		
		
	}
	
	@GET
	@Produces("application/json")
	public Response getResponse(@QueryParam("conversationMsg") String conversationMsg, @QueryParam("conversationCtx") String conversationCtx) {
		
		IamOptions iAmOptions = new IamOptions.Builder()
			.apiKey(apiKey)
		    .build();

		Assistant service = new Assistant("2018-09-20", iAmOptions);
		service.setEndPoint(assistantURL);
		
		// Initialize with empty value to start the conversation.
		JSONObject ctxJsonObj = new JSONObject(conversationCtx);
		Context context = new Context();
		context.putAll(ctxJsonObj.toMap());
		
		String currentAction = "";
		
		InputData input = new InputData.Builder(conversationMsg).build();
		MessageOptions options = new MessageOptions.Builder(workspaceId).input(input).context(context).build();
		
		MessageResponse assistantResponse = service.message(options).execute();
		System.out.println(assistantResponse);
		
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
	
}
