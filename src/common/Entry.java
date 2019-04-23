package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Helper class for reading, register and adding information to messages
 *
 * @author P2G6
 */
public class Entry {

    private List<String> entry;

    /**
     * Deconstructs the message for easier manipulation
     *
     * @param msg
     */
    public Entry(String msg) {
        this.entry = new ArrayList<>(Arrays.asList(msg.split("[|]")));
    }

    /**
     * Does the car registration
     */
    public void register() {
        if (!this.entry.get(2).contains("P-")) {
            this.entry.add(2, "P-" + this.entry.get(0));
        }
    }

    /**
     * Allows access to a specific part of the message
     *
     * @param position The index of the desired part
     * @return
     */
    public String get(int position) {
        assert (position >= 0);
        assert (position < entry.size());
        return this.entry.get(position);
    }

    /**
     * Add information to the end of the message
     *
     * @param value The information to add
     */
    public void add(String value) {
        this.entry.add(value);
    }

    /**
     * Returns the message type
     *
     * @return String Message type
     */
    public String getType() {
        if (!this.entry.get(2).contains("P-")) {
            return this.entry.get(2);
        }

        // if already registered position is the next
        return this.entry.get(3);
    }

    /**
     * Returns the message in a string with all the changes
     *
     * @return String Message
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        this.entry.forEach(part -> {
            if (result.length() > 0) {
                result.append("|");
            }
            result.append(part);
        });
        return result.toString();
    }
}
