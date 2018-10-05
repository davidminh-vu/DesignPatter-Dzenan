Abstract Factory Pattern
========================
*Für das Abstract Factory Pattern gilt:*

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
Ein Kunde möchte ein Framework zur Erstellung, verschiedener Spielewelten (Wüste, Regenwald, etc.). Außerdem soll eine API erstellt werden mit dem sich Objekte für diese Spielewelt generieren lassen (Pflanzen, Tiere, etc.). Das Framework soll Methoden zur Generierung dieser Objekte zur Verfügung stellen.
*Zuerst erstellen wir einen Prototypen mit der Welt: Regenwald und den Objekten: Gras, Baum, Elefant*

	class Regenwaldgenerator{
	    public Elefant createElefant(){
	        return new Elefant();
	    }
	    public Baum createBaum(){
	        return new Baum();
	    }
	    public Gras createGras(){
	        return new Gras();
	    }
	}

	class Elefant{}
	class Baum{}
	class Gras{}	

*Über den Regenwaldgenerator erstellt der Client seine konkreten Spielweltelemente:*

	public static void main(String[] args) {
	    Regenwaldgenerator regenwaldgenerator = new Regenwaldgenerator();
	    Baum baum1 = regenwaldgenerator.createBaum();
	    Baum baum2 = regenwaldgenerator.createBaum();
	    Elefant elefant = regenwaldgenerator.createElefant();
	    Gras gras = regenwaldgenerator.createGras();
	    //Welt zusammen setzen...
	}		
Falls wir jedoch mehrere Welten erstellen wird der Clientcode sehr komplex und schwer zu ändern sein.

*Das hat folgende Nachteile:*

 - **Inkonsistent:** Der Client kann die Welten mischen, was er nicht tun sollte (beispielsweise ein Elefant im Schnee).
 - **Keine Allgemeingültigkeit** Falls man die gleiche Anzahl an Tieren im Regenwald hat, wie am Polargebiet, so muss der Code neu geschrieben und verändert werden.
 - **Keine Erweiterbarkeit** Der Client arbeitet wieder mit konkreten Klasse. Das soll er aber nicht tun, da es sich dann um eine enge Kopplung handelt. Dadurch kann das Programm schwer angepasst werden.

Kontext
-------------------
Das Abstrace Factory Pattern ist eine "Superfabrik". Sie besteht aus anderen Fabriken und wir eigentlich auch als "factory of factories" bezeichnet. Genauso wie das normale Factory Pattern bietet das Abstracte Factory Patter die beste Möglichkeit um Objekte zu erstellen. Dafür ist eine Schnittstelle verantwortlich, welche eine Factory von verwandten Objekten erstellt, ohne deren Klasse explizit anzugeben.	

Lösungen
--------
Das Ziel ist es, dass der Client einen generischen Code zur Spielewelt schreiben kann. Unbedeutend sollen die konkreten Typen der Spielewelt sein. Der Client soll nicht wissen, außer, dass er eine Spielewelt zusammensetzen muss.

***Code des Interfaces AbstractGenerator und dessen Implementierungen Regenwald-, Wüsten und Polargebietgenerator:***

	interface AbstractGenerator{
	    public Tier createTier();
	    public Pflanze createPflanze();
	    public Untergrund createUntergrund();
	}

	class Regenwaldgenerator implements AbstractGenerator{
	    public Tier createTier(){
	        return new Elefant();
	    }
	    public Pflanze createPflanze(){
	        return new Baum();
	    }
	    public Untergrund createUntergrund(){
	        return new Gras();
	    }
	}
	class Wüstengenerator implements AbstractGenerator{
	    public Tier createTier(){
	        return new Kamel();
	    }
	    public Pflanze createPflanze(){
	        return new Kaktus();
	    }
	    public Untergrund createUntergrund(){
	        return new Sand();
	    }
	}
	class Polargebietgenerator implements AbstractGenerator{
	    public Tier createTier(){
	        return new Eisbär();
	    }
	    public Pflanze createPflanze(){
	        return new Flechte();
	    }
	    public Untergrund createUntergrund(){
	        return new Schnee();
	    }
	}	

***Interfaces und Implementierungen der Spielweltobjekte:***
	
	//Interface
	interface Tier{}
	interface Pflanze{}
	interface Untergrund{}

	//Regenwald
	class Elefant implements Tier{}
	class Baum implements Pflanze{}
	class Gras implements Untergrund{}

	//Wüste
	class Kamel implements Tier{}
	class Kaktus implements Pflanze{}
	class Sand implements Untergrund{}

	//Polargebiet
	class Eisbär implements Tier{}
	class Flechte implements Pflanze{}
	class Schnee implements Untergrund{}	

Welche Spielewelt erstellt wird entscheidet alleine die Implementierung.

***Client kann allgemeingültigen Spielweltcode schreiben:***

	public static void main(String[] args) {
	    //An einer zentrale Stelle wird der Typ bestimmt -> schneller Austausch
	    AbstractGenerator generator = new Polargebietgenerator();
	    
	    //Objekte werden erstellt 
	    Pflanze pflanze = generator.createPflanze();
	    Tier tier = generator.createTier();
	    Untergrund untergrund = generator.createUntergrund();
	    //...
	    
	    //Welt wird gebaut
	}		


Dadurch haben wir folgende Vorteile:

 - **Flexibilität und Allgemeingültigkeit** Durch die Entkopplung der Objektverarbeitung von einer *konkreten Implementierung*
 - **Erweiterbarkeit und Wartbarkeit** Die Verarbeitung geschieht nun an einer zentralen Stelle. Dadurch können Spielewelten schneller integriert werden.
 - **Wartbarkeit:** Es können Änderungen in den Spielewelten durchgeführt werden.
 - **Konsistenz:** Jetzt werden wirklich nur Spielewelten erstellt und ein Tiger kann beispielsweise nicht an einem Polargebiet landen.

UML
-------------------	
![Abstract-Factory-UML](https://www.philipphauer.de/study/se/design-pattern/abstract-factory/einleitung5.svg)

Quellen
-------
- Codebeispiele: https://www.philipphauer.de/study/se/design-pattern/abstract-factory.php
- UML: https://www.philipphauer.de/study/se/design-pattern/abstract-factory/einleitung5.svg
- https://www.tutorialspoint.com/design_pattern/abstract_factory_pattern.htm
- https://en.wikipedia.org/wiki/Abstract_factory_pattern


