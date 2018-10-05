
Adapt Pattern
========================
*Für das Adapt Pattern gilt:*

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
Wir verfügen über eine Media-Player-Schnittstelle und einen Audio-Player, welcher Klassen von der Media-Player-Schnittstelle implementiert. Der Audio-Player kann MP3-Formate abspielen. 
Außerdem haben wir eine eine Advanced-Media-Player-Klasse, welche .vlc und .mp4 wiedergeben kann.
Nun soll der Media-Player auch andere Formate abspielen können. Um das zu machen, brauchen wir eine Adapterklasse, welche die Media-Player-Schnittstelle implementiert und die Objekte von Advanced-Media-Player-Klasse verwendet.

Kontext
-------------------
Das Adapt Pattern ist eine Brücke zwischen zwei inkompatiblen Schnittstellen. Das Adapt Patter ist ein Strukturmuster, welches verwendet wird um zwei unabhängige Schnittstellen zu kombinieren. Es beinhaltet eine Klasse, welche die Aufgabe hat unabhängige oder inkompatible Schnittstellen zu verbinden. Ein gutes Beispiel wäre ein Kartenleser, welcher für die Verbindung zwischen Laptop und Speicherkarte fungiert.

Lösungen
--------
 Der Audio-Player verwendet die Adapterklasse MediaAdapter und übergibt diesem den gewünschten Audiotyp, ohne die Klasse zu kennen. Außerdem verwenden wir eine Demoklasse (AdapterPatternDemo), welche uns erlaubt verschiedene Formate abzuspielen.

***MediaPlayer.java:***

	public  interface  MediaPlayer  {  
		public  void play(String audioType,  String fileName);  
	}


***AdvancedMediaPlayer.java:***
	
	public interface AdvancedMediaPlayer {	
	   public void playVlc(String fileName);
	   public void playMp4(String fileName);
	}

***VlcPlayer.java:***

	public class VlcPlayer implements AdvancedMediaPlayer{
	   @Override
	   public void playVlc(String fileName) {
	      System.out.println("Playing vlc file. Name: "+ fileName);		
	   }

	   @Override
	   public void playMp4(String fileName) {
	      //do nothing
	   }
	}

***Mp4Player.java:***
	
		public class Mp4Player implements AdvancedMediaPlayer{

	   @Override
	   public void playVlc(String fileName) {
	      //do nothing
	   }

	   @Override
	   public void playMp4(String fileName) {
	      System.out.println("Playing mp4 file. Name: "+ fileName);		
	   }
	}

***MediaAdapter.java:***

	public class MediaAdapter implements MediaPlayer {

	   AdvancedMediaPlayer advancedMusicPlayer;

	   public MediaAdapter(String audioType){
	   
	      if(audioType.equalsIgnoreCase("vlc") ){
	         advancedMusicPlayer = new VlcPlayer();			
	         
	      }else if (audioType.equalsIgnoreCase("mp4")){
	         advancedMusicPlayer = new Mp4Player();
	      }	
	   }

	   @Override
	   public void play(String audioType, String fileName) {
	   
	      if(audioType.equalsIgnoreCase("vlc")){
	         advancedMusicPlayer.playVlc(fileName);
	      }
	      else if(audioType.equalsIgnoreCase("mp4")){
	         advancedMusicPlayer.playMp4(fileName);
	      }
	   }
	}

***AudioPlayer.java:***

	public class AudioPlayer implements MediaPlayer {
	   MediaAdapter mediaAdapter; 

	   @Override
	   public void play(String audioType, String fileName) {		

	      //inbuilt support to play mp3 music files
	      if(audioType.equalsIgnoreCase("mp3")){
	         System.out.println("Playing mp3 file. Name: " + fileName);			
	      } 
	      
	      //mediaAdapter is providing support to play other file formats
	      else if(audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")){
	         mediaAdapter = new MediaAdapter(audioType);
	         mediaAdapter.play(audioType, fileName);
	      }
	      
	      else{
	         System.out.println("Invalid media. " + audioType + " format not supported");
	      }
	   }   
	}
	
***AdapterPatternDemo:***

	public class AdapterPatternDemo {
	   public static void main(String[] args) {
	      AudioPlayer audioPlayer = new AudioPlayer();

	      audioPlayer.play("mp3", "beyond the horizon.mp3");
	      audioPlayer.play("mp4", "alone.mp4");
	      audioPlayer.play("vlc", "far far away.vlc");
	      audioPlayer.play("avi", "mind me.avi");
	   }
	}
	
Dadurch haben wir folgende Vorteile:

 - **Wiederverwendbarkeit vorhandener Softwarelösungen** Durch das Design ist es möglich vorhandene Lösungen für die Software wiederzuverwenden. Außerdem muss der Code nicht geändert werden, sondern nur erweitert.
 - **Anpassung:** Es passt sich an der Zielklasse an. Dadurch kann das Verhalten von der Zielklasse überschrieben werden. Außerdem kann es Unterklasse anpassen.

UML
-----
![Adapter-Pattern](https://www.tutorialspoint.com/design_pattern/images/adapter_pattern_uml_diagram.jpg)

Quellen
-------
- http://www.inztitut.de/blog/glossar/adapter-pattern/
- Codebeispiele: https://www.tutorialspoint.com/design_pattern/adapter_pattern.htm
- UML: https://www.tutorialspoint.com/design_pattern/images/adapter_pattern_uml_diagram.jpg
- https://de.wikipedia.org/wiki/Adapter_(Entwurfsmuster)
- https://sourcemaking.com/design_patterns/adapter


