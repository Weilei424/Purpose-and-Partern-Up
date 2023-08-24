package com.masonwang.pnp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "username cannot be blank")
    @NonNull
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "password cannot be blank")
    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "Contact")
    private String contact;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Proposal> proposals;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "team_user",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id")
    )
    private Set<Team> teams;
}
