package lotto.services;

import lotto.models.Lotto;
import lotto.models.LottoStatistics;
import lotto.models.enums.Rank;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoStatisticsService {

    public List<LottoStatistics> getLottoStatistics(List<Lotto> lottos, String winningNumber) {
        Map<Rank, LottoStatistics> lottoStatisticsByRank = Arrays.stream(Rank.values())
                .collect(Collectors.toMap(Function.identity(), LottoStatistics::of));

        Lotto winningLotto = Lotto.of(winningNumber);
        lottos.forEach(lotto -> {
            Rank rank = Rank.findRank(new ArrayList<>(winningLotto.getNumbers()), lotto.getNumbers());
            lottoStatisticsByRank.get(rank).addCount();
        });

        return new ArrayList<>(lottoStatisticsByRank.values());
    }

    public float getRevenueRatio(List<LottoStatistics> lottoStatistics, int payment) {
        long totalAmount = lottoStatistics.stream()
                .filter(statistics -> statistics.getCount() > 0)
                .map(statistics -> statistics.getRank().getAmount())
                .mapToLong(Long::longValue)
                .sum();

        return calculateRevenueRatio(payment, totalAmount);
    }

    private float calculateRevenueRatio(int payment, long totalAmount) {
        return totalAmount / (payment / 1000f * 1000);
    }

}
