package com.example.bibliotec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText usr, passwd;
    TextView aqui;
    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usr = findViewById(R.id.username);
        passwd = findViewById(R.id.password);
        View view;
        switch1 = (Switch) findViewById(R.id.switch1);
        aqui = findViewById(R.id.nuevo);
        aqui.setOnClickListener(v -> registro());
    }

    public void onClick(View view) {

        final String user = this.usr.getText().toString();
        final String pass = this.passwd.getText().toString();

        if (user.isEmpty() || pass.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Error: No puedes dejar los campos vacios", Toast.LENGTH_SHORT).show();

        } else {

            if (switch1.isChecked()) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.1.3/bibliotec/loginEmpleado.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("exito")) {
                            Toast.makeText(MainActivity.this, "Ingreso correcto", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MainActivity.this, AgregarLibro.class);
                            startActivity(i);
                            //finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Error: usuario o contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error comunicación", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("user", user);
                        params.put("pass", pass);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(stringRequest);

            } else {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.1.3/bibliotec/login.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("exito")) {
                            Toast.makeText(MainActivity.this, "Ingreso correcto", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MainActivity.this, Inicio.class);
                            startActivity(i);
                            //finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Error: usuario o contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error comunicación", Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("user", user);
                        params.put("pass", pass);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(stringRequest);
            }
        }
    }

    public void registro() {
        if (switch1.isChecked()) {
            Intent i = new Intent(MainActivity.this, RegistroEmpleado.class);
            startActivity(i);
        } else {
            Intent i = new Intent(MainActivity.this, RegistroActivity.class);
            startActivity(i);
            //finish();
        }
    }
}