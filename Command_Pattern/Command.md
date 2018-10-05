
Command Pattern
========================
*Für das Command Pattern gilt:*

 - **Encapsulate what varies:** 
 Veränderbares von nicht-veränderbaren trennen (Kapselung).
 
 - **Favor composition over inheritance:**
 Komposition vor Vererbung favorisieren, da es in der Laufzeit veränderbar ist.
 
 - **Program to interfaces, not implementations:**
 Auf Interfaces und nicht auf Implementierung programmieren, da der geänderte Code einiges ändern kann. Teile die vorher funktionierten, funktionieren nicht mehr.
 
 - **Strive for loosely coupled design between objects that interact:**
Objekte sollen locker gekoppelt sein und sie sollen miteinander interagieren.

 -  **Classes should be open for extension but closed for modification:**
Klassen sollen für Erweiterungen offen sein, jedoch nicht für Änderungen.

 -  **Depend on abstractions. Do not depend on concrete classes:**
Abhängig von Abstraktion und nicht auf konkrete Klassen.

 
Problembeschreibung
-------------------
Es sollen Mitarbeiter modelliert werden, welche die Fähigkeit haben sollen, etwas auszudrucken. Falls ihm dies befohlen wird, soll er zu einem schwarz-weiß-Drucker gehen, und soll das gewünschte Dokument ausdrucken. Damit der Mitarbeier weiß, mit was er drucken soll, wird ihm eine Referenz zu "SchwarzWeißDrucker" gegeben.

	class Sekretaer{
	    private SchwarzWeissDrucker drucker;
	    
	    public void druckAusloesen(String dokument){
	        drucker.konfigurieren();
	        drucker.drucken(dokument);
	    }
	    
	    //Andere Methoden...
	    
	}

	class SchwarzWeissDrucker{
	    public void konfigurieren(){
	        //Einstellungen
	    }
	    public void drucken(String dokument){
	        //Schwarz-Weiß-Druck
	        System.out.println(dokument);
	    }
	}		

*Client:*

	Sekretaer sekretaer = new Sekretaer();
	sekretaer.druckAusloesen("Hallo!");	

Zurzeit wäre das kein Problem, jedoch kriegt die Firma einen neuen Farbdrucker. Diesen sollen aber nicht alle Mitarbeiter nutzen können, sondern nur bestimmte. Außerdem dürfen Praktikanten nur mit Nadeldruckern arbeiten.

*Das hat folgende Nachteile:*

 - **Unflexibel:** Der Code ist end gekoppelt. Falls ein Praktikant nun plötzlich trotzdem etwas mit Farbe drucken muss, ist dies sehr schwer im Code zu ändern. Man müsste Änderungen vornehmen und das verstoßt gegen unsere Richtlinien.
 - **Keine Wiederverwendbarkeit:** Der Code kann nicht wiederverwendet werden. Außerdem muss man mehrmals eine identische Bedingung für Drucker implementieren (Coderedundanz). Führt zu Inkonsistenz. 

Kontext
-------------------
Bei Command Pattern handelt es sich um ein datengesteuertes Entwursmuster. Eine Anfrage bzw. ein Befehl wird unter einem Objekt als Befehl hinzugefügt und dann an das Aufrufobjekt übergeben. Dieses Aufrufobjekt such nach einem Objekt, welches diesen Befehl verarbeiten kann, und gibt diesen Befehl an das ausgewählte Objekt weiter.

Lösungen
--------
Das Strategy Pattern kann nicht funktionieren. Natürlich kann es versuchen eine Druckerschnittstelle einzuführen, dass würde jedoch nichts bringen weil:

 - es sich nicht um ein Verhalten handelt, sondern ein seperates Objekt.
 - die Drucker verschieden viele Arbeitschritte haben (konfigurieren, drucken etc.)

Hier Kommt das Command Pattern ins Spiel, welches eine neue Schicht zwischen Mitarbeiter und Druckern erstellt (Druckbefehl). Dieser kapselt die Zieldrucker und weiß als einziger, welche Schritte notwendig sind. Nur der Befehl kenn den Empfänger (Drucker) und die Schritte, welche gemacht werden sollen. Der Mitarbeiter soll nicht wissen, wie es funktioniert. Er muss nur den Druckbefehl anstoßen.

