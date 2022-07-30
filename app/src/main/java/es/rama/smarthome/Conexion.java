package es.rama.smarthome;

import java.net.InetAddress;
import java.net.Socket;

class Conexion implements Runnable
{
    private Socket conexion;
    private Thread t;
    private String ipServidor;
    private Cola c;
    private boolean verConexion;

    public Conexion()
    {
        this.verConexion = false;
        this.c = new Cola();
        this.t = new Thread(this, "Conexion");
        this.t.start();
    }

    public void establecerIpServidor(String ip)
    {
        this.ipServidor = ip;
    }

    public boolean verificarConexion()
    {
        return this.verConexion;
    }

    @Override
    public void run()
    {
        while(true)
        {
            if(this.verificarConexion()!=false)
            {
                while(true)
                {

                }
            }
            else if(this.ipServidor!=null)
            {
                try
                {
                    InetAddress ip = InetAddress.getByName(this.ipServidor);
                    this.conexion = new Socket(ip, 8085);
                    this.verConexion = true;
                }
                catch (Exception e)
                {
                    this.ipServidor = null;
                }
            }
        }
    }
}