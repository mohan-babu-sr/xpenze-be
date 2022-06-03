package in.project.xpenzebe.exception;

public class XpenzeCollectionException extends Exception {

    private static final long serialVersionUID = 1L;

    public XpenzeCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "xpenze with "+id+" not found!";
    }

    public static String XpenzeAlreadyExists() {
        return "Xpenze with given name already exists";
    }
}
