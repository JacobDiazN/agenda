package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Método para el botón Create
    //se crea un objeto Intent y le asignamos un nombre a este objeto en este caso Create
    //y creamos el método startActivity
    public void Create(View view){
        Intent create = new Intent(this, Create.class);
        startActivity(create);
    }

    //Método para el botón Read
    //se crea un objeto Intent y le asignamos un nombre a este objeto en este caso Read
    //y creamos el método startActivity
    public void Read(View view){
        Intent read = new Intent(this, Read.class);
        startActivity(read);
    }

    //Método para el botón Update
    //se crea un objeto Intent y le asignamos un nombre a este objeto en este caso Update
    //y creamos el método startActivity
    public void Update(View view){
        Intent update = new Intent(this, Update.class);
        startActivity(update);
    }

    //Método para el botón Delete
    //se crea un objeto Intent y le asignamos un nombre a este objeto en este caso Delete
    //y creamos el método startActivity
    public void Delete(View view){
        Intent delete = new Intent(this, Delete.class);
        startActivity(delete);
    }
}