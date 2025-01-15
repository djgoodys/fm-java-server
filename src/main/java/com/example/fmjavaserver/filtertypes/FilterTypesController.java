package com.example.fmjavaserver.filtertypes;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/filtertypes")

public class FilterTypesController {
    private final FilterTypesService filtertypesService;

    public FilterTypesController(FilterTypesService filtertypesService) {
        this.filtertypesService = filtertypesService;
    }



    @GetMapping
    public List<FilterTypes> manageFilterTypes(
    @RequestParam(required = false) String action,
    @RequestParam(required = false) String id,
    @RequestParam(required = false) String filtertype,
    @RequestParam(required = false) String trackable
    ) {
        String validAction = ("undefined".equals(action)) ? null : action;
        String validType = ("undefined".equals(filtertype)) ? null : filtertype;
        String validTrackable = ("undefined".equals(trackable)) ? null : trackable;
        Long validId = (id == null || "undefined".equals(id)) ? null : Long.parseLong(id);
        return filtertypesService.manageFilterTypes(validAction, validType, validId, validTrackable);

}

}
