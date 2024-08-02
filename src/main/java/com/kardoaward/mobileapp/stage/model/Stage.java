package com.kardoaward.mobileapp.stage.model;

import com.kardoaward.mobileapp.events.model.Event;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Builder
@Table(name = "stages")
@AllArgsConstructor
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "stage_status")
    private String stageStatus;
    @Column(name = "name")
    private String name;
    @Column(name = "stage_date_start")
    private LocalDate start;
    @Column(name = "stage_date_end")
    private LocalDate end;
    @Column(name = "task")
    private String task;
    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Stage stage = (Stage) o;
        return getId() != null && Objects.equals(getId(), stage.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
