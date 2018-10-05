package dsuljic;

interface AbstractGenerator{
    public Tier createTier();
    public Pflanze createPflanze();
    public Untergrund createUntergrund();
}

class Regenwaldgenerator implements AbstractGenerator{
    public Tier createTier(){
        return new Elefant();
    }
    public Pflanze createPflanze(){
        return new Baum();
    }
    public Untergrund createUntergrund(){
        return new Gras();
    }
}
class Wüstengenerator implements AbstractGenerator{
    public Tier createTier(){
        return new Kamel();
    }
    public Pflanze createPflanze(){
        return new Kaktus();
    }
    public Untergrund createUntergrund(){
        return new Sand();
    }
}
class Polargebietgenerator implements AbstractGenerator{
    public Tier createTier(){
        return new Eisbär();
    }
    public Pflanze createPflanze(){
        return new Flechte();
    }
    public Untergrund createUntergrund(){
        return new Schnee();
    }
}		