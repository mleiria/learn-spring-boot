package com.in28minutes.springboot.learn_spring_boot.webapp.coordinates;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CoordinatesController {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping("/coordinates-view")
    public String coordinatesView() {
        logger.info("Coordinates view requested");
        return "coordinates";
    }

    @PostMapping("/coordinates")
    @ResponseBody
    public void receiveCoordinates(@RequestBody Coordinates coordinates) {
        logger.info("Received coordinates: " + coordinates);
        template.convertAndSend("/topic/coordinates", new CoordinatesUpdate(coordinates.toString()));
    }
}
