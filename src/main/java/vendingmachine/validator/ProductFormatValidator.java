package vendingmachine.validator;

import java.util.regex.Pattern;

public class ProductFormatValidator {

    private static final String ERR_HEADER = "[ERROR]";
    private static final String ERR_INVALID_FORMAT = ERR_HEADER + "잘못된 상품정보 형식입니다.";
    private static final String ERR_INVALID_PRODUCT_NAME = ERR_HEADER + "상품이름은 숫자와 글자만 허용됩니다.";
    private static final String INFO_DELIMITER = ",";
    private static final Pattern infoRegex = Pattern.compile("^\\[\\w+\\d+\\d+\\]$");
    private static final char INFO_PREFIX = '[';
    private static final char INFO_SUFFIX = ']';
    private static final int INFO_PREFIX_POSITION = 0;
    private static final int INFO_FIELDS_SIZE = 3;
    private static final int PRODUCT_NAME_INDEX = 0;
    private static final int PRODUCT_AMOUNT_INDEX = 1;
    private static final int PRODUCT_STOCK_INDEX = 2;

    public static void validateProductInfo(String info) {
        validateProductFormat(info);
        validateFields(
            info.replace(String.valueOf(INFO_PREFIX), "")
                .replace(String.valueOf(INFO_SUFFIX), "")
                .split(INFO_DELIMITER)
        );
    }

    public static void validateProductFormat(String info) {
        if (info.charAt(INFO_PREFIX_POSITION) != INFO_PREFIX
            || info.charAt(info.length() - 1) != INFO_SUFFIX) {
            throw new IllegalArgumentException(ERR_INVALID_FORMAT);
        }
    }

    public static void validateSize(String[] fields) {
        if (fields.length != INFO_FIELDS_SIZE) {
            throw new IllegalArgumentException(ERR_INVALID_FORMAT);
        }
    }

    public static void validateProductName(String name) {
        if (!name.chars()
            .allMatch(Character::isLetterOrDigit)) {
            throw new IllegalArgumentException(ERR_INVALID_PRODUCT_NAME);
        }
    }

    public static void validateFields(String[] fields) throws IllegalArgumentException {
        validateSize(fields);
        InputValidator.validateNumeric(fields[PRODUCT_STOCK_INDEX]);
        InputValidator.validateNumeric(fields[PRODUCT_AMOUNT_INDEX]);
        validateProductName(fields[PRODUCT_NAME_INDEX]);
    }
}