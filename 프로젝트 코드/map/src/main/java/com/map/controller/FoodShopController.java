package com.map.controller;

import com.map.dto.ChartDto;
import com.map.dto.FoodDto;
import com.map.dto.OverallStatsDto;
import com.map.entity.Food;
import com.map.entity.Lunch_log;
import com.map.entity.Shop;
import com.map.repository.ChartRepository;
import com.map.repository.FoodRepository;
import com.map.repository.FoodShopRepository;
import com.map.repository.LunchLogRepository;
import com.map.service.ChartService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FoodShopController {

    @Autowired
    private FoodShopRepository foodShopRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private LunchLogRepository lunchLogRepository;

    private final ChartService chartService;

    public FoodShopController(ChartService chartService) {
        this.chartService = chartService;
    }

    @GetMapping("/drawings")
    public List<Shop> getAll() {
        return foodShopRepository.findAll();
    }

    @GetMapping("/click")
    public List<Shop> getByClick(@RequestParam double lat, @RequestParam double lng) throws ParseException {
        GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);
        Point point = factory.createPoint(new Coordinate(lng, lat));
        return foodShopRepository.findByPointWithin(point);
    }

    @PostMapping("/newshop")
    public String saveGeometry(@RequestParam String shopname, @RequestParam String shopdetail,
                               @RequestParam String latitude, @RequestParam String longitude) {
        Shop foodshop = new Shop(shopname, shopdetail, Double.parseDouble(latitude), Double.parseDouble(longitude)); // Point는 Geometry의 하위 타입
        foodShopRepository.save(foodshop);
        return "redirect:/";
    }

    @GetMapping("/getdatechart")
    public List<ChartDto> getdateschart(@RequestParam String shop_id) throws ParseException {
        return chartService.getdatechart(shop_id);
    }

    @GetMapping("/getfoodchart")
    public List<ChartDto> getfoodchart(@RequestParam String shop_id) throws ParseException {
        return chartService.getfoodchart(shop_id);
    }

    @GetMapping("/getcategorychart")
    public List<ChartDto> getcategorychart(@RequestParam String shop_id) throws ParseException {
        return chartService.getcategorychart(shop_id);
    }

    @GetMapping("/getagechart")
    public List<ChartDto> getagechart(@RequestParam String shop_id) throws ParseException {
        return chartService.getagechart(shop_id);
    }

    @GetMapping("/gettotaldatechart")
    public List<ChartDto> gettotaldateschart() throws ParseException {
        return chartService.gettotaldatechart();
    }

    @GetMapping("/gettotalfoodchart")
    public List<ChartDto> gettotalfoodchart() throws ParseException {
        return chartService.gettotalfoodchart();
    }

    @GetMapping("/gettotalcategorychart")
    public List<ChartDto> gettotalcategorychart() throws ParseException {
        return chartService.gettotalcategorychart();
    }

    @GetMapping("/gettotalagechart")
    public List<ChartDto> gettotalagechart() throws ParseException {
        return chartService.gettotalagechart();
    }

    // 특정 가게 메뉴 가져오기 (del_date = null만 가져오기)
    @GetMapping("/shop/{shopId}/foods")
    public List<Food> getFoods(@PathVariable Long shopId) {
        Shop shop = foodShopRepository.findById(shopId).orElseThrow();
        List<Food> foods = foodRepository.findByShopAndDel_dateIsNull(shop);
        foods.forEach(f -> {
            if (f.getImgUrl() == null) f.setImgUrl("");
        });
        return foods;
    }

    // 메뉴 추가
    @PostMapping("/shop/{shopId}/foods")
    public Food addFood(@PathVariable Long shopId, @RequestBody Food food) {
        Shop shop = foodShopRepository.findById(shopId).orElseThrow();
        food.setShop(shop);
        food.setReg_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        return foodRepository.save(food);
    }

    // 메뉴 수정
    @PutMapping("/shop/{shopId}/foods/{foodId}")
    public Food updateFood(@PathVariable Long shopId, @PathVariable Long foodId, @RequestBody Food food) {
        Food existing = foodRepository.findById(foodId).orElseThrow();
        existing.setFood_Name(food.getFood_Name());
        existing.setPrice(food.getPrice());
        return foodRepository.save(existing);
    }

    // ✅ 메뉴 소프트 삭제
    @DeleteMapping("/shop/{shopId}/foods/{foodId}")
    public void deleteFood(@PathVariable Long shopId, @PathVariable Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        food.setDel_date(now);
        foodRepository.save(food); // 실제 삭제 ❌, 날짜만 업데이트
    }

    // 이미지 업로드
    @PostMapping("/shop/{shopId}/foods/{foodId}/uploadImage")
    public Map<String, String> uploadImage(@PathVariable Long shopId, @PathVariable Long foodId,
                                           @RequestParam("file") MultipartFile file) throws IOException {
        Food food = foodRepository.findById(foodId).orElseThrow();

        // 실제 static/img 폴더에 저장
        String uploadDir = new File("src/main/resources/static/img/uploadImg/").getAbsolutePath() + "/";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File dest = new File(uploadDir + filename);
        file.transferTo(dest);

        // URL 설정 + 캐시 방지
        food.setImgUrl("/img/uploadImg/" + filename + "?t=" + System.currentTimeMillis());
        foodRepository.save(food);

        return Map.of("imageUrl", food.getImgUrl());
    }


    @GetMapping("/foods")
    public List<FoodDto> getFoodsByShop(@RequestParam Long shop_id) {
        List<Food> foods = foodShopRepository.findFoodsByShopId(shop_id);
        return foods.stream()
                .map(food -> new FoodDto(food.getId(), food.getFood_Name(), food.getPrice(), food.getShop().getId(), food.getImgUrl()))
                .collect(java.util.stream.Collectors.toList());
    }

    @GetMapping("/ageStats")
    public List<ChartDto> getAgeStats(@RequestParam String shop_id) throws ParseException {
        return chartService.getAgeStats(shop_id);
    }

    @GetMapping("/overallStats")
    public OverallStatsDto getOverallStats() {
        long totalMeals = lunchLogRepository.count();
        long totalShops = foodShopRepository.count();
        long totalMenus = foodShopRepository.countFoods();
        return new OverallStatsDto(totalMeals, totalShops, totalMenus);
    }

    @GetMapping("/overallAgeStats")
    public List<ChartDto> getOverallAgeStats() throws ParseException {
        return chartService.getOverallAgeStats();
    }

    @GetMapping("/overallShopStats")
    public List<ChartDto> getOverallShopStats() throws ParseException {
        return chartService.getOverallShopStats();
    }

    @GetMapping("/overallMenuStats")
    public List<ChartDto> getOverallMenuStats() throws ParseException {
        return chartService.getOverallMenuStats();
    }

    @PostMapping("/ordermenu")
    public String orderMenu(@RequestParam String food_id, @RequestParam String use_age,
                            @RequestParam String use_category, @RequestParam String use_date) {
        try {
            // Food 엔티티 조회
            Optional<Food> foodOptional = foodShopRepository.findFoodById(Long.parseLong(food_id));
            if (foodOptional.isPresent()) {
                Food food = foodOptional.get();

                // Lunch_log 엔티티 생성 및 저장
                Lunch_log lunchLog = new Lunch_log();
                lunchLog.setFood(food);
                lunchLog.setUse_date(use_date);
                lunchLog.setUse_category(use_category);
                lunchLog.setUse_age(use_age);

                lunchLogRepository.save(lunchLog);
                return "success";
            } else {
                return "error: Food not found";
            }
        } catch (NumberFormatException e) {
            return "error: Invalid food ID format";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
}