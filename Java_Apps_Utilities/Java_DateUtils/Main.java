import java.util.Date;
import java.text.SimpleDateFormat; //1
import java.time.LocalDateTime;//2


public class Main {

	public static void main(String[] args) {
		Date fecha = new Date();
  	  SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy::MM::dd hh:mm:ss");
  	  String fechaFormateada = formatoFecha.format(fecha); //1
  	  System.out.println(fechaFormateada);
  	  System.out.println(LocalDateTime.now().getHour()+" : "+LocalDateTime.now().getMinute());
      //2
	}

}
