package com.yenyu.a20180117_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yenyu.a20180117_01.data.Student;

public class EditActivity extends AppCompatActivity {
    TextView tv;
    EditText ed1,ed2;
    int id;
    Student s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        tv=(TextView)findViewById(R.id.textView);
        ed1=(EditText)findViewById(R.id.editText4);
        ed2=(EditText)findViewById(R.id.editText2);

        Intent it =getIntent();
        id=it.getIntExtra("id",0);
        s=MainActivity.dao.getStudent(id);
        tv.setText(String.valueOf(s.id));
        ed1.setText(s.name);
        ed2.setText(String.valueOf(s.score));
    }



    public void clickBack(View v)
    {
        finish();
    }
    public void clickUpdate(View v)
    {
        Student s = new Student(id,ed1.getText().toString(),Integer.valueOf(ed2.getText().toString()));
        MainActivity.dao.update(s);
        finish();
    }
}
