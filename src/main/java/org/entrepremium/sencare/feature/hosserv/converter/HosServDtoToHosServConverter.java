package org.entrepremium.sencare.feature.hosserv.converter;

import org.entrepremium.sencare.feature.hosserv.HosServ;
import org.entrepremium.sencare.feature.hosserv.dto.HosServDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HosServDtoToHosServConverter implements Converter<HosServDto, HosServ> {

    @Override
    public HosServ convert(HosServDto source) {
        HosServ hosServ = new HosServ();
        hosServ.setServName(source.servName());
        hosServ.setServDesc(source.servDesc());
        hosServ.setServPrice(source.servPrice());
        hosServ.setServImage(source.servImage());
        hosServ.setAvailable(source.available());
        return hosServ;
    }
}
