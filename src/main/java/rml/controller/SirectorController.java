package rml.controller;


import com.jd.m.sirector.wrapper.event.EventExecute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@Controller
@RequestMapping("/sirector")
public class SirectorController {

    @Resource
    private EventExecute sirectorDemoExecute;

    @Resource
    private ExecutorService sirectorDemoThreadPool;


    @RequestMapping(value="/demo",method = RequestMethod.GET)
    @ResponseBody
    public Map demo(HttpServletRequest request) {

        sirectorDemoExecute.setThreadPool(sirectorDemoThreadPool);

        return (Map) sirectorDemoExecute.execute(null);
    }

}
