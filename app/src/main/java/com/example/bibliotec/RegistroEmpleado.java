package com.example.bibliotec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistroEmpleado extends AppCompatActivity {
    private static final String url = "http://192.168.1.3/bibliotec/registroEmpleado.php";
    EditText nom, ap, am, tel, dir, corr, pass, cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_empleado);

        nom = findViewById(R.id.nombreE);
        ap = findViewById(R.id.apE);
        am = findViewById(R.id.amE);
        corr = findViewById(R.id.correoE);
        pass = findViewById(R.id.passwordE);
        cod = findViewById(R.id.codigo);
    }

    public void RegistrarE(View v) {

        final String nombre = this.nom.getText().toString();
        final String apellido_p = this.ap.getText().toString();
        final String apellido_m = this.am.getText().toString();
        final String correo = this.corr.getText().toString().trim();
        final String contra = this.pass.getText().toString();
        final String codigo = this.cod.getText().toString();
        if (nombre.equals("") || apellido_p.equals("") || apellido_m.equals("") ||  correo.equals("") || contra.equals("")|| cod.equals("amigos")) {

            nom.setError("Error: Campo vacio!");
            ap.setError("Error: Campo vacio!");
            am.setError("Error: Campo vacio!");
            corr.setError("Error: Campo vacio!");
            pass.setError("Error: Campo vacio!");
            cod.setError("Error");
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("exito")){
                    Toast.makeText(RegistroEmpleado.this, "Nuevo empleado ingresado correctamente", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(RegistroEmpleado.this, "Error: usuario o contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistroEmpleado.this, "Error: De comunicación" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nombre", nombre);
                params.put("apellido_p", apellido_p);
                params.put("apellido_m", apellido_m);
                params.put("correo", correo);
                params.put("contra", contra);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(RegistroEmpleado.this);
        requestQueue.add(stringRequest);
    }
}