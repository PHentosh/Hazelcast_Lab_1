import java.io.Serializable;

class Value implements Serializable {
    public int amount;
    public Value() {
    }

    public Value( Value that ) {
        this.amount = that.amount;
    }

    public boolean equals( Object o ) {
        if ( o == this ) return true;
        if ( !(o instanceof Value that) ) return false;
        return that.amount == this.amount;
    }
}