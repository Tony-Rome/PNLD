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
    public void normalizeRegion() {
        String valparaiso = "región de valparaíso";
        String valparaisoExpected = "valparaiso";
        Assert.assertEquals(SchoolService.normalizeRegion(valparaiso), valparaisoExpected);

        String a = "región de la araucanía";
        String aExpected = "araucania";
        Assert.assertEquals(SchoolService.normalizeRegion(a), aExpected);

        String maule = "región del maule";
        String mauleExpected = "maule";
        Assert.assertEquals(SchoolService.normalizeRegion(maule), mauleExpected);

        String rm = "región metropolitana";
        String rmExpected = "metropolitana";
        Assert.assertEquals(SchoolService.normalizeRegion(rm), rmExpected);

        String rm2 = "región metropolitana de santiago";
        //Assert.assertEquals(schoolService.normalizeRegion(rm2),rmExpected);

        String arica = "region de arica y parinacota";
        String aricaExpected = "arica y parinacota";
        Assert.assertEquals(SchoolService.normalizeRegion(arica), aricaExpected);

        String biobio = "region de bio bio";
        String biobioExpected = "bio bio";
        Assert.assertEquals(SchoolService.normalizeRegion(biobio), biobioExpected);

        String losRios = "region de los rios";
        String losRiosExpected = "los rios";
        Assert.assertEquals(SchoolService.normalizeRegion(losRios), losRiosExpected);

        String losLagos = "region de los lagos";
        String losLagosExpected = "los lagos";
        Assert.assertEquals(SchoolService.normalizeRegion(losLagos), losLagosExpected);
    }

    @Test
    public void rbdToInt() {
        String rbdAsStr1 = "4045-9";
        int rbdAsIntExpected1 = 40459;
        Assert.assertEquals(schoolService.rbdToInt(rbdAsStr1), rbdAsIntExpected1);

        String rbdAsStr2 = "22333-6";
        int rbdAsIntExpected2 = 223336;
        Assert.assertEquals(schoolService.rbdToInt(rbdAsStr2), rbdAsIntExpected2);

        String rbdAsStr3 = "Municipal";
        Assert.assertEquals(schoolService.rbdToInt(rbdAsStr3), 0);
    }
}
