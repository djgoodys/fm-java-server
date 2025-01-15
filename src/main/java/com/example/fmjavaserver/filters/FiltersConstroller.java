package com.example.fmjavaserver.filters;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/filters")

public class FiltersConstroller {
    private final FiltersService filtersService;

    public FiltersConstroller(FiltersService filtersService) {
        this.filtersService = filtersService;
    }



    @GetMapping
    public List<Filters> manageFilters(
        @RequestParam(required = false) String action,
        @RequestParam(required = false) String filtersize,
        @RequestParam(required = false) String id,
        @RequestParam(required = false) String filtertype,
        @RequestParam(required = false) String storage,
        @RequestParam(required = false) String par,
        @RequestParam(required = false) String pn,
        @RequestParam(required = false) String notes,
        @RequestParam(required = false) String count
        
        ) {
            String validAction = ("undefined".equals(action)) ? null : action;
            String validSize = ("undefined".equals(filtersize)) ? null : filtersize;
            String validType = ("undefined".equals(filtertype)) ? null : filtertype;
            String validStorage = ("undefined".equals(storage)) ? null : storage;
            String validNotes= ("undefined".equals(notes)) ? null : notes;
            String validPn = ("undefined".equals(pn)) ? null : pn;
            Long validId = (id == null || "undefined".equals(id)) ? null : Long.parseLong(id);
            Long validCount = (count == null || "undefined".equals(count)) ? null : Long.parseLong(count);
            Long validPar = (par == null || "undefined".equals(par)) ? null : Long.parseLong(par);
        return filtersService.manageFilters(validId, validAction, validSize, validType, validCount, validPar, validPn, validStorage, validNotes);

}

}

