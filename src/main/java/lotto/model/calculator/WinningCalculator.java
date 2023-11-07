package lotto.model.calculator;

import static lotto.util.AmountOfLotto.LOTTO_PRICE;
import static lotto.util.AmountOfLotto.MINIMUM_AMOUNT_OF_LOTTO;
import static lotto.util.ExceptionMessage.INVALID_AMOUNT_OF_INVESTMENT;
import static lotto.util.ExceptionMessage.INVALID_RATE_OF_RESULT;

import java.util.List;
import lotto.util.PrizeMoney;

public class WinningCalculator {
    private static final Long MINIMUM_RATE_OF_RESULT = 0L;
    private static final Long MAXIMUM_RATE_OF_RESULT =
            (PrizeMoney.getPrizeMoney(1).longValue() / LOTTO_PRICE.getPrice().longValue()) * 100;

    public int comparingLottoAndWinningNumber(List<Integer> lottoNumber, List<Integer> winningNumber) {
        return (int) lottoNumber.stream()
                .filter(winningNumber::contains)
                .count();
    }

    public int comparingBonusAndWinningNumber(List<Integer> lottoNumber, Integer bonusNumber) {
        if (lottoNumber.contains(bonusNumber)) {
            return 1;
        }
        return 0;
    }

    public Double calculateRateOfReturn(Integer amountOfInvestment, Long totalPrizeMoney) {
        System.out.println("MAXIMUM_RATE_OF_RESULT = " + MAXIMUM_RATE_OF_RESULT);
        checkAmountOfInvestment(amountOfInvestment);

        Double rateOfReturn = (totalPrizeMoney.doubleValue() / amountOfInvestment.doubleValue()) * 100;

        checkRangeOfRateOfReturn(rateOfReturn);

        return roundRateOfReturn(rateOfReturn);
    }

    private void checkAmountOfInvestment(Integer amountOfInvestment) {
        if (amountOfInvestment <= MINIMUM_AMOUNT_OF_LOTTO.getPrice()) {
            throw new IllegalArgumentException(INVALID_AMOUNT_OF_INVESTMENT.getMessage());
        }
    }

    private void checkRangeOfRateOfReturn(Double rateOfReturn) {
        checkMinimum(rateOfReturn);
        checkMaximum(rateOfReturn);
    }

    private void checkMinimum(Double rateOfReturn) {
        if (rateOfReturn < MINIMUM_RATE_OF_RESULT) {
            throw new IllegalArgumentException(INVALID_RATE_OF_RESULT.getMessage());
        }
    }

    private void checkMaximum(Double rateOfReturn) {
        if (rateOfReturn > MAXIMUM_RATE_OF_RESULT) {
            throw new IllegalArgumentException(INVALID_RATE_OF_RESULT.getMessage());
        }
    }

    private Double roundRateOfReturn(Double rateOfReturn) {
        return Math.round(rateOfReturn * 100.0) / 100.0;
    }
}
