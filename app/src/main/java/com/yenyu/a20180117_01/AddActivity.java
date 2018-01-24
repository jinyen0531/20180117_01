package com.yenyu.a20180117_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.yenyu.a20180117_01.data.Student;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void clickAdd(View v)
    {
        EditText ed1= (EditText)findViewById(R.id.editText);
        EditText ed2= (EditText)findViewById(R.id.editText2);
        EditText ed3= (EditText)findViewById(R.id.editText3);

        int id = Integer.valueOf(ed1.getText().toString());
        String name = ed2.getText().toString();
        int score = Integer.valueOf(ed3.getText().toString());
        MainActivity.dao.add(new Student(id,name,score));
        finish(); //利用dao的add方式 將輸入的值傳進去Student陣列
        //輸入finish() 才會回到上一頁
    }
}
