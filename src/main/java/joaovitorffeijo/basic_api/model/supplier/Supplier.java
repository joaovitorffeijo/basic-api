package joaovitorffeijo.basic_api.model.supplier;

import jakarta.persistence.*;
import joaovitorffeijo.basic_api.model.common.BasicAPIEntity;

@Entity
@Table(name = "suppliers")
public class Supplier extends BasicAPIEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
