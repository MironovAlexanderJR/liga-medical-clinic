package liga.medical.medicalmonitoring.core.antiSOLID;

public interface antiI {

    boolean validateShippingAddress(String address);
    boolean validateCardInfo(String cardInfo);
    void processOrder(String order);

}
