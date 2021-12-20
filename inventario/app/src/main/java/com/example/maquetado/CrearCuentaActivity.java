package com.example.maquetado;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class CrearCuentaActivity extends AppCompatActivity {

    ImageButton imagenUsuario;
    EditText nombreUsuario,apellidoPaterno,apellidoMaterno,telefono,correo,contrasena,confirmarContrasena;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearcuenta);
        nombreUsuario = findViewById(R.id.etNombreUsuarioCrear);
        apellidoPaterno = findViewById(R.id.etApellidoPaternoUsuarioCrear);
        apellidoMaterno = findViewById(R.id.etApellidoMaternoUsuarioCrear);
        telefono = findViewById(R.id.etTelefonoUsuarioCrear);
        correo = findViewById(R.id.etCorreoUsuarioCrear);
        contrasena = findViewById(R.id.etContrasenaUsuarioCrear);
        confirmarContrasena = findViewById(R.id.etConfirmarContrasenaUsuarioCrear);
        imagenUsuario = findViewById(R.id.ibImagenUsuarioCrear);

    }


    public void GuardarCrearCuenta(View view) {
        if(nombreUsuario.getText().toString().isEmpty() || apellidoPaterno.getText().toString().isEmpty() || apellidoMaterno.getText().toString().isEmpty() || telefono.getText().toString().isEmpty() || correo.getText().toString().isEmpty() || contrasena.getText().toString().isEmpty() || confirmarContrasena.getText().toString().isEmpty()){

            Toast alertaDatoFaltante = Toast.makeText(CrearCuentaActivity.this, "Falta algún dato, intentelo de nuevo.", Toast.LENGTH_SHORT);
            alertaDatoFaltante.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
            alertaDatoFaltante.show();

        }else{

            String contrasenaT, confirmarContrasenaT;
            contrasenaT = contrasena.getText().toString();
            confirmarContrasenaT = confirmarContrasena.getText().toString();

            if(contrasenaT.equals(confirmarContrasenaT)){

                ejecutarServicio("http://192.168.1.82/inventarionueva/insertarusuario.php");

            }else{

                Toast alertaContrasenasDistintas = Toast.makeText(CrearCuentaActivity.this, "Las contraseñas no coinciden, intentelo de nuevo.", Toast.LENGTH_SHORT);
                alertaContrasenasDistintas.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
                alertaContrasenasDistintas.show();
                confirmarContrasena.setText("");
                contrasena.setText("");

            }
        }
    }

    public void limpiarCrearCuenta(View view) {
        nombreUsuario.setText("");
        apellidoPaterno.setText("");
        apellidoMaterno.setText("");
        telefono.setText("");
        correo.setText("");
        contrasena.setText("");
        confirmarContrasena.setText("");
        Toast camposLimpios = Toast.makeText(CrearCuentaActivity.this, "Campos limpios.", Toast.LENGTH_SHORT);
        camposLimpios.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
        camposLimpios.show();
    }

    private void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();
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
                parametros.put("nombreUsuario",nombreUsuario.getText().toString());
                parametros.put("apellidoPaternoUsuario",apellidoPaterno.getText().toString());
                parametros.put("apellidoMaternoUsuario",apellidoMaterno.getText().toString());
                parametros.put("telefonoUsuario",telefono.getText().toString());
                parametros.put("correoUsuario",correo.getText().toString());
                parametros.put("contrasenaUsuario",contrasena.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
