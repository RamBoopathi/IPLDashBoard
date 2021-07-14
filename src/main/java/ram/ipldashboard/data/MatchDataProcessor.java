package ram.ipldashboard.data;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;

import ram.ipldashboard.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    @Override
    public Match process(final MatchInput mInput) throws Exception {
        Match match = new Match();
        match.setId(Long.parseLong(mInput.getId()));
        match.setCity(mInput.getCity());
        match.setDate(LocalDate.parse(mInput.getDate()));
        match.setPlayerOfMatch(mInput.getPlayer_of_match());
        match.setVenue(mInput.getVenue());
        match.setMatchWinner(mInput.getWinner());
        // Set team 1 and Team2 depending on the innings order

        String firstInningsTeam, secondInningsTeam;
        if ("bat".equalsIgnoreCase(mInput.getToss_decision())) {
            firstInningsTeam = mInput.getToss_winner();
            secondInningsTeam = mInput.getToss_winner().equalsIgnoreCase(mInput.getTeam1()) ? mInput.getTeam2()
                    : mInput.getTeam1();
        } else {
            secondInningsTeam = mInput.getToss_winner();
            firstInningsTeam = mInput.getToss_winner().equalsIgnoreCase(mInput.getTeam1()) ? mInput.getTeam2()
                    : mInput.getTeam1();
        }

        match.setTeam1(firstInningsTeam);
        match.setTeam2(secondInningsTeam);
        match.setTossWinner(mInput.getToss_winner());
        match.setTossDecision(mInput.getToss_decision());
        match.setResult(mInput.getResult());
        match.setResultMargin(mInput.getResult_margin());
        match.setUmpire1(mInput.getUmpire1());
        match.setUmpire2(mInput.getUmpire2());
        return match;
    }

}