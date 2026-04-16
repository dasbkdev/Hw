package homework.jobsearch.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "applicant_id")
    Long applicantId;

    @Column(name = "name")
    String name;

    @Column(name = "category_id")
    Long categoryId;

    @Column(name = "salary")
    BigDecimal salary;

    @Column(name = "is_active")
    Boolean isActive;

    @Column(name = "created_date")
    LocalDateTime createdDate;

    @Column(name = "update_time")
    LocalDateTime updateTime;
}