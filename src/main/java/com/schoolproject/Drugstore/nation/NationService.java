package com.schoolproject.Drugstore.nation;


import com.schoolproject.Drugstore.exception.DataNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class NationService {

    private final NationMapperDto nationMapperDto;
    private final NationRepository nationRepository;

    @Autowired
    public NationService(NationRepository nationRepository, NationMapperDto nationMapperDto) {
        this.nationRepository = nationRepository;
        this.nationMapperDto = nationMapperDto;
        
    }

    public Collection<NationDto> getAllProducts(){
        return nationRepository.findAll().stream().map(nation -> nationMapperDto.toDTO(nation)).toList();
    }

    public NationDto getProductById(Integer id){
        Optional<Nation> nation =  nationRepository.findById(id);
        if (nation.isEmpty()){
            throw new DataNotFoundException(id, Nation.class.getSimpleName());

        }
        return nationMapperDto.toDTO(nationRepository.getReferenceById(id));
    }

    public NationDto updateNation(NationCreationDto newNation, Integer id){
        // parameter trong map se la object ma repository tim duoc
        Nation convertNation = nationMapperDto.toNation(newNation);
        Nation updateNation = nationRepository.findById(id).map(nation ->
                {
                // update nation existing
                    return nationRepository.save(nation);
                }).orElseGet(()->{
                    // create new nation
                convertNation.setId(id);
                return nationRepository.save(convertNation);
        });
        return nationMapperDto.toDTO(updateNation);

    }
    public NationDto addNation(NationCreationDto nationCreationDto){
        Nation nation = nationMapperDto.toNation(nationCreationDto);
        nationRepository.save(nation);
        return nationMapperDto.toDTO(nation);
    }

    public void deleteNation(Integer id){
        nationRepository.deleteById(id);
    }


}
