package login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import login.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Query(value="select * from rstilrac_admin.tblUserInfo where username = ?1",nativeQuery = true)
	User findByUsername(String username);


}
