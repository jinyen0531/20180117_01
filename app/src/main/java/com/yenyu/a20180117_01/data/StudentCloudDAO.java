package com.yenyu.a20180117_01.data;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yenyu.a20180117_01.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Student on 2018/1/18.
 */

public class StudentCloudDAO implements StudentDAO {
    public ArrayList<Student> mylist;
    Context context;
    FirebaseDatabase database;
    DatabaseReference myRef;

    public StudentCloudDAO(final Context context)
    {
        this.context =context;
        mylist = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("scores");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Gson gson = new Gson();
                mylist = gson.fromJson(value, new TypeToken<ArrayList<Student>>(){}.getType());
                if (mylist == null)
                {
                    mylist = new ArrayList<>();
                }
                ((MainActivity) context).refreshData();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }
    public boolean add(Student s)
    {
        if(mylist ==null) //以免一開始的時候沒有值
        {
            mylist = new ArrayList<>();
        }
        mylist.add(s);
        saveFile();
        return true;
    }

    public void saveFile() //儲存檔案
    {
        Gson gson =new Gson();
        String data = gson.toJson(mylist);


        myRef.setValue(data);

    }


    public ArrayList<Student> getList()
    {
        return mylist;
    }

    public Student getStudent(int id ) {
        for (Student s : mylist) {
            if (s.id == id) {
                return s;
            }
        }
        return null;
    }

    public boolean update(Student s)
    {
        for (Student o: mylist)
        {
            if(s.id == o.id)
            {
                o.name= s.name;
                o.score=s.score;
                saveFile();
                return true;
            }
        }
        return  false;
    }

    public boolean delete(int id)
    {
        for(int i =0;i<mylist.size();i++)
        {
            if(id== mylist.get(i).id)
            {
                mylist.remove(i);
                saveFile();
                return true;
            }
        }
        return false;
    }
}
