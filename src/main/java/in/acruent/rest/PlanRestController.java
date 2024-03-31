package in.acruent.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.acruent.constants.AppConstants;
import in.acruent.entity.Plan;
import in.acruent.props.AppProperties;
import in.acruent.service.PlanService;

@RestController
public class PlanRestController {
	
	
	@Autowired
	private PlanService service;
	
	Map<String,String> messages=new HashMap<>();
	
	public PlanRestController(PlanService service,AppProperties props) {
		this.service=service;
		this.messages= props.getMessages();
	}
	
	@GetMapping("/allCategories")
	public ResponseEntity<Map<Integer,String>> getAllCategories(){
		Map<Integer, String> plans = service.getPlanCategories();
		return new ResponseEntity<>(plans,HttpStatus.OK);
		
		
	}
	@PostMapping("/create_Plan")
	public ResponseEntity<String> createPlan(@RequestBody Plan plan){
		boolean savedPlan = service.savePlan(plan);
		String msg=AppConstants.EMP_STRIN;
		if(savedPlan) {
			msg=messages.get(AppConstants.PLAN_SAVE_SUC);
		}else {
			msg=messages.get(AppConstants.PLAN_NOT_SAVE);
		}
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}
	
	@GetMapping("/viewPlans")
	public ResponseEntity<List<Plan>> allViewPlans(){
		List<Plan> allPlans = service.getAllPlans();
		return new ResponseEntity<List<Plan>>(allPlans,HttpStatus.OK);
	}
	
	
	@GetMapping("/edit/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId){
		Plan planById = service.getPlanById(planId);
		return new ResponseEntity<Plan>(planById,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
		boolean isUpdatePlan = service.updatePlan(plan);
		String msg=AppConstants.EMP_STRIN;
		if(isUpdatePlan) {
			msg=messages.get(AppConstants.PLAN_UPDT_SUCC);
		}else {
			msg=messages.get(AppConstants.PLAN_NOT_UPDT);
		}
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{planId}")
	public ResponseEntity<String> deletePlanById(@PathVariable Integer planId){
		boolean isDeletePlan = service.deletePlan(planId);
		String msg=AppConstants.EMP_STRIN;
		if(isDeletePlan) {
			msg=messages.get(AppConstants.PLAN_DEL_SUC);
		}else {
			msg=messages.get(AppConstants.PLAN_NOT_DEL);
		}
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> planStatusChange(@PathVariable Integer planId,@PathVariable String status){
		boolean isStatusChange = service.planStatusChange(planId, status);
		String msg=AppConstants.EMP_STRIN;
		if(isStatusChange) {
			msg=messages.get(AppConstants.PLAN_STATUS_SUC);
		}else {
			msg=messages.get(AppConstants.PLAN_STATUS_NOT);
		}
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}

}


