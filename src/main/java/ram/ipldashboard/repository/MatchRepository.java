package ram.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ram.ipldashboard.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long> {
    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
        String teamName1,LocalDate startDate, LocalDate endDate, String teamName2,LocalDate startDate1, LocalDate endDate1);

    default List<Match> findLatestMatchesByTeam(String teamName, int count) {
        Pageable pageable = PageRequest.of(0, count);
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable);
    }

    default List<Match> getAllMatchesByDate(String teamName, LocalDate startDate, LocalDate endDate){
        return getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(teamName,  startDate, endDate, teamName, startDate, endDate);
    }

    @Query("select m from Match m where (m.team1 =:teamName or m.team2 = :teamName) and m.date between :startDate and :endDate order by date desc")
    List<Match> getMatchesBetweenDates(@Param("teamName") String teamName, 
    @Param("startDate") LocalDate startDate,
    @Param("endDate") LocalDate endDate);

 
}
