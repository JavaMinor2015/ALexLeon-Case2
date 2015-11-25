package javaminor.al.service;

import java.util.*;
import javaminor.al.business.MaintenanceStatus;
import javaminor.al.domain.beans.CarBean;
import javaminor.al.domain.beans.CustomerBean;
import javaminor.al.domain.beans.MaintenanceBean;
import javaminor.al.entities.abs.Customer;
import javaminor.al.entities.concrete.Car;
import javaminor.al.entities.concrete.Driver;
import javaminor.al.entities.concrete.MaintenanceAssignment;
import javaminor.al.error.MaintenanceException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Leon Stam on 25-11-2015.
 */
public class MaintenanceProcessTest {

    private MaintenanceProcess process;

    private CarBean carBean;
    private CustomerBean customerBean;
    private MaintenanceBean maintenanceBean;
    private InspectionService inspectionService;

    @Before
    public void before() {
        carBean = mock(CarBean.class);
        customerBean = mock(CustomerBean.class);
        maintenanceBean = mock(MaintenanceBean.class);
        inspectionService = mock(InspectionService.class);
        process = new MaintenanceProcess(carBean, customerBean, maintenanceBean, inspectionService);
    }

    @Test
    public void testCustomerExists() throws Exception {
        //Exists
        when(customerBean.getCustomer(anyString(), anyString())).thenReturn(new Customer());
        boolean exists = process.customerExists("A", "B");
        assertTrue(exists);

        //Doesn't exist
        when(customerBean.getCustomer(anyString(), anyString())).thenReturn(null);
        exists = process.customerExists("A", "B");
        assertFalse(exists);
    }

    @Test
    public void testCreateCustomer() throws Exception {
        //Already exists
        when(customerBean.getCustomer(anyString(), anyString())).thenReturn(new Customer());
        process.createCustomer(new Driver());
        verify(customerBean, times(0)).createCustomer(any());

        //Doesn't exist yet
        when(customerBean.getCustomer(anyString(), anyString())).thenReturn(null);
        process.createCustomer(new Driver());
        verify(customerBean, times(1)).createCustomer(any());
    }

    @Test
    public void testAddCarAlreadyExists() throws Exception {
        //Arrange
        when(carBean.getByPlate(anyString())).thenReturn(Car.builder().numberPlate("A2").build());
        Car c = mock(Car.class);
        when(c.getNumberPlate()).thenReturn("A2");

        //Act
        process.addCar(c, new Driver());

        //Assert
        verify(c, times(1)).getNumberPlate();
        verify(carBean, times(0)).refresh(any());
    }

    @Test
    public void testAddCar() throws Exception {
        //Arrange
        when(carBean.getByPlate(anyString())).thenReturn(null, null);
        Car c = new Car();
        Driver d = new Driver();

        //Act - Doesn't have car
        process.addCar(c, d);

        //Assert
        assertTrue(d.hasCar(c));
        verify(carBean, times(1)).refresh(c);
        assertEquals(1, d.getCars().size());
        assertEquals(d, c.getDriver());


        //Act - Already has the car
        process.addCar(c, d);

        //Assert
        verify(carBean, times(2)).refresh(c);
        assertEquals(1, d.getCars().size());
    }

    @Test
    public void testAddAssignmentStatus() throws Exception {
        for (MaintenanceStatus status : getAllStatusExcept(MaintenanceStatus.NEW)) {
            try {
                MaintenanceAssignment assignment = MaintenanceAssignment.builder().status(status).build();
                process.addAssignment(assignment, null);
                fail("Exception should have been thrown");
            } catch (MaintenanceException e) {
                assertEquals("This assignment is not new", e.getMessage());
            }
        }
    }

    @Test
    public void testAddAssignment() throws Exception {
        //Arrange
        MaintenanceAssignment assignment = MaintenanceAssignment.builder().status(MaintenanceStatus.NEW).build();
        Car car = new Car();

        //Act
        process.addAssignment(assignment, car);

        //Assert
        assertEquals(car, assignment.getCar());
        assertTrue(car.hasAssignment(assignment));
        verify(carBean, times(1)).refresh(car);
        verify(maintenanceBean, times(1)).refresh(assignment);
    }

    @Test
    public void testFindById() throws Exception {
        //Arrange
        Optional<MaintenanceAssignment> opt = Optional.empty();
        when(maintenanceBean.findById(1)).thenReturn(opt);

        //Act
        Optional<MaintenanceAssignment> result = process.findById(1);

        //Assert
        assertFalse(result.isPresent());
        assertEquals(opt, result);
    }

