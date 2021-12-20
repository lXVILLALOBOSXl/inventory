package com.example.maquetado;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.maquetado.Adaptadores.adaptadorEliminar;
import com.example.maquetado.Adaptadores.adaptadorVer;
import com.example.maquetado.Global.arrayListEmpresa;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EliminarRV extends AppCompatActivity {
    RecyclerView rvEliminar;
    Button eliminar;
    CheckBox cbEliminar;
    adaptadorEliminar adaptadoreliminar = new adaptadorEliminar();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminarrv);
        rvEliminar = findViewById(R.id.rvEliminar);
        eliminar = findViewById(R.id.btnEliminar);
        cbEliminar = findViewById(R.id.cbEliminar);
        adaptadoreliminar.context = this;
        queryItems("http://192.168.1.82/inventarionueva/querycount.php",0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvEliminar.setLayoutManager(linearLayoutManager);
        rvEliminar.setAdapter(adaptadoreliminar);
    }

    public void eliminar(View view) {
        if(arrayListEmpresa.eliminados.size()>=1) {
            for (int i = 0; i < (arrayListEmpresa.eliminados.size()); i++) {
                eliminarProducto("http://192.168.1.82/inventarionueva/eliminarproducto.php", (Integer.parseInt(arrayListEmpresa.eliminados.get(i))));
                queryItems("http://192.168.1.82/inventarionueva/querycount.php", 0);
            }
            arrayListEmpresa.eliminados.clear();
            Intent reiniciar = new Intent(this, EliminarRV.class);
            startActivity(reiniciar);
        }else{
            Toast alertaError = Toast.makeText(EliminarRV.this, "Seleccione un elemento para poder eliminar", Toast.LENGTH_SHORT);
            alertaError.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
            alertaError.show();
        }
    }

    public void regresar(View view) {
            Intent regresar = new Intent(this, MainMenuActivity.class);
            startActivity(regresar);
    }


    private void eliminarProducto(String URL, final int codigo){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast alertaError = Toast.makeText(EliminarRV.this, "Producto: "+codigo+" eliminado", Toast.LENGTH_SHORT);
                alertaError.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
                alertaError.show();
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
                parametros.put("codigoProducto",Integer.toString(codigo));
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    private void queryItems(String URL, final int codigo){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Actualizacion exitosa", Toast.LENGTH_SHORT).show();
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
                parametros.put("contador",Integer.toString(codigo));
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

}