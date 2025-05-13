import axios from 'axios';
import { useEffect, useState } from 'react';
import "../style/SessionList.css";

interface Session {
    id: number;
    roomNumber: number;
    projectorType: string;
    isItDubbed: string;
    sessionDate: string;  // LocalDate (Data)
    sessionTime: string;  // LocalTime (Hora)
}

interface SessionListProps {
    movieId: number;
    filters: {
        date: string;
        projectorType: string;
        language: string;
    };
}

const SessionList = ({ movieId, filters }: SessionListProps) => {
    const [sessions, setSessions] = useState<Session[]>([]);

    useEffect(() => {
        const fetchSessions = async () => {
            try {
                const response = await axios.post(`http://localhost:8080/session/filter`, {
                    projectorType: filters.projectorType,
                    isItDubbed: filters.language,
                    sessionDate: filters.date,
                    movieId: movieId,
                });
                setSessions(response.data);
            } catch (error) {
                console.error('Erro ao buscar sessões:', error);
            }
        };
        fetchSessions();
    }, [movieId, filters]);

    const groupedSessions = sessions.reduce((acc, session) => {
        if (!acc[session.roomNumber]) {
            acc[session.roomNumber] = [];
        }
        acc[session.roomNumber].push(session);
        return acc;
    }, {} as Record<number, Session[]>);

    return (
        <div className='sessions__content'>
            <h3 className='sessions__title'>Sessões disponíveis</h3>
            {Object.keys(groupedSessions).length === 0 ? (
                <p className='sessions__error'>Nenhuma sessão encontrada.</p>
            ) : (
                Object.keys(groupedSessions).map((room) => {
                    const roomSessions = groupedSessions[parseInt(room)];

                    return (
                        <div key={room} className='sessions__section'>
                            <h4 className='sessions__room'>Sala {room}</h4>
                            <div className='sessions__list'>
                                {roomSessions.map((session) => {
                                    const sessionDateTime = new Date(`${session.sessionDate}T${session.sessionTime}`);

                                    if (isNaN(sessionDateTime.getTime())) {
                                        return (
                                            <div key={session.id} className="session-time">
                                                <span>Horário inválido</span>
                                            </div>
                                        );
                                    }

                                    return (
                                        <div key={session.id} className="sessions__item">
                                            <span>{sessionDateTime.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })}</span>
                                        </div>
                                    );
                                })}
                            </div>
                        </div>
                    );
                })
            )}
        </div>
    );
};

export default SessionList;
