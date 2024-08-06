package com.kardoaward.mobileapp.request.model;


import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.stage.model.Stage;
import com.kardoaward.mobileapp.status.UserStatus;
import com.kardoaward.mobileapp.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Builder
@Table
@AllArgsConstructor
@NoArgsConstructor
public class RequestStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "stage_id", referencedColumnName = "id")
    private Stage stage;
    @ManyToOne(optional = false)
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    private Request request;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Column(name = "result")
    private String result;


    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        RequestStage RequestStage = (RequestStage) object;
        return getId() != null && Objects.equals(getId(), RequestStage.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
