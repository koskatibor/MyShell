package shell;

import shell.exceptions.ShellException;
import shell.*;

public class Loader {
    public static Shell load(){
        return new Parancsok();
    }
}
