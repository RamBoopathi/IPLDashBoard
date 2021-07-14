import './TeamPage.scss';
import { React, useEffect, useState } from 'react';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';
import { useParams } from 'react-router';
import { PieChart } from 'react-minimal-pie-chart';
import { Link } from 'react-router-dom';

export const TeamPage = () => {
  const [team, setTeam] = useState({ matches: [] });
  const { teamName } = useParams();

  useEffect(
    () => {
      const fetchMatches = async () => {
        const response = await fetch(`http://localhost:8080/team/${teamName}`);
        const data = await response.json();

        setTeam(data);
      };
      fetchMatches();
    }, [teamName]
  );

  if (!team || !team.teamName) {
    return <h1> Team Not Found</h1>
  }
  return (
    <div className="TeamPage">
      <div className="team-name-section"> <h1><div className="team-name"> {team.teamName}</div></h1></div>
      <div className="win-loss-section"> Wins/ Losses
        <PieChart
          data={[
            { title: 'Losses', value: team.totalMatches-team.totalWins, color: '#f01919' },
            { title: 'Wins', value: team.totalWins, color: '#4da375' }
          ]}
        />
      </div>
      <div className="match-detailed-section">
        <h3>Latest Matches</h3>
        <MatchDetailCard teamName={team.teamName} match={team.matches[0]} />
      </div>
      {team.matches.slice(1).map(match => <MatchSmallCard teamName={team.teamName} match={match} />)}
      <div className="more-link"> 
           <Link to={`/teams/${teamName}/matches/2020`} >More ></Link>
      </div>
    </div>
  );
}
