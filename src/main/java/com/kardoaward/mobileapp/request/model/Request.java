package com.kardoaward.mobileapp.request.model;

import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.status.AdminEventStatus;
import com.kardoaward.mobileapp.status.UserStatus;
import com.kardoaward.mobileapp.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Builder
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "requester_id", referencedColumnName = "id")
    private User requester;
    @Column(name = "current_stage")
    private String currentStage;
    @Column(name = "current_place")
    private String currentPlace;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus statusToUser;
    @Enumerated(EnumType.STRING)
    @Column(name = "admin_status")
    private AdminEventStatus status;
    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<RequestStage> requestsStages;


    @Override
    public final boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy ? ((HibernateProxy) object).getHibernateLazyInitializer().getPersistentClass() : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Request Request = (Request) object;
        return getId() != null && Objects.equals(getId(), Request.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
