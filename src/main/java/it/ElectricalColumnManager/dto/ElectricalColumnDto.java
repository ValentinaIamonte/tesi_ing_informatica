package it.ElectricalColumnManager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ElectricalColumnDto {

    private String serialNumber;
    private Double latitude;
    private Double longitude;
    private String address;
    private Long idChargingModeType;
    

}
