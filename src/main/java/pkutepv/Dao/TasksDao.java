package pkutepv.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pkutepv.model.Task;
import pkutepv.model.User;

import java.util.List;

/**
 * Created by pkute on 09.05.2017.
 */
public class TasksDao implements Dao{
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void save(String name, String password, String description, String status) {

        Session session = this.sessionFactory.openSession();
        Object o = session.createQuery(" FROM User as u Where u.name=" + name + "AND u.password =" + password).uniqueResult();
        if (o == null) {System.out.print("Invalid username or password");
        } else {
            User user = (User) o;
            Task task = new Task(status, description, user);
            Transaction tx = session.beginTransaction();
            session.persist(task);
            tx.commit();
            session.close();
        }
    }

    @Override
    public List<Task> getTaskList(String name,String password, String status) {
        Session session = this.sessionFactory.openSession();
        List<Task> userList = session.createQuery("FROM Task as t inner join fetch User as u WITH u.name="+name+" AND u.password="+password+" AND t.status"=status).list();
        session.close();
        return userList;

    }

    @Override
    public void delete(String name, String password, Long id) {

    }
}
