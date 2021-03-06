package healthcheck.application.mapper;

import healthcheck.application.dto.ProposalDto;
import healthcheck.application.model.Proposal;
import healthcheck.application.service.HospitalService;
import healthcheck.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Component
public class ProposalDtoMapper extends AbstractMapper<Proposal, ProposalDto> {
    @Autowired
    HospitalService hospitalService;
    @Autowired
    UserService userService;

    @Override
    public Proposal convertDtoToModel(ProposalDto proposalDto) {
        Proposal proposal = new Proposal();
        proposal.setId(proposalDto.getId());
        proposal.setCategory(proposalDto.getCategory());
        proposal.setDescription(proposalDto.getDescription());
        proposal.setHospital(hospitalService.getById(proposalDto.getHospitalId()));
        proposal.setProposedBy(userService.getUserByUsername(proposalDto.getProposedBy()));
        proposal.setStatus(proposalDto.getStatus());
        proposal.setTitle(proposalDto.getTitle());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        proposal.setStartDateAndTime(LocalDateTime.parse(proposalDto.getStartDateAndTime(), formatter));
        proposal.setEndDateAndTime(LocalDateTime.parse(proposalDto.getEndDateAndTime(), formatter));
        return proposal;
    }

    @Override
    public ProposalDto convertModelToDto(Proposal proposal) {
        ProposalDto proposalDto = new ProposalDto();
        proposalDto.setId(proposal.getId());
        proposalDto.setCategory(proposal.getCategory());
        proposalDto.setDescription(proposal.getDescription());
        proposalDto.setHospitalId(proposal.getHospital().getId());
        proposalDto.setProposedBy(proposal.getProposedBy().getUsername());
        proposalDto.setStatus(proposal.getStatus());
        proposalDto.setTitle(proposal.getTitle());
        proposalDto.setStartDateAndTime(proposal.getStartDateAndTime().toString());
        proposalDto.setEndDateAndTime(proposal.getEndDateAndTime().toString());
        proposalDto.setUserIdsWhoVotedThisProposal(proposal.getVotes().stream().map(vote -> vote.getUser().getId()).collect(Collectors.toSet()));
        return proposalDto;
    }
}
