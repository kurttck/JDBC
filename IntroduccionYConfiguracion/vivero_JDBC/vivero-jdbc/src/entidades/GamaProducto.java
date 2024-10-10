package entidades;

public class GamaProducto {

    private int idGama;
    private String gama;
    private String descripcionText;
    private String descripcionHtml;
    private String imagen;

    public GamaProducto(int idGama, String gama, String descripcionText, String descripcionHtml, String imagen) {
        this.idGama = idGama;
        this.gama = gama;
        this.descripcionText = descripcionText;
        this.descripcionHtml = descripcionHtml;
        this.imagen = imagen;
    }

    public int getIdGama() {
        return idGama;
    }

    public void setIdGama(int idGama) {
        this.idGama = idGama;
    }

    public String getGama() {
        return gama;
    }

    public void setGama(String gama) {
        this.gama = gama;
    }

    public String getDescripcionText() {
        return descripcionText;
    }

    public void setDescripcionText(String descripcionText) {
        this.descripcionText = descripcionText;
    }

    public String getDescripcionHtml() {
        return descripcionHtml;
    }

    public void setDescripcionHtml(String descripcionHtml) {
        this.descripcionHtml = descripcionHtml;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public GamaProducto() {

    }

}
