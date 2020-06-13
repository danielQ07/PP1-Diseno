package util;

public class ConversionAscii {

	public static char asciiLetra(int pNumero) {
		return (char)pNumero;
	}
	
	
	public static int letraAscii(char pLetra) {
		pLetra = Character.toUpperCase(pLetra);
		return (int)pLetra;
	}

	
}
