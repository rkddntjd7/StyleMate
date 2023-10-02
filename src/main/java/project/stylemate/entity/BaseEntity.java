package project.stylemate.entity;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date_time", nullable = false)
    private Date createDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date_time")
    private Date updateDateTime;

    @PrePersist
    protected void onCreate() {
        createDateTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateDateTime = new Date();
    }

}
