package dsuljic;

abstract class OfficeProgramm { 
    public void einpacken() {} 
    public void etikettieren() {} 
    public abstract void starten(); 
} 

class Word extends OfficeProgramm { 
    public void starten() { 
        System.out.println("Word startet"); 
    } 
} 
class Powerpoint extends OfficeProgramm { 
    public void starten() { 
        System.out.println("Powerpoint startet"); 
    } 
} 
class Excel extends OfficeProgramm { 
    public void starten() { 
        System.out.println("Excel startet"); 
    } 
} 
class Pages extends OfficeProgramm { 
    public void starten() { 
        System.out.println("Pages startet"); 
    } 
} 
class Keynode extends OfficeProgramm { 
    public void starten() { 
        System.out.println("Keynode startet"); 
    } 
} 
class Numbers extends OfficeProgramm { 
    public void starten() { 
        System.out.println("Numbers startet"); 
    } 
} 
class Write extends OfficeProgramm { 
    public void starten() { 
        System.out.println("Write startet"); 
    } 
} 
class Impress extends OfficeProgramm { 
    public void starten() { 
        System.out.println("Impress startet"); 
    } 
} 
class Calc extends OfficeProgramm { 
    public void starten() { 
        System.out.println("Calc startet"); 
    } 
} 	