package com.example.fmjavaserver.filters;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Objects;

@Service
public class FiltersService {
	private final FiltersRepository filtersRepository;

	public FiltersService(FiltersRepository filtersRepository) {
		this.filtersRepository = filtersRepository;
	}

	public List<Filters> manageFilters(String action) {
		System.out.println("action=" + action);
		if ("get-all-filters".equals(action)) {
			return filtersRepository.findAll();
		} else {
			// Handle other actions or provide a default action
			return filtersRepository.findAll(); // Adjust as needed for other actions
		}
	}

	public void addNewFilters(Filters filters) {
		Optional<Filters> filtersByName = filtersRepository.findByfiltersize(filters.getFiltersize());
		if (filtersByName.isPresent()) {
			throw new IllegalStateException("Unit name is taken");
		}
		filtersRepository.save(filters);
	}

	public void deleteFilters(Long filtersId) {

		boolean exists = filtersRepository.existsById(filtersId);
		if (!exists) {
			throw new IllegalStateException("filters with id " + filtersId + " does not exists");
		}
		filtersRepository.deleteById(filtersId);
	}

	@Transactional
	public void updateFilters(Long filtersId, String filtersize, String storage, String areaserved) {
		Filters filters = filtersRepository.findById(filtersId)
				.orElseThrow(() -> new IllegalStateException("Filters with id " + filtersId + " does not exist"));

		if (filtersize != null && filtersize.length() > 0 && !Objects.equals(filters.getFiltersize(), filtersize)) {
			filters.setFiltersize(filtersize);
		}

		if (storage != null && storage.length() > 0) {
			Optional<Filters> filtersOptional = filtersRepository.findById(filtersId);
			filters.setStorage(storage);
		}
	}
}
