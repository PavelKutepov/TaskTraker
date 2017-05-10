package pkutepv.Dao;

/**
 * Created by pkute on 08.05.2017.
 */
import pkutepv.model.Task;
import java.util.List;

public interface Dao {

    void save(String name,String password,String description, String status);
    List<Task> getTaskList(String name,String password, String status);
    void delete(String name,String password,Long id);


}