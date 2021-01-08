package com.example.bibliotec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {

    private static final String url = "http://192.168.1.3/bibliotec/registroUsuario.php";
    EditText nom, ap, am, tel, dir, corr, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nom = findViewById(R.id.nombre);
        ap = findViewById(R.id.ap);
        am = findViewById(R.id.am);
        tel = findViewById(R.id.telefono);
        dir = findViewById(R.id.direccion);
        corr = findViewById(R.id.correo);
        pass = findViewById(R.id.password);
    }

    public void Registrar(View v) {

        final String nombre = this.nom.getText().toString();
        final String apellido_p = this.ap.getText().toString();
        final String apellido_m = this.am.getText().toString();
        final String telefono = this.tel.getText().toString().trim();
        final String direccion = this.dir.getText().toString();
        final String correo = this.corr.getText().toString().trim();
        final String contra = this.pass.getText().toString();
        if (nombre.equals("") || apellido_p.equals("") || apellido_m.equals("") || telefono.equals("") || direccion.equals("") || correo.equals("") || contra.equals("")) {

            nom.setError("Error: Campo vacio!");
            ap.setError("Error: Campo vacio!");
            am.setError("Error: Campo vacio!");
            tel.setError("Error: Campo vacio!");
            dir.setError("Error: Campo vacio!");
            corr.setError("Error: Campo vacio!");
            pass.setError("Error: Campo vacio!");
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("exito")){
                    Toast.makeText(RegistroActivity.this, "Nuevo usuario ingresado correctamente", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(RegistroActivity.this, "Error: usuario o contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistroActivity.this, "Error: De comunicación" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nombre", nombre);
                params.put("apellido_p", apellido_p);
                params.put("apellido_m", apellido_m);
                params.put("telefono", telefono);
                params.put("direccion", direccion);
                params.put("correo", correo);
                params.put("contra", contra);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(RegistroActivity.this);
        requestQueue.add(stringRequest);
    }

}