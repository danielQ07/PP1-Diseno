package logicadenogocios;

public class Mensaje {
	
  private String mensajeViejo;
  private String mensajeCifrado;
  
  
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
  

}
