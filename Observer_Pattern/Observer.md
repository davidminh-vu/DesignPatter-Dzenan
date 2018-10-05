Observer Pattern
================
*Für das Observer Pattern gilt:*

 - **Encapsulate what varies:** 
 Veränderbares von nicht-veränderbaren trennen (Kapselung).
 
 - **Favor composition over inheritance:**
 Komposition vor Vererbung favorisieren, da es in der Laufzeit veränderbar ist.
 
 - **Program to interfaces, not implementations:**
 Auf Interfaces und nicht auf Implementierung programmieren, da der geänderte Code einiges ändern kann. Teile die vorher funktionierten, funktionieren nicht mehr.
  
 - **Strive for loosely coupled design between objects that interact:**
Objekte sollen locker gekoppelt sein und sie sollen miteinander interagieren.
 
Problembeschreibung
-------------------
Der FAZ-Verlag möchte die jeweils aktuelle Zeitung an seine Abonnenten Familie Fischer und Maier senden. Bei jeder neuen Zeitung soll eine Auslieferung erfolgen. Das heißt, dass das Programm ständig eine Änderung hat (nach jeder neu veröffentlichten Zeitung). Hier kann man die Klasse *FAZVerlag* modellieren. Diese soll dann jeweils eine Referenz auf Familie Fischer und Familie Maier haben.

*Das hat jedoch folgende Nachteile:*

*Enge Kopplung* zwischen dem Verlag und den Abonnenten.Der Verlag hat Referenz auf die konkreten Implementierungen, dadurch gibt es folgende Probleme:

- Die Erweiterbarkeit ist stark eingeschränkt. Wenn sich jetzt beispielsweise ein neuer Abonnent anmeldet, muss die ganze Verlagsklasse modifiziert und geändert werden.
- Es gibt keine Chance *Änderungen* (Anmeldungen und Abmeldungen) durchzuführen.

Kontext
-------------------
Das Observer Pattern wird verwendet wenn es eine 1 - N -Beziehung zwischen den Objekten gibt. Hier ist es wichtig, dass geänderte Objekte, abhängige Objekte automatisch benachrichtigen und ändern.

Lösungen
--------
Es werden drei verschiedene Arten von Klassen in diesem Beispiel verwendet:

 - Subject
 - Observer
 - Client
 
Wichtig dabei ist zu verstehen, was sich ändert und was gleich bleibt. Der Aufruf von *erhalteZeitung(Zeitung)* soll auf jedem Abonnenten sein. Das einzige was variabel (dynamisch) sein soll, ist das hinzufügen und entfernen der Abonnenten. Deswegen müssen wir die Abonnenten kapseln, sodass *FAZVerlag* nur das Interface kennt und nicht direkt den Abonnenten.

***FAZVerlag:***

	public class FAZVerlag{ 

    private List<Abonnent> abonnentenList = new ArrayList<Abonnent>();

    private Zeitung aktuelleZeitung; 


    public void aboHinzufuegen(Abonnent abonnent) { 
        abonnentenList.add(abonnent); 
    } 

    public void aboEntfernen(Abonnent abonnent) { 
        abonnentenList.remove(abonnent); 
    } 

    private void verteileZeitung(Zeitung zeitung) { 
        for (Abonnent abonnent : abonnentenList) { 
            abonnent.erhalteZeitung(zeitung); 
        } 
    } 

    public void setAktuelleZeitung(Zeitung aktuelleZeitung) { 
        this.aktuelleZeitung = aktuelleZeitung; 
        //Nach dem einen neue Zeitung gesetzt wurde, werden alle Abonnenten benachrichtigt. 
        verteileZeitung(aktuelleZeitung); 
    } 

    public Zeitung getAktuelleZeitung() { 
        return aktuelleZeitung; 
    } 
    } 
Da wir dieses Programm jedoch wiederverwenden wollen, müssen wir es kohäsiv machen. Das heißt das jede Klasse oder Methode genau für eine Aufgabe verantwortlich sein soll. Da die Methoden getAktuelleZeitung() und setAktuelleZeitung() FAZ spezifisch sind, müssen wir sie trennen.

