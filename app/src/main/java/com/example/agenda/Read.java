package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Read extends AppCompatActivity {

    //creación y encapsulación de los objetos que usaremos en este activity
    private EditText et_id_read, et_nombre_read, et_telefono_read, et_email_read;

    //Método onCreate
    //Crear objetos que nos permitan recuperar y encontrar a través de un ID los componentes que creamos en el activity_read.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        et_id_read = (EditText)findViewById(R.id.txt_id_read);
        et_nombre_read = (EditText)findViewById(R.id.txt_nombre_read);
        et_telefono_read = (EditText)findViewById(R.id.txt_telefono_read);
        et_email_read = (EditText)findViewById(R.id.txt_email_read);

    }

    //Metodo para buscar un contacto
    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String id = et_id_read.getText().toString();

        if(!id.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery
                    ("select nombre, telefono, email from agenda where id ="+ id, null);

            if(fila.moveToFirst()){
                et_nombre_read.setText(fila.getString(0));
                et_telefono_read.setText(fila.getString(1));
                et_email_read.setText(fila.getString(2));
                BaseDeDatos.close();
            }else{
                Toast.makeText(this, "El Contacto no existe", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this, "Error... Debes introducir el ID del Contacto", Toast.LENGTH_SHORT).show();
        }
    }


    //Método para el botón regresar
    public void Regresar_read(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}