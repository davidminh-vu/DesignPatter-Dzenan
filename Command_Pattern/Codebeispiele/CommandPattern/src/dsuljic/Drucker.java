package dsuljic;

//Empf‰nger
class SchwarzWeissDrucker{
  public void konfigurieren(){
      //Einstellungen
  }
  public void drucken(String dokument){
      //Schwarz-Weiﬂ-Druck
      System.out.println(dokument);
  }
}
class FarbDrucker{
  public void drucken(String dokument){
      //Farb-Druck
      System.out.println(dokument.toUpperCase());
  }
}
class NadelDrucker{
  public void umstaendlichKonfigurieren(){
      //Einstellungen
  }
  public void drucken(String dokument){
      //Nadel-Druck
      System.out.println(dokument.toLowerCase());
  }
}
class PDFDrucker{
  public void speichern(String dokument){
      //Als PDF speichern
      System.out.println("PDF:"+dokument);
  }
}		