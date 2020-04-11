package ControlService.Entities;

import ControlService.vo.MovieVO;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Where(clause = "is_full = 0")
@Entity(name = "movies")
public class MovieE {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name="name_original")
    private String nameOriginal;
    private String description;

    @Column(name="image_link")
    private String imageLink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOriginal() {
        return nameOriginal;
    }

    public void setNameOriginal(String nameOriginal) {
        this.nameOriginal = nameOriginal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public static MovieE fromVO(MovieVO movieVO){
        MovieE movie = new MovieE();
        movie.setId(movieVO.getId());
        movie.setName(movieVO.getName());
        movie.setNameOriginal(movieVO.getNameOriginal());
        movie.setDescription(movieVO.getDescription());
        movie.setImageLink(movieVO.getImageLink());
        return movie;
    }
}
