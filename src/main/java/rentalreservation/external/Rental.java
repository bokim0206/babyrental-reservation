package rentalreservation.external;

public class Rental {

    private Long id;
    private String rentalName;
    private String rentalStatus;
    private String rentalType;
    private String rentalPeriod;
    private Float rentalPrice;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRentalName() {
        return rentalName;
    }
    public void setRentalName(String rentalName) {
        this.rentalName = rentalName;
    }
    public String getRentalStatus() {
        return rentalStatus;
    }
    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
    public String getRentalType() {
        return rentalType;
    }
    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }
    public String getRentalPeriod() {
        return rentalPeriod;
    }
    public void setRentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }
    public Float getRentalPrice() {
        return rentalPrice;
    }
    public void setRentalPrice(Float rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

}
