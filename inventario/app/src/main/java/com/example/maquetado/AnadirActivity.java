package com.example.maquetado;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AnadirActivity extends AppCompatActivity {
    ImageButton anadirImagenProducto;
    EditText codigoDelProducto,precioProducto,piezasProducto;
    private Spinner nombreProducto,marcaProducto,cantidadProducto,unidadProducto,tipoProducto;
    ArrayList<String> nombreProductoArray;
    ArrayList<String> nombreMarcaArray;
    ArrayList<String> cantidadProductoArray;
    ArrayList<String> unidadProductoArray;
    ArrayList<String> categoriaProductoArray;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);
        anadirImagenProducto = findViewById(R.id.ibAnadirImagenProducto);
        codigoDelProducto = findViewById(R.id.etCodigoDelProductoAnadir);
        piezasProducto = findViewById(R.id.etPiezasProductoAnadir);
        precioProducto = findViewById(R.id.etPrecioProductoAnadir);
        nombreProducto = findViewById(R.id.spNombreProductoAnadir);
        marcaProducto = findViewById(R.id.spMarcaProductoAnadir);
        cantidadProducto = findViewById(R.id.spCantidadProductoAnadir);
        unidadProducto = findViewById(R.id.spUnidadProductoAnadir);
        tipoProducto = findViewById(R.id.spTipoProductoAnadir);

        nombreMarcaArray = new ArrayList<>();
        cantidadProductoArray = new ArrayList<>();
        nombreProductoArray = new ArrayList<>();
        unidadProductoArray = new ArrayList<>();
        categoriaProductoArray = new ArrayList<>();

        listarNombre();
        listarMarca();
        listarCantidad();
        listarUnidad();
        listarCategoria();

    }

    public void guardarAnadir(View view) {
        if(piezasProducto.getText().toString().isEmpty() || precioProducto.getText().toString().isEmpty() || codigoDelProducto.getText().toString().isEmpty() || tipoProducto.getSelectedItemPosition() == 0 || unidadProducto.getSelectedItemPosition() == 0 || cantidadProducto.getSelectedItemPosition() == 0 || marcaProducto.getSelectedItemPosition() == 0 || nombreProducto.getSelectedItemPosition() == 0 ){
            Toast alertaDatoFaltante = Toast.makeText(AnadirActivity.this, "Falta algún dato, intentelo de nuevo.", Toast.LENGTH_SHORT);
            alertaDatoFaltante.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
            alertaDatoFaltante.show();
        }else{
            ejecutarServicio("http://192.168.1.82/inventarionueva/insertarproducto.php");
        }
    }

    public void limpiarAnadir(View view) {
        piezasProducto.setText("");
        precioProducto.setText("");
        codigoDelProducto.setText("");
        tipoProducto.setSelection(0);
        unidadProducto.setSelection(0);
        cantidadProducto.setSelection(0);
        marcaProducto.setSelection(0);
        nombreProducto.setSelection(0);
        Toast camposLimpios = Toast.makeText(AnadirActivity.this, "Campos limpios.", Toast.LENGTH_SHORT);
        camposLimpios.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
        camposLimpios.show();
    }

    private void ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Producto guardado", Toast.LENGTH_SHORT).show();
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
                parametros.put("codigoProducto","NULL");
                parametros.put("imagenProducto","NULL");
                parametros.put("piezasProducto",piezasProducto.getText().toString());
                parametros.put("precioProducto",precioProducto.getText().toString());
                parametros.put("idTipoProducto",Integer.toString(nombreProducto.getSelectedItemPosition()+1));
                parametros.put("idCategoriaProducto",Integer.toString(tipoProducto.getSelectedItemPosition()+1));
                parametros.put("idMarcaProducto",Integer.toString(marcaProducto.getSelectedItemPosition()+1));
                parametros.put("idCantidadProducto",Integer.toString(cantidadProducto.getSelectedItemPosition()+1));
                parametros.put("idUnidadProducto",Integer.toString(unidadProducto.getSelectedItemPosition()+1));
                parametros.put("codigoInterno",codigoDelProducto.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void listarNombre(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Conexion.URL_WEB_SERVICES + "listar-nombre.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("usuario");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String country = jsonObject1.getString("nombreProducto");
                                nombreProductoArray.add(country);
                            }
                            nombreProducto.setAdapter(new ArrayAdapter<String>(AnadirActivity.this, android.R.layout.simple_spinner_dropdown_item, nombreProductoArray));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();;
                            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    public void listarMarca(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Conexion.URL_WEB_SERVICES + "listar-marca.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("usuario");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String country = jsonObject1.getString("nombreMarca");
                                nombreMarcaArray.add(country);
                            }
                            marcaProducto.setAdapter(new ArrayAdapter<String>(AnadirActivity.this, android.R.layout.simple_spinner_dropdown_item, nombreMarcaArray));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();;
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    public void listarCantidad(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Conexion.URL_WEB_SERVICES + "listar-cantidad.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("usuario");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String country = jsonObject1.getString("cantidad");
                                cantidadProductoArray.add(country);
                            }
                            cantidadProducto.setAdapter(new ArrayAdapter<String>(AnadirActivity.this, android.R.layout.simple_spinner_dropdown_item, cantidadProductoArray));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();;
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    public void listarUnidad(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Conexion.URL_WEB_SERVICES + "listar-unidad.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("usuario");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String country = jsonObject1.getString("unidad");
                                unidadProductoArray.add(country);
                            }
                            unidadProducto.setAdapter(new ArrayAdapter<String>(AnadirActivity.this, android.R.layout.simple_spinner_dropdown_item, unidadProductoArray));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();;
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    public void listarCategoria(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Conexion.URL_WEB_SERVICES + "listar-categoria.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("usuario");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String country = jsonObject1.getString("categoria");
                                categoriaProductoArray.add(country);
                            }
                            tipoProducto.setAdapter(new ArrayAdapter<String>(AnadirActivity.this, android.R.layout.simple_spinner_dropdown_item, categoriaProductoArray));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();;
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

}
