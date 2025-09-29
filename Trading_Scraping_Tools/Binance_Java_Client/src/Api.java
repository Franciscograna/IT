import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.bouncycastle.crypto.digests.SHA256Digest;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.nio.charset.StandardCharsets;

public class Api {
    private static final String API_KEY = "…………...";
    private static final String SECRET_KEY = "………...";
    private static String entryid="";
    private static  String entrycid="";
    private static   String tpid="";
    private static   String tpcid="";
    
    public String getEntryid() {
		return entryid;
	}


	public static void setEntryid(String entryid) {
		Api.entryid = entryid;
	}


	public String getEntrycid() {
		return entrycid;
	}


	public static void setEntrycid(String entrycid) {
		Api.entrycid = entrycid;
	}


	public String getTpid() {
		return Api.tpid;
	}


	public static void setTpid(String tpid) {
		Api.tpid = tpid;
	}


	public String getTpcid() {
		return Api.tpcid;
	}


	public static void setTpcid(String tpcid) {
		Api.tpcid = tpcid;
	}


	//CREAR ORDEN
    public  void Post( String SIDE, String TYPE, String PRICE) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
            
        long timestamp = Instant.now().toEpochMilli();
        String symbol ="BTCUSDC";
        String side = SIDE;
        String type=TYPE;
        String price =PRICE;
        String quantity = "0.001";
       
        String timeInForce = "GTC"; 
        String origClientOrderId=""+LocalDateTime.now().getNano();
        String IsIsolated = "TRUE";
        String rcvWindow = "5000";
        String limit ="5000";
        
        String query = "timestamp=" + timestamp +"&symbol="+symbol+"&price="+price+"&side="+side+"&type="+type+"&quantity="+quantity+"&timeInForce="+timeInForce+"&origClientOrderId="+""+"&IsIsolated="+IsIsolated+"&rcvWindow="+rcvWindow+"&limit="+limit;
        String signature = Signature(query, SECRET_KEY);
        String fullQuery = query + "&signature=" + signature;
       
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.binance.com/sapi/v1/margin/order" ))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("X-MBX-APIKEY", API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(fullQuery))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Body: " + response.body());
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootArray = mapper.readTree(response.body());
        String IdResponse = rootArray.get("orderId").toString();//si es Limit o tp
        String CidResponse = rootArray.get("clientOrderId").textValue(); // testo
        if (	type.equals("LIMIT")	){Api.setEntryid(IdResponse);Api.setEntrycid(CidResponse);}
        if (	type.equals("TAKE_PROFIT")	){Api.setTpid(IdResponse);Api.setTpcid(CidResponse);}
    }
    
    
    //QUERY
    public  boolean Get(String id, String cid ) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        long timestamp =Instant.now().toEpochMilli();
        String symbol ="BTCUSDC";
        String side = "";
        String type="";
        String price = "";
        String quantity = "";
        String stopPrice ="";
        String timeInForce = "GTC"; 
        long orderId =Long.parseLong(id);//fallava el parseo
        String clientOrderId=cid;
        String IsIsolated = "TRUE";
        String rcvWindow = "5000";
        String limit ="5000";
        
        String query = "timestamp=" + timestamp +"&symbol="+symbol+
        		"&orderId="+orderId+"&clientOrderId="+clientOrderId+"&IsIsolated="+IsIsolated+
        		"&rcvWindow="+rcvWindow+"&limit="+limit;
        
        String signature = Signature(query, SECRET_KEY);
        String fullQuery = query + "&signature=" + signature; 
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.binance.com/sapi/v1/margin/order?" + fullQuery))
                .header("X-MBX-APIKEY", API_KEY)
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status code: " + response.statusCode());
         
        //System.out.println("Body: " + response.body());
    	ObjectMapper mapper = new ObjectMapper();
        JsonNode rootArray = mapper.readTree(response.body());
        String responseJson = rootArray.get("status").toString();
        if (responseJson.contains("FILLED")) {		System.out.println("Body: " + response.body());return true;	}
		
		return false;
    }
    
    
    //DELETE 
    public void DeleteAll() throws Exception {
        HttpClient client = HttpClient.newHttpClient();    
        long timestamp =Instant.now().toEpochMilli();
        String symbol ="BTCUSDC";
        String IsIsolated = "TRUE";
        String rcvWindow = "50000";
        String query = "timestamp=" + timestamp +
		        		"&symbol="+symbol+
		        		"&IsIsolated="+IsIsolated+      		
				        "&rcvWindow="+rcvWindow;

        String signature = Signature(query, SECRET_KEY);
        String fullQuery = query + "&signature=" + signature; 
        HttpRequest request = HttpRequest.newBuilder()  
                .uri(URI.create("https://api.binance.com/sapi/v1/margin/openOrders?"+fullQuery))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("X-MBX-APIKEY", API_KEY)
                .DELETE()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Body: " + response.toString());
    }
    
  
    public void Delete(String id, String cid ) throws Exception {
        HttpClient client = HttpClient.newHttpClient();    
        long timestamp =Instant.now().toEpochMilli();
        long orderId =Long.parseLong(id);
        String symbol ="BTCUSDC";
        String IsIsolated = "TRUE";
        String rcvWindow = "50000";
        
        String query = "timestamp=" + timestamp +
		        		"&symbol="+symbol+
		        		"&orderId="+orderId+"&clientOrderId="+cid+
		        		"&IsIsolated="+IsIsolated+      		
				        "&rcvWindow="+rcvWindow;

        String signature = Signature(query, SECRET_KEY);
        String fullQuery = query + "&signature=" + signature; 
        HttpRequest request = HttpRequest.newBuilder() // para cerrar especifica
                .uri(URI.create("https://api.binance.com/sapi/v1/margin/order?"+fullQuery))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("X-MBX-APIKEY", API_KEY)
                .DELETE()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Body: " + response.toString());
    }
    
    
    //Signature script
    public static String Signature(String data, String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);

        HMac hmac = new HMac(new SHA256Digest());
        hmac.init(new KeyParameter(keyBytes));
        hmac.update(dataBytes, 0, dataBytes.length);

        byte[] result = new byte[hmac.getMacSize()];
        hmac.doFinal(result, 0);

        StringBuilder hex = new StringBuilder();
        for (byte b : result) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }
}
