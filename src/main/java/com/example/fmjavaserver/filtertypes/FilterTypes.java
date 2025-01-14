package com.example.fmjavaserver.filtertypes;

import lombok.Getter; 
import lombok.Setter; 
import jakarta.persistence.*;

@Entity
@Table
@Getter 
@Setter
public class FilterTypes {
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, generator = "filtertypes_sequence"
    )
    @SequenceGenerator(
        name="filtertypes_sequence",
        sequenceName = "filtertypes_sequence",
        allocationSize = 1
    )

    private Long _id;
    private String trackable;
    private String type;
    
    public FilterTypes(){

    }

    public FilterTypes(Long _id, String type, String trackable){
        this._id = _id;
        this.type = type;
        this.trackable = trackable;
    }

    public FilterTypes(String type, String trackable){
        this.type = type;
        this.trackable = trackable;
    }
}


