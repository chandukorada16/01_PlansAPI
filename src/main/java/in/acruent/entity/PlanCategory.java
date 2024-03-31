package in.acruent.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="PLAN_Category")
public class PlanCategory {
	
	@Id
	@GeneratedValue
	@Column(name="Plan_CatId")
	private Integer categoryId;
	
	@Column(name="Plan_CategName")
	private String categoryName;
	
	@Column(name="ACTIVE_SW")
	private String activeSw;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@CreationTimestamp
	@Column(name="CREATED_Dt",updatable = false)
	private LocalDate createdDate;
	
	@UpdateTimestamp
	@Column(name="Updated_Dt",insertable=false)
	private LocalDate updatedDate;

}
