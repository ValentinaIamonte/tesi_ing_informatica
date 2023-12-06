package it.ElectricalColumnManager.entity;




import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
public class ChargingModeType {

    @Id
    private Long id;

    private Integer voltage;

    private CurrentTypeEnum currentType;

    private Double maximumPower;

    private ChargingModeEnum mode;

    @OneToMany(mappedBy="type")
    @JsonIgnore
    private List<ElectricalColumn> electricalColumns;

    
}
