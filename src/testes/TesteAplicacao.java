package testes;

import java.util.HashMap;

import algoritmos.PesquisarMac;
import objetos.Dispositivo;

public class TesteAplicacao {
	public static void main(String[] args) {

		try {

			PesquisarMac scan = new PesquisarMac();

			while (true) {

				Runtime run = Runtime.getRuntime();
				run.exec("clear");
				
				scan.realizarVarredura();

				StringBuilder log = new StringBuilder();

//				log.append(scan.filtrarDispositivosTempoAtivo());
//				log.append(scan.filtrarDispositivosPotenciaSinal(-94));

				HashMap<String, Dispositivo> meusDispositivos = scan.getGrupoDispositivos();

				log.append("\n\n**********************************************************\n");
				log.append("           Numero de dispositivos: " + meusDispositivos.size());
				log.append("\n**********************************************************\n\n");

				for (String key : meusDispositivos.keySet()) {
					Dispositivo dispositivo = meusDispositivos.get(key);
					log.append(dispositivo.getEnderecoMAC() + "\t" + dispositivo.getFabricante() + "\n");
				}

				System.out.println(log);
				Thread.sleep(2000);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
