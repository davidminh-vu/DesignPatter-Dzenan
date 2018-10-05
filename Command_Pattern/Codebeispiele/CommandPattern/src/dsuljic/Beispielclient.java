package dsuljic;

public class Beispielclient {

	public static void main(String[] args) {
		/*
		 * Initiale Konfiguration:
		 */
		//Drucker erstellen
		SchwarzWeissDrucker schwarzWeissDrucker = new SchwarzWeissDrucker();
		FarbDrucker farbDrucker = new FarbDrucker();
		NadelDrucker nadelDrucker = new NadelDrucker();
		PDFDrucker pdfDrucker = new PDFDrucker();

		Sekretaer sekretaer = new Sekretaer();
		sekretaer.setDruckBefehl(new SchwarzWeissDruckBefehl(schwarzWeissDrucker));

		/*
		 * Nutzung
		 */
		sekretaer.druckAusloesen("Das Command Pattern ist super!");//Das Command Pattern ist super!

		//ggf. dynamische Umkonfiguration zur Laufzeit
		sekretaer.setDruckBefehl(new FarbDruckBefehl(farbDrucker));

		sekretaer.druckAusloesen("Das Command Pattern ist super!");//DAS COMMAND PATTERN IST SUPER!		
	}
}
