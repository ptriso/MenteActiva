package pe.edu.upc.menteactiva.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Generated;

@Entity
@Table(
        name = "videos"
)
public class Videos {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "title",
            length = 150,
            nullable = false
    )
    private String title;
    @Column(
            name = "description",
            length = 2000,
            nullable = false
    )
    private String description;
    @Column(
            name = "url",
            length = 500,
            nullable = false
    )
    private String url;
    @Column(
            name = "duration",
            nullable = false
    )
    private Integer duration;
    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "profesional_id",
            nullable = false
    )
    private Profesionals profesional;
    @JsonIgnore
    @OneToOne(
            mappedBy = "video",
            fetch = FetchType.EAGER
    )
    private Video_Progress video_progress;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getTitle() {
        return this.title;
    }

    @Generated
    public String getDescription() {
        return this.description;
    }

    @Generated
    public String getUrl() {
        return this.url;
    }

    @Generated
    public Integer getDuration() {
        return this.duration;
    }

    @Generated
    public Profesionals getProfesional() {
        return this.profesional;
    }

    @Generated
    public Video_Progress getVideo_progress() {
        return this.video_progress;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
    }

    @Generated
    public void setTitle(final String title) {
        this.title = title;
    }

    @Generated
    public void setDescription(final String description) {
        this.description = description;
    }

    @Generated
    public void setUrl(final String url) {
        this.url = url;
    }

    @Generated
    public void setDuration(final Integer duration) {
        this.duration = duration;
    }

    @Generated
    public void setProfesional(final Profesionals profesional) {
        this.profesional = profesional;
    }

    @Generated
    public void setVideo_progress(final Video_Progress video_progress) {
        this.video_progress = video_progress;
    }

    @Generated
    public Videos() {
    }

    @Generated
    public Videos(final Long id, final String title, final String description, final String url, final Integer duration, final Profesionals profesional, final Video_Progress video_progress) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.duration = duration;
        this.profesional = profesional;
        this.video_progress = video_progress;
    }
}
