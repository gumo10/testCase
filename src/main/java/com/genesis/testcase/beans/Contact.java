package com.genesis.testcase.beans;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "CONTACT")
@EntityListeners(AuditingEntityListener.class)
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private long id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "is_freelance")
    private Boolean freelance;

    @OneToOne(cascade=CascadeType.ALL)
    private LegalInfo legalinfo;

    @OneToOne(cascade=CascadeType.ALL)
    @NotNull
    private Address address;

    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @NotEmpty
    private Collection<Company> companies;

    public Contact() {
    }

    public Contact(String firstname, String lastname, LegalInfo legalinfo, Address address, Collection<Company> companies) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.legalinfo = legalinfo;
        this.address = address;
        this.companies = companies;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Boolean getFreelance() {
        return freelance;
    }

    public void setFreelance(Boolean freelance) {
        this.freelance = freelance;
    }

    public LegalInfo getLegalinfo() {
        return legalinfo;
    }

    public void setLegalinfo(LegalInfo legalinfo) {
        this.legalinfo = legalinfo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Collection<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Collection<Company> companies) {
        this.companies = companies;
    }
}
