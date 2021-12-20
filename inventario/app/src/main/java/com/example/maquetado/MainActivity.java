package com.example.maquetado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button iniciarSesion,crearCuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarSesion = findViewById(R.id.btnIniciarSesion);
        crearCuenta = findViewById(R.id.btnCrearSesion);
    }

    public void iniciarSesion(View view) {
        Intent cambio = new Intent(this, RegistroActivity.class);
        startActivity(cambio);
    }

    public void crearCuenta(View view) {
        Intent cambio = new Intent(this, CrearCuentaActivity.class);
        startActivity(cambio);
    }
}