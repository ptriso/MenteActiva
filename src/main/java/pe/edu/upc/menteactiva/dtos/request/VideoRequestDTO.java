package pe.edu.upc.menteactiva.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Generated;

public class VideoRequestDTO {
    private @NotBlank(
            message = "El campo no puede estar vacio"
    ) String title;
    private @NotBlank(
            message = "El campo no puede estar vacio"
    ) String description;
    private @NotBlank(
            message = "El campo no puede estar vacio"
    ) @Pattern(
            regexp = "^(https?|ftp)://.*$",
            message = "La URL del video debe ser válida"
    ) String url;
    private @NotNull(
            message = "El campo no puede estar vacio"
    ) Integer duration;
    private @NotNull(
            message = "El campo no puede estar vacio"
    ) Long professionalId;

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
    public VideoRequestDTO(final String title, final String description, final String url, final Integer duration, final Long professionalId) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.duration = duration;
        this.professionalId = professionalId;
    }

    @Generated
    public VideoRequestDTO() {
    }
}