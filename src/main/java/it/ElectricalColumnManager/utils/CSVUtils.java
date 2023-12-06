package it.ElectricalColumnManager.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import it.ElectricalColumnManager.entity.ChargingModeEnum;
import it.ElectricalColumnManager.entity.ChargingModeType;
import it.ElectricalColumnManager.entity.CurrentTypeEnum;

public class CSVUtils {

    public static List<ChargingModeType> csvToChargingModeType()
            throws IOException, CsvValidationException, NumberFormatException {

        Resource resource = new ClassPathResource("ChargingModeType.csv");
        File file = resource.getFile();

        List<ChargingModeType> chargingModeTypes = new ArrayList<ChargingModeType>();
        CSVReader reader = new CSVReader(new FileReader(file));
        String[] record = null;
        boolean check = false;
        while ((record = reader.readNext()) != null) {
            if (check) {
                ChargingModeType type = new ChargingModeType();
                type.setId(Long.parseLong(record[0]));
                type.setVoltage(Integer.parseInt(record[1]));
                CurrentTypeEnum t = CurrentTypeEnum.valueOf(record[2]);
                type.setMaximumPower(Double.parseDouble(record[3]));
                ChargingModeEnum c = ChargingModeEnum.valueOf(record[4]);
                type.setCurrentType(t);
                type.setMode(c);
                chargingModeTypes.add(type);
            }
            check = true;

        }
        reader.close();

        return chargingModeTypes;

    }
}
