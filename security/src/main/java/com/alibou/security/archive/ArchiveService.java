package com.alibou.security.archive;


import java.util.List;

public interface ArchiveService {

    Archive saveArchive(Archive archive);
    List<Archive> findAllArchive();
    void deleteArchive(Integer id);
    Archive findArchive(Integer id);
    Archive updateArchive(Archive archive, Integer id);
}
