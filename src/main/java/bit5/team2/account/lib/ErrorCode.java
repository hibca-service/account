package bit5.team2.account.lib;

public enum ErrorCode {
    E00,
    E01,
    E02,
    E03,
    E04,
    E99;

    private ErrorCode() {
    }

    public String getMessage() {
        switch(this) {
            case E00:
                return "Success";
            case E01:
                return "Empty";
            case E02:
                return "Unauthorized";
            case E03:
                return "Failed";
            case E04:
                return "Invalid";
            case E99:
                return "System Exception";
            default:
                throw new AssertionError("Unknown Error Message" + this);
        }
    }
}
