package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    //creación y encapsulación de los objetos que usaremos en este activity
    private EditText et_id_update, et_nombre_update, et_telefono_update, et_email_update;

    //Método onCreate
    //Crear objetos que nos permitan recuperar y encontrar a través de un ID los componentes que creamos en el activity_update.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        et_id_update = (EditText)findViewById(R.id.txt_id_update);
        et_nombre_update = (EditText)findViewById(R.id.txt_nombre_update);
        et_telefono_update = (EditText)findViewById(R.id.txt_telefono_update);
        et_email_update = (EditText)findViewById(R.id.txt_email_update);

    }

    //Metodo para buscar un contacto
    public void Buscar_update(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String id = et_id_update.getText().toString();

        if(!id.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery
                    ("select nombre, telefono, email from agenda where id ="+ id, null);

            if(fila.moveToFirst()){
                et_nombre_update.setText(fila.getString(0));
                et_telefono_update.setText(fila.getString(1));
                et_email_update.setText(fila.getString(2));
                BaseDeDatos.close();
            }else{
                Toast.makeText(this, "El Contacto no existe", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this, "Error... Debes introducir el ID del Contacto", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para modificar productos
    public void Modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String id = et_id_update.getText().toString();
        String nombre = et_nombre_update.getText().toString();
        String telefono = et_telefono_update.getText().toString();
        String email = et_email_update.getText().toString();

        if(!id.isEmpty() && !nombre.isEmpty() && !telefono.isEmpty() && !email.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("id", id);
            registro.put("nombre", nombre);
            registro.put("telefono", telefono);
            registro.put("email", email);

            int cantidad = BaseDeDatos.update("agenda", registro, "id="+ id, null);
            BaseDeDatos.close();

            if(cantidad == 1){
                Toast.makeText(this, "El Contacto fue modificado exitosamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "El Contacto no existe", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Error... Debes llenar todos los Campos", Toast.LENGTH_SHORT).show();
        }

    }

    //Método para el botón regresar
    public void Regresar_update(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}