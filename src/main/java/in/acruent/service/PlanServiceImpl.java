package in.acruent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.acruent.entity.Plan;
import in.acruent.entity.PlanCategory;
import in.acruent.repo.PlanCategoryRepo;
import in.acruent.repo.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {
	
	@Autowired
	private PlanCategoryRepo categRepo;
	
	@Autowired
	private PlanRepo repo;

	@Override
	public Map<Integer, String> getPlanCategories() {
		
		List<PlanCategory> categories = categRepo.findAll();
		
		Map<Integer,String> categoryMap=new HashMap<>();
		
		categories.forEach(category->{
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		});
		
		
		
			
		return categoryMap;
	}

	@Override
	public boolean savePlan(Plan plan) {
		Plan saved=repo.save(plan);
		/*
		 * if(saved.getPlanId()!=null) { return true; }else { return false; }
		 */
	//	return saved.getPlanId()!=null?true:false;
		return saved.getPlanId()!=null;
	}

	@Override
	public List<Plan> getAllPlans() {
		
		return repo.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {
		
		Optional<Plan> byId = repo.findById(planId);
		
	    if(byId.isPresent()) {
	    	return byId.get();
	    }
	    return null;
		
	}

	@Override
	public boolean updatePlan(Plan plan) {
		Plan save=repo.save(plan);
		
		return save.getPlanId()!=null;
	}

	@Override
	public boolean deletePlan(Integer planId) {
		
		boolean isDelete=false;
		try {
			repo.deleteById(planId);
			isDelete= true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return isDelete;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		Optional<Plan> findById=repo.findById(planId);
		if(findById.isPresent()) {
			Plan plan=findById.get();
			plan.setActiveSw(status);
			repo.save(plan);
			return true;
			
		}
		
		return false;
	}

}


