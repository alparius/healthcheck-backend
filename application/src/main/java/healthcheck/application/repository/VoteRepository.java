package healthcheck.application.repository;

import healthcheck.application.model.Vote;
import healthcheck.application.model.VotePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, VotePK> {
}
