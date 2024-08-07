package com.kardoaward.mobileapp.user.model;


import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.request.model.RequestEvents;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import jakarta.persistence.*;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;



import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    private String password;
    @Column(name = "first_name")
    private String firstName;
    private String surname;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private LocalDate birthday;
    @Column(unique = true)
    private String phone;
    @Column(name = "social_media_url")
    private String socialMediaUrl;
    @Column(name = "portfolioUrl")
    private String portfolioUrl;
    private String country;
    private String city;
    @ToString.Exclude
    @OneToMany(mappedBy = "requester", cascade = CascadeType.ALL)
    private List<RequestEvents> requests;
    @Enumerated(EnumType.STRING)
    private UserRoles role = UserRoles.ROLE_USER;
}
