package patterns.iterator;

public class TestProgram {

    public static void main(String[] args) {

        String [] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        System.out.println("Array<String> \n");

        // don't know anything about the structure of the collection but can still iterate over it
        IArrayIterator<String> arrayOfDays = new ArrayIterator<String>(days);

        while (arrayOfDays.hasNext())
            System.out.println(arrayOfDays.next());

        System.out.println("\nArray<Number> \n");

        Number [] years = {2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021};

        IArrayIterator<Number> arrayOfYears = new ArrayIterator<Number>(years);

        while (arrayOfYears.hasNext())
            System.out.println(arrayOfYears.next());

        System.out.println("\nArray<Character> \n");

        Character vowels [] = {'a', 'e', 'i', 'o', 'u'};

        IArrayIterator<Character> arrayOfVowels = new ArrayIterator<Character>(vowels);

        while (arrayOfVowels.hasNext())
            System.out.println(arrayOfVowels.next());

    }

}