***DruckBefehlinterface und -implementierungen:***

	//Befehle
	interface DruckBefehl{
	    //Jeder Befehl kapselt die Logik zur Ausführung,
	    //sowie den Zieldrucker
	    public void ausfuehren(String dokument);
	}
	class SchwarzWeissDruckBefehl implements DruckBefehl{
	    private SchwarzWeissDrucker drucker;
	    
	    //Der Befehl wird seinem Zieldrucker bei Instanziierung bekannt gemacht
	    public SchwarzWeissDruckBefehl(SchwarzWeissDrucker drucker) {
	        this.drucker = drucker;
	    }

	    public void ausfuehren(String dokument) {
	        drucker.konfigurieren();
	        drucker.drucken(dokument);
	    }
	}
	class FarbDruckBefehl implements DruckBefehl{
	    private FarbDrucker drucker;
	    
	    //Der Befehl wird seinem Zieldrucker bei Instanziierung bekannt gemacht
	    public FarbDruckBefehl(FarbDrucker drucker) {
	        this.drucker = drucker;
	    }
	    
	    public void ausfuehren(String dokument) {
	        drucker.drucken(dokument);
	    }
	}
	class NadelDruckBefehl implements DruckBefehl{
	    private NadelDrucker drucker;
	    
	    //Der Befehl wird seinem Zieldrucker bei Instanziierung bekannt gemacht
	    public NadelDruckBefehl(NadelDrucker drucker) {
	        this.drucker = drucker;
	    }
	    
	    public void ausfuehren(String dokument) {
	        drucker.umstaendlichKonfigurieren();
	        drucker.drucken(dokument);
	    }
	}
	class PDFDruckBefehl implements DruckBefehl{
	    private PDFDrucker drucker;
	    
	    //Der Befehl wird seinem Zieldrucker bei Instanziierung bekannt gemacht
	    public PDFDruckBefehl(PDFDrucker drucker) {
	        this.drucker = drucker;
	    }
	    
	    public void ausfuehren(String dokument) {
	        drucker.speichern(dokument);
	    }
	}			


***Die Aufrufer Sekretär, Vorstand etc.:***
	
	//Aufrufer
	class Sekretaer{
	    private DruckBefehl druckBefehl;
	    
	    //Aufrufer wird mit dem konkreten DruckBefehl über einen Setter geladen
	    //Alternative: Konstruktor
	    public void setDruckBefehl(DruckBefehl druckBefehl) {
	        this.druckBefehl = druckBefehl;
	    }

	    public void druckAusloesen(String dokument){
	        druckBefehl.ausfuehren(dokument);
	    }
	}
	class Vorstand{
	    private DruckBefehl druckBefehl;
	    
	    //Aufrufer wird mit dem konkreten DruckBefehl über einen Setter geladen
	    //Alternative: Konstruktor
	    public void setDruckBefehl(DruckBefehl druckBefehl) {
	        this.druckBefehl = druckBefehl;
	    }
	    
	    public void druckAusloesen(String dokument){
	        druckBefehl.ausfuehren(dokument);
	    }
	}
	//etc. identischer Code...
	//Sinnvoll: Druckmembers in gemeinsame Superklasse Mitarbeiter auslagern		

***Die Empfänger - Drucker:***

	//Empfänger
	class SchwarzWeissDrucker{
	    public void konfigurieren(){
	        //Einstellungen
	    }
	    public void drucken(String dokument){
	        //Schwarz-Weiß-Druck
	        System.out.println(dokument);
	    }
	}
	class FarbDrucker{
	    public void drucken(String dokument){
	        //Farb-Druck
	        System.out.println(dokument.toUpperCase());
	    }
	}
	class NadelDrucker{
	    public void umstaendlichKonfigurieren(){
	        //Einstellungen
	    }
	    public void drucken(String dokument){
	        //Nadel-Druck
	        System.out.println(dokument.toLowerCase());
	    }
	}
	class PDFDrucker{
	    public void speichern(String dokument){
	        //Als PDF speichern
	        System.out.println("PDF:"+dokument);
	    }
	}	

***Konfiguration und Nutzung durch den Client:***

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


Dadurch haben wir folgende Vorteile:

 - **Austauschbarkeit:** Durch die Entkopplung von den Aufrufern und dem Zieldrucker sind die Befehle unabhängig vom Aufrufer. Außerdem kann der Code wiederverwendet werden, wenn es Änderungen im System gibt.
 - **Fehlerresistenz:** Jeder Drucker kann nun richtig verwendet werden, sodass es egal ist, welcher Mitarbeiter mit dem Drucker arbeitet.
 - **Coderedundanz und Inkonsistenz:** Durch die Zentralisierung der Befehle können Coderedundanz und Inkonsistenz vermieden werden.
 - **Flexibel:** Es gibt keine Vorschriften über Ausformung von Empfänger und Aufrufer. Beispielsweise können automatisierte Druckbefehle integriert werden

UML
-----
![Command-Pattern](https://www.philipphauer.de/study/se/design-pattern/command/einleitung5.svg)

Quellen
-------
- Codebeispiele: https://www.philipphauer.de/study/se/design-pattern/command.php
- UML: https://www.philipphauer.de/study/se/design-pattern/command/einleitung5.svg
- https://www.tutorialspoint.com/design_pattern/command_pattern.htm
- https://en.wikipedia.org/wiki/Command_pattern


