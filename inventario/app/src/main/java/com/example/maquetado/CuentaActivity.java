package com.example.maquetado;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class

CuentaActivity extends AppCompatActivity {
    EditText nombreEditarCuenta,apellidoPaternoEditarCuenta,apellidoMaternoEditarCuenta,telefonoEditarCuenta,correoEditarCuenta,contrasenaEditarCuenta,confirmarContrasenaEditarCuenta;
    TextView tipoCuenta;
    Button guardarCambiosCuenta;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);
        nombreEditarCuenta = findViewById(R.id.etNombreUsuarioEditar);
        apellidoPaternoEditarCuenta = findViewById(R.id.etApellidoPaternoUsuarioEditar);
        apellidoMaternoEditarCuenta = findViewById(R.id.etApellidoMaternoUsuarioEditar);
        telefonoEditarCuenta = findViewById(R.id.etTelefonoUsuarioEditar);
        correoEditarCuenta = findViewById(R.id.etCorreoUsuarioEditar);
        contrasenaEditarCuenta =  findViewById(R.id.etContrasenaCuentaEditar);
        confirmarContrasenaEditarCuenta = findViewById(R.id.etConfirmarContrasenaCuentaEditar);
        tipoCuenta = findViewById(R.id.lblTipoUsuario);
        guardarCambiosCuenta = findViewById(R.id.btnGuardarCambiosCuenta);
        String correoCuenta = getIntent().getExtras().getString("correoCuenta");
        buscarCuenta("http://192.168.1.82/inventarionueva/buscarusuario.php?correoUsuario="+correoCuenta+"");
    }

    public void guardarCambiosCuenta(View view) {
        if(nombreEditarCuenta.getText().toString().isEmpty() || apellidoPaternoEditarCuenta.getText().toString().isEmpty() || apellidoMaternoEditarCuenta.getText().toString().isEmpty() || telefonoEditarCuenta.getText().toString().isEmpty() || correoEditarCuenta.getText().toString().isEmpty() || contrasenaEditarCuenta.getText().toString().isEmpty() || confirmarContrasenaEditarCuenta.getText().toString().isEmpty()){

            Toast alertaDatoFaltante = Toast.makeText(CuentaActivity.this, "Falta algún dato, intentelo de nuevo.", Toast.LENGTH_SHORT);
            alertaDatoFaltante.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
            alertaDatoFaltante.show();

        }else{

            String contrasenaT, confirmarContrasenaT;
            contrasenaT = contrasenaEditarCuenta.getText().toString();
            confirmarContrasenaT = confirmarContrasenaEditarCuenta.getText().toString();

            if(contrasenaT.equals(confirmarContrasenaT)){

                ejecutarServicio("http://192.168.1.82/inventarionueva/editarusuario.php");

            }else{

                Toast alertaContrasenasDistintas = Toast.makeText(CuentaActivity.this, "Las contraseñas no coinciden, intentelo de nuevo.", Toast.LENGTH_SHORT);
                alertaContrasenasDistintas.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
                alertaContrasenasDistintas.show();
                confirmarContrasenaEditarCuenta.setText("");
                contrasenaEditarCuenta.setText("");

            }
        }

    }

    private void buscarCuenta(String url){
        JsonArrayRequest jsonArrarRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        nombreEditarCuenta.setText(jsonObject.getString("nombreUsuario"));
                        apellidoPaternoEditarCuenta.setText(jsonObject.getString("apellidoPaternoUsuario"));
                        apellidoMaternoEditarCuenta.setText(jsonObject.getString("apellidoMaternoUsuario"));
                        telefonoEditarCuenta.setText(jsonObject.getString("telefonoUsuario"));
                        correoEditarCuenta.setText((jsonObject.getString("correoUsuario")));
                        contrasenaEditarCuenta.setText("");
                        confirmarContrasenaEditarCuenta.setText("");
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error de conexión.", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrarRequest);
    }

    private void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Cambios hechos exitosamente", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("nombreUsuario",nombreEditarCuenta.getText().toString());
                parametros.put("apellidoPaternoUsuario",apellidoPaternoEditarCuenta.getText().toString());
                parametros.put("apellidoMaternoUsuario",apellidoMaternoEditarCuenta.getText().toString());
                parametros.put("telefonoUsuario",telefonoEditarCuenta.getText().toString());
                parametros.put("correoUsuario",correoEditarCuenta.getText().toString());
                parametros.put("contrasenaUsuario",contrasenaEditarCuenta.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
