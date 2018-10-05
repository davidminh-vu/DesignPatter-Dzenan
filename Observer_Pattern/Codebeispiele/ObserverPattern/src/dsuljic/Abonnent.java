package dsuljic;

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