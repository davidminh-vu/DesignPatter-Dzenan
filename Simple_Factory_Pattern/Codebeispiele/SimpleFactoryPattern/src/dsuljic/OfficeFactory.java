package dsuljic;

class MicrosoftOfficeFactory extends SoftwareShop{ 
    @Override 
    protected OfficeProgramm createOfficeProgram(String zuHolendesProg) { 
        OfficeProgramm programm = null; 
        if (zuHolendesProg.equals("Textverarbeitung")) { 
            programm = new Word(); 
        } 
        else if (zuHolendesProg.equals("Pr�sentation")) { 
            programm = new Powerpoint(); 
        } 
        else if (zuHolendesProg.equals("Tabellenkalkulation")) { 
            programm = new Excel(); 
        } 
        else { 
            System.err.println("Ung�ltig!"); 
        } 
        return programm; 
    } 
} 
class AppleiWorkFactory extends SoftwareShop{ 
    @Override 
    protected OfficeProgramm createOfficeProgram(String zuHolendesProg) { 
        OfficeProgramm programm = null; 
        if (zuHolendesProg.equals("Textverarbeitung")) { 
            programm = new Pages(); 
        } 
        else if (zuHolendesProg.equals("Pr�sentation")) { 
            programm = new Keynode(); 
        } 
        else if (zuHolendesProg.equals("Tabellenkalkulation")) { 
            programm = new Numbers(); 
        } 
        else { 
            System.err.println("Ung�ltig!"); 
        } 
        return programm; 
    } 
} 
class SunOpenOfficeFactory extends SoftwareShop{ 
    @Override 
    protected OfficeProgramm createOfficeProgram(String zuHolendesProg) { 
        OfficeProgramm programm = null; 
        if (zuHolendesProg.equals("Textverarbeitung")) { 
            programm = new Write(); 
        } 
        else if (zuHolendesProg.equals("Pr�sentation")) { 
            programm = new Impress(); 
        } 
        else if (zuHolendesProg.equals("Tabellenkalkulation")) { 
            programm = new Calc(); 
        } 
        else { 
            System.err.println("Ung�ltig!"); 
        } 
        return programm; 
    } 
} 	