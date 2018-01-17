package com.yenyu.a20180117_01;

import com.yenyu.a20180117_01.data.Student;
import com.yenyu.a20180117_01.data.StudentScoreDAO;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test //用來測試DAO的程式是否正確
    public void test1() throws Exception{
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1,"Bob",87));
        dao.add(new Student(2,"Amy",89));
        assertEquals(2,dao.getList().size());
    }

    @Test
    public void test2() throws Exception{
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1,"Bob",87));
        dao.add(new Student(2,"Amy",89));
        assertEquals(89,dao.getList().get(1).score);
    }

    @Test
    public void test_getStudent() throws Exception{
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1,"Bob",87));
        dao.add(new Student(2,"Amy",89));
        assertEquals("Amy",dao.getStudent(2).name);
    }
    public void test_getStudent2() throws Exception{
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1,"Bob",87));
        dao.add(new Student(2,"Amy",89));
        assertEquals(null,dao.getStudent(3));
    }
    @Test
    public void test_update() throws  Exception
    {
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1,"Bob",87));
        dao.add(new Student(2,"Amy",89));
        dao.update(new Student(2,"Louisa",54));
        assertEquals(54,dao.getStudent(2).score);
    }
    @Test
    public void test_delete() throws Exception
    {
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1,"Bob",87));
        dao.add(new Student(2,"Amy",89));
        dao.delete(2);
        assertEquals(1,dao.getList().size());
    }

    @Test
    public void test_delete1() throws Exception
    {
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1,"Bob",87));
        dao.add(new Student(2,"Amy",89));
        dao.delete(2);
        assertEquals(1,dao.getList().get(0).id);
    }



}