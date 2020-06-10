package util;

public class ConversionAscii {

	public static char AsciiLetra(int pNumero) {
		return (char)pNumero;
	}
	
	
	public static int LetraAscii(Character pLetra) {
		pLetra = Character.toUpperCase(pLetra);
		return (int)pLetra;
	}

	
}
