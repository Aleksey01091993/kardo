package com.kardoaward.mobileapp.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kardoaward.mobileapp.request.model.Request;
import com.kardoaward.mobileapp.status.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

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
    private String patronymic;
    @Column(unique = true, nullable = false)
    @Email(message = "Поле email должно быть в форме email@host.domen")
    @Pattern(regexp = "^[a-zA-Z0-9@._-]+$", message = "Email может содержать только латинские буквы")
    private String email;
    @Enumerated(EnumType.STRING)
    private UserSex sex;
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private UserDirections direction;
    @Column(unique = true)
    private String phone;
    @Column(name = "social_media_url")
    private String socialMediaUrl;
    @Column(name = "portfolioUrl")
    private String portfolioUrl;
    private String country;
    private String city;
    private String region;
    @Enumerated(EnumType.STRING)
    private UserRoles role = UserRoles.ROLE_USER;
    @OneToMany(mappedBy = "requester", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Request> requests;
}
