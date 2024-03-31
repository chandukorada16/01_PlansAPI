package in.acruent.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import in.acruent.constants.AppConstants;
import in.acruent.entity.Plan;
import in.acruent.props.AppProperties;
import in.acruent.rest.PlanRestController;
import in.acruent.service.PlanService;

@ExtendWith(MockitoExtension.class)
public class PlansApiControllerTest {
	
	@Mock
    private PlanService service;

    @Mock
    private AppProperties props;

    @InjectMocks
    private PlanRestController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllCategories() {
        // Given
        Map<Integer, String> categories = new HashMap<>();
        categories.put(1, "Category1");
        categories.put(2, "Category2");
        when(service.getPlanCategories()).thenReturn(categories);

        // When
        ResponseEntity<Map<Integer, String>> response = controller.getAllCategories();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categories, response.getBody());
    }

    @Test
    public void testCreatePlan() {
        // Given
        Plan plan = new Plan();
        when(service.savePlan(plan)).thenReturn(true);

        // When
        ResponseEntity<String> response = controller.createPlan(plan);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(AppConstants.PLAN_SAVE_SUC, response.getBody());
    }

    @Test
    public void testAllViewPlans() {
        // Given
        List<Plan> plans = new ArrayList<>();
        plans.add(new Plan());
        when(service.getAllPlans()).thenReturn(plans);

        // When
        ResponseEntity<List<Plan>> response = controller.allViewPlans();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(plans, response.getBody());
    }

    @Test
    public void testEditPlan() {
        // Given
        int planId = 1;
        Plan plan = new Plan();
        when(service.getPlanById(planId)).thenReturn(plan);

        // When
        ResponseEntity<Plan> response = controller.editPlan(planId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(plan, response.getBody());
    }

    @Test
    public void testUpdatePlan() {
        // Given
        Plan plan = new Plan();
        when(service.updatePlan(plan)).thenReturn(true);

        // When
        ResponseEntity<String> response = controller.updatePlan(plan);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(AppConstants.PLAN_UPDT_SUCC, response.getBody());
    }

    @Test
    public void testDeletePlanById() {
        // Given
        int planId = 1;
        when(service.deletePlan(planId)).thenReturn(true);

        // When
        ResponseEntity<String> response = controller.deletePlanById(planId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(AppConstants.PLAN_DEL_SUC, response.getBody());
    }

    @Test
    public void testPlanStatusChange() {
        // Given
        int planId = 1;
        String status = "Active";
        when(service.planStatusChange(planId, status)).thenReturn(true);

        // When
        ResponseEntity<String> response = controller.planStatusChange(planId, status);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(AppConstants.PLAN_STATUS_SUC, response.getBody());
    }

}
