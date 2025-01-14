
package com.example.fmjavaserver.equipment;

import java.util.List;
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
    public List<Equipment> manageEquipment(@RequestParam(required = false) String action) {
        return equipmentService.manageEquipment(action);

}

}
