package pe.edu.upc.menteactiva.dtos.responses;

import lombok.*;
import pe.edu.upc.menteactiva.enums.StatusAp;

@Data
@Getter
@Setter
public class AppointmentClientResponseDTO {
    private Long id;
    private String professionalName;
    private String professionalLastname;
    private String date;
    private String timeStart;
    private String timeEnds;
    private String status;

    public AppointmentClientResponseDTO(Long id, String professionalName, String professionalLastname, String date, String timeStart, String timeEnds, StatusAp statusEnum) {
        this.id = id;
        this.professionalName = professionalName;
        this.professionalLastname = professionalLastname;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnds = timeEnds;
        this.status = statusEnum.name();
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AppointmentClientResponseDTO)) {
            return false;
        } else {
            AppointmentClientResponseDTO other = (AppointmentClientResponseDTO)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                Object this$professionalName = this.getProfessionalName();
                Object other$professionalName = other.getProfessionalName();
                if (this$professionalName == null) {
                    if (other$professionalName != null) {
                        return false;
                    }
                } else if (!this$professionalName.equals(other$professionalName)) {
                    return false;
                }

                Object this$professionalLastname = this.getProfessionalLastname();
                Object other$professionalLastname = other.getProfessionalLastname();
                if (this$professionalLastname == null) {
                    if (other$professionalLastname != null) {
                        return false;
                    }
                } else if (!this$professionalLastname.equals(other$professionalLastname)) {
                    return false;
                }

                Object this$date = this.getDate();
                Object other$date = other.getDate();
                if (this$date == null) {
                    if (other$date != null) {
                        return false;
                    }
                } else if (!this$date.equals(other$date)) {
                    return false;
                }

                Object this$timeStart = this.getTimeStart();
                Object other$timeStart = other.getTimeStart();
                if (this$timeStart == null) {
                    if (other$timeStart != null) {
                        return false;
                    }
                } else if (!this$timeStart.equals(other$timeStart)) {
                    return false;
                }

                Object this$timeEnds = this.getTimeEnds();
                Object other$timeEnds = other.getTimeEnds();
                if (this$timeEnds == null) {
                    if (other$timeEnds != null) {
                        return false;
                    }
                } else if (!this$timeEnds.equals(other$timeEnds)) {
                    return false;
                }

                Object this$status = this.getStatus();
                Object other$status = other.getStatus();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof AppointmentClientResponseDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $professionalName = this.getProfessionalName();
        result = result * 59 + ($professionalName == null ? 43 : $professionalName.hashCode());
        Object $professionalLastname = this.getProfessionalLastname();
        result = result * 59 + ($professionalLastname == null ? 43 : $professionalLastname.hashCode());
        Object $date = this.getDate();
        result = result * 59 + ($date == null ? 43 : $date.hashCode());
        Object $timeStart = this.getTimeStart();
        result = result * 59 + ($timeStart == null ? 43 : $timeStart.hashCode());
        Object $timeEnds = this.getTimeEnds();
        result = result * 59 + ($timeEnds == null ? 43 : $timeEnds.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "AppointmentClientResponseDTO(id=" + var10000 + ", professionalName=" + this.getProfessionalName() + ", professionalLastname=" + this.getProfessionalLastname() + ", date=" + this.getDate() + ", timeStart=" + this.getTimeStart() + ", timeEnds=" + this.getTimeEnds() + ", status=" + this.getStatus() + ")";
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getProfessionalName() {
        return this.professionalName;
    }

    @Generated
    public String getProfessionalLastname() {
        return this.professionalLastname;
    }

    @Generated
    public String getDate() {
        return this.date;
    }

    @Generated
    public String getTimeStart() {
        return this.timeStart;
    }

    @Generated
    public String getTimeEnds() {
        return this.timeEnds;
    }

    @Generated
    public String getStatus() {
        return this.status;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
    }

    @Generated
    public void setProfessionalName(final String professionalName) {
        this.professionalName = professionalName;
    }

    @Generated
    public void setProfessionalLastname(final String professionalLastname) {
        this.professionalLastname = professionalLastname;
    }

    @Generated
    public void setDate(final String date) {
        this.date = date;
    }

    @Generated
    public void setTimeStart(final String timeStart) {
        this.timeStart = timeStart;
    }

    @Generated
    public void setTimeEnds(final String timeEnds) {
        this.timeEnds = timeEnds;
    }

    @Generated
    public void setStatus(final String status) {
        this.status = status;
    }

    @Generated
    public AppointmentClientResponseDTO() {
    }
}