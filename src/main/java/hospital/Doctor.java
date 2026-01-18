package hospital;

public class Doctor extends Person implements HospitalService {

    private String specialization;

    public Doctor(String name, String surname, String specialization) {
        super(name, surname);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public void provideService() {
        System.out.println("Doctor provides consultation");

    }

    @Override
    public void displayInfo() {
        System.out.println("Doctor -> " + toString() + ", Specialization: " + specialization);
    }
}

