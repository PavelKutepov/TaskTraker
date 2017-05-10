package pkutepv.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pkutepv.model.Task;
import pkutepv.model.User;

import java.util.ArrayList;
import java.util.List;




public class TasksDao implements Dao{
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Coхранение в базу данных
     * @param name
     * @param password
     * @param description
     * @param status
     */
    @Override
    public void save(String name, String password, String description, String status) {
        Session session = this.sessionFactory.openSession();
        try {
            Query query = session.createQuery(" FROM User WHERE name=:n AND password=:p");
            query.setParameter("n", name);
            query.setParameter("p", password);
            List<User> us = query.list();
            Task task = new Task(status, description, us.get(0));
            Transaction tx = session.beginTransaction();
            session.persist(task);
            tx.commit();
        }
        catch (java.lang.IndexOutOfBoundsException n)
        {System.out.println("Incorrect data");}
        session.close();
        session.close();
    }

    /**
     * Получение списка задач из бд
     * @param name
     * @param password
     * @param status
     * @return список задач
     */
    @Override
    public List<Task> getTaskList(String name,String password, String status) {
        Session session = this.sessionFactory.openSession();
        List<Task> ts = new ArrayList<>();
        try {
            Query query = session.createQuery(" FROM Task as t INNER JOIN t.user as u WITH  u.name=:n AND u.password=:p AND t.status=:s ");
            query.setParameter("n", name);
            query.setParameter("p", password);
            query.setParameter("s", status);
            List<Object[]> taskList = query.list();
            for (Object[] task : taskList) {
                ts.add((Task) task[0]);
            }
            session.close();
        }catch (java.lang.IndexOutOfBoundsException n)
            {System.out.println("Incorrect data");}
            session.close();
        return ts;
        }

    /**
     * Удаление задачи из бд
     * @param name
     * @param password
     * @param id
     */
    @Override
    public void delete(String name, String password, Long id) {
        Session session = this.sessionFactory.openSession();
        try {
            Query query = session.createQuery(" FROM Task as t INNER JOIN t.user as u WITH u.name=:n AND u.password=:p AND t.id=:i");
            query.setParameter("n", name);
            query.setParameter("p", password);
            query.setParameter("i", id);
            List<Object[]> taskList = query.list();
            Task task = (Task) taskList.get(0)[0];
            Transaction tx = session.beginTransaction();
            session.delete(task);
            tx.commit();
        }
        catch (java.lang.IndexOutOfBoundsException n)
        {System.out.print("Incorrect data");}
        session.close();
        }

    }

