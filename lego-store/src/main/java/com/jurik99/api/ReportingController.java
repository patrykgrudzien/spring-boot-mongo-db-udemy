package com.jurik99.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jurik99.model.AvgRatingModel;
import com.jurik99.persistence.ReportService;

import java.util.List;

@RestController
@RequestMapping("legostore/api/reports")
public class ReportingController {

    private final ReportService reportService;

    public ReportingController(final ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/avgRatingReport")
    public List<AvgRatingModel> avgRatingReport() {
        return reportService.getAvgRatingReport();
    }
}
