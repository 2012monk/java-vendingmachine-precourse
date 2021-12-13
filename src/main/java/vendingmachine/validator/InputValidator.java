package vendingmachine.validator;

public class InputValidator {

    private static final String ERR_HEADER = "[ERROR]";
    private static final String ERR_INVALID_NUMBER = ERR_HEADER + " 숫자만 입력해주세요";
    private static final String ERR_INVALID_LETTER = ERR_HEADER + " 숫자와 글자만 허용됩니다.";

    public static void validateNumeric(String source) {
        if (!source.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(ERR_INVALID_NUMBER);
        }
    }

    public static void validateNonSpecialChar(String source) {
        if (!source.chars().allMatch(Character::isLetterOrDigit)) {
            throw new IllegalArgumentException(ERR_INVALID_LETTER);
        }
    }
}
