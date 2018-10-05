Factory Pattern
================
*Für das Factory Pattern gilt:*

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
Ein Geschäft verkauft nur Microsoft Office Applikationen (Word, Excel, PowerPoint, etc.).
*Zurzeit sieht die Verwaltungssoftware so aus, wenn ein Kunde eine Applikation bestellen will:*

	public class SoftwareShop { 

    public OfficeProgramm holeApp(String zuHolendesProg) { 
        OfficeProgramm programm = null; 
        //Auswahl der benötigten Applikation 
        if (zuHolendesProg.equals("Word")) { 
            programm = new Word(); 
        } 
        else if (zuHolendesProg.equals("Powerpoint")) { 
            programm = new Powerpoint(); 
        } 
        else if (zuHolendesProg.equals("Excel")) { 
            programm = new Excel(); 
        } 
        else { 
            System.err.println("Ungültig!"); 
        } 

        //Weitere Verarbeitung 
        programm.einpacken(); 
        programm.etikettieren(); 

        return programm; 
    } 
	} 	

*Die Schnittstelle der Office Programme und die Implementierung von Word, Excel, PowerPoint, etc sieht so aus:*

	abstract class OfficeProgramm { 
	    public void einpacken() {} 
	    public void etikettieren() {} 
	    public abstract void starten(); 
	} 
	
	class Word extends OfficeProgramm { 
	    public void starten() { 
	        System.out.println("Word startet"); 
	    } 
	} 
	class Powerpoint extends OfficeProgramm { 
	    public void starten() { 
	        System.out.println("Powerpoint startet"); 
	    } 
	} 
	class Excel extends OfficeProgramm { 
	    public void starten() { 
	        System.out.println("Excel startet"); 
	    } 
	}	

*Das hat jedoch folgende Nachteile:*

 - **Geringe Wiederverwendbarkeit:** Falls ein neues Office Programm erstellt wird muss der der Verarbeitungscode verändert werden.
 - **Offen/Geschlossen-Prinzip:** Im Code müssen Änderungen und anpassungen vorgenommen werden, jedoch wollen wir nichts löschen oder ändern, sondern nur Code hinzufügen.

Kontext
-------------------
Das Factory Pattern ist das am häufigsten verwendete Designpattern in Java. Es bietet die beste Möglichkeit zum erstellen von Objekten. Es wird ein Objekt erstellt, welche dem Client keine Erstellungslogik zur Verfügung stellt. Jedoch verweisen beide über eine gemeinsame Schnittstelle auf ein neu erstelltes Objekt.

Lösungen
--------
Der Softwareshop weiß nicht um welches konkrete Programm es sich handelt. Das soll alleine die Subklasse entscheiden, welche die Factory-Method *createOfficeProgramm* implementiert. Daher gibt der Softwareshop die Aufgabe (die Objektinstanziierung) an seine Subklasse weiter. Von außen ist die Methode *protected*. Dadurch ist der Verarbeitungsprozess immer durchführbar.

***Die Subklassen MicrosoftOfficeFactory, AppleiWorkFactory, SunOpenOfficeFactory instanziieren ihre Programme und geben sie zurück:***

		class MicrosoftOfficeFactory extends SoftwareShop{ 
	    @Override 
	    protected OfficeProgramm createOfficeProgram(String zuHolendesProg) { 
	        OfficeProgramm programm = null; 
	        if (zuHolendesProg.equals("Textverarbeitung")) { 
	            programm = new Word(); 
	        } 
	        else if (zuHolendesProg.equals("Präsentation")) { 
	            programm = new Powerpoint(); 
	        } 
	        else if (zuHolendesProg.equals("Tabellenkalkulation")) { 
	            programm = new Excel(); 
	        } 
	        else { 
	            System.err.println("Ungültig!"); 
	        } 
	        return programm; 
	    } 
	} 
	class AppleiWorkFactory extends SoftwareShop{ 
	    @Override 
	    protected OfficeProgramm createOfficeProgram(String zuHolendesProg) { 
	        OfficeProgramm programm = null; 
	        if (zuHolendesProg.equals("Textverarbeitung")) { 
	            programm = new Pages(); 
	        } 
	        else if (zuHolendesProg.equals("Präsentation")) { 
	            programm = new Keynode(); 
	        } 
	        else if (zuHolendesProg.equals("Tabellenkalkulation")) { 
	            programm = new Numbers(); 
	        } 
	        else { 
	            System.err.println("Ungültig!"); 
	        } 
	        return programm; 
	    } 
	} 
	class SunOpenOfficeFactory extends SoftwareShop{ 
	    @Override 
	    protected OfficeProgramm createOfficeProgram(String zuHolendesProg) { 
	        OfficeProgramm programm = null; 
	        if (zuHolendesProg.equals("Textverarbeitung")) { 
	            programm = new Write(); 
	        } 
	        else if (zuHolendesProg.equals("Präsentation")) { 
	            programm = new Impress(); 
	        } 
	        else if (zuHolendesProg.equals("Tabellenkalkulation")) { 
	            programm = new Calc(); 
	        } 
	        else { 
	            System.err.println("Ungültig!"); 
	        } 
	        return programm; 
	    } 
	} 	

