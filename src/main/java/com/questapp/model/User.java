package com.questapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "t_user")
@Data
public class User {

    @Id
    Long id;

    String username;

    String password;
}
