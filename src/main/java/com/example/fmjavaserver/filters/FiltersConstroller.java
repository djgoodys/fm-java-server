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
    public List<Filters> manageFilters(@RequestParam(required = false) String action) {
        return filtersService.manageFilters(action);

}

}

