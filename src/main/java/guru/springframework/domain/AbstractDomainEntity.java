package guru.springframework.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by juancho on 22/01/2017.
 */
@MappedSuperclass
public class AbstractDomainEntity implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Version
    private Integer version;
    private Date dateCreated;
    private Date lastUpdated;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        lastUpdated = new Date();
        if(dateCreated == null) {
            dateCreated = new Date();
        }
    }

}
