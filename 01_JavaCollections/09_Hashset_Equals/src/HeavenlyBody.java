import java.util.HashSet;
import java.util.Set;

public final class HeavenlyBody {
    private final String name;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;

    public HeavenlyBody(String name, double orbitalPeriod) {
        this.name = name;
        this.orbitalPeriod = orbitalPeriod;
        this.satellites = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }


    public boolean addMoon(HeavenlyBody moon) {
        return satellites.add(moon);
    }

    public Set<HeavenlyBody> getSatellites() {
        //creating a new hashset of the current content of the satellite
        return new HashSet<>(satellites);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //1
    //This method is used in order to give the object a hashcode....
    //If two objects have the same name, then they will have the same hashcode as well
    //IF they have the same hashcode they will be put in the same hashcode bucket and after will be compared by using the method equals
    //If the objects are equal by the rules established in the equals method the second object won't be added to the hashset/hashmap
    @Override
    public int hashCode() {
        System.out.println("hashcode called");
        return this.name.hashCode() + 57;
    }


    //2
    //OVERRIDE ANNOTATION MAKES SURE YOU HAVE THE RIGHT SIGNATURE FOR THE METHOD
    @Override
    public boolean equals(Object obj) {
        // 2.1 REFERENCE COMPARISON, ADDRESS COMPARISON CHECK IF THE OBJECT IT SS THE SAME OBJECT IN THE MEMORY
        if (this == obj) {
            return true;
        }
        //DEBUGGING PURPOSE
        System.out.println("this.getClass() is " + this.getClass());
        System.out.println("obj.getClass() is " + obj.getClass());
        // 2 this object cannot be null because this is the object which we compare with
        //THERE IS A CHANGE AS THE OBJECT WHICH WE WANT TO COMPARE TO BE NULL, IF THAT OBJECT IS NULL RETURN FALSE - I AM NOT SURE ABOUT THAT
        //ALSO IF THAT OBJECT WHICH WE COMPARE WITH MAY BE AN INSTANCE OF ANOTHER CLASS, IN THIS CASE WILL RETURN FALSE AS WELL
        //IF WE TRY TO CALL THE GETCLASS METHOD ON A NULL OBJECT WE'LL GET AT NULL POINTER EXCEPTION ERROR
        if (obj == null || (obj.getClass() != this.getClass())) {
            return false;
        }
        //3
        String objectName = ((HeavenlyBody) obj).getName();
        return this.name.equals(objectName);
    }


    //EQUALS 5 rules
//    Reflexive: x.equals(x) == true , an object must equal to itself.
//    Symmetry: if(x.equals(y)==true) then y.equals(x) == true.
//    Transitive: if x.equals(y) and y.equals(z); then x.equals(z)
//    Consistent: if x.equals(y)==true and no value is modified, then it’s always true for every call
//    For any non-null object x, x.equals(null)==false

    //HASHING FIVE RULES

    //IF TWO OBJECT ARE EUQLS THEY MUST HAVE THE SAME HASHCODE

}
