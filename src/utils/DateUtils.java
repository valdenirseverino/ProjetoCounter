package utils;

public class DateUtils {

	public Integer converterDataParaSegundos(String data) {

		try {

			String vetor[] = data.substring(11).split(":");
			return Integer.parseInt(vetor[1]) + (60 * Integer.parseInt(vetor[0]));

		} catch (Exception e) {
			return 0;
		}
	}
}