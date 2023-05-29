package com.alibou.security.formation;

import java.util.List;

public interface FormationService {

    Formation saveFormation(Formation formation);
    List<Formation> findAllFormation();
    void deleteFormation(Integer id);
    Formation findFormation(Integer id);
    Formation updateFormation(Formation formation, Integer id);
}
