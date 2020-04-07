package edu.andrews.cptr252.aisensee.quizer;

import java.util.UUID;

/**
 * Model for a true/false question.
 */
public class Question {

    /** Unique ID for question **/
    private UUID mID;

    /** Question */
    private String mQuestion;

    /** Answer to question */
    private boolean mAnswer;

    /**
     * Create a new question.
     * @param question Resource id for question text.
     * @param answer Resource id for question answer (true/false).
     */
    public Question(String question, boolean answer) {
        mID = UUID.randomUUID();    // a constructor
        mQuestion = question;
        mAnswer = answer;
    }

    // Getters & Setters

    /**
     * Returns the ID
     * @return ID
     */
    public UUID getID() { return mID; }

    /**
     * Returns the question.
     * @return Question
     */
    public String getQuestion() { return mQuestion; }

    /**
     * Sets the question.
     * @param question
     */
    public void setQuestion(String question) { mQuestion = question; }

    /**
     * Returns the true/false answer.
     * @return Answer
     */
    public boolean getAnswer() { return mAnswer; }

    /**
     * Sets the true/false answer.
     * @param answer
     */
    public void setAnswer(boolean answer) { mAnswer = answer; }

}
