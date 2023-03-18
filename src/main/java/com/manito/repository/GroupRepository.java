package com.manito.repository;

import com.manito.Status;
import com.manito.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {

    @Query("select g from Group g where g.status = :status and g.endDate < current_date")
    List<Group> findByStatus(@Param("status") Status status);
}
