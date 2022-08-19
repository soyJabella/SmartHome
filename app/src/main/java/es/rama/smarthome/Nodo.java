package es.rama.smarthome;

class Nodo{
    private String dato;
    private Nodo sig;

    public Nodo(String dato)
    {
        this.dato = dato;
        this.sig = null;
    }

    public String getDato()
    {
        return this.dato;
    }

    public Nodo getSig()
    {
        return this.sig;
    }

    public void setDato(String dato)
    {
        this.dato = dato;
    }

    public void setSig(Nodo sig)
    {
        this.sig = sig;
    }
}