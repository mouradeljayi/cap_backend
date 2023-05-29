package com.alibou.security.club;

import java.util.List;

public interface ClubService {

     Club saveClub(Club club);
     List<Club> findAllClub();
     void deleteClub(Integer id);
     Club findClub(Integer id);
     Club updatClub(Club personnel, Integer id);
     void addUserToClub(Integer userId, Integer clubId);
}
