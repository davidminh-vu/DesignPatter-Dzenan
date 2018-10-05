package dsuljic;

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