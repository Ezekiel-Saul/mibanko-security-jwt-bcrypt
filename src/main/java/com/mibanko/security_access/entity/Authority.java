package com.mibanko.security_access.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorityId;

    @Column(name = "authority")
    private String authority;

    @Column(name = "created_at")
    private Timestamp creationDate;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    @JsonIgnore /*faz com que o Jackson ignore o campo durante a serialização.quebram a recursividade infinita*/
    private User user;

    public Authority() {
        this.creationDate = Timestamp.valueOf(LocalDateTime.now());
    }

    public Authority(String authority, User user) {
        this.authority = authority;
        this.creationDate = Timestamp.valueOf(LocalDateTime.now());
        this.user = user;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }



    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
