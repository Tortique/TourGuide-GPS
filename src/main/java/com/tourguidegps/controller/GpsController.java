package com.tourguidegps.controller;

import com.tourguidegps.domain.NearByAttraction;
import com.tourguidegps.domain.location.Attraction;
import com.tourguidegps.domain.location.VisitedLocation;
import com.tourguidegps.service.GpsService;
import com.tourguidegps.util.GpsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/gps")
public class GpsController {
    @Autowired
    private GpsService gpsService;

    @Autowired
    private GpsUtil gpsUtil;

    @GetMapping("/getUserLocation")
    public VisitedLocation getUserLocation(@RequestParam UUID uuid) {
        return gpsUtil.getUserLocation(uuid);
    }

    @GetMapping("/nearByAttractions")
    public List<NearByAttraction> getNearByAttraction(@RequestParam VisitedLocation visitedLocation) {
        return gpsService.getNearByAttractions(visitedLocation);
    }

    @GetMapping("/getAttractions")
    public List<Attraction> getAttractions() { return gpsUtil.getAttractions();}
}
