package com.alibou.security.archive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class ArchiveServiceImpl implements ArchiveService {

    private final ArchiveRepository archiveRepository;

    public ArchiveServiceImpl(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    @Override
    public Archive saveArchive(Archive archive) {
        return this.archiveRepository.save(archive);
    }

    @Override
    public List<Archive> findAllArchive() {
        return this.archiveRepository.findAll();
    }

    @Override
    public void deleteArchive(Integer id) {
        if(id == null) {
            log.error("ID de archive est nul");
            return;
        }
        this.archiveRepository.deleteById(id);
    }

    @Override
    public Archive updateArchive(Archive archive, Integer id) {
        archive.setId(id);
        return archiveRepository.save(archive);

    }

    @Override
    public Archive findArchive(Integer id) {
        return archiveRepository.findById(id).get();
    }
}
