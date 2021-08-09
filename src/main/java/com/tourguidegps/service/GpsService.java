package com.tourguidegps.service;

import com.tourguidegps.domain.NearByAttraction;
import com.tourguidegps.domain.location.Location;
import com.tourguidegps.domain.location.VisitedLocation;
import com.tourguidegps.service.feignClient.RewardFeignClient;
import com.tourguidegps.util.GetDistance;
import com.tourguidegps.util.GpsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GpsService {
    @Autowired
    private RewardFeignClient rewardFeignClient;
    @Autowired
    private GetDistance getDistance;
    @Autowired
    private GpsUtil gpsUtil;

    public List<NearByAttraction> getNearByAttractions(VisitedLocation visitedLocation) {
        return gpsUtil.getAttractions().stream()
                .map(attraction ->
                {
                    NearByAttraction nearByAttraction = new NearByAttraction();
                    Location attractionLocation = new Location(attraction.longitude, attraction.latitude);
                    nearByAttraction.setName(attraction.attractionName);
                    nearByAttraction.setAttractionLocation(attractionLocation);
                    nearByAttraction.setUserLocation(visitedLocation.location);
                    nearByAttraction.setDistance(getDistance.getDistance(attractionLocation, visitedLocation.location));
                    nearByAttraction.setRewardPoints(rewardFeignClient.getAttractionRewardsPoints(attraction.attractionId, visitedLocation.userId));
                    return nearByAttraction;
                }).sorted(Comparator.comparingDouble(NearByAttraction::getDistance)).collect(Collectors.toList()).subList(0,5);
    }
}
