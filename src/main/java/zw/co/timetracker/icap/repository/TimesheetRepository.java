package zw.co.timetracker.icap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import zw.co.timetracker.icap.model.Timesheet;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long>{
    
}