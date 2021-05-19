/*
Name: Quinn Roemer
Class ID: 6877

Summary: Partial functional implementation of the Map interface. Public methods compose the interface of this class
while private methods are used as helpers to the public methods. All items in this list are immutable, no changes
are allowed on created objects. This implementation supports Java generics and allows for any type of object and/or
data type to be used as the value and key item. Most operations are performed in O(N) time.
*/

public final class Map135<K,V>
{
    private final K key;
    private final V value;
    private final Map135<K,V> restOfMap;

    // Initializes a map node with "key" mapped to "value" and "restOfMap" referring
    // to the next node in the map
    private Map135(K key, V value, Map135<K,V> restOfMap)
    {
        this.key = key;
        this.value = value;
        this.restOfMap = restOfMap;
    }

    //Initializes a map node to represent an empty map.
    public Map135()
    {
        //Instantiate all variables to null using the private constructor
        this(null, null, null);
    }

    //Returns whether the map beginning at this node is an empty object
    public boolean isEmpty()
    {
        //If the value inside the object is null, the map must be empty
        if(this.value == null)
            return true;

        //The map contains a value and is not empty
        return false;
    }

    //Returns whether "key" exists as a key in the map beginning at this node
    public boolean containsKey(K key)
    {
        //If the current map object isEmpty return false
        if (isEmpty())
            return false;

        //If the map object is not empty, check the key
        else if(this.key.equals(key))
            return true;

        //Else, check the rest of the map
        else
            return this.restOfMap.containsKey(key);
    }

    //Returns the value mapped to by "key" or null if it is not in the map
    public V get(K key)
    {
        //If the current map object isEmpty return null
        if (isEmpty())
            return null;

        //If the map object is not empty, check the value
        else if(this.key.equals(key))
            return this.value;

        //Else, check the rest of the map
        else
            return this.restOfMap.get(key);
    }

    //Returns a reference to a map that is the same as the current map but
    //with "key" mapped to "value"
    public Map135<K,V> put(K key, V value)
    {
        //If the map doesn't contain the key being added
        if(!containsKey(key))
            return new Map135<>(key, value, this);

        //If the first element is the key to be replaced
        else if (this.key.equals(key))
            return new Map135<>(key, value, this.restOfMap);

        //Else, keep copying elements until the key to be replaced is found
        else
            return new Map135<>(this.key, this.value, this.restOfMap.putHelper(key, value));
    }

    private Map135<K,V> putHelper(K key, V value)
    {
        //If this element is the key needing to be replaced.
        if (this.key.equals(key))
            return new Map135<>(key, value, this.restOfMap);

        //Else, continue copying the map...
        else
            return new Map135<>(this.key, this.value, this.restOfMap.putHelper(key, value));
    }

    //Returns String representation of current map. Should be formatted as
    //Java formats maps (eg, "{hello=1,world=2}"). The order in which
    //pairs are listed does not matter
    public String toString()
    {
        //Return an empty string the object isEmpty()
        if(isEmpty())
            return "";

        //Else, call toStringHelper with the starter string to accumulate the output
        else
            return toStringHelper("{");

    }

    //Using an accumulator to accumulate the return value.
    private String toStringHelper(String acc)
    {
        //If rest of Map isEmpty
        if(this.restOfMap.isEmpty())
            return acc + this.key.toString() + "=" + this.value.toString() + "}";

        //Else, there must be more elements
        else
            return this.restOfMap.toStringHelper(acc + this.key.toString() + "=" + this.value.toString() + ",");
    }
}
