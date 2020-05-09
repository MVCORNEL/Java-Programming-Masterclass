
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        //If you class is called Map to avoid the compile time error
        java.util.Map<String, String> languages = new HashMap<>();
        languages.put("Python", "an interptreted, object-oriented, high-level programming language with dynamic semantics");
        languages.put("Algol", "an algorithmic languages");
        languages.put("Basic", "Beginners All Purposes Symbolic Intruction code");
        languages.put("Lisp", "There in lies madness");
        //1
        //System.out.println(hash.put())
        //If the key is reused will print the prior existed value , if not will print null
        //It tells you what prior value you had, but doesn't prevent you from adding it
        System.out.println(languages.put("Java", "a compiled, high level, object-oriented, platform independant language"));
        System.out.println(languages.put("Java", "This course is about Java"));
        //System.out.println(languages.get("Java"));

        //2 CONTAINS KEY
        if (languages.containsKey("Java")) {
            System.out.println("Java is already in the map");
        } else {
            languages.put("Java", "This course is about java");
        }

        //3 PUT IF ABSENT
        // USED TO PREVENT CONCURRENCY ISSUES
        languages.putIfAbsent("Java", "This course is java");

        //4 LOOP THROUGH A MAP BY getting the key of sets
        //Hashmap doesn't return you the object in the presume order
        //KeySet returns a for all of the keys
        for (String key : languages.keySet()) {
            System.out.println(key + " : " + languages.get(key));
        }

        //5 REMOVE
        // languages.remove("Java");
        //5.1 REMOVE ONLY IF TH EKEY Algolh has the corresponding value
        //return true if it was successfully executed
        if (languages.remove("Algol", "an algorithmic languages")) {
            System.out.println("Algol removed");
        } else {
            System.out.println("Algol not removed, key/value pair not found");
        }

        //6 REPLACE
        System.out.println(languages.replace("Java","java has been replaced"));
        //6.1 REPLACE IF THE OLD VALUE MATCHED TO
        System.out.println(languages.replace("Java","java has been replaced","new value come here"));

        for (String key : languages.keySet()) {
            System.out.println(key + " : " + languages.get(key));
        }
    }
}


