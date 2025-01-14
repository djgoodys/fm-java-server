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
    public List<FilterTypes> manageFilterTypes(@RequestParam(required = false) String action) {
        return filtertypesService.manageFilterTypes(action);

}

}
