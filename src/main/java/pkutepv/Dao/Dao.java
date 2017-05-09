package pkutepv.Dao;

/**
 * Created by pkute on 08.05.2017.
 */
import pkutepv.model.Task;
import pkutepv.model.User;

import java.awt.*;
import java.util.List;

public interface Dao {
    public void save(String name,String password,String description, String status);
    public List<Task> getTaskList(String name,String password, String status);
    public void delete(String name,String password,Long id);


}