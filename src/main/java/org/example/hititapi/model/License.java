package org.example.hititapi.model;

import lombok.Data;


import javax.persistence.Embeddable;

@Data
@Embeddable
public class License {
    private String name;
}
