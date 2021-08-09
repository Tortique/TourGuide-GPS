import com.tourguidegps.GpsApplication;
import com.tourguidegps.domain.NearByAttraction;
import com.tourguidegps.domain.User;
import com.tourguidegps.domain.location.Attraction;
import com.tourguidegps.domain.location.VisitedLocation;
import com.tourguidegps.service.GpsService;
import com.tourguidegps.util.GpsUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = GpsApplication.class)
public class GpsServiceTests {
    @Autowired
    public GpsUtil gpsUtil;

    @Autowired
    private GpsService gpsService;

    @Test
    public void getNearbyAttractions() {
        Attraction attraction = gpsUtil.getAttractions().get(0);
        User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
        VisitedLocation visitedLocation = new VisitedLocation(user.getUserId(), attraction, new Date());

        List<NearByAttraction> attractions = gpsService.getNearByAttractions(visitedLocation);

        assertEquals(5, attractions.size());
    }

    @Test
    public void getUserLocation() {
        UUID uuid = UUID.randomUUID();
        User user = new User(uuid,"jon", "000", "jon@tourGuide.com");
        VisitedLocation visitedLocation = gpsUtil.getUserLocation(uuid);
        user.addToVisitedLocations(visitedLocation);

        assertEquals(1, user.getVisitedLocations().size());
    }
}
