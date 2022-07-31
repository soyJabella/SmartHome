package es.rama.smarthome;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintSet;

public class SmartHome extends Activity {
    private Conexion conn;

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        this.conn = new Conexion();
        new Login();
    }

    class Login
    {
        private Button bConectar;
        private EditText iIpServidor;
        private ProgressBar barEspera;

        public Login()
        {
            setContentView(R.layout.login);

            iIpServidor = (EditText) findViewById(R.id.iIpServidor);
            barEspera = (ProgressBar) findViewById(R.id.barEspera);
            bConectar = (Button) findViewById(R.id.bConectar);
            bConectar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    barEspera.setVisibility(ProgressBar.VISIBLE);
                    String ipServidor = iIpServidor.getText().toString();
                    conn.establecerIpServidor(ipServidor);

                    try
                    {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e)
                    {

                    }

                    if(conn.verificarConexion()==true)
                    {
                        Toast.makeText(SmartHome.this, "Se conecto", Toast.LENGTH_LONG).show();
                        new SmartRemote();
                    }
                    else
                    {
                        barEspera.setVisibility(ProgressBar.INVISIBLE);
                        Toast.makeText(SmartHome.this, "No se pudo establecer conexion", Toast.LENGTH_LONG).show();
                    }

                    barEspera.setVisibility(ProgressBar.INVISIBLE);
                }
            });
        }
    }

    class SmartRemote
    {
        private Button bPuerta;
        private Button bLuz;
        private Button bAlarma;
        private EscuchadorBotones escuchador;

        public SmartRemote()
        {
            setContentView(R.layout.smarthome);
            bPuerta = (Button) findViewById(R.id.bPuerta);
            bLuz = (Button) findViewById(R.id.bLuz);
            bAlarma = (Button) findViewById(R.id.bAlarma);
            escuchador = new EscuchadorBotones();

            //Asignando el escuchador a los botones
            bPuerta.setOnClickListener(escuchador);
            bLuz.setOnClickListener(escuchador);
            bAlarma.setOnClickListener(escuchador);
        }

        class EscuchadorBotones implements View.OnClickListener
        {
            @Override
            public void onClick(View view)
            {
                if(view.getId() == bPuerta.getId())
                {
                    conn.obtenerCola().insertar(1);
                }
                else if(view.getId() == bLuz.getId())
                {
                    conn.obtenerCola().insertar(2);
                }
                else if(view.getId() == bAlarma.getId())
                {
                    conn.obtenerCola().insertar(3);
                }
            }
        }

    }
}