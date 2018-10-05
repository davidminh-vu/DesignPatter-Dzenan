Strategy Pattern
================
*Für das Strategy Pattern gilt:*

 - **Encapsulate what varies:** 
 Veränderbares von nicht-veränderbaren trennen (Kapselung).
 
 - **Favor composition over inheritance:**
 Komposition vor Vererbung favorisieren, da es in der Laufzeit veränderbar ist.
 
 - **Program to interfaces, not implementations:**
 Auf Interfaces und nicht auf Implementierung programmieren, da der geänderte Code einiges ändern kann. Teile die vorher funktionierten, funktionieren nicht mehr.
 
Problembeschreibung
-------------------
Es sollen verschiedenste Hunde modelliert werden. Jeder Hund soll bellen und laufen.
Hier kann man eine abstrakte Superklasse Hund erstellen, von der alle konkreten Subklassen (in diesem Beispiel: Husky, Bulldogge und Pudel) ableiten und somit, das Bell- und Laufverhalten erben.
Jedoch soll ein Husky anders laufen als ein Pudel oder eine Bulldogge. Daher müssen die Subklassen *bellen()* und *laufen()* überschrieben werden.

*Das hat jedoch folgende Nachteile:*

 - *bellen()* und *laufen()* werden immer vererbt, auch bei Subklassen die nicht laufen können (beispielsweise eine Hundeatrappe)
 - *Code Redundanz:* Das heißt, dass man eine Codeänderungen in mehreren Klassen durchführen muss, welche zu Fehlern führen kann.
 - Es ist nicht möglich das Verhalten der Hunde in der Laufzeit zu ändern.
 - Es können keine allgemeinen Daten über Hunde getroffen werden.
 - *Wiederverwendbarkeit:* Das Verhalten muss immer wieder neu geschrieben werden.

Kontext
-------------------
Die Bestandteile des Programmes werden als ein eigenes Objekt definiert. Dadurch kann man in späterer Folge Algorithmen, bzw. ganze Teile des Programmes austauschen. Diese Objekte repräsentieren verschiedene Strategien. Das heißt, dass sich dieses Objekt leichter ersetzen bzw. austauschen lässt.

Lösungen
--------
Ein Interface für das Verhalten wird definiert. Die konkreten Verhaltensklassen implementieren dieses Interface. Dadurch weiß der Husky beispielsweise nicht welches Verhalten er besitzt, jedoch weiß er das man damit *bellen()* und *laufen()* kann.

***Laufverhalten (Interface und Implementation):***

	interface LaufVerhalten { 
	public void laufen(); 
	} 
	
	class NormalLaufen implements LaufVerhalten{ 
	    public void laufen() { 
	        System.out.println("Normal laufen."); 
	    } 
	} 
	
	class SchnellLaufen implements LaufVerhalten { 
	    public void laufen() { 
	        System.out.println("Schnell laufen."); 
	    } 
	} 
	
	class KannNichtLaufen implements LaufVerhalten{ 
	    public void laufen() { 
	        System.out.println("Kann doch gar nicht laufen."); 
	    } 
	} 
	
	class Humpeln implements LaufVerhalten{ 
	    public void laufen() { 
	        System.out.println("Humpeln."); 
	    } 
	} 

***Bellverhalten (Interface und Implementation):***

	interface BellVerhalten { 
	    public void bellen(); 
	} 
	
	class LeiseBellen implements BellVerhalten { 
	    public void bellen() { 
	        System.out.println("ganz leise bellen..."); 
	    } 
	} 
	
	class LautBellen implements BellVerhalten{ 
	    public void bellen() { 
	        System.out.println("GANZ LAUT BELLEN!!"); 
	    } 
	} 
	
	class ElektronischBellen implements BellVerhalten { 
	    public void bellen() { 
	        System.out.println("Elekkkkktronisch Bellen!"); 
	    } 
	}  

***Abstrakte Hundeklasse und die konkreten Hundeklassen:***

	abstract class Hund { 
	
	    //Instanzvariablen vom Typ des Interfaces. Defaultverhalten 
	    BellVerhalten bellVerhalten = new LautBellen(); 
	    LaufVerhalten laufVerhalten = new SchnellLaufen(); 
	
	
	    public void setBellVerhalten(BellVerhalten bellVerhalten) { 
	        this.bellVerhalten = bellVerhalten; 
	    } 
	
	
	    public void setLaufVerhalten(LaufVerhalten laufVerhalten) { 
	        this.laufVerhalten = laufVerhalten; 
	    } 
	
	    public void bellen(){ 
	        //Delegation des Verhaltens an Verhaltensobjekt 
	        bellVerhalten.bellen(); 
	    } 
	
	    public void laufen(){ 
	        //Delegation des Verhaltens an Verhaltensobjekt 
	        laufVerhalten.laufen(); 
	    } 
	} 

***Fertiger Husky-Code mit gekapselten Bellverhalten:***

	public class Husky extends Hund { 
	    //Defaultverhalten: LeiseBellen 
	    private BellVerhalten bellVerhalten = new LeiseBellen(); 
	
	    public void bellen(){ 
	        bellVerhalten.bellen(); 
	    } 
	
	    public void setBellVerhalten(BellVerhalten bellVerhalten){ 
	        this.bellVerhalten = bellVerhalten; 
	    } 
	}  
***Beispielclient:***

	public class Client { 
	    public static void main(String[] args) { 
	        Husky husky = new Husky(); 
	        husky.bellen(); //ganz leises bellen... 
	        husky.laufen(); //Schnelles laufen 
	        husky.setLaufVerhalten(new Humpeln()); 
	        husky.laufen(); //Humpeln 
	        //... 
	    } 
	}  
	

UML
--------

![Strategy-UML](https://www.philipphauer.de/study/se/design-pattern/strategy/einfuehrungV.svg)

Quellen
-------
- Codebeispiele: https://www.philipphauer.de/study/se/design-pattern/strategy.php
- UML: https://www.philipphauer.de/study/se/design-pattern/strategy/einfuehrungV.svg
- https://www.tutorialspoint.com/design_pattern/strategy_pattern.htm
- https://de.wikipedia.org/wiki/Strategie_(Entwurfsmuster)