***Die OfficeProgramme der verschiedenen Hersteller:***

		abstract class OfficeProgramm { 
	    public void einpacken() {} 
	    public void etikettieren() {} 
	    public abstract void starten(); 
	} 
	
	class Word extends OfficeProgramm { 
	    public void starten() { 
	        System.out.println("Word startet"); 
	    } 
	} 
	class Powerpoint extends OfficeProgramm { 
	    public void starten() { 
	        System.out.println("Powerpoint startet"); 
	    } 
	} 
	class Excel extends OfficeProgramm { 
	    public void starten() { 
	        System.out.println("Excel startet"); 
	    } 
	} 
	class Pages extends OfficeProgramm { 
	    public void starten() { 
	        System.out.println("Pages startet"); 
	    } 
	} 
	class Keynode extends OfficeProgramm { 
	    public void starten() { 
	        System.out.println("Keynode startet"); 
	    } 
	} 
	class Numbers extends OfficeProgramm { 
	    public void starten() { 
	        System.out.println("Numbers startet"); 
	    } 
	} 
	class Write extends OfficeProgramm { 
	    public void starten() { 
	        System.out.println("Write startet"); 
	    } 
	} 
	class Impress extends OfficeProgramm { 
	    public void starten() { 
	        System.out.println("Impress startet"); 
	    } 
	} 
	class Calc extends OfficeProgramm { 
	    public void starten() { 
	        System.out.println("Calc startet"); 
	    } 
	} 		

***Beispielclient:***

	public class Client{ 
	    public static void main(String[] args) { 
	        SoftwareShop appleShop = new AppleiWorkFactory(); 
	        OfficeProgramm appleTextProgram = appleShop.holeApp("Textverarbeitung"); 
	        appleTextProgram.starten();//"Pages startet" 
	
	        SoftwareShop msShop = new MicrosoftOfficeFactory(); 
	        OfficeProgramm wordPresProgram = msShop.holeApp("Präsentation"); 
	        wordPresProgram.starten();//"Powerpoint startet" 
	    } 
	} 	

Dadurch haben wir folgende Vorteile:

 - **Wiederverwendbar:** Durch die Entkopplung der Objektverarbeitung von einer *konkreten Implementierung*
 - **Konsitenz:** Die Verarbeitung geschieht nun an einer zentralen Stelle. Außerdem kann kein unverarbeitetes Programm an den Client gehen
 - **Erweiterbarkeit:** Nun können neue Programme hinzugefügt werden und der Code muss nicht neu geschrieben bzw. angepasst und geändert werden.

UML
--------	

![Simple-Factory-UML](https://www.philipphauer.de/study/se/design-pattern/factory-method/einleitung4.svg)	

Quellen
-------
- Codebeispiel: https://www.philipphauer.de/study/se/design-pattern/factory-method.php
- UML: https://www.philipphauer.de/study/se/design-pattern/factory-method/einleitung4.svg
- https://www.tutorialspoint.com/design_pattern/factory_pattern.htm
- https://en.wikipedia.org/wiki/Factory_method_pattern

