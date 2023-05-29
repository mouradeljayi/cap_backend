package com.alibou.security.formation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class FormationServiceImpl implements FormationService{

    private final FormationRepository formationRepository;

    public FormationServiceImpl(FormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }

    @Override
    public Formation saveFormation(Formation formation) {
        return this.formationRepository.save(formation);
    }

    @Override
    public List<Formation> findAllFormation() {
        return this.formationRepository.findAll();
    }

    @Override
    public void deleteFormation(Integer id) {
        if(id == null) {
            log.error("ID de formation est nul");
            return;
        }
        this.formationRepository.deleteById(id);
    }

    @Override
    public Formation updateFormation(Formation formation, Integer id) {
        formation.setId(id);
        return formationRepository.save(formation);

    }

    @Override
    public Formation findFormation(Integer id) {
        return formationRepository.findById(id).get();
    }
}
