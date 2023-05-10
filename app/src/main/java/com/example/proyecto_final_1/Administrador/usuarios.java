package com.example.proyecto_final_1.Administrador;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto_final_1.AdminSQLiteOpenHelper;
import com.example.proyecto_final_1.MainActivity;
import com.example.proyecto_final_1.R;

import java.util.ArrayList;

public class usuarios extends AppCompatActivity {
    ListView lv1;
    ArrayList<String> listaUsuarios;
    AdminSQLiteOpenHelper bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        bd=new AdminSQLiteOpenHelper(this);
        lv1=findViewById(R.id.lv3);

        consultarListaUsuarioa();
        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaUsuarios);
        lv1.setAdapter(adaptador);
    }

    private void consultarListaUsuarioa() {
        SQLiteDatabase db=bd.getReadableDatabase();

        listaUsuarios=new ArrayList<String>();
        Cursor cursor = db.rawQuery(
                "select cod_empleado from empleados", null);
        while(cursor.moveToNext()){

            listaUsuarios.add(cursor.getString(0));

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
           case R.id.annadir:
                Intent i=new Intent(this,registrar_empleados.class);
                startActivity(i);
                return true;

            case R.id.editar:
                Intent m=new Intent(this,modificar_empleado.class);
                startActivity(m);
                return true;

            case R.id.eliminar:
                Intent e=new Intent(this,eliminar_empleado.class);
                startActivity(e);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void salir(View view){
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
        finishAffinity();
    }

}

