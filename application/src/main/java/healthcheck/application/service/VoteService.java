package healthcheck.application.service;

import healthcheck.application.model.Vote;
import healthcheck.application.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {
    @Autowired
    VoteRepository voteRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProposalService proposalService;

    public void addVote(Long userId, Long proposalId){
        Vote vote = new Vote(userService.getUserById(userId), proposalService.getProposalById(proposalId));
        voteRepository.save(vote);
    }

    public void removeVote(Long userId, Long proposalId){
        Vote vote = new Vote(userService.getUserById(userId), proposalService.getProposalById(proposalId));
        voteRepository.delete(vote);
    }
}
