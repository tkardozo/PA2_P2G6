package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Entry {
    private List<String> entry;

    public Entry(String msg){
        this.entry = new ArrayList<>( Arrays.asList( msg.split("[|]") ) );
    }

    public void register(){
        if(!this.entry.get(2).contains("P-"))
            this.entry.add(2, "P-"+this.entry.get(0));
    }

    public String get(int position){
        assert(position >= 0);
        assert(position < entry.size());
        return this.entry.get(position);
    }

    public void add(String value){
        this.entry.add(value);
    }

    public String getType(){
        if(!this.entry.get(2).contains("P-"))
            return this.entry.get(2);
        return this.entry.get(3);
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        this.entry.forEach(part->{
            if(result.length() > 0)
                result.append("|");
            result.append(part);
        });
        return result.toString();
    }
}
