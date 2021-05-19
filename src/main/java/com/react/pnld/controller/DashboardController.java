package com.react.pnld.controller;

import com.react.pnld.controller.response.DimensionResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/v1/dimensions")
public class DashboardController {

    @GetMapping(produces = {"application/json"})
    public List<DimensionResponse> getDimensions() {
        List<DimensionResponse> dimensionResponseList = new ArrayList<>();
        dimensionResponseList.add(new DimensionResponse("capacitaciones"));
        dimensionResponseList.add(new DimensionResponse("uso-codeorg"));
        dimensionResponseList.add(new DimensionResponse("test-pc"));
        return dimensionResponseList;
    }
}
