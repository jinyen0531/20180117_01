package com.yenyu.a20180117_01.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

public class StudentFileDAO implements StudentDAO {
    public ArrayList<Student> mylist;
    Context context;

    public StudentFileDAO(Context context)
    {
        this.context =context;
        mylist = new ArrayList<>();
    }
    public boolean add(Student s)
    {
        mylist.add(s);
        saveFile();
        return true;
    }

    public void saveFile() //儲存檔案
    {
        File f =new File(context.getFilesDir(),"mydata.txt");
        //getFilesDir 是context的方法，原本在MainActivtiy的AppCompatActivity可以用
        //因為context 是AppCompatActivity的父類別
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            Gson gson =new Gson();
            String data = gson.toJson(mylist);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load() //載入檔案
    {
        File f = new File(context.getFilesDir(),"mydata.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            Gson gson = new Gson();
            mylist=gson.fromJson(str,new TypeToken<ArrayList<Student>>(){}.getType());
            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Student> getList()
    {
        load();
        return mylist;
    }
    public Student getStudent(int id ) {
        load();
        for (Student s : mylist) {
            if (s.id == id) {
                return s;
            }
        }
        return null;
    }

    public boolean update(Student s)
    {
        load();
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
        load();
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
