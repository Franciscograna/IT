import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


;

public class Main {

	public static void main(String[] args) {
		
		ClaseTest claseTest = new ClaseTest(null, null, 0);
		claseTest.setNombre("pack1");
		claseTest.setCualidad("bianco");
		claseTest.setCantidad(786);
		
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsontext = null;
		try {
			jsontext = objectMapper.writeValueAsString(claseTest);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("JAVA --> JSON");
		System.out.println(jsontext);
		System.out.println("-");
	ObjectMapper objectMapper2 = new ObjectMapper();
		
		Map<String, Object> map = null;
		try {
			map = objectMapper2.readValue(jsontext, new TypeReference<Map<String,Object>>(){});
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("JSON --> JAVA");
		//System.out.println(map);
		String nombre = (String) map.get("nombre");
		String cualidad = (String) map.get("cualidad");
		int cantidad = (Integer) map.get("cantidad");


		System.out.println("Nome: " + nombre);
		System.out.println("Qualita: " + cualidad);
		System.out.println("Quantita: " + cantidad);


	}

}
