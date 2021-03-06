package healthcheck.application.mapper;

import healthcheck.application.dto.ReportDto;
import healthcheck.application.model.Report;
import healthcheck.application.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportDtoMapper extends AbstractMapper<Report, ReportDto> {
    @Autowired
    ActivityDtoMapper activityDtoMapper;

    @Autowired
    ActivityService activityService;

    @Override
    public Report convertDtoToModel(ReportDto reportDto) {
        Report report = new Report();
        report.setId(reportDto.getId());
        report.setCategory(reportDto.getCategory());
        report.setDescription(reportDto.getDescription());
        report.setText(reportDto.getText());
        report.setTitle(reportDto.getTitle());
        report.setActivity(activityService.getActivityById(reportDto.getActivityId()));
        return report;
    }

    @Override
    public ReportDto convertModelToDto(Report report) {
        ReportDto reportDto = new ReportDto();
        reportDto.setId(report.getId());
        reportDto.setActivityId(report.getActivity().getId());
        reportDto.setText(report.getText());
        reportDto.setDescription(report.getDescription());
        reportDto.setTitle(report.getTitle());
        reportDto.setCategory(report.getCategory());
        return reportDto;
    }
}
