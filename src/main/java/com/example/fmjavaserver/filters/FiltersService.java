package com.example.fmjavaserver.filters;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Objects;
import java.time.LocalDate;
import org.springframework.data.domain.Sort;

@Service
public class FiltersService {
	private final FiltersRepository filtersRepository;

	public FiltersService(FiltersRepository filtersRepository) {
		this.filtersRepository = filtersRepository;
	}


	public List<Filters> manageFilters(Long Id, String action, String filtersize, String filtertype, Long filtercount, Long par, String pn, String storage, String notes) {
		System.out.println("action=" + action);
		
		if ("get-all-filters".equals(action)) {
			return filtersRepository.findAll();
		} else if ("update-filter".equals(action)) {
			if (Id != null) {
				Filters filter = filtersRepository.findById(Id)
						.orElseThrow(() -> new IllegalArgumentException("Invalid filter ID: " + Id));
				filter.setFiltersize(filtersize); 
				filter.setFiltertype(filtertype);
				filter.setFiltercount(filtercount);
				filter.setPar(par);
				filter.setPn(pn);
				filter.setNotes(notes);
				filter.setStorage(storage);
				filter.setLastupdated(LocalDate.now());
	
				filtersRepository.save(filter);
			}
		} else if ("add-new-filter".equals(action)) {
			if (filtersize != null) {
				Optional<Filters> filtersBySize = filtersRepository.findByfiltersize(filtersize);
				if (filtersBySize.isPresent()) {
					throw new IllegalStateException("Filter size: " + filtersize + " with type: " + filtertype + " is already in use");
				} else {
					Filters newFilter = new Filters(
						(filtertype != null && !"undefined".equals(filtertype)) ? filtertype : "defaultFilterType",
						(filtersize != null && !"undefined".equals(filtersize)) ? filtersize : "defaultFilterSize",
						(filtercount != null && !"undefined".equals(filtercount)) ? filtercount : 0L,
						(par != null && !"undefined".equals(par)) ? par : 0L,
						(pn != null && !"undefined".equals(pn)) ? pn : "defaultPn",
						(notes != null && !"undefined".equals(notes)) ? notes : " ",
						(storage != null && !"undefined".equals(storage)) ? storage : "defaultStorage",
						LocalDate.now()
					);
					filtersRepository.save(newFilter);

				}
			}		
		}
		else if ("delete-filter".equals(action)) {
			System.out.println("deleteing with id="+ Id);
			if (Id != null) {
				filtersRepository.deleteById(Id);
			} else {
				throw new IllegalArgumentException("Invalid filter ID: " + Id);
			}
		}		
		return filtersRepository.findAll(); 
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
