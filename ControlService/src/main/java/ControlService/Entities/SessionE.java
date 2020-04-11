package ControlService.Entities;

import ControlService.vo.SessionVO;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Where(clause = "is_full = 0")
@Entity(name = "sessions")
public class SessionE {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="movie_id")
    private Long movieId;

    @Column(name="date_time")
    private LocalDateTime dateTime;

    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static SessionE fromVO(SessionVO sessionVO){
        SessionE session = new SessionE();
        session.setId(sessionVO.getId());
        session.setMovieId(sessionVO.getMovieId());
        session.setDateTime(sessionVO.getDateTime());
        session.setPrice(sessionVO.getPrice());
        return session;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
