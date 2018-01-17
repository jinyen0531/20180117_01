package com.yenyu.a20180117_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yenyu.a20180117_01.data.Student;
import com.yenyu.a20180117_01.data.StudentScoreDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    final public static StudentScoreDAO dao=new StudentScoreDAO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override //onCreate只有在一開始開app才會啟動，所以離開後回來是啟動onResume
    protected void onResume() {
        super.onResume();

        lv = (ListView)findViewById(R.id.listView);
        ArrayList<String> studentNames = new ArrayList<>();
        for(Student s:dao.getList())
        {
            studentNames.add(s.name);
        }
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(
                MainActivity.this,android.R.layout.simple_list_item_1,studentNames);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent it = new Intent(MainActivity.this,ShowActivity.class);
                it.putExtra("id",dao.getList().get(i).id);
                startActivity(it);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_add)
        {
            Intent it = new Intent(MainActivity.this,AddActivity.class);
            startActivity(it);
        }



        return super.onOptionsItemSelected(item);
    }


}


