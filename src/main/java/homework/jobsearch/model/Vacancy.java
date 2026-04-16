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
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "category_id")
    Long categoryId;

    @Column(name = "salary")
    BigDecimal salary;

    @Column(name = "exp_from")
    Integer expFrom;

    @Column(name = "exp_to")
    Integer expTo;

    @Column(name = "is_active")
    Boolean isActive;

    @Column(name = "author_id")
    Long authorId;

    @Column(name = "created_date")
    LocalDateTime createdDate;

    @Column(name = "update_time")
    LocalDateTime updateTime;
}