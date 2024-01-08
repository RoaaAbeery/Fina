package com.example.foodtruck.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    @PrimaryKeyJoinColumn
    private Set<Orders> orders;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "customer")
    @PrimaryKeyJoinColumn
    private Profile profile;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    @JsonIgnore
    private Set<Ticket> ticket;

//    @ManyToOne
//    @JoinColumn(name = "evaluation_id",referencedColumnName = "id")
//    @JsonIgnore
//    private Evaluation evaluation;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    @JsonIgnore
    private Set<Evaluation> evaluations;

    @OneToOne
    @JoinColumn(name = "user_id" ,referencedColumnName = "id")
    @JsonIgnore
    private User user;

//    @OneToOne
//    @MapsId
//    @JsonIgnore
//    private User user;
}
