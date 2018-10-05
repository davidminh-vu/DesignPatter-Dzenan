package dsuljic;

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