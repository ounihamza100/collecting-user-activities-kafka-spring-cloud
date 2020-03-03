package com.dashboard.aop;

import com.dashboard.model.receiver.KafkaResponse;
import com.dashboard.model.sender.KafkaRecord;
import com.dashboard.model.sender.Record;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.appinfo.ApplicationInfoManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Hamza.Ouni
 */
@Aspect
@Component
public class RpcAop {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RestTemplate restTemplate;
    private UserRequestDetails userRequestDetails;

    @Around("@annotation(Rpc)")
    public Object sendUserActivity(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        // prepare userRequestDetails
        userRequestDetails = UserRequestDetails.builder().build();
        userRequestDetails = this.chargerUserRequestDetails();
        userRequestDetails.setStartRequest(start);
        //  end userRequestDetails

        //  proceed
        Object proceed = joinPoint.proceed();
        // end proceed
        long executionTime = System.currentTimeMillis() - start;


        // prepare execution Time for userRequestDetails
        userRequestDetails.setExecutionTime(executionTime);
        //  end set execution Time

        // start sending to kafka
        String str = objectMapper.writeValueAsString(userRequestDetails);
        System.out.println("==========\n " + str + "\n =============");
        Record record = new Record();
        record.setValue(str);
        KafkaRecord kafkaRecord = new KafkaRecord();
        List<Record> records = new ArrayList<>();
        records.add(record);
        kafkaRecord.setRecords(records);
        String rec = objectMapper.writeValueAsString(kafkaRecord);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/vnd.kafka.json.v2+json"));

        HttpEntity<String> httpEntity = new HttpEntity<>(rec, headers);
        ResponseEntity<KafkaResponse> response = restTemplate.exchange("http://localhost:8082/topics/userDetails", HttpMethod.POST, httpEntity, KafkaResponse.class);

        //  end sending to kafka
        // good bye
        return proceed;
    }

    public UserRequestDetails chargerUserRequestDetails() throws Exception {
        RequestAttributes reqAttr = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servlReqAttr = (ServletRequestAttributes) reqAttr;
        HttpServletRequest req = servlReqAttr.getRequest();
        System.out.println("=============================");
        this.userRequestDetails.setUserName(req.getHeader("name"));
        this.userRequestDetails.setCountry(req.getHeader("country"));
        this.userRequestDetails.setCity(req.getHeader("city"));
        this.userRequestDetails.setPlateform(req.getHeader("plateform"));
        this.userRequestDetails.setDevice(req.getHeader("device"));
        this.userRequestDetails.setUri(req.getRequestURI());
        this.userRequestDetails.setUrl(req.getRequestURL());
        HandlerExecutionChain handlerExecutionChain = this.requestMappingHandlerMapping.getHandler(req);
        HandlerMethod handlerMethod = (HandlerMethod) handlerExecutionChain.getHandler();
        this.userRequestDetails.setControllerName(handlerMethod.getBeanType().getSimpleName());
        this.userRequestDetails.setMethodName(handlerMethod.getMethod().getName());
        String port = String.valueOf(ApplicationInfoManager.getInstance().getInfo().getPort());
        String microService = String.valueOf(ApplicationInfoManager.getInstance().getInfo().getAppName());
        this.userRequestDetails.setMicroService(microService);
        this.userRequestDetails.setPort(port);
        System.out.println("=============================");
        return userRequestDetails;
    }
}
