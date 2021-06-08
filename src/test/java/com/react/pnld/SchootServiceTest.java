package com.react.pnld;

import com.react.pnld.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest
public class SchootServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    SchoolService schoolService;

    @Test
    void contextLoads() {
        Assert.assertNotNull(schoolService);
    }

    @Test
    public void normalizeRegion(){
        String valparaiso = "región de valparaíso";
        String valparaisoExpected = "valparaiso";
        Assert.assertEquals(schoolService.normalizeRegion(valparaiso), valparaisoExpected);

        String maule = "región del maule";
        String mauleExpected = "maule";
        Assert.assertEquals(schoolService.normalizeRegion(maule), mauleExpected);

        String rm = "región metropolitana";
        String rmExpected = "metropolitana";
        Assert.assertEquals(schoolService.normalizeRegion(rm),rmExpected);

        String a = "región de la araucanía";
        String aExpected = "araucania";
        Assert.assertEquals(schoolService.normalizeRegion(a),aExpected);
    }

    @Test
    public void rbdToInt(){
        String rbdAsStr1 = "4045-9";
        int rbdAsIntExpected1 = 40459;
        Assert.assertEquals(schoolService.rbdToInt(rbdAsStr1), rbdAsIntExpected1);

        String rbdAsStr2 = "22333-6";
        int rbdAsIntExpected2 = 223336;
        Assert.assertEquals(schoolService.rbdToInt(rbdAsStr2), rbdAsIntExpected2);

        String rbdAsStr3 = "Municipal";
        Assert.assertEquals(schoolService.rbdToInt(rbdAsStr3), null);
    }
}
