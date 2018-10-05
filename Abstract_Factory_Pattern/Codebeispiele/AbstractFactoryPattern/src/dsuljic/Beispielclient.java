package dsuljic;

public class Beispielclient {
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
}
