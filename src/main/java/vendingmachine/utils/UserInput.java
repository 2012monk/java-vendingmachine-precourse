package vendingmachine.utils;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.function.Supplier;
import vendingmachine.domain.HoldingAmount;
import vendingmachine.domain.InputAmount;
import vendingmachine.domain.Product;
import vendingmachine.domain.ProductSeller;
import vendingmachine.validator.InputValidator;

public class UserInput {

    private static final String PROMPT_HOLDING_AMOUNT = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String PROMPT_PRODUCTS_INFO = "상품명과 가격, 수량을 입력해 주세요.";
    private static final String PROMPT_INPUT_AMOUNT = "투입 금액을 입력해 주세요.";
    private static final String PROMPT_ORDER_PRODUCT = "구매할 상품명을 입력해 주세요.";

    public static ProductSeller getProductSeller() {
        return new ProductSeller(getProductList());
    }

    public static List<Product> getProductList() {
        return getValidInput(
            () -> ProductParser.parse(getInputWithPrompt(PROMPT_PRODUCTS_INFO))
        );
    }

    public static String getProductOrder() {
        return getValidInput(() -> {
            String input = getInputWithPrompt(PROMPT_ORDER_PRODUCT);
            InputValidator.validateNonSpecialChar(input);
            return input;
        });
    }

    public static InputAmount getValidInputAmount() {
        return new InputAmount(getValidNumberWithPrompt(PROMPT_INPUT_AMOUNT));
    }

    public static HoldingAmount getValidHoldingAmount() {
        return new HoldingAmount(getValidNumberWithPrompt(PROMPT_HOLDING_AMOUNT));
    }

    private static int getValidNumberWithPrompt(String prompt) {
        return getValidInput(() -> {
            String input = getInputWithPrompt(prompt);
            InputValidator.validateNumeric(input);
            return Integer.parseInt(input);
        });
    }

    private static String getInputWithPrompt(String prompt) {
        System.out.println(prompt);
        return Console.readLine();
    }

    private static <T> T getValidInput(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getValidInput(supplier);
        }
    }
}
