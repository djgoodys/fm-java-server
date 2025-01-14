package com.example.fmjavaserver.equipment;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipment_sequence")
    @SequenceGenerator(name = "equipment_sequence", sequenceName = "equipment_sequence", allocationSize = 1)

    private Long _id;

    private String unitname;

    private String location;

    private String areaserved;

    private String filtersize;

    private LocalDate filtersdue;

    private String belts;

    private String notes;

    private String filterrotation;

    private String filtertype;

    private LocalDate filterslastchanged;

    private String assignedto;

    private String image;

    public Equipment() {
    }

    public Equipment(Long _id, String unitname, String areaserved, String location, String filtersize,
            LocalDate filtersdue, String belts, String notes, String filterrotation, String filtertype,
            LocalDate filterslastchanged, String assignedto, String image) {
        this._id = _id;
        this.unitname = unitname;
        this.location = location;
        this.areaserved = areaserved;
        this.filtersdue = filtersdue;
        this.filtersize = filtersize;
        this.filtertype = filtertype;
        this.filterslastchanged = filterslastchanged;
        this.filterrotation = filterrotation;
        this.assignedto = assignedto;
        this.belts = belts;
        this.notes = notes;
        this.image = image;
    }

    public Equipment(String unitname, String areaserved, String location, String filtersize, LocalDate filtersdue,
            String belts, String notes, String filterrotation, String filtertype, LocalDate filterslastchanged,
            String assignedto, String image) {
        this.unitname = unitname;
        this.location = location;
        this.areaserved = areaserved;
        this.filtersdue = filtersdue;
        this.filtersize = filtersize;
        this.filtertype = filtertype;
        this.filterslastchanged = filterslastchanged;
        this.filterrotation = filterrotation;
        this.assignedto = assignedto;
        this.belts = belts;
        this.notes = notes;
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "equipment{" +
                "id=" + _id +
                ", name='" + unitname + '\'' +
                ", location='" + location + '\'' + ", areaserved=" + areaserved + "}";
    }
}
