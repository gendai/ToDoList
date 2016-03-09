package com.example.english.todolist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    Button addb;
    Button delb;
    EditText edt;
    ListView lstv;
    Chronometer chr;
    ArrayAdapter<String> aa;
    ArrayList<String> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addb = (Button) findViewById(R.id.buttonADD);
        delb = (Button) findViewById(R.id.buttonDEL);
        edt = (EditText) findViewById(R.id.textbox);
        edt.setText("");
        lstv = (ListView) findViewById(R.id.ListView);
        al = new ArrayList<String>();
        aa = new ArrayAdapter<String>(this, R.layout.items, android.R.id.text1, al);
        ArrayList<String> listtest = new ArrayList<String>();
        //listtest.add("data 1");
        final CusAdapt cus = new CusAdapt(this, new String[]{}, listtest);
        lstv.setAdapter(cus);
        addb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edt.getText().toString().equals("")) {
                    //Log.d("Text entered", edt.getText().toString());
                    cus.add(edt.getText().toString());
                    cus.notifyDataSetChanged();
                    edt.setText("");
                }else{
                    Toast.makeText(getApplicationContext(), "Please enter a valid task name", Toast.LENGTH_LONG).show();
                }
            }
        });
       delb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cus.Clear();
                cus.notifyDataSetChanged();
            }
        });
        lstv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                cus.delete(position);
                cus.notifyDataSetChanged();
                return true;
            }
        });

        lstv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    public boolean CheckState(CusAdapt cs){
        for(int i = 0; i < cs.items.size()-1; i++){
            if(cs.items.get(i).getChecked() == true){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
