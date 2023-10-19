package com.ChitChat.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "myuser") // This annotation specifies the name of the database table associated with this entity.
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods.
public class User {

    @Id // Indicates that this field is the primary key of the database table.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies how the primary key value is generated (identity strategy for auto-increment).
    @Column(name = "id") // Maps this field to the "id" column in the database table.
    private int id;

    @Column(name = "name") // Maps this field to the "name" column in the database table.
    private String username;

    @Column(name = "email") // Maps this field to the "email" column in the database table.
    private String email;

    @CreationTimestamp // JPA Hibernate annotation that automatically sets the current timestamp when the entity is created.
    @Column(name = "created_at", nullable = false, updatable = false) // Maps this field to the "created_at" column in the database table.
    private LocalDateTime localDate;

    @Column(name = "is_active") // Maps this field to the "is_active" column in the database table.
    private boolean qeActive;
}
