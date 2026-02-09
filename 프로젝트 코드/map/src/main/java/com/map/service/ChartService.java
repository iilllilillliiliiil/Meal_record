package com.map.service;

import com.map.dto.ChartDto;
import com.map.repository.LunchLogRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartService {

    private LunchLogRepository lunchLogRepository;

    public ChartService(LunchLogRepository lunchLogRepository) {
        this.lunchLogRepository = lunchLogRepository;
    }

    public List<ChartDto> getAgeStats(String shop_id) {
        List<Object[]> results = lunchLogRepository.getAgeStats(shop_id);
        List<ChartDto> chartList = results.stream()
                .map(row -> new ChartDto((String) row[0], (String) row[1], ((Long) row[2]).intValue()))
                .collect(Collectors.toList());
        return chartList;
    }

    public List<ChartDto> getOverallAgeStats() {
        List<Object[]> results = lunchLogRepository.getOverallAgeStats();
        List<ChartDto> chartList = results.stream()
                .map(row -> new ChartDto((String) row[0], (String) row[1], ((Long) row[2]).intValue()))
                .collect(Collectors.toList());
        return chartList;
    }

    public List<ChartDto> getOverallShopStats() {
        List<Object[]> results = lunchLogRepository.getOverallShopStats();
        List<ChartDto> chartList = results.stream()
                .map(row -> new ChartDto((String) row[0], (String) row[1], ((Long) row[2]).intValue()))
                .collect(Collectors.toList());
        return chartList;
    }

    public List<ChartDto> getOverallMenuStats() {
        List<Object[]> results = lunchLogRepository.getOverallMenuStats();
        List<ChartDto> chartList = results.stream()
                .map(row -> new ChartDto((String) row[0], (String) row[1], ((Long) row[2]).intValue()))
                .collect(Collectors.toList());
        return chartList;
    }

    public List<ChartDto> getdatechart(String shop_id) {
        List<Object[]> results = lunchLogRepository.getdatechart(shop_id);
        List<ChartDto> chartList = results.stream()
                .map(row -> new ChartDto((String) row[0], (String) row[1], ((Long) row[2]).intValue()))
                .collect(Collectors.toList());
        return chartList;
    }

    public List<ChartDto> getfoodchart(String shop_id) {
        List<Object[]> results = lunchLogRepository.getfoodchart(shop_id);
        List<ChartDto> chartList = results.stream()
                .map(row -> new ChartDto((String) row[0], (String) row[1], ((Long) row[2]).intValue()))
                .collect(Collectors.toList());
        return chartList;
    }

    public List<ChartDto> getcategorychart(String shop_id) {
        List<Object[]> results = lunchLogRepository.getcategorychart(shop_id);
        List<ChartDto> chartList = results.stream()
                .map(row -> new ChartDto((String) row[0], (String) row[1], ((Long) row[2]).intValue()))
                .collect(Collectors.toList());
        return chartList;
    }

    public List<ChartDto> getagechart(String shop_id) {
        List<Object[]> results = lunchLogRepository.getagechart(shop_id);
        List<ChartDto> chartList = results.stream()
                .map(row -> new ChartDto((String) row[0], (String) row[1], ((Long) row[2]).intValue()))
                .collect(Collectors.toList());
        return chartList;
    }

    public List<ChartDto> gettotaldatechart() {
        List<Object[]> results = lunchLogRepository.gettotaldatechart();
        List<ChartDto> chartList = results.stream()
                .map(row -> new ChartDto((String) row[0], (String) row[1], ((Long) row[2]).intValue()))
                .collect(Collectors.toList());
        return chartList;
    }

    public List<ChartDto> gettotalfoodchart() {
        List<Object[]> results = lunchLogRepository.gettotalfoodchart();
        List<ChartDto> chartList = results.stream()
                .map(row -> new ChartDto((String) row[0], (String) row[1], ((Long) row[2]).intValue()))
                .collect(Collectors.toList());
        return chartList;
    }

    public List<ChartDto> gettotalcategorychart() {
        List<Object[]> results = lunchLogRepository.gettotalcategorychart();
        List<ChartDto> chartList = results.stream()
                .map(row -> new ChartDto((String) row[0], (String) row[1], ((Long) row[2]).intValue()))
                .collect(Collectors.toList());
        return chartList;
    }

    public List<ChartDto> gettotalagechart() {
        List<Object[]> results = lunchLogRepository.gettotalagechart();
        List<ChartDto> chartList = results.stream()
                .map(row -> new ChartDto((String) row[0], (String) row[1], ((Long) row[2]).intValue()))
                .collect(Collectors.toList());
        return chartList;
    }
}