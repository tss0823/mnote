package com.loong.mnote.task.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: sam
 * @date: 2019-01-17 09:59
 */
@Component
public class IntegralSchedule {

//    @Autowired
//    private UserIntegralService userIntegralService;

    /**
     * 凌晨1分执行
     */
    @Scheduled(cron = "0 1 0 * * *")
    public void likeClickToIntegral(){

//        userIntegralService.likeClickToIntegralForYesterday();

//        userIntegralService.integralToBalanceForYesterday();
    }


}
