package controles;
import entidades.Cuenta;
import entidades.Suscripcion;
import java.util.Date;
import java.util.Calendar;

public class ControlConsulta {
   Cuenta c;
   Suscripcion s;

   public ControlConsulta(){
     c = new Cuenta(); //Asume que la instancia persiste durante la sesion
     s = new Suscripcion();
   }  
   public String consultaTipo(int IDC){
       return(s.getTipo(IDC));
   }
   public int consultaDuracion(int IDC){
       return(s.getDuracion(IDC));
   }
   public Calendar consultaInicio(int IDC){
       return (s.getInicio(IDC));
   }
   public Calendar consultaFin(int IDC){
       return (s.getFin(IDC));
   }
   public String consultaNombre(int ncuenta) {
     return (c.getNombre(ncuenta));
   }
   public String consultaCorreo(int ncuenta) {
     return (c.getCorreo(ncuenta));
   }
   public String consultaTelefono(int ncuenta) {
     return (c.getTelefono(ncuenta));
   }
   public String consultaDireccion(int ncuenta) {
     return (c.getDireccion(ncuenta));
   }
}
