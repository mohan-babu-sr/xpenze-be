package in.project.xpenzebe.repository;

import in.project.xpenzebe.model.Xpenze;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface XpenzeRepository extends MongoRepository<Xpenze, String>  {
    @Query("{'xpenze': ?0}")
    Optional<Xpenze> findByXpenze(String xpenze);
}
