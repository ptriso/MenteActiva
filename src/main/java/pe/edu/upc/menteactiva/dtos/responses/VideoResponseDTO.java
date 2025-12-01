package pe.edu.upc.menteactiva.dtos.responses;

import lombok.*;

import java.time.Duration;


import lombok.Generated;

public class VideoResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String url;
    private Integer duration;
    private Long professionalId;

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
    public Long getProfessionalId() {
        return this.professionalId;
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
    public void setProfessionalId(final Long professionalId) {
        this.professionalId = professionalId;
    }

    @Generated
    public VideoResponseDTO(final Long id, final String title, final String description, final String url, final Integer duration, final Long professionalId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.duration = duration;
        this.professionalId = professionalId;
    }

    @Generated
    public VideoResponseDTO() {
    }
}
