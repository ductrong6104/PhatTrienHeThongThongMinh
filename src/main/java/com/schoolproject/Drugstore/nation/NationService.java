package com.schoolproject.Drugstore.nation;

import com.schoolproject.Drugstore.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class NationService {
    private final NationRepository nationRepository;

    @Autowired
    public NationService(NationRepository nationRepository) {
        this.nationRepository = nationRepository;
    }

    public Collection<Nation> getAllNations(){
        return nationRepository.findAll();
    }

    public Nation getNationById(Integer id){
        Optional<Nation> nation =  nationRepository.findById(id);
        if (nation.isEmpty()){
            throw new DataNotFoundException(id, Nation.class.getSimpleName());

        }
        return nationRepository.getReferenceById(id);
    }

    public Nation updateNation(Nation newNation, Integer id){
        // parameter trong map se la object ma repository tim duoc

        Nation updateNation = nationRepository.findById(id).map(nation ->
        {
            nation.setName(newNation.getName());
            //nation.setBrands(newNation.getBrands());
            return nationRepository.save(nation);
        }).orElseGet(()->{
            newNation.setId(id);
            return nationRepository.save(newNation);
        });

        return updateNation;
    }
    public Nation addNation(Nation nation){
        return nationRepository.save(nation);
    }

    public void deleteNation(Integer id){
        nationRepository.deleteById(id);
    }
}
