package dsuljic;

//Befehle
interface DruckBefehl{
  //Jeder Befehl kapselt die Logik zur Ausführung,
  //sowie den Zieldrucker
  public void ausfuehren(String dokument);
}
class SchwarzWeissDruckBefehl implements DruckBefehl{
  private SchwarzWeissDrucker drucker;
  
  //Der Befehl wird seinem Zieldrucker bei Instanziierung bekannt gemacht
  public SchwarzWeissDruckBefehl(SchwarzWeissDrucker drucker) {
      this.drucker = drucker;
  }

  public void ausfuehren(String dokument) {
      drucker.konfigurieren();
      drucker.drucken(dokument);
  }
}
class FarbDruckBefehl implements DruckBefehl{
  private FarbDrucker drucker;
  
  //Der Befehl wird seinem Zieldrucker bei Instanziierung bekannt gemacht
  public FarbDruckBefehl(FarbDrucker drucker) {
      this.drucker = drucker;
  }
  
  public void ausfuehren(String dokument) {
      drucker.drucken(dokument);
  }
}
class NadelDruckBefehl implements DruckBefehl{
  private NadelDrucker drucker;
  
  //Der Befehl wird seinem Zieldrucker bei Instanziierung bekannt gemacht
  public NadelDruckBefehl(NadelDrucker drucker) {
      this.drucker = drucker;
  }
  
  public void ausfuehren(String dokument) {
      drucker.umstaendlichKonfigurieren();
      drucker.drucken(dokument);
  }
}
class PDFDruckBefehl implements DruckBefehl{
  private PDFDrucker drucker;
  
  //Der Befehl wird seinem Zieldrucker bei Instanziierung bekannt gemacht
  public PDFDruckBefehl(PDFDrucker drucker) {
      this.drucker = drucker;
  }
  
  public void ausfuehren(String dokument) {
      drucker.speichern(dokument);
  }
}	