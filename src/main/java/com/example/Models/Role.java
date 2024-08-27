package com.example.Models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    private Long id;
    
    private String roleName;
    
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users = new HashSet<>();

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", users=" + users + "]";
	}

    
}
