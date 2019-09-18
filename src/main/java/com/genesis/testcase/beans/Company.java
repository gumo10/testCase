package com.genesis.testcase.beans;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "COMPANY")
@EntityListeners(AuditingEntityListener.class)
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade=CascadeType.ALL)
    @OrderColumn
    @NotEmpty
    private Collection<Address> addresses;

    @OneToOne(cascade=CascadeType.ALL)
    @NotNull
    private LegalInfo legalinfo;

    @ManyToOne(cascade=CascadeType.PERSIST,fetch = FetchType.LAZY)
    private Contact contact;

    public Company() {
    }

    public Company(Collection<Address> addresses, Contact contact) {
        this.addresses = addresses;
        this.contact = contact;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }

    public LegalInfo getLegalinfo() {
        return legalinfo;
    }

    public void setLegalinfo(LegalInfo legalinfo) {
        this.legalinfo = legalinfo;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
