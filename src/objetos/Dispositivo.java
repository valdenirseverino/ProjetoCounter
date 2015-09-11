package objetos;

import utils.DateUtils;

public class Dispositivo {

	private String enderecoMAC;
	private String horaPrimeiraLocalizacao;
	private String horaUltimaLocalizacao;
	private String potenciaSinal;
	private String numPackets;
	private String redeConectada;
	private String fabricante;

	public String getEnderecoMAC() {
		return enderecoMAC;
	}

	public void setEnderecoMAC(String enderecoMAC) {
		this.enderecoMAC = enderecoMAC;
	}

	public String getHoraPrimeiraLocalizacao() {
		return horaPrimeiraLocalizacao;
	}

	public void setHoraPrimeiraLocalizacao(String horaPrimeiraLocalizacao) {
		this.horaPrimeiraLocalizacao = horaPrimeiraLocalizacao;
	}

	public String getHoraUltimaLocalizacao() {
		return horaUltimaLocalizacao;
	}

	public void setHoraUltimaLocalizacao(String horaUltimaLocalizacao) {
		this.horaUltimaLocalizacao = horaUltimaLocalizacao;
	}

	public String getPotenciaSinal() {
		return potenciaSinal;
	}

	public void setPotenciaSinal(String potenciaSinal) {
		this.potenciaSinal = potenciaSinal;
	}

	public String getNumPackets() {
		return numPackets;
	}

	public void setNumPackets(String numPackets) {
		this.numPackets = numPackets;
	}

	public String getRedeConectada() {
		return redeConectada;
	}

	public void setRedeConectada(String redeConectada) {
		this.redeConectada = redeConectada;
	}

	public String toString() {
		return "MAC: " + getEnderecoMAC() + "\tTempo de Atividade(min): " + calcularTempoPermanecia()
				+ "\tPotencia de Sinal: " + getPotenciaSinal();
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String calcularTempoPermanecia() {

		try {

			DateUtils data = new DateUtils();

			Integer tempoInicio = data.converterDataParaSegundos(getHoraPrimeiraLocalizacao());
			Integer tempoFinal = data.converterDataParaSegundos(getHoraUltimaLocalizacao());

			return (tempoFinal - tempoInicio) + "";

		} catch (Exception e) {
			return "nil";
		}
	}

}
