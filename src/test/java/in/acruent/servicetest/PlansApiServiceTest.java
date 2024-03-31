package in.acruent.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import in.acruent.entity.Plan;
import in.acruent.entity.PlanCategory;
import in.acruent.repo.PlanCategoryRepo;
import in.acruent.repo.PlanRepo;
import in.acruent.service.PlanServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PlansApiServiceTest {
	
	@Mock
    private PlanCategoryRepo categoryRepo;

    @Mock
    private PlanRepo planRepo;

    @InjectMocks
    private PlanServiceImpl planService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPlanCategories() {
        // Given
        List<PlanCategory> categories = new ArrayList<>();
        categories.add(new PlanCategory());
        categories.add(new PlanCategory());

        when(categoryRepo.findAll()).thenReturn(categories);

        // When
        Map<Integer, String> result = planService.getPlanCategories();

        // Then
        assertEquals(categories.size(), result.size());
        assertEquals("Category1", result.get(1));
        assertEquals("Category2", result.get(2));
    }

    @Test
    public void testSavePlan() {
        // Given
        Plan plan = new Plan();
        when(planRepo.save(plan)).thenReturn(plan);

        // When
        boolean result = planService.savePlan(plan);

        // Then
        assertTrue(result);
    }

    @Test
    public void testGetAllPlans() {
        // Given
        List<Plan> plans = new ArrayList<>();
        plans.add(new Plan());
        plans.add(new Plan());

        when(planRepo.findAll()).thenReturn(plans);

        // When
        List<Plan> result = planService.getAllPlans();

        // Then
        assertEquals(plans.size(), result.size());
    }

    @Test
    public void testGetPlanById() {
        // Given
        Plan plan = new Plan();
        Optional<Plan> optionalPlan = Optional.of(plan);

        when(planRepo.findById(1)).thenReturn(optionalPlan);

        // When
        Plan result = planService.getPlanById(1);

        // Then
        assertNotNull(result);
        assertEquals(plan, result);
    }

    @Test
    public void testUpdatePlan() {
        // Given
        Plan plan = new Plan();
        when(planRepo.save(plan)).thenReturn(plan);

        // When
        boolean result = planService.updatePlan(plan);

        // Then
        assertTrue(result);
    }

    @Test
    public void testDeletePlan() {
        // Given
        Integer planId = 1;
        doNothing().when(planRepo).deleteById(planId);

        // When
        boolean result = planService.deletePlan(planId);

        // Then
        assertTrue(result);
    }

    @Test
    public void testPlanStatusChange() {
        // Given
        Plan plan = new Plan();
        plan.setPlanId(1);
        plan.setActiveSw("Inactive");

        Optional<Plan> optionalPlan = Optional.of(plan);

        when(planRepo.findById(1)).thenReturn(optionalPlan);
        when(planRepo.save(plan)).thenReturn(plan);

        // When
        boolean result = planService.planStatusChange(1, "Active");

        // Then
        assertTrue(result);
        assertEquals("Active", plan.getActiveSw());
    }

}
