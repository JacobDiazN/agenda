package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Create extends AppCompatActivity {

    //creación y encapsulación de los objetos que usaremos en este activity
    private EditText et_id_create, et_nombre_create, et_telefono_create, et_email_create;


    //Método onCreate
    //Crear objetos que nos permitan recuperar y encontrar a través de un ID los componentes que creamos en el activity_create.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        et_id_create = (EditText)findViewById(R.id.txt_id_create);
        et_nombre_create = (EditText)findViewById(R.id.txt_nombre_create);
        et_telefono_create = (EditText)findViewById(R.id.txt_telefono_create);
        et_email_create = (EditText)findViewById(R.id.txt_email_create);

    }

    //Metodo para registrar nuevos productos
    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String id = et_id_create.getText().toString();
        String nombre = et_nombre_create.getText().toString();
        String telefono = et_telefono_create.getText().toString();
        String email = et_email_create.getText().toString();

        if(!id.isEmpty() && !nombre.isEmpty() && !telefono.isEmpty() && !email.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("id", id);
            registro.put("nombre", nombre);
            registro.put("telefono", telefono);
            registro.put("email", email);

            BaseDeDatos.insert("agenda", null, registro);
            BaseDeDatos.close();

            et_id_create.setText("");
            et_nombre_create.setText("");
            et_telefono_create.setText("");
            et_email_create.setText("");

            Toast.makeText(this, "El Contacto fue registrado exitosamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error... Debes llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }




    //Método para el botón regresar
    public void Regresar_create(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}