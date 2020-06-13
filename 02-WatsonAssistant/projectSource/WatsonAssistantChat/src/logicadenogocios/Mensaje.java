package logicadenogocios;

public class Mensaje {
	
  private String mensajeViejo;
  private String mensajeCifrado;
  private String mensajeDescifrado;
  
  
  public Mensaje(String pMensajeViejo) {
 
	  mensajeViejo = pMensajeViejo;  
	  
  }
  
  public String getMensajeViejo() {

    return mensajeViejo;
  }
  
  public String getMensajeCifrado() {

    return mensajeCifrado;
  }
  
  public void setMensajeCifrado(String pMensajeCifrado) {
	  
	  mensajeCifrado = pMensajeCifrado; 
  }
  
  public void setMensajeDescifrado(String pMensajeDescifrado) {
	  
	  mensajeDescifrado = pMensajeDescifrado; 
  }
  
  public String getMensajeDescifrado() {

    return mensajeDescifrado;
  }
  
  

}
