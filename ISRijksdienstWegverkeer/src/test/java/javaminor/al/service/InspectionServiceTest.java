package javaminor.al.service;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Leon Stam on 26-11-2015.
 */
public class InspectionServiceTest {

    @Test
    public void testSteekproef() throws Exception {
        //Arrange
        InspectionService service = new InspectionService();

        //Act
        Boolean nextBoolean = service.steekproef("Hello, is it me you're looking for? I can see it in your eyes. I can see it in your smile.");

        //Assert
        assertNotNull(nextBoolean);
    }

}