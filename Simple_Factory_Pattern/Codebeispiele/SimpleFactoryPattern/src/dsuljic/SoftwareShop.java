package dsuljic;

public abstract class SoftwareShop { 

    public OfficeProgramm holeApp(String zuHolendesProg) { 
        //Delegation der Objekterstellung an Subklasse 
        OfficeProgramm programm = createOfficeProgram(zuHolendesProg); 

        //weitere verarbeitung 
        programm.einpacken(); 
        programm.etikettieren(); 

        return programm; 
    } 

    //Definition der Factory Method 
    protected abstract OfficeProgramm createOfficeProgram(String zuHolendesProg); 

}  	