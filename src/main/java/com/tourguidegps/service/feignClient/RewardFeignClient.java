package com.tourguidegps.service.feignClient;

import com.tourguidegps.domain.location.Location;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "rewardservice", url = "localhost:8081")
public interface RewardFeignClient {

    @GetMapping("/getAttractionRewardsPoints/{attractionId}/{userId}")
    int getAttractionRewardsPoints(@PathVariable("attractionId") UUID attractionId,
                                   @PathVariable("userId") UUID userId);
}
