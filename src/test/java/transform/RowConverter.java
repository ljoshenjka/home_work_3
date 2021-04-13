package transform;

public class RowConverter {
    public static Integer getIntegerValueFromWord(String value) {
        switch (value) {
            case "first":
                return 1;
            case "second":
                return 2;
            case "third":
                return 3;
            case "fourth":
                return 4;
            case "fifth":
                return 5;
            case "sixth":
                return 6;
            case "seventh":
                return 7;
            case "eighth":
                return 8;
            case "ninth":
                return 9;
            case "tenth":
                return 10;
            default:
                return Integer.parseInt(value);
        }
    }
}
