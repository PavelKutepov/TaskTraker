package pkutepv.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by pkute on 07.05.2017.
 */
@Entity
@Table(name="tasks")
@JsonIgnoreProperties({"user"})
public class Task extends Model{
    @Column(name="status")
    private String status;
    @Column(name="description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Task(){super();}
    public Task(Long id){
        super(id);
    }

    public Task(String status, String description, User user) {
        this.status = status;
        this.description = description;
        this.user = user;
    }

    public Task(Long id, String status, String description, User user) {
        super(id);
        this.status = status;
        this.description = description;
        this.user = user;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getStatus() {
        return status;
    }
    public String getDescription() {
        return description;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public User getUser() {

        return user;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}