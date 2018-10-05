package dsuljic;

class Hueftsteak implements Gericht { 
    public void druckeBeschreibung() { 
        System.out.print("Hüftsteak"); 
    } 
    public double getPreis() { 
        return 13.0; 
    } 
} 


class Tofu implements Gericht { 
    public void druckeBeschreibung() { 
        System.out.print("Tofu"); 
    } 
    public double getPreis() { 
        return 8.50; 
    } 
} 


class Garnelen implements Gericht { 
    public void druckeBeschreibung() { 
        System.out.print("Garnelen"); 
    } 
    public double getPreis() { 
        return 13.50; 
    } 

} 


class WienerSchnitzel implements Gericht { 
    public void druckeBeschreibung() { 
        System.out.print("WienerSchnitzel"); 
    } 
    public double getPreis() { 
        return 10.50; 
    } 
} 	