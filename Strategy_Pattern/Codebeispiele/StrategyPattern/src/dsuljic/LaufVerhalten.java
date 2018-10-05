package dsuljic;

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