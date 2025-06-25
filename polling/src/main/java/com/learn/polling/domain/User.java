package com.learn.polling.domain;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.NaturalId;

import com.learn.polling.domain.base.DateAudit;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Data
@ToString(exclude = {"password", "roles"})
@EqualsAndHashCode(of = {"id", "username", "email"}, callSuper = true)
public class User extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40, message = "You can use only {max} characters for name")
    private String name;

    @NotBlank
    @Size(max = 15, message = "You can use only {max} characters for username")
    private String username;

    // The @NaturalId annotation is used to indicate that the email field is a
    // natural identifier for the User entity.
    // In this context, email is unique and can be used to look up users
    // efficiently.
    // Example use case: finding a user by their email address.
    @NaturalId
    @NotBlank
    @Size(max = 50, message = "You can use only {max} characters for email")
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
