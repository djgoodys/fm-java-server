package com.example.fmjavaserver.equipment;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Objects;

@Service
public class EquipmentService {
	private final EquipmentRepository equipmentRepository;

	public EquipmentService(EquipmentRepository equipmentRepository) {
		this.equipmentRepository = equipmentRepository;
	}

	public List<Equipment> manageEquipment(String action) {
		if ("get-all-equipment".equals(action)) {
			return equipmentRepository.findAll();
		} else {
			// Handle other actions or provide a default action
			return equipmentRepository.findAll(); // Adjust as needed for other actions
		}
	}

	public void addNewEquipment(Equipment equipment) {
		Optional<Equipment> equipmentByName = equipmentRepository.findByUnitName(equipment.getUnitname());
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
