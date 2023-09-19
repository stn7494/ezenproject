package ez.en.support.repository;

import ez.en.support.domain.Partner;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Integer> {



}
