package com.example.maquetado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    Button anadir,editar,eliminar,ver,cuenta,salir;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        anadir = findViewById(R.id.btnAnadir);
        editar = findViewById(R.id.btnEditar);
        eliminar = findViewById(R.id.btnEliminar);
        ver = findViewById(R.id.btnVer);
        cuenta = findViewById(R.id.btnCuenta);
        salir = findViewById(R.id.btnSalir);
    }

    public void anadirMenuAdmin(View view) {
        Intent cambio = new Intent(this, MenuAnadirActivity.class);
        startActivity(cambio);
    }

    public void editarMenuAdmin(View view) {
        Intent cambio = new Intent(this, EditarActivity.class);
        startActivity(cambio);
    }

    public void eliminarMenuAdmin(View view) {
        Intent cambio = new Intent(this, EliminarRV.class);
        startActivity(cambio);
    }

    public void cuentaMenuAdmin(View view) {
        String correoCuenta = getIntent().getExtras().getString("correoUsuario");
        Intent cambio = new Intent(this, CuentaActivity.class);
        cambio.putExtra("correoCuenta", correoCuenta);
        startActivity(cambio);
    }

    public void salirMenuadmin(View view) {
        finish();
    }

    public void verLista(View view) {
        Intent cambio = new Intent(this, DatosRvActivity.class);
        startActivity(cambio);
    }
}
