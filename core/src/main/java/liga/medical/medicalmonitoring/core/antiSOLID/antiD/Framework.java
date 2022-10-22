package liga.medical.medicalmonitoring.core.antiSOLID.antiD;

public class Framework {

    // в данном случаи Framework зависит от конкретного класса, а не от интерфейса или абстрактного класса
    // лучше было бы создать интерфейс для форм и передавать его
    public void save(Form form) {
        MySQL db = new MySQL();
        db.save(form);
    }
}
