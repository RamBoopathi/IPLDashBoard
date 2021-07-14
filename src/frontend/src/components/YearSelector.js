import { React } from 'react';
import { Link } from 'react-router-dom';
import './YearSelector.scss';

export const YearSelector = ({teamName}) => {
    let years = [];
    const startYear = 2008;
    const endYear = 2021;

    for (let i = startYear; i <= endYear; i++) {
        years.push(i);
    }

    return (<ol className="YearSelector">
       <p>Select Year</p>
        {years.map(year => (
            <li>
                <Link to={`/teams/${teamName}/matches/${year}`} >{year}</Link></li>)
        )}
    </ol>
    );
}