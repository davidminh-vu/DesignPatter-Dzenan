
Proxy Pattern
========================
*Für das Proxy Pattern gilt:*

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
Es soll eine Bildschnittstelle und andere konkrete Klassen erstellt werden, die die Bildschnittstelle implementieren. Außerdem brauchen wir eine Klasse zur Reduzierung des Speicherbedarfs beim Laden von Image-Objekten (Proxy-Bild-Klasse).

Kontext
-------------------
Das Proxy Pattern wird eine Klasse verwendet, welche die Funktionalität einer anderen Klasse repräsentiert. Außerdem wird ein Objekt erstellt, welches ein Objekt repräsentiert. Dadurch ist es möglich die Funktionalitäten mit der Außenwelt zu verbinden.

Lösungen
--------
Wir werden eine Demoklasse verwenden, welche die Proxy-Bild-Klasse verwendet. Dadurch ist es uns möglich ein Image-Objekt auszulesen und es anzeigen zu lassen.

***Image.java:***
	
	public interface Image {
	   void display();
	}

***RealImage.java:***
	
	
	public class RealImage implements Image {

	   private String fileName;

	   public RealImage(String fileName){
	      this.fileName = fileName;
	      loadFromDisk(fileName);
	   }

	   @Override
	   public void display() {
	      System.out.println("Displaying " + fileName);
	   }

	   private void loadFromDisk(String fileName){
	      System.out.println("Loading " + fileName);
	   }
	}

***ProxyImage.java:***

	public class ProxyImage implements Image{

	   private RealImage realImage;
	   private String fileName;

	   public ProxyImage(String fileName){
	      this.fileName = fileName;
	   }

	   @Override
	   public void display() {
	      if(realImage == null){
	         realImage = new RealImage(fileName);
	      }
	      realImage.display();
	   }
	}
	
***ProxyPatternDemo.java:***

	public class ProxyPatternDemo {
		
	   public static void main(String[] args) {
	      Image image = new ProxyImage("test_10mb.jpg");

	      //image will be loaded from disk
	      image.display(); 
	      System.out.println("");
	      
	      //image will not be loaded from disk
	      image.display(); 	
	   }
	}

UML
-------------------	
![https://www.tutorialspoint.com/design_pattern/images/proxy_pattern_uml_diagram.jpg](https://www.tutorialspoint.com/design_pattern/images/proxy_pattern_uml_diagram.jpg)

Quellen
-------
- Codebeispiele: https://www.tutorialspoint.com/design_pattern/proxy_pattern.htm
- UML:https://www.tutorialspoint.com/design_pattern/images/proxy_pattern_uml_diagram.jpg
- https://sourcemaking.com/design_patterns/proxy
- https://en.wikipedia.org/wiki/Proxy_pattern


