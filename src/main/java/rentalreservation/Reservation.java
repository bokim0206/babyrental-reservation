package rentalreservation;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Reservation_table")
public class Reservation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long rentalId;
    private String rentalName;
    private String rentalStatus;
    private String rentalType;
    private String rentalPeriod;
    private Float rentalPrice;
    private String memberName;

    @PostUpdate
    public void onPostUpdate(){
        ReservationCanceled reservationCanceled = new ReservationCanceled();
        BeanUtils.copyProperties(this, reservationCanceled);
        reservationCanceled.publishAfterCommit();

    }

    @PrePersist
    public void onPrePersist() throws Exception {
        rentalreservation.external.Rental rental = new rentalreservation.external.Rental();
       
        System.out.print("#######rentalId="+rental);
        //Rental 서비스에서 Rental의 상태를 가져옴
        rental = ReservationApplication.applicationContext.getBean(rentalreservation.external.RentalService.class)
            .getRentalStatus(rentalId);

        // 예약 가능상태 여부에 따라 처리
        if ("Available".equals(rental.getRentalStatus())){
            this.setRentalName(rental.getRentalName());
            this.setRentalPeriod(rental.getRentalPeriod());
            this.setRentalPrice(rental.getRentalPrice());
            this.setRentalType(rental.getRentalType());
            this.setRentalStatus("Confirmed");
        } else {
            throw new Exception("The rental is not in a usable status.");
        }


    }

    @PostPersist
    public void onPostPersist() throws Exception {

        ReservationRegistered reservationRegistered = new ReservationRegistered();
        BeanUtils.copyProperties(this, reservationRegistered);
        reservationRegistered.publishAfterCommit();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
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
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }




}
