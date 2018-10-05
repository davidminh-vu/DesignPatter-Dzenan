Decorator Pattern
================
*Für das Decorator Pattern gilt:*

 - **Encapsulate what varies:** 
 Veränderbares von nicht-veränderbaren trennen (Kapselung).
 
 - **Favor composition over inheritance:**
 Komposition vor Vererbung favorisieren, da es in der Laufzeit veränderbar ist.
 
 - **Program to interfaces, not implementations:**
 Auf Interfaces und nicht auf Implementierung programmieren, da der geänderte Code einiges ändern kann. Teile die vorher funktionierten, funktionieren nicht mehr.
 
 - **Strive for loosely coupled design between objects that interact:**
Objekte sollen locker gekoppelt sein und sie sollen miteinander interagieren.

- **Classes should be open for extension but closed for modification:**
Klassen sollen für Erweiterungen offen sein, jedoch nicht für Änderungen.


 
Problembeschreibung
-------------------
Ein Restaurant möchte zwei Gerichte ("Wiener Schnitzel mit Pommes" und "Wiener Schnitzel mit Bratkartoffeln") modellieren. Der Preis und die Beschreibung sollen mit Methoden abgefragt werden. Für jedes Gericht wird eine eigene Klasse erstellt, die das Gerichtinterface implementiert und die Methoden *getPreis()* und *druckeBeschreibung()* ausprogrammiert. Was passiert, wenn das Restaurant alle Gerichte angezeigt haben will?

*Das hat jedoch folgende Nachteile:*

- Die Wartungsarbeiten und Erweiterungsaufgaben erfordern hohen Zeitaufwand und werde fehleranfällig. Für nur eine Beilage müssten viele neue Klassen erstellt werden, und was passiert wenn ein Gericht teurer wird?

- Es ist Unflexibel, da eine Person vielleicht eine doppelte Portion einer Beilage haben will. Für so eine Situation ist das Programm nicht gemacht.

- Änderungen können zur Laufzeit nicht geändert werden, da es an Dynamik fehlt. Beispielsweise kann man Beilagen nicht entfernen oder hinzufügen.

Außerdem müssen wir konstante Aspekte von variablen Aspekten trennen. 

 

Kontext
-------------------
Durch das Decorator Pattern ist es möglich einem vorhandenen Objekt, neue Funktionen hinzuzufügen. Daher wird eine Decorator Klasse erstellt, welche die eigentliche Klasse umschließt und diese dann mit neuen Funktionen ausstattet. 

Lösungen
--------
Für jedes Basisgericht und für jede Beilage wird eine einzelne Klasse geschrieben. Diese werden dann dynamisch miteinander kombiniert. Dazu werden die Beilagen genommen und als Wrapper (Hülle) zu den Basisgerichten dazugelegt. Diese sind dann Objekte welche ein Gericht besitzen. Dazu brauchen sie eine Instanzvariable. Falls nun eine Salatbeilage beispielweise seinen Preis ausgeben soll, so fragt er zuerst das dazugehörige Gericht nach dessen Preis und addiert nun den Salatpreis dazu.


**getPreis() von Salat delegiert die Preisberechnung an sein (Basis)Gericht und fügt seinen eigenen Preis dazu:**

	//Salatcode 
	private Gericht gericht; 
	
	public double getPreis() { 
	    return gericht.getPreis() + 2.25; 
	} 
	
Außerdem soll es möglich sein Beilagen beliebig oft zu schachteln. Das heißt mehrere Beilagen ausgesucht werden können. Die Beilagen müssen daher vom selben Typ sein, wie die Basisgerichte. Dadurch erweitern Beilagen das Interface vom Gericht. Daher ist es möglich Beilagen auch als ein eigenes Gericht zu sehen, die man im Praxisbeispiel auch alleine bestellen kann.

**Quellcode Gerichtinterface:**

	public interface Gericht { 
	    public double getPreis(); 
	    public void druckeBeschreibung(); 
	}  	
	
**Quellcode konkrete Basisgerichte:**

		class Hueftsteak implements Gericht { 
	    public void druckeBeschreibung() { 
	        System.out.print("Hüftsteak"); 
	    } 
	    public double getPreis() { 
	        return 13.0; 
	    } 
	} 
	
	
	class Tofu implements Gericht { 
	    public void druckeBeschreibung() { 
	        System.out.print("Tofu"); 
	    } 
	    public double getPreis() { 
	        return 8.50; 
	    } 
	} 
	
	
	class Garnelen implements Gericht { 
	    public void druckeBeschreibung() { 
	        System.out.print("Garnelen"); 
	    } 
	    public double getPreis() { 
	        return 13.50; 
	    } 
	
	} 
	
	
	class WienerSchnitzel implements Gericht { 
	    public void druckeBeschreibung() { 
	        System.out.print("WienerSchnitzel"); 
	    } 
	    public double getPreis() { 
	        return 10.50; 
	    } 
	} 			

