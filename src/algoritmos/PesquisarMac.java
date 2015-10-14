package algoritmos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import objetos.Dispositivo;
import objetos.Redes;

public class PesquisarMac {

	private HashMap<String, Dispositivo> grupoDispositivos;
	private HashMap<String, Redes> grupoRedes;

	private boolean isDispositivos;

	private void formatarDados() {
		this.isDispositivos = false;
		this.grupoDispositivos = new HashMap<String, Dispositivo>();
	}

	public PesquisarMac() {
		formatarDados();
	}

	public HashMap<String, Dispositivo> getGrupoDispositivos() {
		return grupoDispositivos;
	}

	public HashMap<String, Redes> getGrupoRedes() {
		return grupoRedes;
	}

	public Dispositivo pesquisarMac(String mac) {
	
		for (String key : this.grupoDispositivos.keySet()) {

			Dispositivo dispositivo = grupoDispositivos.get(key);

			if (dispositivo.getEnderecoMAC().equalsIgnoreCase(mac))
				return dispositivo;
		}
	
		return null;
	}

	public void realizarVarredura() {

		try {

			formatarDados();

			BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/logAirCrack/text.txt-01.csv"));
			String line = "";

			while ((line = bufferedReader.readLine()) != null) {

				String[] coluna = line.split(",");

				if (coluna.length >= 1) {

					if (coluna[0].equalsIgnoreCase("BSSID")) 
						continue;
					
					if (coluna[0].equalsIgnoreCase("Station MAC")) {
						this.isDispositivos = true;
						continue;
					}

					if (isDispositivos) {

						Dispositivo dispositivo = new Dispositivo();

						try {

							dispositivo.setEnderecoMAC(coluna[0].trim());
							dispositivo.setHoraPrimeiraLocalizacao(coluna[1].trim());
							dispositivo.setHoraUltimaLocalizacao(coluna[2].trim());
							dispositivo.setPotenciaSinal(coluna[3].trim());
							dispositivo.setNumPackets(coluna[4].trim());
							dispositivo.setRedeConectada(coluna[5].trim());

						} catch (Exception e) {
						}

						grupoDispositivos.put(coluna[0], dispositivo);
					}
				}
			}

			bufferedReader.close();

			for (String key : this.grupoDispositivos.keySet()) {
				Dispositivo dispositivo = grupoDispositivos.get(key);
				String mac = dispositivo.getEnderecoMAC();
				dispositivo.setFabricante(new PesquisarFabricante().buscarFabricante(mac));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StringBuilder filtrarDispositivosTempoAtivo() {

		HashMap<String, Dispositivo> map = getGrupoDispositivos();
		ArrayList<String> mac = new ArrayList<>();

		for (String key : map.keySet())
			if (Integer.parseInt(map.get(key).calcularTempoPermanecia()) <= 0)
				mac.add(key);

		StringBuilder log = new StringBuilder();

		log.append("\n===========================================================");
		log.append("\n=                                                         =");
		log.append("\n=     MAC's removidos por tempo de inatividade:           =");
		log.append("\n=                                                         =");
		log.append("\n===========================================================");

		for (String key : mac) {
			Dispositivo dispositivo = this.grupoDispositivos.get(key);
			log.append("\n" + dispositivo.getEnderecoMAC() + "\t" + dispositivo.getFabricante());
			map.remove(key);
		}

		return log;
	}

	public StringBuilder filtrarDispositivosPotenciaSinal(int valorMinimo) {

		HashMap<String, Dispositivo> map = getGrupoDispositivos();
		ArrayList<String> mac = new ArrayList<>();

		for (String key : map.keySet())
			if (map.get(key).getPotenciaSinal() == null
					|| Integer.parseInt(map.get(key).getPotenciaSinal()) > valorMinimo)
				mac.add(key);

		StringBuilder log = new StringBuilder();

		log.append("\n===========================================================");
		log.append("\n=                                                         =");
		log.append("\n=  MAC's removidos com potencia de sinal inferior a " + valorMinimo + "   =");
		log.append("\n=                                                         =");
		log.append("\n===========================================================");

		for (String key : mac) {
			Dispositivo dispositivo = this.grupoDispositivos.get(key);
			log.append("\n" + dispositivo.getEnderecoMAC() + "\t" + dispositivo.getFabricante());
			map.remove(key);
		}

		return log;

	}
}