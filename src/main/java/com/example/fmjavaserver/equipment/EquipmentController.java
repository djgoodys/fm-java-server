
package com.example.fmjavaserver.equipment;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/equipment")

public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }



    @GetMapping
    public List<Equipment> manageEquipment(
        @RequestParam(required = false) String action,
        @RequestParam(required = false) String newtasks,
        @RequestParam(required = false) String username,
        @RequestParam(required = false) String unit_id,
        @RequestParam(required = false) String searchwords,
        @RequestParam(required = false) String filtertype,
        @RequestParam(required = false) String rotation,
        @RequestParam(required = false) String sortby) {
  
        List<Long> taskList = null;
        if (newtasks != null && !"undefined".equals(newtasks)) {
            taskList = Arrays.stream(newtasks.split(","))
                             .map(Long::parseLong)
                             .collect(Collectors.toList());
        }

        // Handle "undefined" for other parameters if necessary
        String validAction = ("undefined".equals(action)) ? null : action;
        String validUsername = ("undefined".equals(username)) ? null : username;
        String validSearchwords = ("undefined".equals(searchwords)) ? null : searchwords;
        Long validUnitId = (unit_id == null || "undefined".equals(unit_id)) ? null : Long.parseLong(unit_id);
        String validFilterType = ("undefined".equals(filtertype)) ? null : filtertype;
        Integer validRotation = (rotation == null || "undefined".equals(rotation)) ? 0 : Integer.parseInt(rotation);
        String validSortby = ("undefined".equals(sortby)) ? null : sortby;
        return equipmentService.manageEquipment(validAction, taskList, validUsername, validUnitId, validSearchwords, validFilterType, validRotation, validSortby);
        
    }


}
