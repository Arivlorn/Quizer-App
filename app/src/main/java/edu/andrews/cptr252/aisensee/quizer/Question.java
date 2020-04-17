package edu.andrews.cptr252.aisensee.quizer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Model for a true/false question.
 */
public class Question {

    /** Unique ID for question **/
    private UUID mID;

    /** Question */
    private String mQuestion;

    /** Answer to question. */
    private boolean mAnswer;

    /**
     * Create a new question.
     * Question answer is set to true on creation, to avoid possibility of a null answer.
     */
    public Question() {
        mID = UUID.randomUUID();    // a constructor
        mAnswer = true;
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
     * Returns the true/false answer.
     * @return Answer
     */
    public boolean getAnswer() { return mAnswer; }

    /**
     * Sets the question.
     * @param question
     */
    public void setQuestion(String question) { mQuestion = question; }

    /**
     * Set the question and answer at the same time.
     * @param question The question.
     * @param answer The true/false answer.
     */
    public void setQuestionAnswer(String question, boolean answer) {
        mQuestion = question;
        mAnswer = answer;
    }

    /**
     * Sets the true/false answer.
     * @param answer
     */
    public void setAnswer(boolean answer) { mAnswer = answer; }

    //==============================================================================================
    // For JSON file saving
    //==============================================================================================

    /** JSON attribute for question id */
    private static final String JSON_ID = "id";
    /** JSON attribute for question */
    private static final String JSON_QUESTION = "title";
    /** JSON attribute for true/false answer */
    private static final String JSON_ANSWER_TRUE_FALSE = "answer_true_false";

    /**
     * Initialize a new question from a JSON object
     * @param json is the JSON object for a question
     * @throws org.json.JSONException
     */
    public Question(JSONObject json) throws JSONException {
        mID = UUID.fromString(json.getString(JSON_ID));
        mQuestion = json.optString(JSON_QUESTION);
        mAnswer = json.getBoolean(JSON_ANSWER_TRUE_FALSE);
    }

    /**
     * Write the question to a JSON object
     * @return JSON object containing the question information
     * @throws JSONException
     */
    public JSONObject toJSON() throws JSONException {

        // initialize a new JSON object
        JSONObject jsonObject = new JSONObject();

        // store items inside of the JSON object
        jsonObject.put(JSON_ID, mID.toString());
        jsonObject.put(JSON_QUESTION, mQuestion);
        jsonObject.put(JSON_ANSWER_TRUE_FALSE, mAnswer);

        // return the question as a JSON object.
        return jsonObject;

    }
}