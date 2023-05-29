package com.alibou.security.formation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Formation {

    @Id
    @GeneratedValue
    public Integer id;
    public String fullname;
    public String image;
    public String email;
    public String departement;
    public String Cluster;
    public String teamManager;
    public String teamLeader;
    public String filename;
    public String fileType;
    public String club;
    public String description;
    public String file;
    public boolean confidentiality;

}
