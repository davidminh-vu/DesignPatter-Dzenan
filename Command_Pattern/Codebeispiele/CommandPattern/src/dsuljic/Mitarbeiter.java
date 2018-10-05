package dsuljic;

//Aufrufer
class Sekretaer{
  private DruckBefehl druckBefehl;
  
  //Aufrufer wird mit dem konkreten DruckBefehl über einen Setter geladen
  //Alternative: Konstruktor
  public void setDruckBefehl(DruckBefehl druckBefehl) {
      this.druckBefehl = druckBefehl;
  }

  public void druckAusloesen(String dokument){
      druckBefehl.ausfuehren(dokument);
  }
}
class Vorstand{
  private DruckBefehl druckBefehl;
  
  //Aufrufer wird mit dem konkreten DruckBefehl über einen Setter geladen
  //Alternative: Konstruktor
  public void setDruckBefehl(DruckBefehl druckBefehl) {
      this.druckBefehl = druckBefehl;
  }
  
  public void druckAusloesen(String dokument){
      druckBefehl.ausfuehren(dokument);
  }
}
//etc. identischer Code...
//Sinnvoll: Druckmembers in gemeinsame Superklasse Mitarbeiter auslagern		