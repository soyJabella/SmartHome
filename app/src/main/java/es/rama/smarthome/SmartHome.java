package es.rama.smarthome;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.WidgetContainer;

public class SmartHome extends AppCompatActivity {
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

        private boolean validarIp(String ip)
        {
            String vlsIp[] = ip.split("\\.");
            if(vlsIp.length != 4)
                return false;

            try
            {
                for(int i=0; i<4; i++)
                {
                    Integer.parseInt(vlsIp[i]);
                }
            }
            catch (NumberFormatException e)
            {
                return false;
            }

            return true;
        }

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

                    //Borrando espacios en blanco
                    ipServidor = ipServidor.replaceAll(" ", "");

                    if(!validarIp(ipServidor))
                    {
                        iIpServidor.setText("");
                        Toast.makeText(SmartHome.this, "No se reconoce la dirección ip", Toast.LENGTH_LONG).show();
                        barEspera.setVisibility(ProgressBar.INVISIBLE);
                        return;
                    }

                    conn.establecerIpServidor(ipServidor);
                    while(conn.isValIpServidor());

                    if(conn.verificarConexion()==true)
                    {
                        Toast.makeText(SmartHome.this, "Se conecto", Toast.LENGTH_LONG).show();
                        new SmartRemote();
                    }
                    else
                    {
                        iIpServidor.setText("");
                        Toast.makeText(SmartHome.this, "No se pudo establecer conexion", Toast.LENGTH_LONG).show();
                    }
                    barEspera.setVisibility(ProgressBar.INVISIBLE);
                }
            });
        }
    }

    class SmartRemote
    {
        private Button bAPuerta;
        private Button bELuz;
        private Button bEAlarma;
        private Button bAGarage;

        private Button bCPuerta;
        private Button bALuz;
        private Button bAAlarma;
        private Button bCGarage;

        private EscuchadorBotones escuchador;

        public SmartRemote()
        {
            setContentView(R.layout.smarthome);

            bAPuerta = (Button) findViewById(R.id.bAPuerta);
            bELuz = (Button) findViewById(R.id.bELuz);
            bEAlarma = (Button) findViewById(R.id.bEAlarma);
            bAGarage = (Button) findViewById(R.id.bAGarage);

            bCPuerta = (Button) findViewById(R.id.bCPuerta);
            bALuz = (Button) findViewById(R.id.bALuz);
            bAAlarma = (Button) findViewById(R.id.bAAlarma);
            bCGarage = (Button) findViewById(R.id.bCGarage);

            escuchador = new EscuchadorBotones();

            //Asignando el escuchador a los botones
            bAPuerta.setOnClickListener(escuchador);
            bELuz.setOnClickListener(escuchador);
            bEAlarma.setOnClickListener(escuchador);
            bAGarage.setOnClickListener(escuchador);

            bCPuerta.setOnClickListener(escuchador);
            bALuz.setOnClickListener(escuchador);
            bAAlarma.setOnClickListener(escuchador);
            bCGarage.setOnClickListener(escuchador);

            bCPuerta.setEnabled(false);
            bALuz.setEnabled(false);
            bAAlarma.setEnabled(false);
            bCGarage.setEnabled(false);
        }

        class EscuchadorBotones implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                if (view.getId() == bAPuerta.getId()) {
                    conn.obtenerCola().insertar("1");
                    bAPuerta.setEnabled(false);
                    bCPuerta.setEnabled(true);
                } else if (view.getId() == bCPuerta.getId()) {
                    conn.obtenerCola().insertar("2");
                    bAPuerta.setEnabled(true);
                    bCPuerta.setEnabled(false);
                } else if (view.getId() == bELuz.getId()) {
                    conn.obtenerCola().insertar("3");
                    bELuz.setEnabled(false);
                    bALuz.setEnabled(true);
                } else if (view.getId() == bALuz.getId()) {
                    conn.obtenerCola().insertar("4");
                    bELuz.setEnabled(true);
                    bALuz.setEnabled(false);
                } else if (view.getId() == bEAlarma.getId()) {
                    conn.obtenerCola().insertar("5");
                    bEAlarma.setEnabled(false);
                    bAAlarma.setEnabled(true);
                } else if (view.getId() == bAAlarma.getId()) {
                    conn.obtenerCola().insertar("6");
                    bEAlarma.setEnabled(true);
                    bAAlarma.setEnabled(false);
                } else if (view.getId() == bAGarage.getId()) {
                    conn.obtenerCola().insertar("7");
                    bAGarage.setEnabled(false);
                    bCGarage.setEnabled(true);

                } else if (view.getId() == bCGarage.getId()) {
                    conn.obtenerCola().insertar("8");
                    bAGarage.setEnabled(true);
                    bCGarage.setEnabled(false);
                }
            }
        }
    }
}