package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> , JpaSpecificationExecutor<User> {

    @Query(value = "from User where username = :username and address = :address"
            , nativeQuery = true)
    List<User> findByUsernameAndAddress(@Param(value = "username") String username
            ,@Param(value = "address") String address);




    // projection interface
//   @Query(value  = "select u.id as userId , u.name as username, d.name as departmentName" +
//           ", com.name as companyName , cop.name as coporationName from user u\n" +
//           " left join department d on u.department_id  = d.id \n" +
//           " left join company com  on d.company_id = com.id\n" +
//           " left join coporation cop on com.coporation_id  = cop.id \n" +
//           " where u.id  = :id ;")  // todo  -> move to Hql
//    UserDetailProjection findDetailById(@Param(value= "id") Long id);


    // nativeQuery = false -> hql (hibernate query language + hibernate dialect )

}
