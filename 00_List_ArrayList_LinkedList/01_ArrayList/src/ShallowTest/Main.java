package ShallowTest;

import java.util.ArrayList;

public class Main{
    //SHALLOW COPY WORK WITH OBJECT NOT WITH PRIMARY TYPES
    public static void main(String[] args){

        ArrayList<Dummy> oldArray = new ArrayList<Dummy>();
        oldArray.add(new Dummy(100,false));
        oldArray.add(new Dummy(100,false));

        //SHALLOW COPY
        ArrayList<Dummy> copyArray = new ArrayList<Dummy>(oldArray);
        //makes changes to the initial array because is changing the value by getting the reference from that stored object
        copyArray.get(0).setNumber(9999);
        copyArray.get(0).setDummy(true);


        //DOESN'T AFFECT THE ORIGINAL ARRAY
        copyArray.set(0,new Dummy(5555,true));
      copyArray.clear();
        copyArray.add(0, new Dummy(5555, true));



        for(Dummy dummy :oldArray){
            System.out.println(dummy.getNumber() +" "+ dummy.isDummy());
        }

    }

    static class Dummy {
        int number;
        boolean isDummy;

        public Dummy(int number, boolean isDummy) {
            this.number = number;
            this.isDummy = isDummy;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public boolean isDummy() {
            return isDummy;
        }

        public void setDummy(boolean dummy) {
            isDummy = dummy;
        }
    }

}