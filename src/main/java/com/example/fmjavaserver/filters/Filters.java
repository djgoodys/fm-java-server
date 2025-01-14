package com.example.fmjavaserver.filters;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table

@Getter
@Setter
public class Filters {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "filters_sequence")
    @SequenceGenerator(name = "filters_sequence", sequenceName = "filters_sequence", allocationSize = 1)

    private Long _id;
    private String filtertype;
    private String filtersize;
    private Long filtercount;
    private Long par;
    private String notes;
    private String storage;
    private String pn;
    private LocalDate lastupdated;

    public Filters() {

    }

    public Filters(Long _id, String filtertype, String filtersize, Long filtercount, Long par, String storage,
            String pn, String notes, LocalDate lastupdated) {
        this._id = _id;
        this.filtertype = filtertype;
        this.filtersize = filtersize;
        this.filtercount = filtercount;
        this.par = par;
        this.pn = pn;
        this.notes = notes;
        this.storage = storage;
        this.lastupdated = lastupdated;
    }

    public Filters(String filtertype, String filtersize, Long filtercount, Long par, String pn, String notes,
            String storage, LocalDate lastupdated) {
        this.filtertype = filtertype;
        this.filtersize = filtersize;
        this.filtercount = filtercount;
        this.par = par;
        this.storage = storage;
        this.pn = pn;
        this.notes = notes;
        this.lastupdated = lastupdated;
    }
}
