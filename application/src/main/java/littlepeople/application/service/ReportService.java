package littlepeople.application.service;


import littlepeople.application.model.Report;
import littlepeople.application.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ReportService {

    @Autowired
    ReportRepository reportRepository;

    public Report addReport(Report report) {
        return reportRepository.save(report);
    }

    public Report getReportByActivityId(Long activityId) {
        return reportRepository.getByActivity_Id(activityId);
    }

    public void deleteReport(Long reportId) {
        reportRepository.deleteById(reportId);
    }

    public Report updateReport(Report report) {
        return reportRepository.save(report);
    }
}