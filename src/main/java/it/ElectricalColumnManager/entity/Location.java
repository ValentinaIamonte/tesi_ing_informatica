package it.ElectricalColumnManager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @Column(name = "electrical_column_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "electrical_column_id")
    @JsonIgnore
    private ElectricalColumn electricalColumn;

    private Double latitude;
    private Double longitude;
    private String address;

}
