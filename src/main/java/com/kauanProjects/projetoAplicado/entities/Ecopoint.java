package com.kauanProjects.projetoAplicado.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ecopoints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ecopoint {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id", nullable = false)
    private District district;
}
