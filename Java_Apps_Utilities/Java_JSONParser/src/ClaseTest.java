
public class ClaseTest {
private String nombre;
private String cualidad;
private int cantidad;



public ClaseTest(String nombre, String cualidad, int cantidad) {
	
	this.nombre = nombre;
	this.cualidad = cualidad;
	this.cantidad = cantidad;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getCualidad() {
	return cualidad;
}
public void setCualidad(String cualidad) {
	this.cualidad = cualidad;
}
public int getCantidad() {
	return cantidad;
}
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}
}
