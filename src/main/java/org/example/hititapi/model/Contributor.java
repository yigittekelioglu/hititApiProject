package org.example.hititapi.model;

import lombok.Data;


import javax.persistence.*;

@Entity
@Data

public class Contributor {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "repository_id")
    private Repository repository;

    private String login;

    private String location;

    private String company;

    private int contributions;
}