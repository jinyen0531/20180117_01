package com.yenyu.a20180117_01.data;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/18.
 */

public interface StudentDAO {
    //設定介面 ，強迫繼承介面後，一定要實際操作以下所有的method
    public boolean add(Student s);
    public ArrayList<Student> getList();
    public Student getStudent(int id );
    public boolean update(Student s);
    public boolean delete(int id);
}
