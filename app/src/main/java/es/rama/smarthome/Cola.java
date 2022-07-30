package es.rama.smarthome;


class Cola
{
    private Nodo cab;
    private int n;
    public Cola()
    {
        this.cab = null;
        this.n = 0;
    }

    public int getLen()
    {
        return this.n;
    }

    public void insertar(int dato)
    {
        if (this.cab==null)
        {
            this.cab = new Nodo(dato);
            this.n++;
        }
        else
        {
            Nodo cab = this.cab;
            while(cab.getSig()!=null)
                cab = cab.getSig();
            cab.setSig(new Nodo(dato));
            this.n++;
        }
    }

    public int retirar()
    {
        if(cab!=null)
        {
            int dato = this.cab.getDato();
            this.cab = this.cab.getSig();
            this.n--;
            return dato;
        }
        return -1;
    }
}