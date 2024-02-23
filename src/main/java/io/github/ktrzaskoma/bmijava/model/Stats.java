package io.github.ktrzaskoma.bmijava.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "statistics")
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String weight;
    private String height;
    private String bmi;
    private String infoCode;
    private LocalDate dateOfMeasurement;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public Stats(User user, String weight, String height) {
        this.user = user;
        this.weight = weight;
        this.height = height;
    }

    @PrePersist
    public void setDateOfMeasurement() {
        this.dateOfMeasurement = LocalDate.now();
    }
}
