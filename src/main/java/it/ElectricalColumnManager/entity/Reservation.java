package it.ElectricalColumnManager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonIgnore
    private User user;


    @ManyToOne
    @JoinColumn(name="electrical_column_id", nullable=false)
    @JsonIgnore
    private ElectricalColumn electricalColumn;

    private Date reservetionDate;

}
