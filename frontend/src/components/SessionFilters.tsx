import '../style/SessionFilters.css';

interface SessionFiltersProps {
    filters: {
        date: string;
        projectorType: string;
        language: string;
    };
    onFilterChange: (field: string, value: string) => void;
}

const SessionFilters = ({ filters, onFilterChange }: SessionFiltersProps) => {
    const formatarData = (date: Date) => {
        return date.toISOString().split('T')[0];
    };

    const hoje = new Date();
    const amanha = new Date();
    amanha.setDate(hoje.getDate() + 1);
    const depoisAmanha = new Date();
    depoisAmanha.setDate(hoje.getDate() + 2);

    const opcoesDatas = [
        { label: 'Hoje', value: formatarData(hoje) },
        { label: 'Amanhã', value: formatarData(amanha) },
        { label: 'Depois de Amanhã', value: formatarData(depoisAmanha) },
    ];

    return (
        <div className="moviePage__filters">
            <h3>Filtrar Sessões</h3>
            <div className="moviePage__filtersRow">
                <div className="moviePage__filterItem">
                    <label htmlFor="filter-date">Data</label>
                    <select
                        id="filter-date"
                        name="date"
                        value={filters.date}
                        onChange={(e) => onFilterChange('date', e.target.value)}
                    >
                        {opcoesDatas.map(({ label, value }) => (
                        <option key={value} value={value}>
                            {label} {value}
                        </option>
                        ))}
                    </select>
                </div>

                <div className="moviePage__filterItem">
                    <label htmlFor="filter-type">Tipo de Sessão</label>
                    <select
                        id="filter-type"
                        name="type"
                        value={filters.projectorType}
                        onChange={(e) => onFilterChange('projectorType', e.target.value)}
                    >
                        <option value="2D">2D</option>
                        <option value="3D">3D</option>
                        <option value="IMAX">IMAX</option>
                    </select>
                </div>

                <div className="moviePage__filterItem">
                    <label htmlFor="filter-language">Idioma</label>
                    <select
                        id="filter-language"
                        name="language"
                        value={filters.language}
                        onChange={(e) => onFilterChange('language', e.target.value)}
                    >
                        <option value="dub">Dublado</option>
                        <option value="leg">Legendado</option>
                    </select>
                </div>
            </div>
        </div>
    );
};

export default SessionFilters;
