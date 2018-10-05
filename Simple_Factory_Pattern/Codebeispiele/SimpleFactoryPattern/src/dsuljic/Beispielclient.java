package dsuljic;

public class Beispielclient{ 
    public static void main(String[] args) { 
        SoftwareShop appleShop = new AppleiWorkFactory(); 
        OfficeProgramm appleTextProgram = appleShop.holeApp("Textverarbeitung"); 
        appleTextProgram.starten();//"Pages startet" 

        SoftwareShop msShop = new MicrosoftOfficeFactory(); 
        OfficeProgramm wordPresProgram = msShop.holeApp("Pr�sentation"); 
        wordPresProgram.starten();//"Powerpoint startet" 
    } 
} 			