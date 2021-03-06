package controles;
import entidades.Cuenta;
public class ControlInicioSesion {
   Cuenta cuenta;

   public ControlInicioSesion(){
     cuenta = new Cuenta(); //Asume que la instancia persiste durante la sesion
   }
   //Valida si la cuenta existe en la base de datos
   public boolean inicioSesion(String n, String p){
       return cuenta.autenticar(n, p);
   }
   public void conectar(String n){
       cuenta.conect(n);
   }
   public String [] obtenerDatos(String n){
       return cuenta.getDatos(n);
   }
   public String getConected(){
       return cuenta.getConectado();
   }
   public void desconectar(String n){
       cuenta.disconect(n);
   }
}
