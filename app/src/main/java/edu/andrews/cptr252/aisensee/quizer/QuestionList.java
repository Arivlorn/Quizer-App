package edu.andrews.cptr252.aisensee.quizer;

import android.content.Context;


import java.util.ArrayList;
import java.util.UUID;

/**
 * Manage a list of questions. This is a singleton class, so only one
 * one instance of the class may ever be created.
 */
public class QuestionList {

    /** Variable for our only instance of QuestionList */
    private static QuestionList sOurInstance;

    /** A list of questions */
    private ArrayList<Question> mQuestions;

    /** Reference to information about our app environment */
    private Context mAppContext;    // adding a reference to the app context allows the class to start activities and/or access project resources.

    /** Constructs the internal question list. */
    private QuestionList(Context appContext) {

        // retrieves context so that it knows where to send things.
        mAppContext = appContext;

        //==========================================================================================
        // JSON Serializer stuff, again

        // create our serializer to load and save questions
        mSerializer = new QuestionJSONSerializer(mAppContext, FILENAME);

        try {
            // load questions from JSON file, this initializes our list of questions :)
            mQuestions = mSerializer.loadQuestions();
        }
        catch (Exception e) {
            // unable to load from file, so create empty question list
            // either file does not exist (which is okay)
            // or file contains an error (which isn't great)
            mQuestions = new ArrayList<>();

            // populates list with 3 sample questions (should only ever happen once, once data is being stored locally)
            Question question1 = new Question();
            question1.setQuestionAnswer("Is Anthony's app the best?", true);
            mQuestions.add(question1);
            Question question2 = new Question();
            question2.setQuestionAnswer("Is the sky orange?", false);
            mQuestions.add(question2);
            Question question3 = new Question();
            question3.setQuestionAnswer("5 + 5 = 10", true);
            mQuestions.add(question3);
        }
        //==========================================================================================


    }

    /**
     * Return one and only instance of the question list.
     * If it does not exist, this method creates it.
     * @param c to eventually be the Application context (See BugList.java)
     * @return Reference to the question list.
     */
    public static QuestionList getInstance(Context c) {
        if (sOurInstance == null) {
            sOurInstance = new QuestionList(c.getApplicationContext());
        }
        return sOurInstance;
    }

    /**
     * Return the question with a given id.
     * @param id Unique id for the question.
     * @return The question object or null if not found.
     */
    public Question getQuestion(UUID id) {
        for (Question question : mQuestions) {
            if (question.getID().equals(id))
                return question;
        }
        return null;
    }

    /**
     * Add a question to the list.
     * @param q the question to add.
     */
    public void addQuestion(Question q) {

        // adds a question to the question list
        mQuestions.add(q);

        //=========================================================================
        // JSON
        // saves questions to file every time a new question is added to the list.=
        saveQuestions();
        //=========================================================================
    }

    /**
     * Add a question to the list at a given position.
     * @param position is the index that the question should be stored at.
     * @param question is the question to add.
     */
    public void addQuestion(int position, Question question) {
        mQuestions.add(position, question);     // add the question at position
        saveQuestions();                        // save the questions to JSON file
    }

    /**
     * Remove the question at a certain index from the list.
     * @param position is the index of the question to be deleted.
     */
    public void deleteQuestion(int position) {
        mQuestions.remove(position);    // remove the question at index position
        saveQuestions();                // save updated list to JSON file
    }


    /**
     * Return a list of questions.
     * @return array of Question objects
     */
    public ArrayList<Question> getQuestions() { return mQuestions; }



    //==============================================================================================
    // For writing to JSON file
    //==============================================================================================

    /** Name of JSON file containing list of questions */
    private static final String FILENAME = "questions.json";

    /** Reference to JSON serializer for a list of questions */
    private  QuestionJSONSerializer mSerializer;

    /**
     * Write question list to JSON file.
     * @return true if successful, false otherwise.
     */
    public boolean saveQuestions() {
        try {

            mSerializer.saveQuestions(mQuestions);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }


}