    @Test
    public void testGetUnfinishedAssignments() throws Exception {
        //Arrange
        List<MaintenanceAssignment> assignments = Arrays.asList(new MaintenanceAssignment(), new MaintenanceAssignment());
        when(maintenanceBean.getAssignmentsWithStatus(MaintenanceStatus.NEW, MaintenanceStatus.IN_PROGRESS)).thenReturn(assignments);

        //Act
        List<MaintenanceAssignment> result = process.getUnfinishedAssignments();

        //Assert
        verify(maintenanceBean, times(1)).getAssignmentsWithStatus(MaintenanceStatus.NEW, MaintenanceStatus.IN_PROGRESS);
        assertEquals(assignments, result);
    }

    @Test
    public void testMarkInvalidAssignmentInProgress() throws Exception {
        for (MaintenanceStatus status : getAllStatusExcept(MaintenanceStatus.NEW)) {
            //Arrange
            MaintenanceAssignment assignment = MaintenanceAssignment.builder().status(status).build();

            //Act & assert
            try {
                process.markAssignmentInProgress(assignment);
                fail("Exception should have been thrown");
            } catch (MaintenanceException e) {
                assertEquals("This assignment is already in progress or finished", e.getMessage());
            }
        }
    }

    @Test
    public void testMarkAssignmentInProgress() throws Exception {
        //Arrange
        MaintenanceAssignment assignment = MaintenanceAssignment.builder().status(MaintenanceStatus.NEW).build();

        //Act
        process.markAssignmentInProgress(assignment);

        //Assert
        assertEquals(MaintenanceStatus.IN_PROGRESS, assignment.getStatus());
        verify(maintenanceBean, times(1)).refresh(assignment);
    }

    @Test
    public void testMarkInspectionInvalidDone() throws Exception {
        for (MaintenanceStatus status : getAllStatusExcept(MaintenanceStatus.IN_PROGRESS)) {
            //Arrange
            MaintenanceAssignment assignment = MaintenanceAssignment.builder().status(status).build();

            //Act & assert
            try {
                process.markInspectionDone(assignment);
                fail("Exception should have been thrown");
            } catch (MaintenanceException e) {
                assertEquals("This assignment has a status which does not allow modification", e.getMessage());
                assertEquals(assignment, e.getAssignment());
            }
        }
    }

    @Test
    public void testMarkNonApkInspectionDone() throws Exception {
        //Arrange
        MaintenanceAssignment assignment = MaintenanceAssignment.builder()
                .status(MaintenanceStatus.IN_PROGRESS).apk(false).build();

        //Act & assert
        try {
            process.markInspectionDone(assignment);
            fail("Expection should have been thrown");
        } catch (MaintenanceException e) {
            assertEquals("This assignment is not marked for inspection.", e.getMessage());
        }
    }

    @Test
    public void testMarkInspectionDone() throws Exception {
        //Arrange
        Car c = Car.builder().numberPlate("A2").build();
        MaintenanceAssignment assignment = MaintenanceAssignment.builder()
                .status(MaintenanceStatus.IN_PROGRESS).apk(true)
                .car(c).build();
        when(inspectionService.requiresInspection("A2")).thenReturn(true);

        //Act
        boolean result = process.markInspectionDone(assignment);

        //Assert
        assertTrue(result);
        verify(inspectionService, times(1)).requiresInspection("A2");
    }


    @Test
    public void testMarkInvalidAssignmentFinished() throws Exception {
        for (MaintenanceStatus status : getAllStatusExcept(MaintenanceStatus.IN_PROGRESS)) {
            //Arrange
            MaintenanceAssignment assignment = MaintenanceAssignment.builder().status(status).build();

            //Act & assert
            try {
                process.markAssignmentFinished(assignment);
                fail("Exception should have been thrown");
            } catch (MaintenanceException e) {
                assertEquals("This assignment is not in progress", e.getMessage());
            }
        }
    }

    @Test
    public void testMarkAssignmentFinished() throws Exception {
        //Arrange
        MaintenanceAssignment assignment = MaintenanceAssignment.builder()
                .status(MaintenanceStatus.IN_PROGRESS).build();

        //Act
        process.markAssignmentFinished(assignment);

        //Arrange
        assertEquals(MaintenanceStatus.FINISHED, assignment.getStatus());
        verify(maintenanceBean, times(1)).refresh(assignment);
    }


    private Set<MaintenanceStatus> getAllStatusExcept(MaintenanceStatus... statuses) {
        Set<MaintenanceStatus> statusSet = new HashSet<>(
                Arrays.asList(MaintenanceStatus.values())
        );
        for (MaintenanceStatus status : statuses) {
            statusSet.remove(status);
        }
        return statusSet;
    }

}