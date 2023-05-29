package com.alibou.security.club;

import com.alibou.security.user.User;
import com.alibou.security.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    public ClubServiceImpl(ClubRepository clubRepository, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    public Club saveClub(Club club) {
        return this.clubRepository.save(club);
    }

    public List<Club> findAllClub() {
        return this.clubRepository.findAll();
    }

    public void deleteClub(Integer id) {
        if(id == null) {
            log.error("ID de club est nul");
            return;
        }
        this.clubRepository.deleteById(id);
    }

    @Override
    public Club updatClub(Club club, Integer id) {
        club.setId(id);
        return clubRepository.save(club);
     
    }

    @Override
    public Club findClub(Integer id) {
        return clubRepository.findById(id).get();
    }

    @Override
    @Transactional
    public void addUserToClub(Integer userId, Integer clubId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new EntityNotFoundException("Club not found."));

        user.getClubs().add(club);
        club.getUsers().add(user);

        userRepository.save(user);
        clubRepository.save(club);
    }

    @Override
    public boolean isUserMember(Integer userId, Integer clubId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new EntityNotFoundException("Club not found."));

        return club.getUsers().contains(user);
    }

}
