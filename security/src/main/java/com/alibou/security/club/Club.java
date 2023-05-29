package com.alibou.security.club;

import com.alibou.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Club {

    @Id
    @GeneratedValue
    public Integer id;
    public String title;
    public String leader;
    public String description;
    public String image;
    @ManyToMany(mappedBy = "clubs")
    private Set<User> users = new HashSet<>();


}
