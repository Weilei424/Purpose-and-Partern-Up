package com.masonwang.pnp.entity;

import jakarta.persistence.*;
import lombok.NonNull;

import java.util.List;

@Entity
@Table(name = "proposal")
public class Proposal {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @NonNull
    private String name;

    private List<User> users;
}
