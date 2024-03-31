package in.acruent.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.acruent.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer> {

}
