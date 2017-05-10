package pkutepv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;




@Entity
@Table(name="user")

public class User extends Model {
    @Column(name="name")
    private String name;
    @Column(name="password")
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
    public User(){super();}

    public  User(Long id){
        super(id);
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name +
                '}';
    }
}