***Quellcode Verlag, FAZVerlag:***

	public abstract class Verlag { 
	
	    private List<Abonnent> abonnentenList = new ArrayList<Abonnent>(); 
	
	    public void aboHinzufuegen(Abonnent abonnent) { 
	        abonnentenList.add(abonnent); 
	    } 
	
	    public void aboEntfernen(Abonnent abonnent) { 
	        abonnentenList.remove(abonnent); 
	    } 
	
	    protected void verteileZeitung(Zeitung zeitung) { 
	        for (Abonnent abonnent : abonnentenList) { 
	            abonnent.erhalteZeitung(zeitung); 
	        } 
	    } 
	} 
	public class FAZVerlag extends Verlag { 
	
	    private Zeitung aktuelleZeitung; 
	
	    public void setAktuelleZeitung(Zeitung aktuelleZeitung) { 
	        this.aktuelleZeitung = aktuelleZeitung; 
	        //Nach dem einen neue Zeitung gesetzt wurde, werden alle Abonnenten benachrichtigt. 
	        verteileZeitung(aktuelleZeitung); 
	    } 
	
	    public Zeitung getAktuelleZeitung() { 
	        return aktuelleZeitung; 
	    } 
	} 			
	
***Quellcode Zeitung:***

	public class Zeitung { 
	
	    //Ein examplarisches Field. 
	    private final String titel; 
	
	    public Zeitung(String titel) { 
	        this.titel = titel; 
	    } 
	
	    public String getTitel() { 
	        return titel; 
	    } 
	}  

***Quellcode Abonnent mit den Realisierungen FamilieFischer, FamilieMeier und FirmaXY:***

	interface Abonnent { 
	
	    public void erhalteZeitung(Zeitung zeitung); 
	
	} 
	
	class FamilieFischer implements Abonnent { 
	
	    public void erhalteZeitung(Zeitung zeitung) { 
	        System.out.println("Familie Fischer erhielt die aktuelle Zeitung: " + zeitung.getTitel()); 
	    } 
	} 
	
	class FamilieMeier implements Abonnent { 
	
	    public void erhalteZeitung(Zeitung zeitung) { 
	        System.out.println("Familie Meier erhielt die aktuelle Zeitung: " + zeitung.getTitel()); 
	    } 
	} 
	
	class FirmaXY implements Abonnent { 
	
	    public void erhalteZeitung(Zeitung zeitung) { 
	        System.out.println("Firma XY erhielt die aktuelle Zeitung: " + zeitung.getTitel()); 
	    } 
	}  	
***Beispielclient:***
	
	public class Beispielclient { 
	
	    public static void main(String[] args) { 
	        FAZVerlag verlag = new FAZVerlag(); 
	        verlag.aboHinzufuegen(new FamilieFischer()); 
	        verlag.aboHinzufuegen(new FamilieMeier()); 
	        FirmaXY firma = new FirmaXY(); 
	        verlag.aboHinzufuegen(firma); 
	
	        verlag.setAktuelleZeitung(new Zeitung("Skandal!")); 
	        //Familie Fischer erhielt die aktuelle Zeitung: Skandal! 
	        //Familie Meier erhielt die aktuelle Zeitung: Skandal! 
	        //Firma XY erhielt die aktuelle Zeitung: Skandal! 
	
	        verlag.aboEntfernen(firma); 
	        verlag.setAktuelleZeitung(new Zeitung("Doch alles halb so wild!")); 
	        //Familie Fischer erhielt die aktuelle Zeitung: Doch alles halb so wild! 
	        //Familie Meier erhielt die aktuelle Zeitung: Doch alles halb so wild! 
	    } 
	} 	


*Auf dieser Art haben wir folgende Vorteile:*

Verlag und Abonnenten sind entkoppelt, was bedeutet, dass:
- problemloses hinzufügen / entfernen der Abonnenten möglich ist.
- Abonnenten jederzeit hinzugefügt / entfernt werden können.
- der Code für andere Verläge wiederverwendet werden kann und man Abonnent von mehreren Sachen sein kann.

Jedoch verstößt es gegen die "Auf Interfaces und nicht auf Implementierung programmieren" Regel.
	
UML
--------	

![Observer-UML](https://www.philipphauer.de/study/se/design-pattern/observer/observer-def.svg)

	
Quellen
-------
- Codebeispiele: https://www.philipphauer.de/study/se/design-pattern/observer.php
- UML: https://www.philipphauer.de/study/se/design-pattern/observer/observer-def.svg
- https://www.tutorialspoint.com/design_pattern/observer_pattern.htm
- https://de.wikipedia.org/wiki/Beobachter_(Entwurfsmuster)



