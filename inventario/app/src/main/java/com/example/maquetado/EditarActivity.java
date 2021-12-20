package com.example.maquetado;

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
import com.android.volley.toolbox.JsonArrayRequest;
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

public class EditarActivity extends AppCompatActivity {
    ImageButton editarImagenProducto;
    EditText editarCodigoDelProducto,editarPrecioProducto,editarPiezasProducto,buscarProducto;
    private Spinner editarNombreProducto,editarMarcaProducto,editarCantidadProducto,editarUnidadProducto,editarTipoProducto;
    ArrayList<String> nombreProductoArray;
    ArrayList<String> nombreMarcaArray;
    ArrayList<String> cantidadProductoArray;
    ArrayList<String> unidadProductoArray;
    ArrayList<String> categoriaProductoArray;
    String codigoProductoBuscar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        editarImagenProducto = findViewById(R.id.ibEditarImagenProducto);
        editarCodigoDelProducto = findViewById(R.id.etCodigoDelProductoEditar);
        editarPrecioProducto = findViewById(R.id.etPrecioProductoEditar);
        editarPiezasProducto = findViewById(R.id.etPiezasProductoEditar);
        editarNombreProducto = findViewById(R.id.spNombreProductoEditar);
        editarMarcaProducto = findViewById(R.id.spMarcaProductoEditar);
        editarCantidadProducto = findViewById(R.id.spCantidadProductoEditar);
        editarUnidadProducto = findViewById(R.id.spUnidadProductoEditar);
        editarTipoProducto = findViewById(R.id.spTipoProductoEditar);
        buscarProducto = findViewById(R.id.etBuscarProducto);

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


    public void guardarEditar(View view) {
        if(editarPiezasProducto.getText().toString().isEmpty() || editarPrecioProducto.getText().toString().isEmpty() || editarCodigoDelProducto.getText().toString().isEmpty() || editarTipoProducto.getSelectedItemPosition() == 0 || editarUnidadProducto.getSelectedItemPosition() == 0 || editarCantidadProducto.getSelectedItemPosition() == 0 || editarMarcaProducto.getSelectedItemPosition() == 0 || editarNombreProducto.getSelectedItemPosition() == 0 ){
            Toast alertaDatoFaltante = Toast.makeText(EditarActivity.this, "Falta algún dato, intentelo de nuevo.", Toast.LENGTH_SHORT);
            alertaDatoFaltante.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER, 0, 0);
            alertaDatoFaltante.show();
        }else{
            ejecutarServicio("http://192.168.1.82/inventarionueva/editarproducto.php");
        }

    }

    public void buscarProducto(View view) {
        codigoProductoBuscar = buscarProducto.getText().toString();
        buscarProducto("http://192.168.1.82/inventarionueva/buscarproducto.php?codigoProducto="+codigoProductoBuscar+"");
    }

    private void buscarProducto(String url){
        JsonArrayRequest jsonArrarRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        editarCodigoDelProducto.setText(jsonObject.getString("codigoInterno"));
                        editarPrecioProducto.setText(jsonObject.getString("precioProducto"));
                        editarPiezasProducto.setText(jsonObject.getString("piezasProducto"));
                        editarNombreProducto.setSelection(jsonObject.getInt("idTipoProducto")-1);
                        editarMarcaProducto.setSelection(jsonObject.getInt("idMarcaProducto")-1);
                        editarCantidadProducto.setSelection(jsonObject.getInt("idCantidadProducto")-1);
                        editarTipoProducto.setSelection(jsonObject.getInt("idCategoriaProducto")-1);
                        editarUnidadProducto.setSelection(jsonObject.getInt("idUnidadProducto")-1);
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
                parametros.put("codigoProducto",buscarProducto.getText().toString());
                parametros.put("codigoInterno",editarCodigoDelProducto.getText().toString());
                parametros.put("imagenProducto","NULL");
                parametros.put("piezasProducto",editarPiezasProducto.getText().toString());
                parametros.put("precioProducto",editarPrecioProducto.getText().toString());
                parametros.put("idTipoProducto",Integer.toString(editarNombreProducto.getSelectedItemPosition()+1));
                parametros.put("idCategoriaProducto",Integer.toString(editarTipoProducto.getSelectedItemPosition()+1));
                parametros.put("idMarcaProducto",Integer.toString(editarMarcaProducto.getSelectedItemPosition()+1));
                parametros.put("idCantidadProducto",Integer.toString(editarCantidadProducto.getSelectedItemPosition()+1));
                parametros.put("idUnidadProducto",Integer.toString(editarUnidadProducto.getSelectedItemPosition()+1));
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
                            editarNombreProducto.setAdapter(new ArrayAdapter<String>(EditarActivity.this, android.R.layout.simple_spinner_dropdown_item, nombreProductoArray));
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
                            editarMarcaProducto.setAdapter(new ArrayAdapter<String>(EditarActivity.this, android.R.layout.simple_spinner_dropdown_item, nombreMarcaArray));
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
                            editarCantidadProducto.setAdapter(new ArrayAdapter<String>(EditarActivity.this, android.R.layout.simple_spinner_dropdown_item, cantidadProductoArray));
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
                            editarUnidadProducto.setAdapter(new ArrayAdapter<String>(EditarActivity.this, android.R.layout.simple_spinner_dropdown_item, unidadProductoArray));
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
                            editarTipoProducto.setAdapter(new ArrayAdapter<String>(EditarActivity.this, android.R.layout.simple_spinner_dropdown_item, categoriaProductoArray));
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
