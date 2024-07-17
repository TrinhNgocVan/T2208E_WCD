package org.example.repository;

import org.example.dto.DepartmentDetailProjection;
import org.example.entity.Department;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepositoty
        extends JpaRepository<Department,Long>, JpaSpecificationExecutor<Department> {

     //  hql user only class and field name  , not table name or column name
    @Query(
            "Select d.id as departmentId , d.name as departmentName " +
                    ", count(u.id)  as userQuantity " +
                    "from  Department d " +
                    "left join User u " +
                    "on d.id  = u.department.id " +
                    " group by (d.id)"
    )
    List<DepartmentDetailProjection> getDepartcmdmentDetail();
}
