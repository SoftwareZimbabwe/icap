package zw.co.timetracker.icap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import zw.co.timetracker.icap.model.Timesheet;
import zw.co.timetracker.icap.repository.TimesheetRepository;

@Service
public class TimesheetService implements ITimesheetService {

	@Autowired
	private TimesheetRepository timesheetRepository;

	@Override
	public List<Timesheet> getAllTimesheets() {
		return timesheetRepository.findAll();
	}

	@Override
	public void saveTimesheet(Timesheet timesheet) {
		this.timesheetRepository.save(timesheet);
	}

	@Override
	public Timesheet getTimesheetById(long id) {
		Optional<Timesheet> optional = timesheetRepository.findById(id);
		Timesheet timesheet = null;

		if (optional.isPresent()) {
			timesheet = optional.get();
		} else {
			throw new RuntimeException(" Timesheet not found for id :: " + id);
		}
		return timesheet;
	}

	@Override
	public void deleteTimesheetById(long id) {
		this.timesheetRepository.deleteById(id);
	}

	@Override
	public Page<Timesheet> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.timesheetRepository.findAll(pageable);
	}
}