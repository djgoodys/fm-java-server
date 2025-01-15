package com.example.fmjavaserver.equipment;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Objects;

@Service
public class EquipmentService {
	private final EquipmentRepository equipmentRepository;

	public EquipmentService(EquipmentRepository equipmentRepository) {
		this.equipmentRepository = equipmentRepository;
	}

	public List<Equipment> manageEquipment(String action, List<Long> newtasks, String username, Long unitId, String searchwords, String filterType, int rotation, String sortby) {
        if ("get-all-equipment".equals(action)) {
            return equipmentRepository.findAll();
        } else if ("add-all-tasks".equals(action)) {
            if (newtasks != null && !newtasks.isEmpty() && username != null) {
                for (Long id : newtasks) {
                    Equipment equipment = equipmentRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Invalid unit ID: " + id));
                    equipment.setAssignedto(username); // Ensure this matches your field name
                    equipmentRepository.save(equipment);
                }
            }
            return equipmentRepository.findAll(); // Adjust as needed
        } else if ("search".equals(action) && searchwords != null && !searchwords.isEmpty()) {
            return equipmentRepository.searchByAllFields(searchwords);
        } else if ("get_all_tasks".equals(action) && username != null) {
            return equipmentRepository.findByassignedto(username);
        } else if ("cancel-task".equals(action) && unitId != null) {
            Equipment equipment = equipmentRepository.findById(unitId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid unit ID: " + unitId));
            equipment.setAssignedto(""); 
            equipmentRepository.save(equipment);
            return equipmentRepository.findByassignedto(username); 
        } else if ("complete-task".equals(action) && unitId != null && filterType != null && rotation > 0) {
			System.out.println("completing task now");
            completeTask(unitId, username, filterType, rotation);
            return equipmentRepository.findByassignedto(username);
        }
		else if ("filtersdone".equals(action) && unitId != null && filterType != null && rotation > 0) {
			System.out.println("completing task now");
            completeTask(unitId, username, filterType, rotation);
            return equipmentRepository.findAll();
        }
        else if ("sort".equals(action) && "ASC".equals(sortby)){
			
			Sort sort = Sort.by(Sort.Direction.ASC, "filtersdue");
			List<Equipment> equipment = equipmentRepository.findAll(sort);
            return equipment;
		}
        else if ("sort".equals(action) && "DES".equals(sortby)){
			
			Sort sort = Sort.by(Sort.Direction.ASC, "filtersdue");
			List<Equipment> equipment = equipmentRepository.findAll(sort);
            return equipment;
		}
        else if ("sort".equals(action) && "today".equals(sortby)){
			
			LocalDate today = LocalDate.now(); 
        return equipmentRepository.findAllByFiltersDueToday(today);
		}
        else if ("sort".equals(action) && "NORMAL".equals(sortby)){
        return equipmentRepository.findAll();
		}
		else {
            return equipmentRepository.findAll(); // Adjust as needed for other actions
        }
    }

	public void completeTask(Long id, String username, String filterType, int rotation) {
        LocalDate date = LocalDate.now();
        int dueYear = date.getYear();
        int dueDay = date.getDayOfMonth();
        int dueMonth;
        int totalMonths = 0;
        int numMonths = date.getMonthValue() + rotation;
        
        if (numMonths > 12) {
            int rotYears = numMonths / 12;
            dueYear += rotYears;
            totalMonths = rotation + (date.getMonthValue());
            dueMonth = totalMonths % 12;
            if (DateUtils.isGreaterThanDaysInMonth(dueMonth, dueYear, dueDay)) {
                dueDay = DateUtils.getLastDayOfMonth(dueMonth, dueYear);
            }
        } else {
            dueMonth = numMonths;
        }

        LocalDate nextDueDate = LocalDate.of(dueYear, dueMonth, dueDay);

        LocalDate lastChanged = LocalDate.now();


        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unit with ID " + id + " was not found"));
        equipment.setAssignedto("");
        equipment.setFiltertype(filterType);
        equipment.setFilterslastchanged(lastChanged);
        equipment.setFiltersdue(nextDueDate);
        equipmentRepository.save(equipment);
    }

    public List<Equipment> getTasksByUsername(String username) {
        return equipmentRepository.
		findByassignedto(username);
    }

	public void addNewEquipment(Equipment equipment) {
		Optional<Equipment> equipmentByName = equipmentRepository.findByunitname(equipment.getUnitname());
		if (equipmentByName.isPresent()) {
			throw new IllegalStateException("Unit name is taken");
		}
		equipmentRepository.save(equipment);
	}

	public void deleteEquipment(Long equipmentId) {

		boolean exists = equipmentRepository.existsById(equipmentId);
		if (!exists) {
			throw new IllegalStateException("equipment with id " + equipmentId + " does not exists");
		}
		equipmentRepository.deleteById(equipmentId);
	}

	@Transactional
	public void updateEquipment(Long equipmentId, String unitname, String location, String areaserved) {
		Equipment equipment = equipmentRepository.findById(equipmentId)
				.orElseThrow(() -> new IllegalStateException("Equipment with id " + equipmentId + " does not exist"));

		if (unitname != null && unitname.length() > 0 && !Objects.equals(equipment.getUnitname(), unitname)) {
			equipment.setUnitname(unitname);
		}

		if (location != null && location.length() > 0) {
			Optional<Equipment> equipmentOptional = equipmentRepository.findById(equipmentId);
			equipment.setLocation(location);
		}
	}
}
