package com.kauanProjects.projetoAplicado.entities;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.kauanProjects.projetoAplicado.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;

public class DistrictDeserializer extends StdDeserializer<District> {

    private final DistrictRepository districtRepository;

    @Autowired
    public DistrictDeserializer(DistrictRepository districtRepository) {
        super(District.class);
        this.districtRepository = districtRepository;
    }

    @Override
    public District deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        String value = jsonParser.getValueAsString();

        return districtRepository.findByName(value);
    }
}
