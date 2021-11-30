package cn.bosch.controller;

import cn.bosch.model.viewobject.StasticRespVO;
import cn.bosch.service.EventHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    EventHubService eventHubService;

    @PostMapping("startPublishMessage")
    public void start() {
        eventHubService.publishMessage();

    }


    @PostMapping("stopPublishMessage")
    public void stop() {
        eventHubService.stopMessage();

    }

    @PostMapping("getStatisticMessage")
    public StasticRespVO getStaticMessage() {
        return eventHubService.getStaticMessage();
    }
}
