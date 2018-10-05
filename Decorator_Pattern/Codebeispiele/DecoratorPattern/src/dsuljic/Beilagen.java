package dsuljic;

class Pommes extends Beilage { 
    public Pommes(Gericht gericht) { 
        super(gericht); 
    } 
    public void druckeBeschreibung() { 
        gericht.druckeBeschreibung(); 
        System.out.print(", Pommes"); 
    } 
    public double getPreis() { 
        return gericht.getPreis() + 2.50; 
    } 
} 


class Salat extends Beilage { 
    public Salat(Gericht gericht) { 
        super(gericht); 
    } 
    public void druckeBeschreibung() { 
        gericht.druckeBeschreibung(); 
        System.out.print(", Salat"); 
    } 
    public double getPreis() { 
        return gericht.getPreis() + 2.25; 
    } 
} 


class Nudeln extends Beilage { 
    public Nudeln(Gericht gericht) { 
        super(gericht); 
    } 
    public void druckeBeschreibung() { 
        gericht.druckeBeschreibung(); 
        System.out.print(", Nudeln"); 
    } 
    public double getPreis() { 
        return gericht.getPreis() + 4.50; 
    } 
} 


class Suppe extends Beilage { 
    public Suppe(Gericht gericht) { 
        super(gericht); 
    } 
    public void druckeBeschreibung() { 
        gericht.druckeBeschreibung(); 
        System.out.print(", Suppe"); 
    } 
    public double getPreis() { 
        return gericht.getPreis() + 1.50; 
    } 
} 


class Bratkartoffeln extends Beilage { 
    public Bratkartoffeln(Gericht gericht) { 
        super(gericht); 
    } 
    public void druckeBeschreibung() { 
        gericht.druckeBeschreibung(); 
        System.out.print(", Bratkartoffeln"); 
    } 
    public double getPreis() { 
        return gericht.getPreis() + 1.50; 
    } 
}  	