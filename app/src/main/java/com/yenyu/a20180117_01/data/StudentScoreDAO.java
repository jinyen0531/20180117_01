package com.yenyu.a20180117_01.data;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/17.
 */

public class StudentScoreDAO {

    public ArrayList<Student> mylist;
    public StudentScoreDAO()
    {
        mylist = new ArrayList<>();
    }
    public boolean add(Student s)
    {
        mylist.add(s);
        return true;
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
                return true;
            }
        }
        return false;
    }

}
