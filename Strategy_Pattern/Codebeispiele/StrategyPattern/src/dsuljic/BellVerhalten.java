package dsuljic;

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