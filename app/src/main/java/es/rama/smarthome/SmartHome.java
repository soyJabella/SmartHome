package es.rama.smarthome;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SmartHome extends Activity {
    private Conexion conn;
    private Login l;

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        this.conn = new Conexion();
        this.l = new Login();
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

                    if(conn.verificarConexion())
                    {
                        Toast.makeText(SmartHome.this, "Se conecto", Toast.LENGTH_LONG).show();
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
}