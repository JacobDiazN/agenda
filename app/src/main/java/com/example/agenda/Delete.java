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

public class Delete extends AppCompatActivity {

    //creación y encapsulación de los objetos que usaremos en este activity
    private EditText et_id_delete, et_nombre_delete, et_telefono_delete, et_email_delete;

    //Método onCreate
    //Crear objetos que nos permitan recuperar y encontrar a través de un ID los componentes que creamos en el activity_delete.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        et_id_delete = (EditText) findViewById(R.id.txt_id_delete);
        et_nombre_delete = (EditText) findViewById(R.id.txt_nombre_delete);
        et_telefono_delete = (EditText) findViewById(R.id.txt_telefono_delete);
        et_email_delete = (EditText) findViewById(R.id.txt_email_delete);

    }

    //Metodo para buscar un contacto
    public void Buscar_delete(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String id = et_id_delete.getText().toString();

        if (!id.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery
                    ("select nombre, telefono, email from agenda where id =" + id, null);

            if (fila.moveToFirst()) {
                et_nombre_delete.setText(fila.getString(0));
                et_telefono_delete.setText(fila.getString(1));
                et_email_delete.setText(fila.getString(2));
                BaseDeDatos.close();
            } else {
                Toast.makeText(this, "El Contacto no existe", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        } else {
            Toast.makeText(this, "Error... Debes introducir el ID del Contacto", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para Eliminar algun Contacto
    public void Eliminar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String id = et_id_delete.getText().toString();

        if (!id.isEmpty()) {
            int cantidad = BaseDeDatos.delete("agenda", "id =" + id, null);
            BaseDeDatos.close();

            et_id_delete.setText("");
            et_nombre_delete.setText("");
            et_telefono_delete.setText("");
            et_email_delete.setText("");

            if (cantidad == 1) {
                Toast.makeText(this, "El Contacto fue eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El Contacto no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Error... Debes introducir el ID del Contacto", Toast.LENGTH_SHORT).show();

        }
    }



    //Método para el botón regresar
    public void Regresar_delete(View view){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }
}