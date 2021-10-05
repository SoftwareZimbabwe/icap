package zw.co.timetracker.icap.service;

import java.util.List;

import org.springframework.data.domain.Page;

import zw.co.timetracker.icap.model.Timesheet;

public interface ITimesheetService {

	List<Timesheet> getAllTimesheets();
	void saveTimesheet(Timesheet timesheet);
	Timesheet getTimesheetById(long id);
	void deleteTimesheetById(long id);
	Page<Timesheet> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}