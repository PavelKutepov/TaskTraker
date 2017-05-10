package pkutepv.model;

import javax.persistence.*;


/**
 * Created by pkute on 07.05.2017.
 */
@MappedSuperclass
public abstract class Model  {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    public void setId(Long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public Model(){}
    public Model(Long id){
        this.id=id;
    }
}
