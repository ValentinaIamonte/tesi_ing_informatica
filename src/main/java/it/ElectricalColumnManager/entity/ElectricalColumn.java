package it.ElectricalColumnManager.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
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
public class ElectricalColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "electricalColumn", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Location location;

    private ElectricalColumnStateEnum state;

    @ManyToOne
    @JoinColumn(name="type_id", nullable=false)
    private ChargingModeType type;

    @OneToMany(mappedBy = "electricalColumn")
    private List<Reservation> reservations;

    @Column(unique = true)
    private String serialNumber;

}
