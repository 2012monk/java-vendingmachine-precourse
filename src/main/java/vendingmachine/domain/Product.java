package vendingmachine.domain;

public class Product {

    private static final String ERR_HEADER = "[ERROR]";
    private static final String ERR_INVALID_STOCK = ERR_HEADER + "재고가 비어있으면 안됩니다.";
    private static final String ERR_INVALID_PRICE =
        ERR_HEADER + "상품 가격은 100원 이상이고 10원으로 나누어 떨어져야합니다.";
    private static final String ERR_INVALID_NAME = ERR_HEADER + "상품 이름이름은 글자와 숫자만 허용됩니다..";
    private static final String ERR_INVALID_INPUT_AMOUNT = "투입금액이 상품가격보다 적습니다.";
    private static final String ERR_EMPTY_STOCK = "상품의 재고가 없습니다.";
    private static final int MIN_AMOUNT = 10;
    private static final int MIN_PRICE = 100;
    private static final int EMPTY_STOCK = 0;

    private final String name;
    private int stock;
    private int amount;

    public Product(String name, int amount, int stock) {
        validateName(name);
        validateAmount(amount);
        validateStock(stock);
        this.name = name;
        this.stock = stock;
        this.amount = amount;
    }

    public void sell(InputAmount inputAmount) throws IllegalArgumentException {
        validateDeal(inputAmount);
        this.stock--;
        inputAmount.consume(this);
    }

    public boolean isEmptyStock() {
        return this.stock == EMPTY_STOCK;
    }

    public String getName() {
        return this.name;
    }

    public int getStock() {
        return stock;
    }

    public int getAmount() {
        return amount;
    }

    private void validateName(String name) {
        if (!name.chars().allMatch(Character::isLetterOrDigit)
            || name.chars().anyMatch(Character::isSpaceChar)) {
            throw new IllegalArgumentException(ERR_INVALID_NAME);
        }
    }

    private void validateStock(int stock) {
        if (stock <= EMPTY_STOCK) {
            throw new IllegalArgumentException(ERR_INVALID_STOCK);
        }
    }

    private void validateAmount(int amount) {
        if (amount < MIN_PRICE || amount % MIN_AMOUNT != 0) {
            throw new IllegalArgumentException(ERR_INVALID_PRICE);
        }
    }

    private void validateDeal(InputAmount inputAmount) throws IllegalArgumentException {
        if (this.amount > inputAmount.getAmount()) {
            throw new IllegalArgumentException(ERR_INVALID_INPUT_AMOUNT);
        }
        if (isEmptyStock()) {
            throw new IllegalArgumentException(ERR_EMPTY_STOCK);
        }
    }
}
