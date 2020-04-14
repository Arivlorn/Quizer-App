package edu.andrews.cptr252.aisensee.quizer;

import android.content.Context;

import java.util.ArrayList;

/**
 * Manage a list of questions. This is a singleton class, so only one
 * one instance of the class may ever be created.
 */
public class QuestionList {

    /** Variable for our only instance of Questionist */
    private static QuestionList sOurInstance;

    /** A list of questions */
    private ArrayList<Question> mQuestions;

    /** Reference to information about our app environment */
    private Context mAppContext;    // adding a reference to the app context allows the class to start activities and/or access project resources.

    /** Constructs the internal question list. */
    private QuestionList(Context appContext) {
        mAppContext = appContext;
        mQuestions = new ArrayList<>();
        // populates our list with 100 fake questions
        for (int i = 0; i < 100; i++) {
            Question question = new Question("Test Question #" + i, true);
            mQuestions.add(question);
        }
    }

    /**
     * Return one and only instance of the question list.
     * If it does not exist, this method creates it.
     * TODO: Add @param c to eventually be the Application context (See BugList.java)
     * @return Reference to the question list.
     */
    public static QuestionList getInstance(Context c) {
        if (sOurInstance == null) {
            sOurInstance = new QuestionList(c.getApplicationContext());
        }
        return sOurInstance;
    }

    /**
     * Add a question to the list.
     * @param q the question to add.
     *
    public void addQuestion(Question q) {
        //TODO: finish

    }
    */

    /**
     * Return a list of questions.
     * @return array of Question objects
     */
    public ArrayList<Question> getQuestions() { return mQuestions; }
}
