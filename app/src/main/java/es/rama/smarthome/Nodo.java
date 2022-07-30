package es.rama.smarthome;

class Nodo{
    private int dato;
    private Nodo sig;

    public Nodo(int dato)
    {
        this.dato = dato;
        this.sig = null;
    }

    public int getDato()
    {
        return this.dato;
    }

    public Nodo getSig()
    {
        return this.sig;
    }

    public void setDato(int dato)
    {
        this.dato = dato;
    }

    public void setSig(Nodo sig)
    {
        this.sig = sig;
    }
}