package com.yenyu.a20180117_01;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yenyu.a20180117_01.data.Student;
import com.yenyu.a20180117_01.data.StudentScoreDAO;

public class ShowActivity extends AppCompatActivity {
    TextView tv1,tv2,tv3;
    Student s;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        tv1=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        tv3=(TextView)findViewById(R.id.textView3);

        Intent it =getIntent();
        id = it.getIntExtra("id",0);



    }

    @Override
    protected  void onResume() {
        super.onResume();

        s = MainActivity.dao.getStudent(id);
        tv1.setText(String.valueOf(s.id));
        tv2.setText(s.name);
        tv3.setText(String.valueOf(s.score));
    }
    public void clickBack(View v)
    {
        finish();
    }
    public void clickDelete(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ShowActivity.this);
        builder.setTitle("刪除確認");
        builder.setMessage("確定要刪除這筆資料嗎?");
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.dao.delete(id);
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
    public void clickEdit(View v)
    {
        Intent it = new Intent(ShowActivity.this,EditActivity.class);
        it.putExtra("id",id);
        startActivity(it);
    }
}
