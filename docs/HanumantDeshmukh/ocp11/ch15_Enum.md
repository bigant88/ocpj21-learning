# You need to know the following facts about enums:
- Enum constructor is always private. 
- If an enum type has no constructor declarations -> private constructor that takes no parameters is automatically provided.
- enum is implicitly final -> cannot extend it. -> it cannot be sealed.
- You cannot extend an enum, but can implement interfaces.
- Since enum maintains exactly one instance of its constants, you cannot clone it. You cannot even override the clone method in an enum because java.lang.Enum makes it final.
- Compiler provides an enum with two public static methods automatically - values() and valueOf(String). The values() method returns an array of its constants and valueOf() method tries to match the String argument exactly (i.e. case sensitive) with an enum constant and returns that constant if successful otherwise it throws java.lang.IllegalArgumentException.
- By default, an enum's toString() prints the enum name but you can override it to print anything you want.
# The following are a few more important facts about java.lang.Enum which you should know:
* It implements java.lang.Comparable (thus, an enum can be added to sorted collections such as SortedSet, TreeSet, and TreeMap). The natural order of the enum values is the order in which they are defined i.e. in the order of their ordinal value.
* It has a method ordinal(), which returns the index (starting with 0) of that constant i.e. the position of that constant in its enum declaration.
* It has a method name(), which returns the name of this enum constant, exactly as declared in its enum declaration.