**Quellcode abstrakte Beilage:**

	public abstract class Beilage implements Gericht { 
	    protected Gericht gericht; 

	    public Beilage(Gericht gericht) { 
        this.gericht = gericht; 
	    } 
	}  	
		
**Quellcode konkrete Beilagen:**

		class Pommes extends Beilage { 
	    public Pommes(Gericht gericht) { 
	        super(gericht); 
	    } 
	    public void druckeBeschreibung() { 
	        gericht.druckeBeschreibung(); 
	        System.out.print(", Pommes"); 
	    } 
	    public double getPreis() { 
	        return gericht.getPreis() + 2.50; 
	    } 
	} 
	
	
	class Salat extends Beilage { 
	    public Salat(Gericht gericht) { 
	        super(gericht); 
	    } 
	    public void druckeBeschreibung() { 
	        gericht.druckeBeschreibung(); 
	        System.out.print(", Salat"); 
	    } 
	    public double getPreis() { 
	        return gericht.getPreis() + 2.25; 
	    } 
	} 
	
	
	class Nudeln extends Beilage { 
	    public Nudeln(Gericht gericht) { 
	        super(gericht); 
	    } 
	    public void druckeBeschreibung() { 
	        gericht.druckeBeschreibung(); 
	        System.out.print(", Nudeln"); 
	    } 
	    public double getPreis() { 
	        return gericht.getPreis() + 4.50; 
	    } 
	} 
	
	
	class Suppe extends Beilage { 
	    public Suppe(Gericht gericht) { 
	        super(gericht); 
	    } 
	    public void druckeBeschreibung() { 
	        gericht.druckeBeschreibung(); 
	        System.out.print(", Suppe"); 
	    } 
	    public double getPreis() { 
	        return gericht.getPreis() + 1.50; 
	    } 
	} 
	
	
	class Bratkartoffeln extends Beilage { 
	    public Bratkartoffeln(Gericht gericht) { 
	        super(gericht); 
	    } 
	    public void druckeBeschreibung() { 
	        gericht.druckeBeschreibung(); 
	        System.out.print(", Bratkartoffeln"); 
	    } 
	    public double getPreis() { 
	        return gericht.getPreis() + 1.50; 
	    } 
	}  		

**Beispielclient:**

	public static void main(String[] args) { 
	    Gericht gericht = new Salat(new Nudeln(new Hueftsteak())); 
	    gericht.druckeBeschreibung(); 
	    //Hüftsteak, Nudeln, Salat 
	    System.out.println(" für "+gericht.getPreis() + " Euro"); 
	    // für 19.75 Euro 
	
	    gericht = new Suppe(gericht); 
	    gericht.druckeBeschreibung(); 
	    //Hüftsteak, Nudeln, Salat, Suppe 
	    System.out.println(" für "+gericht.getPreis() + " Euro"); 
	    // für 21.25 Euro 
	} 
*Auf dieser Art haben wir folgende Vorteile:*

 - *Flexibilität und Dynamik:* Wir können Basisgerichte nun mit anderen Beilagen umhüllen, und dieses Verhalten dynamisch und flexibel modifizieren.

 - *Erweiterbarkeit und Robustheit:* Durch das System kann man nun keine Veränderung machen, ist es Erweiterbar. Neue Basisgerichte und Beilagen können ohne Änderung von anderen Basisgerichten und Beilagen hinzugefügt werden.

 - *Konsistenz und Warbarkeit:* Die Anfälligkeiten von Fehlern und Inkonsistenz wurdne nun verringert, da bei einer Wartungsarbeit nur auf eine Klasse geschaut werden muss.

UML
--------	

![Decorator-UML](https://www.philipphauer.de/study/se/design-pattern/decorator/einleitung6.svg)	
	
Quellen
-------
- Codebeispiele: https://www.philipphauer.de/study/se/design-pattern/decorator.php
- UML: https://www.philipphauer.de/study/se/design-pattern/decorator/einleitung6.svg
- https://www.tutorialspoint.com/design_pattern/decorator_pattern.htm
- https://de.wikipedia.org/wiki/Decorator




