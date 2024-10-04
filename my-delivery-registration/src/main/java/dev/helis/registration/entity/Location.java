package dev.helis.registration.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Location extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    public double longitude;

    @NotNull
    public double latitude;

    public Location() {
        // Default constructor
    }

    public Location(String longitude, String latitude) {
        this.longitude = convertDMSToDecimal(longitude);
        this.latitude = convertDMSToDecimal(latitude);
    }

    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = convertDMSToDecimal(latitude);
    }

    public void setLongitude(String longitude) {
        this.longitude = convertDMSToDecimal(longitude);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(longitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(latitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Location other = (Location) obj;
        return Double.compare(this.longitude, other.longitude) == 0 && Double.compare(this.latitude, other.latitude) == 0;
    }

    @Override
    public String toString() {
        return "Location [longitude=" + longitude + ", latitude=" + latitude + "]";
    }
 
    public String convertLatitudeToDMS() {
        return convertToDMS(latitude, true);
    }

    public String convertLongitudeToDMS() {
        return convertToDMS(longitude, false);
    }
     
    private String convertToDMS(double coordinate, boolean isLatitude) {
        // Obtendo o símbolo de direção
        String direction;
        if (isLatitude) {
            direction = (coordinate >= 0) ? "N" : "S";
        } else {
            direction = (coordinate >= 0) ? "E" : "W";
        }

        // Convertendo para valor absoluto (tirar o negativo para o cálculo)
        coordinate = Math.abs(coordinate);

        // Calculando graus, minutos e segundos
        int graus = (int) coordinate;
        double decimalMinutes = (coordinate - graus) * 60;
        int minutos = (int) decimalMinutes;
        double segundos = (decimalMinutes - minutos) * 60;

        // Formatando o resultado
        return String.format("%d°%d'%4.1f\"%s", graus, minutos, segundos, direction);
    }

    public double convertDMSToDecimal(String dms) {
        // Identificando a direção
        char direction = dms.charAt(dms.length() - 1);
        boolean isNegative = (direction == 'S' || direction == 'W');

        // Remover o símbolo de direção para fazer o cálculo
        dms = dms.substring(0, dms.length() - 1);

        // Dividindo a string pelos componentes de graus, minutos e segundos
        String[] dmsParts = dms.split("[°'\"]");

        // Extraindo graus, minutos e segundos
        int degrees = Integer.parseInt(dmsParts[0].trim());
        int minutes = Integer.parseInt(dmsParts[1].trim());
        double seconds = Double.parseDouble(dmsParts[2].trim());

        // Convertendo para decimal
        double decimal = degrees + (minutes / 60.0) + (seconds / 3600.0);

        // Aplicando o sinal negativo, se necessário
        return isNegative ? -decimal : decimal;
    }


}
