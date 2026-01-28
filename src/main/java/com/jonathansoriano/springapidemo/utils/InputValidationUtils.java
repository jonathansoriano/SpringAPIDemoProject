package com.jonathansoriano.springapidemo.utils;

import com.jonathansoriano.springapidemo.domain.StudentRequest;

public class InputValidationUtils {
    private InputValidationUtils() {
        //default private constructor
    }

    /**
     * Checks if any mandatory field within the provided StudentRequest object
     * is either null or an empty string.
     * @param request The StudentRequest object containing potential null or empty data.
     * @return True if any required field (first name, last name, city, state, grade,
     * university ID, or DOB) is null or empty; false otherwise.
     */
    public static boolean isInputNullOrEmpty(StudentRequest request)
    {
        return request.getFirstName() == null || request.getFirstName().matches("^*$") ||
                request.getLastName() == null || request.getLastName().matches("^*$") ||
                request.getResidentCity() == null || request.getResidentCity().matches("^*$") ||
                request.getResidentState() == null || request.getResidentState().matches("^*$") ||
                request.getGrade() == null || request.getGrade().matches("^*$") ||
                request.getUniversityId() == null || request.getDob() == null;
    }

    /**
     * Verifies whether a string is correctly capitalized as a proper noun.
     * A properly formatted proper noun starts with an uppercase letter
     * followed by only lowercase letters.
     * **METHOD NEEDS CHECKED/TESTED ** 1.28.26
     *
     * @param input The string to validate against the specified format.
     * @return True if the input matches the expected format; false otherwise.
     */
    public static boolean isStringInputFormattedCorrectly(String input){
        return input.matches("b[A-Z][a-z]*b");
    }

    /**
     * Standardizes proper nouns by capitalizing the first letter and lowercasing the rest.
     * @param input a string that needs corrected
     * @return a string
     */
    public static String properNounStringFormatter (String input){
        //Use to increment the placement of the new inserted char in a char array
        int counter = 0;

        //Turn firstname into a char array so we can parse through it
        char [] oldFirstName = input.toCharArray();

        //Create a new char array with updated chars (with the correct name format)
        char [] newFirstName = new char[input.length()];

        //Combine elements in the newFirstName array into a single StringBuilder
        StringBuilder resultingFirstName = new StringBuilder();

        for (char letter : oldFirstName)
        {
            if (letter != oldFirstName[0])
            {
                newFirstName[1 + counter] = Character.toLowerCase(letter);
                counter++;
            }
            else
            {
                newFirstName[0] =Character.toUpperCase(letter);
            }
        }

        return resultingFirstName.append(newFirstName).toString();
    }


}
