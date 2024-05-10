package br.com.personmanagement.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_people")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "Date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "people", cascade = CascadeType.ALL)
    private List<Address> address = new ArrayList<>();
}
