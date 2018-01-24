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

import com.yenyu.a20180117_01.data.DBtype;
import com.yenyu.a20180117_01.data.Student;
import com.yenyu.a20180117_01.data.StudentDAO;
import com.yenyu.a20180117_01.data.StudentDAOFactory;
import com.yenyu.a20180117_01.data.StudentFileDAO;
import com.yenyu.a20180117_01.data.StudentScoreDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    public static StudentDAO dao;
    DBtype dbtype;
    ArrayAdapter<String> adapter;
    ArrayList<String> studentNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbtype=DBtype.CLOUD; //1.記憶體 2.檔案 3.資料庫 4.雲端
        dao= StudentDAOFactory.getDAOInstance(this,dbtype);
        //dao 在onCreate 新增，因為this 必須Activtiy啟動才會產生
        //放最外層宣告 會讀不到
        studentNames = new ArrayList<>();
        adapter= new ArrayAdapter<String>(
                MainActivity.this,android.R.layout.simple_list_item_1,studentNames);
        lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(adapter); //利用ArrayAdapter 編輯ListView 內容及顯示模式
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override   //設定每個ListView的按鈕
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent it = new Intent(MainActivity.this,ShowActivity.class);
                it.putExtra("id",dao.getList().get(i).id);
                startActivity(it);
            }
        });
    }
    @Override //onCreate只有在一開始開app才會啟動，所以離開後回來是啟動onResume
    protected void onResume() {
        super.onResume();
        //將所有名字都顯示在ListView上
       refreshData();
    }


    public void refreshData()
    {
        studentNames.clear();
        for(Student s:dao.getList())
        {
            studentNames.add(s.name);
        }
        adapter.notifyDataSetChanged();
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


