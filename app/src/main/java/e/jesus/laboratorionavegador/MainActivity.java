package e.jesus.laboratorionavegador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int OPCION_UNO = Menu.FIRST;

    ArrayList<String> sitios;
    ListView listView;
    ArrayAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sitios = new ArrayList<>();

        listView = findViewById(R.id.listaSitiosFavoritos);
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, sitios);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Buscador.class);
                intent.putExtra("url", sitios.get(position).toString());
                startActivity(intent);
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, OPCION_UNO, Menu.NONE, "Navegar");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case OPCION_UNO:
                Intent intent = new Intent(MainActivity.this, Buscador.class);
                startActivityForResult(intent, 11);
                break;

        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 11){
            if(resultCode == RESULT_OK){
                Toast.makeText(this, "si regresaste", Toast.LENGTH_SHORT).show();
                sitios.add(data.getData().toString());
                adapter.notifyDataSetChanged();
            }
        }
    }
}
