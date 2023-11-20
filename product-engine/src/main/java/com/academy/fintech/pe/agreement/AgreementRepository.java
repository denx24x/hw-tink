package com.academy.fintech.pe.agreement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Integer> {

}
