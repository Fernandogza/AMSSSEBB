package interfaces;
import controles.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import controles.ControlInicioSesion;
import controles.ControlVotacion;
import controles.ControlCuenta;

public class InterfazVotacion extends HttpServlet {
  HttpServletResponse thisResponse;
  HttpServletRequest thisRequest;
  PrintWriter out;
  ControlInicioSesion ci;
  ControlVotacion cv;
  ControlCuenta cu;
  //Es importante observar que todos los metodos definen la operacion GET para
  //que el metodo doGet sea el que se ejecuta al presionar el boton "Enviar". 
  public void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws IOException {
    thisResponse = response;
    thisRequest = request;
    thisResponse.setContentType("text/html");
    out = thisResponse.getWriter();
    //Preparar el encabezado de la pagina Web de respuesta
    out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<META http-equiv=Content-Type content=\"text/html\">");
    out.println("</HEAD>");
    out.println("<BODY>");
    out.println("<TITLE>SEBB</TITLE>");
    out.println("<h2>Votacion</h2>");    
    String operacion = request.getParameter("operacion");
    if(operacion == null){ // El menu nos envia un parametro para indicar el inicio de una transaccion
      inicioVotacion();  
    }else if(operacion.equals("votar")){
       votar();
    } 
  }
  
  public void inicioVotacion(){
    ci = new ControlInicioSesion();
    String n = ci.getConected();
    cv = new ControlVotacion();
    cu = new ControlCuenta();
    boolean juez = cv.validarJuez(cu.obtenerID(n));
    if(juez){
        out.println("<p>Indique el articulo por el cual quiere votar</p>");
        out.println("<p>ID del articulo:</p>");
        out.println("<form method=\"GET\" action=\"Votacion\">");
        out.println("<input type=\"hidden\" name=\"operacion\" value=\"votar\"/>");
        out.println("<input type=\"text\" name=\"IDA\" size=\"15\"></p>");
        out.println("<p><input type=\"submit\" value=\"Enviar\"name=\"B1\"></p>");
        out.println("</form>");

        out.println("<form method=\"GET\" action=\"menu.html\">");
        out.println("<p><input type=\"submit\" value=\"Cancelar\"name=\"B2\"></p>");
        out.println("</form>");

        out.println("</BODY>");
        out.println("</HTML>"); 
    }
    else{
        out.println("<p>No tiene permisos suficientes para estar aqui </p>");
        out.println("<p>Nombre</p>");
        out.println("<form method=\"GET\" action=\"menu.html\">");
        out.println("<p><input type=\"submit\" value=\"Regresar\"name=\"B2\"></p>");
        out.println("</form>");

        out.println("</BODY>");
        out.println("</HTML>"); 
    }
  }
  
  public void votar(){  
    int ID = Integer.parseInt(thisRequest.getParameter("IDA").trim());
    boolean art = cv.validarArticulo(ID);
    if (art){
      cv.votarArticulo(ID);
      votado();
    }
    else{
        inicioVotacion();
    }
  }
  public void votado(){  
      int ID = Integer.parseInt(thisRequest.getParameter("IDA").trim());
      out.println("<p>Gracias por votar por el articulo "+ ID +"</p>");
      out.println("<p>El articulo ahora tiene: "+cv.obtenerVotos(ID)+" votos</p>");
      out.println("<p>Presione el boton para regresar al indice.</p>");
      out.println("<form method=\"GET\" action=\"menu.html\">");
      out.println("<p><input type=\"submit\" value=\"Terminar\"name=\"B1\"></p>");
      out.println("</form>");
      out.println("</BODY>");
      out.println("</HTML>");   
  } 
}