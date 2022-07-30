package es.rama.smarthome;

import java.io.DataOutputStream;
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

    public synchronized Cola obtenerCola()
    {
        return this.c;
    }

    private void enviarMensaje(int Msg)
    {
        DataOutputStream cSalida;
        try
        {
            cSalida = new DataOutputStream(this.conexion.getOutputStream());
            cSalida.writeInt(Msg);
        } catch (Exception e) {

        }
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
                    if(this.obtenerCola().getLen()>0)
                    {
                        this.enviarMensaje(this.obtenerCola().retirar());
                    }
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