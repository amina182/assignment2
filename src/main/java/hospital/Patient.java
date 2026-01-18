package hospital;

public class Patient extends Person implements HospitalService{

    private int age;

    public Patient(String name, String surname, int age) {
        super(name, surname);
        this.age = age;
    }

    public int getAge() { return age; }

    @Override
    public void provideService() {
        System.out.println("Patient receives medical treatment");

    }

    @Override
    public void displayInfo(){
        System.out.println("Patient ->" + toString() + ", Age " + age);
    }


}