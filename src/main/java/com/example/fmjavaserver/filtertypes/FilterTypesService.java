package com.example.fmjavaserver.filtertypes;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Objects;

@Service
public class FilterTypesService {
	private final FilterTypesRepository filtertypesRepository;

	public FilterTypesService(FilterTypesRepository filtertypesRepository) {
		this.filtertypesRepository = filtertypesRepository;
	}

	public List<FilterTypes> manageFilterTypes(String action) {
		if ("get-filter-types".equals(action)) {
			System.out.println("getting filter types");
			return filtertypesRepository.findAll();
		} else {
			// Handle other actions or provide a default action
			return filtertypesRepository.findAll(); // Adjust as needed for other actions
		}
	}

	public void addNewFilterType(FilterTypes filtertypes) {
		Optional<FilterTypes> filtertypesByType = filtertypesRepository.findByType(filtertypes.getType());
		if (filtertypesByType.isPresent()) {
			throw new IllegalStateException("Filter type is taken");
		}
		filtertypesRepository.save(filtertypes);
	}

	public void deleteFilterTypes(Long filtertypesId) {

		boolean exists = filtertypesRepository.existsById(filtertypesId);
		if (!exists) {
			throw new IllegalStateException("filtertypes with id " + filtertypesId + " does not exists");
		}
		filtertypesRepository.deleteById(filtertypesId);
	}

	@Transactional
	public void updateFilterTypes(Long filtertypesId, String trackable, String type) {
		FilterTypes filtertypes = filtertypesRepository.findById(filtertypesId)
				.orElseThrow(
						() -> new IllegalStateException("FilterTypes with id " + filtertypesId + " does not exist"));

		if (type != null && type.length() > 0 && !Objects.equals(filtertypes.getType(), type)) {
			filtertypes.setType(type);
		}

		if (trackable != null && trackable.length() > 0) {
			Optional<FilterTypes> filtertypesOptional = filtertypesRepository.findById(filtertypesId);
			filtertypes.setTrackable(trackable);
		}
	}
}
