package com.yenyu.a20180117_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yenyu.a20180117_01.data.Student;
import com.yenyu.a20180117_01.data.StudentScoreDAO;

public class ShowActivity extends AppCompatActivity {
    TextView tv1,tv2,tv3;
    Student s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        tv1=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        tv3=(TextView)findViewById(R.id.textView3);

        Intent it =getIntent();
        int id = it.getIntExtra("id",0);
        s = MainActivity.dao.getStudent(id);
        tv1.setText(String.valueOf(s.id));
        tv2.setText(s.name);
        tv3.setText(String.valueOf(s.score));


    }
    public void clickBack(View v)
    {
        Intent it =new Intent(ShowActivity.this,MainActivity.class);
        startActivity(it);
    }
    public void clickDelete(View v)
    {

    }
    public void clickEdit(View v)
    {

    }
}
