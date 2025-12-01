package pe.edu.upc.menteactiva.dtos.querys;


public interface NativeQuery_MostViewedVideosDTO {
    Long getVideo_id();

    String getVideo_title();

    Integer getTotal_views();

    String getAuthor_name();

    String getAuthor_lastname();

    Long getAuthor_id();
}